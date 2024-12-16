import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SecondScan extends lab2 {
    Scanner file;
    int curr = 0;

    public SecondScan(String file) {
        try {
            this.file = new Scanner(new File(file));
            while (this.file.hasNextLine()) {
                String line = this.file.nextLine();
                line = line.replaceAll(":", ": ");
                line = line.replaceAll("#.*$", "").trim().replaceAll("\\s", " ");

                if (line.isEmpty()) {
                    continue;
                }

                if (line.charAt(0) == 'j') {
                    if (line.charAt(1) == 'a') {
                        line = line.replaceAll("jal", "jal,");
                    } else if (line.charAt(1) == 'r') {
                        line = line.replaceAll("jr", "jr,");
                    } else {
                        line = line.replaceAll("j", "j,");
                    }
                }
                line = line.replaceAll(",", " ");
                line = line.replaceAll(" {2}", " ");
                line = line.replaceAll("slt\\$", "slt \\$");
                line = line.replaceAll("beq\\$", "beq \\$").replaceAll("\\s+", " ");

                /* Need to figure out what to do with ':' and other stuff */

                String[] parts = line.split(" ");

                if (parts[0].contains(":") && parts.length == 1) {
                    continue;
                }
                if (parts[0].contains(":")) {
                    parts = Arrays.copyOfRange(parts, 1, parts.length);
                }
                if (!line.isEmpty()) {
                    ++curr;
                }

                if (RInstructions.containsKey(parts[0])) {
                    if (parts[0].equals("jr")) {
                        new Instructions(parts[0], "$0", parts[1], "$0");
                    } else if (parts[0].equals("sll")) {
                        System.out.println("000000" + " " + registers.get("$0") + " " + registers.get(parts[2]) + " " +
                                registers.get(parts[1]) + " " + String.format("%5s", Integer.toBinaryString(Integer.parseInt(parts[3]))).replace(' ', '0') + " "
                                + "000000");
                    } else new Instructions(parts[0], parts[1], parts[2], parts[3]);

                    // I-Instructions conditional statements for printing
                } else if (IInstructions.containsKey(parts[0])) {
                    if (parts[0].equals("beq") || parts[0].equals("bne")) {
                        new Instructions(parts[0], parts[1], parts[2], LabelLocation.get(parts[3]) - curr);
                    }
                    if (parts[0].equals("lw") || parts[0].equals("sw")) {
                        System.out.println(IInstructions.get(parts[0]) + " " +
                                registers.get("$" + parts[2].charAt(3) + parts[2].charAt(4)) + " " +
                                registers.get(parts[1]) + " " +
                                String.format("%16s", Integer.toBinaryString(Integer.parseInt(String.valueOf(parts[2].charAt(0))))).replace(' ', '0'));
                    }
                    if (parts[0].equals("addi")) {
                        new Instructions(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                    }
                    // J-Instructions conditional statements for printing
                } else if (JInstructions.containsKey(parts[0])) {
                    new Instructions(parts[0], parts[1]);
                }
                else {
                    System.out.println("invalid instruction: " + parts[0] + '\n');
                    System.exit(0);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}