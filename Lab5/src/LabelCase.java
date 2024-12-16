public class LabelCase extends lab5 {

    public LabelCase() {

        String elem = cleanedFile.get(LineCount).get(1);
        if (elem.equals("add")) { // add
            registers.replace(cleanedFile.get(LineCount).get(2),  // rd = r1 + r2;
                    registers.get(cleanedFile.get(LineCount).get(3))
                            + registers.get(cleanedFile.get(LineCount).get(4)));
        } else if (elem.equals("addi")) {
            registers.replace(cleanedFile.get(LineCount).get(2),  // rd = r1 + #;
                    registers.get(cleanedFile.get(LineCount).get(3))
                            + Integer.parseInt(cleanedFile.get(LineCount).get(4)));
        } else if (elem.equals("sub")) { // sub
            registers.replace(cleanedFile.get(LineCount).get(2),  // rd = r1 - r2;
                    registers.get(cleanedFile.get(LineCount).get(3))
                            - registers.get(cleanedFile.get(LineCount).get(4)));
        } else if (elem.equals("and")) { // and
            registers.replace(cleanedFile.get(LineCount).get(2),  // rd = r1 & r2;
                    registers.get(cleanedFile.get(LineCount).get(3))
                            & registers.get(cleanedFile.get(LineCount).get(4)));
        } else if (elem.equals("or")) { // or
            registers.replace(cleanedFile.get(LineCount).get(2),  // rd = r1 | r2;
                    registers.get(cleanedFile.get(LineCount).get(3))
                            | registers.get(cleanedFile.get(LineCount).get(4)));
        } else if (elem.equals("sll")) { // sll
            registers.replace(cleanedFile.get(LineCount).get(2),  // rd = r1 << r2;
                    registers.get(cleanedFile.get(LineCount).get(3))
                            << registers.get(cleanedFile.get(LineCount).get(4)));
        } else if (elem.equals("slt")) { // set less than
            if (registers.get(cleanedFile.get(LineCount).get(3)) < registers.get(cleanedFile.get(LineCount).get(4))) {
                registers.replace(cleanedFile.get(LineCount).get(2), 1);    // if rs < rt then rd = 1. else, rd = 0
            } else {
                registers.replace(cleanedFile.get(LineCount).get(2), 0);
            }
        } else if (elem.equals("jr")) { // jr
            registers.replace("pc", registers.get(cleanedFile.get(LineCount).get(2)));
        } else if (elem.equals("beq")) { // if rs = rt then, j -> label
            if (registers.get(cleanedFile.get(LineCount).get(2)).equals(registers.get(cleanedFile.get(LineCount).get(3)))) {
                LineCount = LabelLocation.get(cleanedFile.get(LineCount).get(4));  // lineCount = lineCount - LabelLocation
            }
        } else if (elem.equals("bne")) { // rs != rt then, j -> label
            if (!registers.get(cleanedFile.get(LineCount).get(2)).equals(registers.get(cleanedFile.get(LineCount).get(3)))) {
                LineCount = LabelLocation.get(cleanedFile.get(LineCount).get(4));  // lineCount = lineCount - LabelLocation
            }
        } else if (elem.equals("sw")) {
            if (!("$" + cleanedFile.get(LineCount).get(3).charAt(3) + cleanedFile.get(LineCount).get(3).charAt(4)).equals("$sp")) {
                registers.replace(cleanedFile.get(LineCount).get(2),  // rd = #($register)
                        registers.get("$" + cleanedFile.get(LineCount).get(3).charAt(3)
                                + cleanedFile.get(LineCount).get(3).charAt(4)));
            } else {  // sp case
                // sw $a0 4($sp)
                // memory[4] = $a0
                data[cleanedFile.get(LineCount).get(3).charAt(0)] =
                        registers.get(cleanedFile.get(LineCount).get(2));
            }
        } else if (elem.equals("lw")) {
            // rd = data[#]
            registers.replace(cleanedFile.get(LineCount).get(2), data[cleanedFile.get(LineCount).get(3).charAt(0)]);
        } else if (elem.equals("j")) {
            // x = Line of Label
            LineCount = LabelLocation.get(cleanedFile.get(LineCount).get(2));
        } else if (elem.equals("jal")) {
            registers.replace("$ra", registers.get(cleanedFile.get(LineCount).get(2)));
        }
        LineCount++;
    }
}