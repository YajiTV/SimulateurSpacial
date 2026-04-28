package capsules;

public abstract class Capsule {
    private String name;
    private boolean crewed;
    private int maxOccupants;
    private double price;
    private double weight;

    public Capsule(String name, boolean crewed, int maxOccupants, double price, double weight) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be null or empty");
        }
        if (maxOccupants < 0) {
            throw new IllegalArgumentException("Max occupants can not be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }

        this.name = name;
        this.crewed = crewed;
        this.maxOccupants = maxOccupants;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    public boolean isCrewed() {
        return crewed;
    }
    public int getMaxOccupants() {
        return maxOccupants;
    }
    public double getPrice() {
        return price;
    }
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Capsule{"
                + "name='" + name + "'"
                + ", crewed=" + crewed
                + ", maxOccupants=" + maxOccupants
                + ", price=" + price + "M€"
                + ", weight=" + weight + "t"
                + '}';
    }
}