import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScanAssembly extends lab3 {
    Scanner assemblyFile;

    public ScanAssembly(String file) {
        try {
            this.assemblyFile = new Scanner(new File(file));
            while (this.assemblyFile.hasNextLine()) {
                String line = this.assemblyFile.nextLine();
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
                System.out.println(line);

                List<String> parts = Arrays.asList(line.split(" "));

                cleanedFile.add(parts);

//                if (parts[0].equals("add")){
//                    Registers.replace(parts[1], Registers.get(parts[3]) + Registers.get(parts[2]));
//                } else if (parts[0].equals("sub")) {
//                    Registers.replace(parts[1], Registers.get(parts[3]) - Registers.get(parts[2]));
//                }

            }
//            List<String> eof = Collections.singletonList("eof");
//            cleanedFile.add(eof);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
//        System.out.println(cleanedFile.get(0));
    }
}