package services;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import capsules.Apollo;
import capsules.Capsule;
import capsules.CargoDragon;
import capsules.CrewDragon;
import capsules.Orion;
import launchers.Ariane5;
import launchers.Falcon9;
import launchers.Launcher;
import launchers.SLS;
import launchers.SaturneV;
import booster.BE3;
import booster.Booster;
import booster.EAP;
import booster.SRB;
import models.Launch;
import models.Rocket;
import missions.ISS;
import missions.Lune;
import missions.Mars;
import missions.Mission;
import missions.MissionPointLagrangeL2;
import missions.OrbitTerrestre;

// Singleton: only one instance of the app
public class SimulatorApp {
    private static SimulatorApp instance;

    // Available objects
    private List<Launcher> launchers;
    private List<Capsule> capsules;
    private List<Booster> boosters;
    private List<Mission> missions;
    private List<Launch> history;

    // Current user selection
    private Rocket currentRocket;
    private Mission currentMission;
    private Launcher selectedLauncher;
    private Capsule selectedCapsule;

    // Private constructor: fills the lists on startup
    private SimulatorApp() {
        launchers = new ArrayList<>(List.of(new SLS(), new SaturneV(), new Ariane5(), new Falcon9()));
        capsules  = new ArrayList<>(List.of(new Orion(), new CrewDragon(), new CargoDragon(), new Apollo()));
        boosters  = new ArrayList<>(List.of(new SRB(), new EAP(), new BE3()));
        missions  = new ArrayList<>(List.of(new ISS(), new Lune(), new Mars(), new MissionPointLagrangeL2(), new OrbitTerrestre()));
        history   = new ArrayList<>(List.of());
    }

    // Always returns the same instance
    public static SimulatorApp getInstance() {
        if (instance == null) {
            instance = new SimulatorApp();
        }
        return instance;
    }

    // Main menu loop
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("1. Choose launcher");
            System.out.println("2. Choose capsule");
            System.out.println("3. Choose mission");
            System.out.println("4. Launch");
            System.out.println("5. Exit");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // flush the buffer
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    chooseLauncher(scanner);
                    break;
                case 2:
                    chooseCapsule(scanner);
                    break;
                case 3:
                    chooseMission(scanner);
                    break;
                case 4:
                    launch();
                    break;
                case 5:
                    System.out.println("Bye !");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    // Shows the launcher list and saves the choice
    private void chooseLauncher(Scanner scanner) {
        System.out.println("0. Back");
        for (int i = 0; i < launchers.size(); i++) {
            System.out.println((i + 1) + ". " + launchers.get(i).getName());
        }
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) return;
            if (choice < 1 || choice > launchers.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            selectedLauncher = launchers.get(choice - 1);
            // Build the rocket only if the capsule is already chosen
            if (selectedCapsule != null) {
                currentRocket = new Rocket(selectedLauncher, selectedCapsule);
            }
            System.out.println("Launcher selected: " + selectedLauncher.getName());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }

    // Shows the capsule list and saves the choice
    private void chooseCapsule(Scanner scanner) {
        System.out.println("0. Back");
        for (int i = 0; i < capsules.size(); i++) {
            System.out.println((i + 1) + ". " + capsules.get(i).getName());
        }
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) return;
            if (choice < 1 || choice > capsules.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            selectedCapsule = capsules.get(choice - 1);
            // Build the rocket only if the launcher is already chosen
            if (selectedLauncher != null) {
                currentRocket = new Rocket(selectedLauncher, selectedCapsule);
            }
            System.out.println("Capsule selected: " + selectedCapsule.getName());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }

    // Shows the mission list and saves the choice
    private void chooseMission(Scanner scanner) {
        if (currentRocket == null) {
            System.out.println("Configure a rocket first!");
            return;
        }
        System.out.println("0. Back");
        for (int i = 0; i < missions.size(); i++) {
            System.out.println((i + 1) + ". " + missions.get(i).getName()
                + " - " + missions.get(i).getDistance() + " km"
                + " | crew: " + missions.get(i).isCrewRequired());
        }
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) return;
            if (choice < 1 || choice > missions.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            Mission chosen = missions.get(choice - 1);
            if (chosen.isCrewRequired() && !currentRocket.getCapsule().isCrewed()) {
                System.out.println("Warning: your capsule (" + currentRocket.getCapsule().getName() + ") is incompatible with this mission!");
                return;
            }
            currentMission = chosen;
            System.out.println("Mission selected: " + currentMission.getName());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }

    // Checks all conditions then runs the simulation
    private void launch() {
        if (currentRocket == null || currentMission == null) {
            System.out.println("Configure a rocket and a mission first!");
            return;
        }
        System.out.println("LAUNCH");
        double fuel;
        try {
            fuel = currentMission.calculateRequiredFuel(currentRocket);
            System.out.println("Fuel needed: " + fuel + " t");
        } catch (Exception e) {
            System.out.println("Launch failed: " + e.getMessage());
            return;
        }
        if (currentRocket.getTotalMass() > currentRocket.getLauncher().getMaxPayload()) {
            System.out.println("Launch failed: payload exceeded");
            return;
        }
        if (currentRocket.getBoosters().size() > currentRocket.getLauncher().getMaxBoosters()) {
            System.out.println("Launch failed: too many boosters");
            return;
        }
        if (currentMission.isCrewRequired() && (!currentRocket.getCapsule().isCrewed() || currentRocket.getCapsule().getMaxOccupants() == 0)) {
            System.out.println("Launch failed: capsule incompatible with crewed mission");
            return;
        }

        Simulator simulator = new Simulator();
        Launch result = simulator.simulate(currentRocket, currentMission);
        history.add(result);

        if (result.isSuccess()) {
            System.out.println("Launch successful!");
            System.out.println("Total cost: " + result.getTotalCost() + " euros");
        } else {
            System.out.println("Launch failed: " + result.getReason());
        }
    }
}