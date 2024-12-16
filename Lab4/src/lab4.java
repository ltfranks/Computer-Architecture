import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lab4 {
    public static HashMap<String, Integer> registers = new HashMap<>();
    public static HashMap<String, String> pipelineRegisters = new HashMap<>();
    public static LinkedList<String> pipelineData = new LinkedList<>();
    public static HashMap<String, Integer> LabelLocation = new HashMap<>();
    public static ArrayList<List<String>> cleanedFile = new ArrayList<>();
    public static int LineCount = 0;
    public static int LabelTracker = -1;
    public static int[] data = new int[8192];
    public static boolean branchFlag = false;
    public static int labelBranchLine;
// after branch squashes, it still loops
    // in addition, it skips addi with label? idk skips some commands.
    public static void main(String[] args) {
        new Registers();
        new FirstScan(args[0]);
        if (args.length == 1) {
            new ScanAssembly(args[0]);
            new Simulator();
        }
        else {
            new ScanAssembly(args[0]);
            new Simulator(args[1]);
        }
    }
}