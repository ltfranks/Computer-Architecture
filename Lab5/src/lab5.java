import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class lab5 {

    public static HashMap<String, Integer> registers = new HashMap<>();
    public static HashMap<String, Integer> LabelLocation = new HashMap<>();
    public static ArrayList<List<String>> cleanedFile = new ArrayList<>();
    public static int LineCount = 0;
    public static int LabelTracker = -1;
    public static int twoBitGHR = 0;
    public static int fourBitGHR = 0;
    public static int eightBitGHR = 0;

    public static int[] data = new int[8192];

    public static void main(String[] args) throws IOException {
        new Registers();
        new FirstScan(args[0]);

        if (args.length == 1) {
            new ScanAssembly(args[0]);
            new InteractiveMode();
        } else {
            new ScanAssembly(args[0]);
            new InteractiveMode(args[1]);
        }
    }
}