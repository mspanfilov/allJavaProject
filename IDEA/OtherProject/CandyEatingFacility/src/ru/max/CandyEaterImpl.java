package ru.max;

import ru.Candy;
import ru.CandyEater;

import java.util.concurrent.TimeUnit;

/**
 * @author panfilov_ms
 */
public class CandyEaterImpl implements CandyEater {

    @Override
    public void eat(Candy candy) {
        try{
            System.out.println(" start eating candy " + candy.hashCode() + " (flavour: " +
                    candy.hashCode() + ") eater: " + this.hashCode());
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(" end eating candy " + candy.hashCode() + " (flavour: " +
                    candy.hashCode() + ") eater: " + this.hashCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
