package services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import capsules.Capsule;
import launchers.Launcher;
import booster.Booster;
import models.Launch;
import models.Rocket;
import missions.Mission;

public class SimulatorApp {
    private static SimulatorApp instance;

    private List<Launcher> launchers;
    private List<Capsule> capsules;
    private List<Booster> boosters;
    private List<Mission> missions;
    private List<Launch> history;
    private Rocket currentRocket;
    private Mission currentMission;

    private SimulatorApp() {
        launchers = new ArrayList<>();
        capsules  = new ArrayList<>();
        boosters  = new ArrayList<>();
        missions  = new ArrayList<>();
        history   = new ArrayList<>();
        // launchers.add(new SLS()); etc.
    }

    public static SimulatorApp getInstance() {
        if (instance == null) {
            instance = new SimulatorApp();
        }
        return instance;
    }

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
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }

            switch (choice) {       // ← DANS le while
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
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private void launch() {
        if (currentRocket == null || currentMission == null) {
            System.out.println("Configure a rocket and a mission first!");
            return;
        }
        Simulator simulator = new Simulator();
        Launch result = simulator.simulate(currentRocket, currentMission);
        history.add(result);
        System.out.println(result);
    }
}
