package launchers;

public class Falcon9 extends Launcher {
    public Falcon9()  {
        super("Falcon 9", 500, 20, 0, true, 60);
    }   
    @Override
    public String getType() {
        return "Launcher Américain";
    }
}
