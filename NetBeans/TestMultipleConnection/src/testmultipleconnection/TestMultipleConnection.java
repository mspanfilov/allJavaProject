/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testmultipleconnection;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author panfilov_ms
 */
public class TestMultipleConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("start");
        
        Generator gen = new Generator();

        gen.launch();

        try {
            TimeUnit.SECONDS.sleep(Long.parseLong("30"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gen.shutdown();

        System.out.println("end");
        
    }
}
