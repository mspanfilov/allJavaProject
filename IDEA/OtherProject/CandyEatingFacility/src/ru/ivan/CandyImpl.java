package ru.ivan;

import ru.Candy;
import ru.Flavour;

public class CandyImpl implements Candy{

    private Flavour flavour;

    public CandyImpl() {
    }

    public CandyImpl(Flavour flavour) {
        this.flavour = flavour;
    }

    @Override
    public Flavour getFlavour() {
        return flavour;
    }

    public void setFlavour(Flavour flavour) {
        this.flavour = flavour;
    }

    @Override
    public String toString() {
        return "Candy{" +
                "flavour=" + flavour +
                '}';
    }
}