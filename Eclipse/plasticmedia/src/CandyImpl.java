/**
 * @author panfilov_ms
 */
public class CandyImpl implements Candy {

    Flavour flavour;

    @Override
    public Flavour getFlavour() {
        return flavour;
    }

    public void setFlavour(Flavour flavour) {
        this.flavour = flavour;
    }

}