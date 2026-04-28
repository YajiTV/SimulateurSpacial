package missions;

import exceptions.FuelInsufficient;
import models.Rocket;

public class MissionPointLagrangeL2 extends Mission {
    public MissionPointLagrangeL2() {
        super(
            "Mission Point de Lagrange L2",  
            1500000,                        
            0.0015,                          
            false
        );
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