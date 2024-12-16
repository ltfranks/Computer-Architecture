public class Instructions extends lab2 {

    // R INSTRUCTIONS
    public Instructions(String funct, String rd, String rs, String rt) {
        if (funct.equals("jr")) {
            System.out.println("000000" + " " + registers.get(rs) + " " + registers.get(rt) + "" + registers.get(rd)
                    + "00000 " + RInstructions.get(funct));
        }
        else {
            System.out.println("000000" + " " + registers.get(rs) + " " + registers.get(rt) + " " + registers.get(rd)
                    + " 00000 " + RInstructions.get(funct));
        }
    }

    // I INSTRUCTIONS
    public Instructions(String op, String rs, String rt, int immediate) {
        if (immediate < 0){
            String bin = String.format("%16s", Integer.toBinaryString(immediate));
            bin = bin.substring(16, 32);

            System.out.println(IInstructions.get(op) + " " + registers.get(rs) + " " + registers.get(rt) + " " +
                    bin);
        }
        else {
            System.out.println(IInstructions.get(op) + " " + registers.get(rs) + " " + registers.get(rt) + " " +
                    String.format("%16s", Integer.toBinaryString(immediate)).replace(' ', '0'));
        }
    }

    // J INSTRUCTIONS
    public Instructions(String op, String jumpTarget) {
        System.out.println(JInstructions.get(op) + " " +
                String.format("%26s", Integer.toBinaryString(LabelLocation.get(jumpTarget))).replace(' ', '0'));
    }

    public Instructions() {
        // value = funct because it's the only thing that differentiates between R instructions
        RInstructions.put("and", "100100");
        RInstructions.put("or", "100101");
        RInstructions.put("add", "100000");
        RInstructions.put("sll", "000000");
        RInstructions.put("sub", "100010");
        RInstructions.put("slt", "101010");
        RInstructions.put("jr", "001000");
        // I-instructions
        IInstructions.put("addi", "001000");
        IInstructions.put("beq", "000100");
        IInstructions.put("bne", "000101");
        IInstructions.put("lw", "100011");
        IInstructions.put("sw", "101011");
        // J-instructions
        JInstructions.put("j", "000010");
        JInstructions.put("jal", "000011");

        registers.put("$zero", "00000");
        registers.put("$0", "00000");

        registers.put("$v0", "00010");
        registers.put("$v1", "00011");

        registers.put("$a0", "00100");
        registers.put("$a1", "00101");
        registers.put("$a2", "00110");
        registers.put("$a3", "00111");

        registers.put("$t0", "01000");
        registers.put("$t1", "01001");
        registers.put("$t2", "01010");
        registers.put("$t3", "01011");
        registers.put("$t4", "01100");
        registers.put("$t5", "01101");
        registers.put("$t6", "01110");
        registers.put("$t7", "01111");

        registers.put("$s0", "10000");
        registers.put("$s1", "10001");
        registers.put("$s2", "10010");
        registers.put("$s3", "10011");
        registers.put("$s4", "10100");
        registers.put("$s5", "10101");
        registers.put("$s6", "10110");
        registers.put("$s7", "10111");

        registers.put("$t8", "11000");
        registers.put("$t9", "11001");

        registers.put("$sp", "11101");
        registers.put("$ra", "11111");
    }
}