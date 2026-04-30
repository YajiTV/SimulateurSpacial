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

public class SimulatorApp {
    private static SimulatorApp instance;

    private List<Launcher> launchers;
    private List<Capsule> capsules;
    private List<Booster> boosters;
    private List<Mission> missions;
    private List<Launch> history;
    private Rocket currentRocket;
    private Mission currentMission;
    private Launcher selectedLauncher;
    private Capsule selectedCapsule;

    private SimulatorApp() {
        launchers = new ArrayList<>(List.of(new SLS(), new SaturneV(), new Ariane5(), new Falcon9()));
        capsules  = new ArrayList<>(List.of(new Orion(), new CrewDragon(), new CargoDragon(), new Apollo()));
        boosters  = new ArrayList<>(List.of(new SRB(), new EAP(), new BE3()));
        missions  = new ArrayList<>(List.of(new ISS(), new Lune(), new Mars(), new MissionPointLagrangeL2(), new OrbitTerrestre()));
        history   = new ArrayList<>(List.of());

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
                scanner.nextLine();
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
            if (selectedCapsule != null) {
                currentRocket = new Rocket(selectedLauncher, selectedCapsule);
            }
            System.out.println("Launcher selected: " + selectedLauncher.getName());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }
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
            if (selectedLauncher != null) {
                currentRocket = new Rocket(selectedLauncher, selectedCapsule);
            }
            System.out.println("Capsule selected: " + selectedCapsule.getName());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }
    private void chooseMission(Scanner scanner) {
        System.out.println("0. Back");
        for (int i = 0; i < missions.size(); i++) {
            System.out.println((i + 1) + ". " + missions.get(i).getName());
        }
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) return;
            if (choice < 1 || choice > missions.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            currentMission = missions.get(choice - 1);
            System.out.println("Mission selected: " + currentMission.getName());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }
}