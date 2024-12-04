import java.io.*;
import java.util.*;

public class Proj4 {

    // create a deep copy
    public static void copyFFNList(ArrayList<FastFoodNutritionInfo> newList, ArrayList<FastFoodNutritionInfo> ogList){
        for (FastFoodNutritionInfo item : ogList) {

            // copy each element
            newList.add(new FastFoodNutritionInfo(item));
        }
    }
    /**
     * Overloaded method that copies specified number of lines from
     * global list with all FFN dataset
     *
     * @parameter number of lines to copy.
     */
    public static void copyFFNList(ArrayList<FastFoodNutritionInfo> newList, int lines){
        for(int i = 0; i < lines; i++){
            newList.add(FastFoodNutritionInfo.allFFN.get(i));
        }

    }
    // writes to file and prints to console
    public static void writeToFileAndPrint(String content) throws IOException {
        // create file variable
        File myFile = new File(analysisFilePath);

        // ensure it exists or create one
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {

            }
        }
        System.out.println(content);

        FileWriter dataTyper = new FileWriter(myFile, true); // file writer
        dataTyper.write(content +"\n"); // write given string and new line

        dataTyper.close(); // close file to preserve data
    }
    static String analysisFilePath = "src/analysis.txt"; // holds analysis file path

    /**
     * Insert data from arrayList into the hash table.
     *
     * @parameter list.
     */
    public static void insertListToHash(SeparateChainingHashTable table, ArrayList<FastFoodNutritionInfo> list){
        for (FastFoodNutritionInfo item : list ) {
            // Creating a new instance of each element
                table.insert(new FastFoodNutritionInfo(item));
        }
    }
    /**
     * remove data from list from hash table
     *
     * @parameter list.
     */
    public static void removeFromHash(SeparateChainingHashTable table, ArrayList<FastFoodNutritionInfo> list) {
        for (FastFoodNutritionInfo item : list) {
            // Creating a new instance of each element
            table.remove(item);
        }
    }
    /**
     * search for data in a list with hash table
     *
     * @parameter list.
     */
        public static void searchHash(SeparateChainingHashTable table, ArrayList<FastFoodNutritionInfo> list){
            for (FastFoodNutritionInfo item : list) {
                // Creating a new instance of each element
                table.contains(item);
            }
        }
    public static void main(String[] args) throws IOException {
        FastFoodNutritionInfo.readFastFoodData("src/FFNDataProj4.csv");

        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        Scanner scanner = new Scanner(inputFileNameStream);

        // make our lists
        ArrayList<FastFoodNutritionInfo> sortedList = new ArrayList<>();
        copyFFNList(sortedList,numLines);
        Collections.sort(sortedList);
        ArrayList<FastFoodNutritionInfo> reversedList = new ArrayList<>();
        copyFFNList(reversedList,sortedList);
        Collections.reverse(reversedList);
        ArrayList<FastFoodNutritionInfo> shuffledList = new ArrayList<>();
        copyFFNList(shuffledList,sortedList);
        Collections.shuffle(shuffledList);

        // create our hash tables
        SeparateChainingHashTable<Object> sortedHashTable = new SeparateChainingHashTable<>();
        SeparateChainingHashTable<Object> shuffledHashTable = new SeparateChainingHashTable<>();
        SeparateChainingHashTable<Object> reverseHashTable = new SeparateChainingHashTable<>();

        // formatting for results files
        writeToFileAndPrint("_____________________________________________" + numLines+"______________________________________________________");
        String messagePreTest = "Sorted Trial for " + numLines + " lines";
        writeToFileAndPrint(messagePreTest);

        // insert
        long start1 = System.nanoTime(); // start time
        insertListToHash(sortedHashTable, sortedList);
        long end1 = System.nanoTime(); // end time

        // search
        long start2 = System.nanoTime(); // start time
        searchHash(sortedHashTable, sortedList);
        long end2 = System.nanoTime(); // end time

        // remove
        long start3 = System.nanoTime(); // start time
        removeFromHash(sortedHashTable,sortedList);
        long end3 = System.nanoTime(); // end time


        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");
        writeToFileAndPrint("\t Remove Time: " + (end3 - start3) / 1e9 + " sec");

        writeToFileAndPrint("\t"); // formatting

        messagePreTest = "shuffled Trial for " + numLines + " lines";
        writeToFileAndPrint(messagePreTest);

        // insert
        start1 = System.nanoTime(); // start time
        insertListToHash(shuffledHashTable,shuffledList);
        end1 = System.nanoTime(); // end time

        // search
        start2 = System.nanoTime(); // start time
        searchHash(shuffledHashTable,shuffledList);
        end2 = System.nanoTime(); // end time

        // remove
        start3 = System.nanoTime(); // start time
        removeFromHash(shuffledHashTable,shuffledList);
        end3 = System.nanoTime(); // end time


        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");
        writeToFileAndPrint("\t Remove Time: " + (end3 - start3) / 1e9 + " sec");

        writeToFileAndPrint("\t"); // formatting

        messagePreTest = "Reverse Trial for " + numLines + " lines";
        writeToFileAndPrint(messagePreTest);

        // insert
        start1 = System.nanoTime(); // start time
        insertListToHash(reverseHashTable,reversedList);
        end1 = System.nanoTime(); // end time

        // search
        start2 = System.nanoTime(); // start time
        searchHash(reverseHashTable,reversedList);
        end2 = System.nanoTime(); // end time

        // remove
        start3 = System.nanoTime(); // start time
        removeFromHash(reverseHashTable,reversedList);
        end3 = System.nanoTime(); // end time


        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");
        writeToFileAndPrint("\t Remove Time: " + (end3 - start3) / 1e9 + " sec");


    }
}
