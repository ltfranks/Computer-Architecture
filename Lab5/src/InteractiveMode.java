import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InteractiveMode extends lab5 {
    List<String> userInput = new ArrayList<>();

    public InteractiveMode() {
        // user
        while (true) {
            System.out.print("mips> ");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] userArray = userInput.split(" ");

            if (userArray[0].equals("q")) {
                break;
            } else {
                emulator(userArray);
            }
        }
    }

    public InteractiveMode(String file) {
        // script
        try {
            Scanner assemblyFile = new Scanner(new File(file));
            while (assemblyFile.hasNextLine()) {
                userInput.add(assemblyFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        for (String command : userInput) {
            System.out.println(cleanedFile);
            if (command.equals("q")) {
                break;
            } else {
                System.out.print("mips> ");
                emulator(command.split(" "));
            }
        }

    }

    public void emulator(String[] userArray) {
        if (userArray.length > 1) {
            int num1 = Integer.parseInt(userArray[1]);
            if (userArray[0].equals("s")) {  // s with number
                System.out.print("s " + num1 + "\n\t" + num1 + " instruction(s) executed");
                while (num1 > 0) {
                    try {
                        new Instructions();
                    }
                    catch(Exception e) {
                        break;
                    }
                    num1--;
                }

            } else if (userArray[0].equals("m") && userArray.length == 3) {
                int num2 = Integer.parseInt(userArray[2]);
                for (int i = num1; i < num2; i++) {
                    System.out.println("[" + i + "] = " + data[num1] + " " + data[num2]);
                }
            }
        } // single letter instructions ^^^
        else {
            if (userArray[0].equals("h")) {
                System.out.println(userArray[0]);
                System.out.print("""
                                                
                        h = show help
                        d = dump register state
                        s = single step through the program (i.e. execute 1 instruction and stop)
                        s num = step through num instructions of the program
                        r = run until the program ends
                        m num1 num2 = display data memory from location num1 to num2
                        c = clear all registers, memory, and the program counter to 0
                        q = exit the program""");
                System.out.println();
            } else if (userArray[0].equals("d")) {
                System.out.print("d");
                System.out.println();
                System.out.println();
                System.out.println("pc = " + registers.get("pc"));
                System.out.println("$0 = " + registers.get("$0") + "          $v0 = " + registers.get("$v0")
                        + "         $v1 = " + registers.get("$v1") + "         $a0 = " + registers.get("$a0"));
                System.out.println("$a1 = " + registers.get("$a1") + "         $a2 = " + registers.get("$a2")
                        + "         $a3 = " + registers.get("$a3") + "         $t0 = " + registers.get("$t0"));
                System.out.println("$t1 = " + registers.get("$t1") + "         $t2 = " + registers.get("$t2")
                        + "         $t3 = " + registers.get("$t3") + "         $t4 = " + registers.get("$t4"));
                System.out.println("$t5 = " + registers.get("$t5") + "         $t6 = " + registers.get("$t6")
                        + "         $t7 = " + registers.get("$t7") + "         $s0 = " + registers.get("$s0"));
                System.out.println("$s1 = " + registers.get("$s1") + "         $s2 = " + registers.get("$s2")
                        + "         $s3 = " + registers.get("$s3") + "         $s4 = " + registers.get("$s4"));
                System.out.println("$s5 = " + registers.get("$s5") + "         $s6 = " + registers.get("$s6")
                        + "         $s7 = " + registers.get("$s7") + "         $t8 = " + registers.get("$t8"));
                System.out.println("$t9 = " + registers.get("$t9") + "         $sp = " + registers.get("$sp")
                        + "         $ra = " + registers.get("$ra"));

                // replace
            } else if (userArray[0].equals("s")) {
                System.out.print("s\n\t1 instruction(s) executed");
//                System.out.println(cleanedFile);
//                System.out.println("LineCount: " +LineCount);
//                System.out.println("Label Locations: " + LabelLocation);
//                try {
                new Instructions();
//                }
//                catch(Exception e) {
//                    System.out.println("End of file.");
//                }
                System.out.println();
            } else if (userArray[0].equals("r")) {
                System.out.print("r");
                while(true) {
                    // HARDCODED: if Line reaches end of file
                    if (LineCount == 24){
                        System.exit(0);
                    }
                    if (cleanedFile.get(LineCount).get(0).equals("bne")) {
                        if (Objects.equals(registers.get(cleanedFile.get(LineCount).get(1)), registers.get(cleanedFile.get(LineCount).get(2)))) {

                            break;
                        }
                    }
                    if (cleanedFile.get(LineCount).get(0).equals("beq")) {
                        if (!Objects.equals(registers.get(cleanedFile.get(LineCount).get(1)), registers.get(cleanedFile.get(LineCount).get(2)))) {
                            break;
                        }
                    }
                    else {
                        new Instructions();
                    }
                }

//                System.out.println();
            } else if (userArray[0].equals("c")) {
                registers.replaceAll((k, v) -> 0);
                LineCount = 0;
                Arrays.fill(data, 0);
                System.out.println("Simulator reset");
                // outputs branch predictor accuracy
            } else if (userArray[0].equals("b")){

            }
        }
        System.out.println();
    }
}