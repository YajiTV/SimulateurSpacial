package booster;

public abstract class Booster {
    private String name;
    private double thrust;
    private double mass;
    private double price;

    public Booster(String name, double thrust, double mass, double price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (thrust <= 0) {
            throw new IllegalArgumentException("Thrust must be positive");
        }
        if (mass <= 0) {
            throw new IllegalArgumentException("Mass must be positive");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.name = name;
        this.thrust = thrust;
        this.mass = mass;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getThrust() {
        return thrust;
    }
    public double getMass() {
        return mass;   
    }
    public double getPrice() {
        return price;
    }
    public String toString() {
        return "Booster{"
                + "name='" + name + "'"
                + ", thrust=" + thrust + "kN"
                + ", mass=" + mass + "t"
                + ", price=" + price + "M$"
                + "}";  
    }
}