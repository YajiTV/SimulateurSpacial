package missions;

import exceptions.FuelInsufficient;
import models.Rocket;

public abstract class Mission {

    private String name;
    private double distance;
    private double fuelCoefficient;
    private boolean crewRequired;

    public Mission(String name, double distance, double fuelCoefficient,
                   boolean crewRequired) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (fuelCoefficient < 0) {
            throw new IllegalArgumentException("Fuel coefficient cannot be negative");
        }

        this.name = name;
        this.distance = distance;
        this.fuelCoefficient = fuelCoefficient;
        this.crewRequired = crewRequired;
    }

    public double calculateRequiredFuel(Rocket rocket) throws FuelInsufficient {
    if (rocket == null) {
        throw new IllegalArgumentException("Rocket cannot be null");
    }
    double fuel = (rocket.getTotalMass() * getDistance() * getFuelCoefficient()) / 1000;
    if (fuel > rocket.getLauncher().getMaxFuel()) {
        throw new FuelInsufficient(fuel, rocket.getLauncher().getMaxFuel());
    }
    return fuel;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public double getFuelCoefficient() {
        return fuelCoefficient;
    }

    public boolean isCrewRequired() {
        return crewRequired;
    }

    public String toString() {
        return "Mission{"
                + "name='" + name + "'"
                + ", distance=" + distance + "km"
                + ", fuelCoefficient=" + fuelCoefficient
                + ", crewRequired=" + crewRequired
                + "}";
    }
}
