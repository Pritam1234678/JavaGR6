import java.io.*;
import java.util.*;

public class DuplicateLines {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("lm.md"));

        ArrayList<String> seen = new ArrayList<>();
        ArrayList<String> duplicate = new ArrayList<>();

        String line;

        while ((line = br.readLine()) != null) {

            if (seen.contains(line) && !duplicate.contains(line)) {
                duplicate.add(line);
            } 
            else {
                seen.add(line);
            }
        }

        br.close();

        for (String s : duplicate)
            System.out.println(s);
    }
}