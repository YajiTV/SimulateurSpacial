package missions;

import exceptions.FuelInsufficient;
import models.Rocket;

public class Mars extends Mission {
    public Mars() {
        super("Mars", 225000000, 0.000015, true);
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
