package services;

import models.Rocket;
import models.Launch;
import missions.Mission;
import exceptions.FuelInsufficient;

public class Simulator {

    public static final double KEROSENE_PRICE_PER_TON = 1_200;
    public static final double FAILURE_PROBABILITY    = 0.05; // 5% chance of failure

    public Launch simulate(Rocket rocket, Mission mission) {

        // Condition 1 : not enough fuel
        double fuel;
        try {
            fuel = mission.calculateRequiredFuel(rocket);
        } catch (FuelInsufficient e) {
            return new Launch(rocket, mission, false, "Insufficient fuel", 0);
        }

        // Condition 2 : rocket is too heavy for the launcher
        if (rocket.getTotalMass() > rocket.getLauncher().getMaxPayload()) {
            return new Launch(rocket, mission, false, "Payload exceeded", 0);
        }

        // Condition 3 : too many boosters
        if (rocket.getBoosters().size() > rocket.getLauncher().getMaxBoosters()) {
            return new Launch(rocket, mission, false, "Too many boosters", 0);
        }

        // Condition 4 : crewed mission but capsule has no crew
        if (mission.isCrewRequired() &&
            (!rocket.getCapsule().isCrewed() || rocket.getCapsule().getMaxOccupants() == 0)) {
            return new Launch(rocket, mission, false, "Capsule incompatible with crewed mission", 0);
        }

        double cost = rocket.getTotalPrice() + (fuel * KEROSENE_PRICE_PER_TON);

        if (Math.random() < FAILURE_PROBABILITY) {
            return new Launch(rocket, mission, false, "Unexpected technical anomaly", cost);
        }

        return new Launch(rocket, mission, true, "Success", cost);
    }
    public double calculateTotalCost(Rocket rocket, double requiredFuel) {
            if (requiredFuel <= 0) {
                throw new IllegalArgumentException("Fuel must be > 0");
            } else {
                return rocket.getTotalPrice() + (requiredFuel * KEROSENE_PRICE_PER_TON);
            }
        }
}
