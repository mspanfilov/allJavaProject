package ru.max;

import ru.Candy;
import ru.CandyEater;
import ru.CandyEatingFacility;
import ru.Flavour;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.*;


/**
 * @author panfilov_ms
 */
public class CandyEatingFacilityImpl implements CandyEatingFacility {

    private class InnerCandyEaterImpl implements Runnable {

        CandyEater candyEater;

        public InnerCandyEaterImpl(CandyEater candyEater) {
            this.candyEater = candyEater;
        }

        @Override
        public void run() {
            // eaters eat candies until all candies is not empty, then wait
            System.out.println("eater " + candyEater.hashCode() + " start eating");
            Candy candyForEating;
            while (true) {
                if ((candyForEating = findCandy()) != null){
                    candyEater.eat(candyForEating);
                    eatingFlavourSet.remove(candyForEating.getFlavour());
                    notifyAll(); // !!! нужно перенисти в синхронизированный блок
                }else{
                    try {
                        System.out.println("eater " + candyEater.hashCode() + " is waiting");
                        wait();  // !!! нужно перенисти в синхронизированный блок
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    private synchronized Candy findCandy(){

        CopyOnWriteArraySet<Flavour> eatingFlavourSetLocal = eatingFlavourSet;  // local copy eatingFlavourSet

            //boolean candyFromBuffer = false;
            //Flavour flavourCurrent;
        if (candies.isEmpty() && bufferCandyList.isEmpty()){
            shutdown();
        }else{

            //first find in buffer
            for (Candy i : bufferCandyList) {
                if (!eatingFlavourSetLocal.contains(i.getFlavour())) {
                    eatingFlavourSet.add(i.getFlavour());
                    //return i;
                    bufferCandyList.remove(i);
                        //eatingFlavourSet.remove(i.getFlavour());
                        //candyFromBuffer = true;
                    return i;
                }
            }

            /*if (!candyFromBuffer && candies.isEmpty()) {
                System.out.println("eater: " + this.candyEater.hashCode() + " finished");
                break;
            }*/

            //second find in original Queue
            if (!candies.isEmpty()) {
                Candy candy;
                while ((candy = candies.poll()) != null){
                    if (eatingFlavourSetLocal.contains(candy.getFlavour())) {
                        bufferCandyList.add(candy);
                    } else {
                        eatingFlavourSet.add(candy.getFlavour());
                        return candy;
                    }
                }
            }
        }
        return  null;
    }

    private ExecutorService service;                            // service for streams control
    private BlockingQueue<Candy> candies;
    private LinkedList<Candy> bufferCandyList;                  // ordered list candies that are not in the queue but should be eaten
    private CopyOnWriteArraySet<Flavour> eatingFlavourSet;      // flavours that are eaten at this time

    @Override
    public void launch(BlockingQueue<Candy> candies, Set<CandyEater> candyEaters) {

        if (candies == null || candyEaters == null) {
            throw new IllegalArgumentException("candies or candyEaters is null");
        }

        // todo if candies.isEmpty()
        // todo if candyEaters.isEmpty()

        service = Executors.newFixedThreadPool(candyEaters.size()); // create pool of quantity eaters of candy
        this.candies = candies;
        this.bufferCandyList = new LinkedList<Candy>();
        this.eatingFlavourSet = new CopyOnWriteArraySet<Flavour>();
        for (CandyEater i : candyEaters) {
            service.execute(new InnerCandyEaterImpl(i));
        }
        // !!! здесь нужно корректно ждать shutdown, а потом возвращать управление (использовать Future)
        /*try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void shutdown() {
        service.shutdown();

    }

}