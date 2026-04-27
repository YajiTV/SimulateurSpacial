package launchers;

public abstract class Launcher {
    private String name;
    private double maxFuel;
    private double maximumUsefulLoad;
    private int boosterMax;
    private boolean resident;
    private boolean price;

    public Launcher(String name, double maxFuel, double maximumUsefulLoad, int boosterMax, boolean resident, boolean price) {
        this.name = name;
        this.maxFuel = maxFuel;
        this.maximumUsefulLoad = maximumUsefulLoad;
        this.boosterMax = boosterMax;
        this.resident = resident;
        this.price = price;
    }
}