package exceptions;

public class FuelInsufficient extends Exception {
    public FuelInsufficient(String message) {
        super(message);
    }
    public FuelInsufficient(double need, double max ) {
        super("Fuel insufficient, need " + need + "tonnes because max is " + max);
    }
}
