import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author panfilov_ms
 */
public class CandyEatingFacilityImpl implements CandyEatingFacility {

    private class InnerCandyEaterImpl implements Callable<Integer> {

        CandyEater candyEater;

        public InnerCandyEaterImpl(CandyEater candyEater) {
            this.candyEater = candyEater;
        }

        @Override
        public Integer call() {
            System.out.println("eater " + candyEater.hashCode() + " start eating");
            Candy candyForEating;
            while (!isShutdown.get()) {
                if ((candyForEating = findCandy()) != null){
                    candyEater.eat(candyForEating);
                    eatingFlavourSet.remove(candyForEating.getFlavour());
                    waitOrNotify(true);
                }
            }
            return 0;
        }

    }

    private synchronized Candy findCandy(){

        CopyOnWriteArraySet<Flavour> eatingFlavourSetLocal = eatingFlavourSet;  // local copy eatingFlavourSet

        if (candies.isEmpty() && bufferCandyList.isEmpty()){
            System.out.println("shutdown");
            isShutdown.set(true);
            shutdown();
        }else{

            //first find in buffer
            for (Candy i : bufferCandyList) {
                if (!eatingFlavourSetLocal.contains(i.getFlavour())) {
                    eatingFlavourSet.add(i.getFlavour());
                    bufferCandyList.remove(i);
                    return i;
                }
            }

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
            waitOrNotify(false);
        }
        return  null;
    }

    private synchronized void waitOrNotify(boolean notify){
        if (notify){
            notifyAll();
        }else{
            try {
                System.out.println("waiting");
                wait();
                System.out.println("continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ExecutorService service;                                    // service for streams control
    private BlockingQueue<Candy> candies;
    private LinkedList<Candy> bufferCandyList;                          // ordered list candies that are not in the queue but should be eaten
    private CopyOnWriteArraySet<Flavour> eatingFlavourSet;              // flavours that are eaten at this time
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    @Override
    public void launch(BlockingQueue<Candy> candies, Set<CandyEater> candyEaters) {

        if (candies == null || candyEaters == null) {
            throw new IllegalArgumentException("candies or candyEaters is null");
        }

        if (candyEaters.isEmpty()) {
            throw new IllegalArgumentException("candyEaters is empty");
        }

        service = Executors.newFixedThreadPool(candyEaters.size());                         // create pool of quantity eaters of candy
        this.candies = candies;
        this.bufferCandyList = new LinkedList<Candy>();
        this.eatingFlavourSet = new CopyOnWriteArraySet<Flavour>();
        List<Future<Integer>> threadResults= new ArrayList<Future<Integer>>();              //Threads results can be stored in the collection of Futures
        for (CandyEater i : candyEaters) {
            threadResults.add(service.submit(new InnerCandyEaterImpl(i)));
        }
        for (Future<Integer> future : threadResults) {
            try {
                future.get();
                System.out.println("shutdown fine");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        service.shutdown();

    }

}