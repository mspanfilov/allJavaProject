package ru.ivan;

import ru.Flavour;

public class FlavourImpl implements Flavour {

    private final String name;

    public FlavourImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Flavour o) {
        if (this.equals(o)) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlavourImpl flavour = (FlavourImpl) o;

        if (name != null ? !name.equals(flavour.name) : flavour.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Flavour{" +
                "name='" + name + '\'' +
                '}';
    }
}
