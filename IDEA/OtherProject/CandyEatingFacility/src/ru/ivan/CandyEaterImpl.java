package ru.ivan;

import ru.Candy;
import ru.CandyEater;

import java.util.concurrent.TimeUnit;

public class CandyEaterImpl implements CandyEater {

    private final String name;

    public CandyEaterImpl(String name) {
        this.name = name;
    }

    @Override
    public void eat(Candy candy) {
        System.out.println(name + " start eating candy... " + candy);
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
//            System.out.println(" end eating candy... " + candy);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CandyEater{" +
                "name='" + name + '\'' +
                '}';
    }
}
