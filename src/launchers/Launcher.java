package launchers;

public abstract class Launcher {

    // private = only accessible inside this class
    private String name;
    private double maxFuel;
    private double maxPayload;
    private int maxBoosters;
    private boolean crewed;
    private double price;

    // Constructor: called when a subclass creates a new Launcher ---
    public Launcher(String name, double maxFuel, double maxPayload, int maxBoosters, boolean crewed, double price) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be null or empty");
        }
        if (maxFuel <= 0) {
            throw new IllegalArgumentException("Max fuel must be greater than 0");
        }
        if (maxPayload <= 0) {  
            throw new IllegalArgumentException("Max payload must be greater than 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        // Assignment: we store each value in its attribute
        this.name = name;
        this.maxFuel = maxFuel;
        this.maxPayload = maxPayload;
        this.maxBoosters = maxBoosters;
        this.crewed = crewed;
        this.price = price;
    }

    // Getters: the only way to read private attributes from outside
    public String getName() {
        return name;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public double getMaxPayload() {
        return maxPayload;
    }

    public int getMaxBoosters() {
        return maxBoosters;
    }

    public boolean isCrewed() {
        return crewed;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getType();

    // toString: readable summary when we print a Launcher
    @Override
    public String toString() {
        return "Launcher{"
                + "name='" + name + "'"
                + ", maxFuel=" + maxFuel + "t"
                + ", maxPayload=" + maxPayload + "t"
                + ", maxBoosters=" + maxBoosters
                + ", crewed=" + crewed
                + ", price=" + price + "M$"
                + ", my type is " + getType()
                + "}";
    }
}
