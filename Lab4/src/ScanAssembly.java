import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScanAssembly extends lab4 {
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

                String[] parts = line.split(" ");


                if (parts[0].contains(":") && parts.length == 1) {
                    continue;
                }
                if (parts[0].contains(":")) {
                    parts = Arrays.copyOfRange(parts, 1, parts.length);
                }
                List<String> newParts = Arrays.asList(parts);
                cleanedFile.add(newParts);

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}