package launchers;

public class SLS extends Launcher {
    public SLS()  {
        super("SLS", 2600, 130, 2, true, 2000);
    }   
    @Override   
    public String getType() {
        return "Launcher Américain";
    }
}
