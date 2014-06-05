/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testmultipleconnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author panfilov_ms
 */
public class Generator {
    
    private class InnerCandyEaterImpl implements Callable<Integer> {

        public Integer call() throws ClassNotFoundException, SQLException {
            System.out.println(" start thread ");
            ConnectToOracleDB connectToOracleDB = new ConnectToOracleDB();
            while (!isShutdown.get()) {
                    waitOrNotify(true);
            }
            connectToOracleDB.CloseConnectToOracleDB();
            return 0;
        }

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
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);
    
    public void launch() {

        service = Executors.newFixedThreadPool(15);                         // create pool of quantity eaters of candy
        List<Future<Integer>> threadResults= new ArrayList<Future<Integer>>();              //Threads results can be stored in the collection of Futures
        for (int i=0; i < 15; i++) {
            threadResults.add(service.submit(new InnerCandyEaterImpl()));
        }
        
    }

    public void shutdown() {
        waitOrNotify(true);
        isShutdown.set(true);
        service.shutdown();

    }
    
}
