package models;

import capsules.Capsule;
import launchers.Launcher;

public class Rocket {
    private Launcher launcher;
    private Capsule capsule;

    public Rocket(Launcher launcher, Capsule capsule) {
        if (launcher == null) {
            throw new IllegalArgumentException("Launcher cannot be null");
        }
        if (capsule == null) {
            throw new IllegalArgumentException("Capsule cannot be null");
        }
        this.launcher = launcher;
        this.capsule = capsule;
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public Capsule getCapsule() {
        return capsule;
    }

    public double getTotalMass() {
        return launcher.getMaxPayload() + capsule.getWeight();
    }

    public String toString() {
        return "Rocket{"
                + "launcher=" + launcher.getName()
                + ", capsule=" + capsule.getName()
                + ", totalMass=" + getTotalMass() + "t"
                + "}";
    }
}
