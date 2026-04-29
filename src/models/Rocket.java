package models;

import capsules.Capsule;
import launchers.Launcher;
import java.util.List;

import booster.Booster;

import java.util.ArrayList;


public class Rocket {
    private Launcher launcher;
    private Capsule capsule;
    private List<Booster> boosters;

    public void addBooster(Booster booster) {
        if (booster == null) {
            throw new IllegalArgumentException("Booster cannot be null");
        }
        if (boosters.size() >= launcher.getMaxBoosters()) {
            throw new IllegalArgumentException("Max boosters reached for this launcher");
        }
        boosters.add(booster);
    }
    public void addBooster(Booster booster, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addBooster(booster);
        }
    }

    public Rocket(Launcher launcher, Capsule capsule) {
        if (launcher == null) {
            throw new IllegalArgumentException("Launcher cannot be null");
        }
        if (capsule == null) {
            throw new IllegalArgumentException("Capsule cannot be null");
        }
        this.launcher = launcher;
        this.capsule = capsule;
        this.boosters = new ArrayList<>();
    }

    public List<Booster> getBoosters() {
        return boosters;
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public Capsule getCapsule() {
        return capsule;
    }

    public double getTotalMass() {
        double totalMass = launcher.getMaxPayload() + capsule.getWeight();
        for (Booster booster : boosters) {
            totalMass += booster.getMass();
        }
        return totalMass;
    }

    public double getTotalPrice() {
        double total = launcher.getPrice() + capsule.getPrice();
        for (Booster booster : boosters) {
            total += booster.getPrice();
            }
        return total;
    }

    public String toString() {
        return "Rocket{"
                + "launcher=" + launcher.getName()
                + ", capsule=" + capsule.getName()
                + ", totalMass=" + getTotalMass() + "t"
                + "}";
    }
}
