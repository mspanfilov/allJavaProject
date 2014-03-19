package ru.ivan;

import ru.Candy;
import ru.CandyEater;
import ru.CandyEatingFacility;
import ru.Flavour;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class CandyEatingFacilityImpl implements CandyEatingFacility {

    private class EaterTask implements Runnable {

        private final CandyEater eater;

        public EaterTask(CandyEater eater) {
            this.eater = eater;
        }

        @Override
        public void run() {
            barrierAwait();
            Candy nextCandy;
            while ((nextCandy = getCandyAndBlockFlavourWithRetry()) != null) {
                eater.eat(nextCandy);
                unblockFlavour(nextCandy.getFlavour());
            }
            // if no any candies then will finish work for this CandyEater
            System.out.println("eater is finish launch " + eater + ", because queue is empty!");
            shutdown();
        }
    }

    private ExecutorService service;                        // service for streams control
    private CyclicBarrier barrier;                            // barrier to run streams at the same time
    private BlockingQueue<Candy> candies;
    private CopyOnWriteArrayList<Candy> bufferCandyList;    // ordered list candies that are not in the queue but should be eaten
    private CopyOnWriteArraySet<Flavour> eatingFlavourSet;    // tastes that are eaten at this time
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);


    @Override
    public void launch(BlockingQueue<Candy> candies, Set<CandyEater> candyEaters) {

        if (candies == null || candyEaters == null) {
            throw new IllegalArgumentException("candies or candyEaters is null");
        }

        // todo if candies.isEmpty()
        // todo if candyEaters.isEmpty()

        service = Executors.newFixedThreadPool(candyEaters.size()); // create pool of quantity eaters of candy
        this.barrier = new CyclicBarrier(candyEaters.size()); // for start all eater at the same time 'Поедает конфеты хором.'
        this.candies = candies;
        this.bufferCandyList = new CopyOnWriteArrayList<Candy>();
        this.eatingFlavourSet = new CopyOnWriteArraySet<Flavour>();
        for (CandyEater eater : candyEaters) {
            service.execute(new EaterTask(eater));
        }

        System.out.println("initialized");
    }

    // return null if empty
    // return next if exist free flavour
    // special case: repeat finding if buffer is not empty (some flavours are blocked)
    private synchronized Candy getCandyAndBlockFlavourWithRetry() {
        Candy candy = getCandyAndBlockFlavour();
        if (candy != null) {
            return candy;
        }
        // if buffer is not empty because some flavours are blocked now
        if (candy == null && !bufferCandyList.isEmpty()) {
            return getCandyAndBlockFlavour();
        } else {
            return null; // empty
        }
    }

    // return null if queue is empty (but not buffer!)
    // return next if exist free flavour
    private synchronized Candy getCandyAndBlockFlavour() {

        if (isShutdown.get()) {
            return null;
        }

        boolean noAnyCandies = candies.isEmpty() && bufferCandyList.isEmpty();
        if (noAnyCandies) {
            return null;
        }
        // first find in buffer
        // condition: 'конфеты одного вкуса поедаются в той очерёдности, в которой они находились в очереди.'
        for (Candy candy : bufferCandyList) {
            if (!isBlockedFlavour(candy.getFlavour())) {
                bufferCandyList.remove(candy);
                blockFlavour(candy.getFlavour());
                return candy;
            }
        }
        // second find in origin queue
        Candy candy;
        while ((candy = candies.poll()) != null) {
            if (isBlockedFlavour(candy.getFlavour())) {
                bufferCandyList.add(candy);
            } else {
                blockFlavour(candy.getFlavour());
                return candy;
            }
        }

        // no any candies in queue
        // but not in buffer! Some flavours can be blocked now!
        return null;
    }

    private void barrierAwait() {
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    // condition: 'в любой момент времени поедается не более одной конфеты каждого вкуса'
    private boolean isBlockedFlavour(Flavour flavour) {
        return eatingFlavourSet.contains(flavour);
    }

    private void blockFlavour(Flavour flavour) {
        eatingFlavourSet.add(flavour);
    }

    private void unblockFlavour(Flavour flavour) {
        eatingFlavourSet.remove(flavour);
    }

    @Override
    public void shutdown() {
        isShutdown.set(true);
        service.shutdown();
    }
}