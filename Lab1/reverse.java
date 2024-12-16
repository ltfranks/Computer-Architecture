public class reverse {
    // bit shift 32 times
    public static int reverseBits(int input)
    {
        int Result = 0;
        int totBits = 32;
        int i = 0;
        while (i < totBits){
            Result = Result << 1;  // left shift by 1
            Result = Result | (input & 1);
            input = input >> 1;
            i++;
        }
        return Result;
    }

    // Driver code
    public static void main(String[] args) {
        int n = 4;
        System.out.println(reverseBits(n));
    }

}
