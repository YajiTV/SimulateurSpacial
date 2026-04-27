package capsules;

public class Apollo extends Capsule {
    public Apollo() {
        super("Apollo", true, 3, 200, 5.6);
    }
    @Override
    public boolean isCrewed() {
        return true;
    }
}
