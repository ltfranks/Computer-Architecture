import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
//        new CacheOne(file);
//        new CacheTwo(file);
//        new CacheThree(file);
        new CacheFour(file);
//        new CacheFive(file);
//        new CacheSix(file);
//        new CacheSeven(file);
    }
}