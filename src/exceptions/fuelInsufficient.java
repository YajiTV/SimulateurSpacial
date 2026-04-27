package exceptions;

public class fuelInsufficient extends Exception {
    public fuelInsufficient(String message) {
        super(message);
    }
    public fuelInsufficient(double need, double max ) {
        super("Fuel insufficient, need " + need + "tonnes because max is " + max);
    }
    
}
