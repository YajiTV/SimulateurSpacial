package missions;
import exceptions.FuelInsufficient;
import models.Rocket;

public abstract class Mission {
    private String name;
    private double distance;
    private double fuelCoefficient;
    private boolean crewedRequired;
    
    public Mission(String name, double distance, double fuelCoefficient, boolean crewedRequired) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be null or empty");
        }
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        if (fuelCoefficient <= 0) {
            throw new IllegalArgumentException("Fuel coefficient must be greater than 0");
        }

        this.name = name;
        this.distance = distance;
        this.fuelCoefficient = fuelCoefficient;
        this.crewedRequired = crewedRequired;
    }
    public abstract double calculateFuelNeeded(Rocket rocket) throws FuelInsufficient;

    public String getName() {
        return name;
    }
    public double getDistance() {
        return distance;
    }
    public double getFuelCoefficient() {
        return fuelCoefficient;
    }
    public boolean isCrewedRequired() {
        return crewedRequired;
    }

    @Override
    public String toString() {
        return "Mission{"
                + "name='" + name + "'"
                + ", distance=" + distance + "Mille kms"
                + ", fuelCoefficient=" + fuelCoefficient
                + ", crewedRequired=" + crewedRequired
                + '}';
    }
}