package capsules;

public class Orion extends Capsule {
    public Orion() {
        super("Orion", true, 4, 300, 10.4);
    }
    @Override
    public boolean isCrewed() {
        return true;
    }
}
