import javax.sound.sampled.Line;
import java.util.Objects;

public class Instructions extends lab4 {
    public Instructions() {
        String elem = cleanedFile.get(LineCount).get(0);
        if (elem.equals("beq")) { // if rs = rt then, j -> label
            // if the branch is taken, then set the flag to true so we can SQUASH
            if (registers.get(cleanedFile.get(LineCount).get(1)).equals(registers.get(cleanedFile.get(LineCount).get(2)))) {
                branchFlag = true;
                labelBranchLine = LabelLocation.get(cleanedFile.get(LineCount).get(3));  // lineCount = lineCount - LabelLocation
            }
        } else if (elem.equals("bne")) { // rs != rt then, j -> label
            // if the branch is taken, then set the flag to true so we can SQUASH
            if (!registers.get(cleanedFile.get(LineCount).get(1)).equals(registers.get(cleanedFile.get(LineCount).get(2)))) {
                branchFlag = true;
                labelBranchLine = LabelLocation.get(cleanedFile.get(LineCount).get(3));  // lineCount = lineCount - LabelLocation
            }
        }
        else {
            if (!elem.contains(":")) {
                if (elem.equals("add")) { // add
                    registers.replace(cleanedFile.get(LineCount).get(1),  // rd = r1 + r2;
                            registers.get(cleanedFile.get(LineCount).get(2))
                                    + registers.get(cleanedFile.get(LineCount).get(3)));
                } else if (elem.equals("addi")) {
                    registers.replace(cleanedFile.get(LineCount).get(1),  // rd = r1 + #;
                            registers.get(cleanedFile.get(LineCount).get(2))
                                    + Integer.parseInt(cleanedFile.get(LineCount).get(3)));
                } else if (elem.equals("sub")) { // sub
                    registers.replace(cleanedFile.get(LineCount).get(1),  // rd = r1 - r2;
                            registers.get(cleanedFile.get(LineCount).get(2))
                                    - registers.get(cleanedFile.get(LineCount).get(3)));
                } else if (elem.equals("and")) { // and
                    registers.replace(cleanedFile.get(LineCount).get(1),  // rd = r1 & r2;
                            registers.get(cleanedFile.get(LineCount).get(2))
                                    & registers.get(cleanedFile.get(LineCount).get(3)));
                } else if (elem.equals("or")) { // or
                    registers.replace(cleanedFile.get(LineCount).get(1),  // rd = r1 | r2;
                            registers.get(cleanedFile.get(LineCount).get(2))
                                    | registers.get(cleanedFile.get(LineCount).get(3)));
                } else if (elem.equals("sll")) { // sll
                    registers.replace(cleanedFile.get(LineCount).get(1),  // rd = r1 << r2;
                            registers.get(cleanedFile.get(LineCount).get(2))
                                    << registers.get(cleanedFile.get(LineCount).get(3)));
                } else if (elem.equals("slt")) { // set less than
                    if (registers.get(cleanedFile.get(LineCount).get(2)) < registers.get(cleanedFile.get(LineCount).get(3))) {
                        registers.replace(cleanedFile.get(LineCount).get(1), 1);    // if rs < rt then rd = 1. else, rd = 0
                    } else {
                        registers.replace(cleanedFile.get(LineCount).get(1), 0);
                    }
                } else if (elem.equals("jr")) { // jr
                    registers.replace("pc", registers.get(cleanedFile.get(LineCount).get(1)));
                } else if (elem.equals("sw")) {
                    if (!("$" + cleanedFile.get(LineCount).get(2).charAt(3) + cleanedFile.get(LineCount).get(2).charAt(4)).equals("$sp")) {
                        registers.replace(cleanedFile.get(LineCount).get(1),  // rd = #($register)
                                registers.get("$" + cleanedFile.get(LineCount).get(2).charAt(3)
                                        + cleanedFile.get(LineCount).get(2).charAt(4)));
                    } else {  // sp case
                        // sw $a0 4($sp)
                        // memory[4] = $a0
                        data[cleanedFile.get(LineCount).get(2).charAt(0)] =
                                registers.get(cleanedFile.get(LineCount).get(1));
                    }
                } else if (elem.equals("lw")) {
                    // rd = data[#]
                    registers.replace(cleanedFile.get(LineCount).get(1), data[cleanedFile.get(LineCount).get(2).charAt(0)]);
                } else if (elem.equals("j")) {
                    // x = Line of Label
                    LineCount = LabelLocation.get(cleanedFile.get(LineCount).get(1));
                } else if (elem.equals("jal")) {
                    registers.replace("$ra", registers.get(cleanedFile.get(LineCount).get(1)));
                } else if (elem.equals("eof")) {
                    System.out.println("End of file reached.");
                }
                LineCount++;
            }
        }
    }
}