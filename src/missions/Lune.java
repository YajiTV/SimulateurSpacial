package missions;

import exceptions.FuelInsufficient;
import models.Rocket;

public class    Lune extends Mission {
    public Lune(){
        super("Lune", 400000, 0.005, true);

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
}
