package missions;

import exceptions.FuelInsufficient;
import models.Rocket;

public class OrbitTerrestre extends Mission {
    public OrbitTerrestre() {
        super("Orbit Terrestre", 400, 1, false);
    }

    @Override
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
