public class FlavourImpl implements Flavour {

    @Override
    public int compareTo(Flavour o) {
        if (this.hashCode() == o.hashCode()) {
            return 0;
        } else {
            return -1;
        }
    }

}
