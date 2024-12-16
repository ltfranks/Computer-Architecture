import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CacheFour {

    // convert address (1fffff58) into a binary number and get tag, index.
    // binary index -> (int) will tell where to put the tag in array.

    // make the binary tag into integer and then store in array[index].
    // if address has been taken with same tag then , ++hits & lineNumber = currLinenumber.
    // after file is done scanning,
    // calculate hit rate.

    // LRU - Least Recently Used

    public CacheFour(File file) throws FileNotFoundException {
        // 2KB, 2-way, 1-word blocks
        int cacheSize = 2048;
        int blockSize = 1;
        int byteOffset = 2;
        int blockOffset = 0;
        int indexBits = 8;
        int tagBits = 22;
        int currLineNumber = 0;
        double hits = 0;
        double hitRate = 0;
        int tagMask =((1 << tagBits) - 1) << (indexBits+ byteOffset+blockOffset);
        int indexMask =

        // rows = 1024, columns = 2
        // column1 = cache0
        // column2 = cache1
//        int[][] cache = new int[(int) Math.pow(2.0, indexBits)][2];
        int[][] cache = new int[cacheSize / 2][2];
        int[][] timeStamp = new int[cacheSize / 2][2];

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            // 1fffff58
            // 000 1111 1111 1111 1111 1111 101011000
            String hexAddress = scanner.nextLine(); //scanner.nextLine();
            int num = Integer.parseInt(hexAddress, 16);
            String binaryAddress = String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0');
            String tagString = binaryAddress.substring(0, tagBits);
            String indexString = binaryAddress.substring(tagBits, tagBits + indexBits);
            int tag = Integer.parseInt(tagString, 2);
            int index = Integer.parseInt(indexString, 2);

            boolean hit = false;
            // if the index exists in either cache
            if (cache[index][0] == tag) {
                hit = true;
                hits++;
                // overwrite timeStamp(currLineNumber) of LRU
                timeStamp[index][0] = currLineNumber;
                currLineNumber++;
            }
            else if (cache[index][1] == tag) {
                hit = true;
                hits++;
                // overwrite timeStamp(currLineNumber) of LRU
                timeStamp[index][1] = currLineNumber;
                currLineNumber++;
            }

            if (!hit) { // miss
                // if cache0 was used before cache1
                // then overwrite cache0's tag/timeStamp
                // else, overwrite cache1's tag/timeStamp
                if (timeStamp[index][0] < timeStamp[index][1]) {
                    cache[index][0] = tag;
                    timeStamp[index][0] = currLineNumber;
                } else {
                    cache[index][1] = tag;
                    timeStamp[index][1] = currLineNumber;
                }
                currLineNumber++;
            }
        }
        currLineNumber--;

        hitRate = (hits / currLineNumber) * 100;
        System.out.println("Cache #4");
        System.out.println("Cache size: 2048B\tAssociativity: 2\tBlock size: 1");
        System.out.println("Hits: " + (int) hits + " \tHit Rate: " + String.format("%.2f", hitRate) + "%");
        System.out.println("---------------------------");
    }
}