package ru.ivan;

import ru.Candy;
import ru.CandyEater;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestMain {

    public static void main(String[] args) {

        FlavourImpl f1 = new FlavourImpl("1");
        FlavourImpl f2 = new FlavourImpl("2");
        FlavourImpl f3 = new FlavourImpl("3");

        BlockingQueue<Candy> candies = new ArrayBlockingQueue<Candy>(10);
        candies.add(new CandyImpl(f1));
        candies.add(new CandyImpl(f1));
        candies.add(new CandyImpl(f1));
        candies.add(new CandyImpl(f2));
        candies.add(new CandyImpl(f2));
        candies.add(new CandyImpl(f3));
        candies.add(new CandyImpl(f1));

        System.out.println(candies);

        Set<CandyEater> candyEaters = new HashSet<CandyEater>();
        candyEaters.add(new CandyEaterImpl("A"));
        candyEaters.add(new CandyEaterImpl("B"));
        candyEaters.add(new CandyEaterImpl("C"));

        CandyEatingFacilityImpl facility = new CandyEatingFacilityImpl();
        facility.launch(candies, candyEaters);
    }

}
