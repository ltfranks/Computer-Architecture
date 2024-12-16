public class LabelCase extends lab2 {
    public LabelCase(String[] parts){
        if (RInstructions.containsKey(parts[0])) {
            if (parts[0].equals("jr")) {
                new Instructions(parts[0], "$0", parts[1], "$0");
            } else if (parts[0].equals("sll")) {
                System.out.println("000000" + " " + registers.get("$0") + registers.get(parts[2]) + " " +
                        registers.get(parts[1]) + " " + String.format("%5s", Integer.toBinaryString(Integer.parseInt(parts[3]))).replace(' ', '0') + " "
                        + "000000");
            } else new Instructions(parts[0], parts[1], parts[2], parts[3]);

            // I-Instructions conditional statements for printing
        } else if (IInstructions.containsKey(parts[0])) {
            if (parts[0].equals("beq") || parts[0].equals("bne")) {
                new Instructions(parts[0], parts[1], parts[2], LabelLocation.get(parts[3]));
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
    }
}