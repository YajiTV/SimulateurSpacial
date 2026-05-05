package launchers;

public class Ariane5 extends Launcher {
    public Ariane5() {
        super("Ariane 5", 700, 20, 2,  false,  180);
    }
    @Override
    public String getType() {
        return "Launcher Européen";
    }
}
