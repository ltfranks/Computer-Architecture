import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Simulator extends lab4 {

    /* Prints `mips> ` and gets user command inputs. removes ` ` and places each command into list */
    public Simulator() {
        // Interactive Mode
        while (true) {
            System.out.print("mips> ");
            Scanner input = new Scanner(System.in);
            String list = input.nextLine();
            String[] inputArr = list.split(" ");
            if (inputArr[0].equals("q")) {
                break;
            }
            else {
                emulator(inputArr);
            }
        }
    }

    /* Prints `mips> ` and gets commands from script file. removes ` ` and places each command into list */
    public Simulator(String file) {
        // Script Mode
        System.out.print("mips> ");
        List<String> inputArr = new ArrayList<>();
        try {
            Scanner scriptFile = new Scanner(new File(file));
            while (scriptFile.hasNextLine()) {
                inputArr.add(scriptFile.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        for (String command: inputArr) {
            if (command.equals("q")) {
                System.out.println(command);
                break;
            }
            else {
                System.out.println(command);
            }
        }
    }
    public static void emulator(String[] commands) {
//        System.out.println(pipelineData); // printing out pipeline - test
//        System.out.println(cleanedFile); // just showing linked list inside linked list
        if (commands.length > 1) {
            int num1 = Integer.parseInt(commands[1]);

            if (commands[0].equals("s")) {
                System.out.println("s " + num1);
                LineCount++;
            }
            else if (commands[0].equals("m") && commands.length == 3) {
                int num2 = Integer.parseInt(commands[2]);
                System.out.println("m " + num1 + " " + num2);
            }
        }
        else {
            String command = commands[0];

            if (command.equals("h")) {
                System.out.println(command);
                System.out.print("\nh = show help\n");
                System.out.print("d = dump register state\n");
                System.out.print("p = show pipeline registers\n");
                System.out.print("s = step through a single clock cycle step (i.e. simulate 1 cycle and stop)\n");
                System.out.print("s num = step through num clock cycles\n");
                System.out.print("r = run until the program ends and display timing summary\n");
                System.out.print("m num1 num2 = display data memory from location num1 to num2\n");
                System.out.print("c = clear all registers, memory, and the program counter to 0\n");
                System.out.print("q = exit the program\n");
            }
            else if (command.equals("d")) {
                System.out.println(command);
            }
            else if (command.equals("p")) {
                System.out.println(command);
                System.out.printf("%-6s %-6s %-6s %-6s %-6s%n", "pc", "if/id", "id/exe", "exe/mem", "mem/wb");
                System.out.print(LineCount+"    ");
                System.out.printf("%-6s %6s %-6s %-6s%n", pipelineData.get(0),pipelineData.get(1),
                        pipelineData.get(2),pipelineData.get(3));
            }
            else if (command.equals("s")) {
                new Instructions();
                boolean flag = false;
                int loadLine = LineCount-2;
                if(pipelineData.get(1).equals("lw")){
                    // if theres two loads in a row
                    if (pipelineData.get(0).equals("lw")){
                        if (cleanedFile.get(LineCount-1).get(1).equals(cleanedFile.get(loadLine).get(1))){
                            // adds element into pipeline (linkedList<> pipelineData) and removes last element
                            pipelineData.add(1,"stall");
                            pipelineData.removeLast();
                            LineCount--;
                        } else {
                            pipelineData.addFirst(cleanedFile.get(LineCount).get(0));
                            pipelineData.removeLast();
                        }
                        // if lw is in "id/exe", is its register being used in the following instruction?
                        // if so, then stall for a cycle. if not, continue/no stall
                    } else if (cleanedFile.get(LineCount-1).get(2).equals(cleanedFile.get(loadLine).get(1)) ||
                            cleanedFile.get(LineCount-1).get(3).equals(cleanedFile.get(loadLine).get(1))){
                        // adds element into pipeline (linkedList<> pipelineData) and removes last element
                        pipelineData.add(1,"stall");
                        pipelineData.removeLast();
                        LineCount--;
                    } else {
                        pipelineData.addFirst(cleanedFile.get(LineCount).get(0));
                        pipelineData.removeLast();
                    }
                    // if there is no LW Hazard, just push next element into data pipeline
                } else {
                    pipelineData.addFirst(cleanedFile.get(LineCount).get(0));
                    pipelineData.removeLast();
                }

                // if the mem/wb has any branch and the branchFlag is true, SQUASH
                if ((pipelineData.get(3).equals("bne") || pipelineData.get(3).equals("beq")) &&
                    branchFlag){
                    branchFlag = false;
                    pipelineData.removeFirst();
                    pipelineData.removeFirst();
                    pipelineData.removeFirst();
                    pipelineData.addFirst("squash");
                    pipelineData.addFirst("squash");
                    pipelineData.addFirst("squash");
                    LineCount = labelBranchLine;
                }



                LineCount++;
                System.out.printf("%-6s %-6s %-6s %-6s %-6s%n", "pc", "if/id", "id/exe", "exe/mem", "mem/wb");
                System.out.print(LineCount+"    ");
                System.out.printf("%-6s %-6s %-6s %-6s%n", pipelineData.get(0),pipelineData.get(1),
                        pipelineData.get(2),pipelineData.get(3));


//                System.out.println(pipelineData);
//                System.out.println("Line Count: " +LineCount);

//                System.out.println(command);
            }
            else if (command.equals("r")) {
                System.out.println(command);
            }
            else if (command.equals("c")) {
                System.out.println(command);
            }
        }
        System.out.println();
    }
}