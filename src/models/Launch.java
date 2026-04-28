package models;
import models.Rocket;
import java.time.LocalDateTime;

import missions.Mission;

public class Launch {
    private Rocket rocket;
    private Mission mission;
    private LocalDateTime date;
    private boolean success;
    private String reason;
    private double totalCost;

    public Launch(Rocket rocket, Mission mission, boolean success, String reason, double totalCost) {
        if (rocket == null) {
            throw new IllegalArgumentException("Rocket cannot be null");
        }
        if (mission == null) {
            throw new IllegalArgumentException("Mission cannot be null");
        }
        if (reason == null) {
            throw new IllegalArgumentException("Reason cannot be null");
        }
        if (totalCost < 0) {
            throw new IllegalArgumentException("Total cost cannot be negative");
        }
        this.rocket = rocket;
        this.mission = mission;
        this.date = LocalDateTime.now();
        this.success = success;
        this.reason = reason;
        this.totalCost = totalCost;
    }
    public String toString() {
        return String.format("[%s] %s | Mission: %s | %s | Cost: %.2f",
        date,
        rocket.getLauncher().getName(),
        mission.getName(),
        success ? "SUCCESS" : "FAILURE (" + reason + ")",
        totalCost);
    }

}