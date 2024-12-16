import java.io.IOException;
import java.util.HashMap;

public class lab2 {
    public static HashMap<String, String> RInstructions = new HashMap<>();
    public static HashMap<String, String> IInstructions = new HashMap<>();
    public static HashMap<String, String> JInstructions = new HashMap<>();
    public static HashMap<String, String> registers = new HashMap<>();
    public static int LineCounter = 0;
    public static int labelTracker = - 1;
    public static HashMap<String, Integer> LabelLocation = new HashMap<>();


    public static void main(String[] args) throws IOException {
        new Instructions();  // Initializes all registers and instructions
        new FirstScan(args[0]);
        new SecondScan(args[0]);
    }

}