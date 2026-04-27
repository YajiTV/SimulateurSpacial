package capsules;

public class CrewDragon extends Capsule {
    public CrewDragon() {
        super("Crew Dragon", true, 7, 150, 12.0);
    }
    @Override
    public boolean isCrewed() {
        return true;
    }
}
