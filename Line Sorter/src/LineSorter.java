/**
 * This program sorts lines from a file
 * according to their length (ascending
 * order).If a line starts with '#', that 
 * line is not included in the output. If 
 * two lines have the same length, they are
 * sorted lexicographically. 
 * Date: Oct 25, 2018
 * Name: Shreeman Gautam
 */

import java.io.*;
import java.util.*;

public class LineSorter {
    /**
     * 
     * @param args: lines from the input file
     * @throws IOException
     * args[0] is the input file and is stored
     * in the variable fr. Until fr is null, 
     * which means that the file has ended, 
     * lines are added to the list called 
     * listOfLines (unless the line starts with 
     * a '#'). After that lines are sorted 
     * by length, then lexicographically, using 
     * the comparator interface. I got the
     * idea of piping strings into a method
     * (custom comparator) using this website: 
     * https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
     * To print line by line, the println
     * method of Printwriter class is used.
     * To prevent resource leak, after the input file has 
     * nothing to give, the input and output streams are closed.
     */
    public static void main(String[] args) throws IOException{
        BufferedReader fr = null;
        PrintWriter fw = null;
        List<String> listOfLines = new ArrayList<>();
        try {
            fr = new BufferedReader(new FileReader(args[0]));
            fw = new PrintWriter (new FileWriter(args[1]));
            String line;
            while ((line = fr.readLine()) != null) {
                if (!line.startsWith("#")) {
                    listOfLines.add(line);
                }
            }
            listOfLines.sort((first, second) -> compare(first, second));
            for (String element : listOfLines) {
                fw.println(element);
            }
        } finally {
            if (fr != null) {
                fr.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }
    /**
     * 
     * @param first: String
     * @param second: String
     * @return if first length
     * is greater than second length,
     * return 1, if second length is greater
     * than first length, return -1. If the strings
     * are equal, use the compareTo method
     * to break the tie. 
     */
    public static int compare(String first, String second) {
        if (first.length() < second.length()) {
            return -1;
        } else if (first.length() > second.length()){
            return 1;
        } else {
            return first.compareTo(second);
        }
    }
}
