import java.io.*;

public class MergeFiles {
    public static void main(String[] args) throws Exception {

        File folder = new File("src/files");
        File[] files = folder.listFiles();

        BufferedWriter bw = new BufferedWriter(new FileWriter("merged.md"));

        for (File f : files) {
            if (f.getName().endsWith(".md")) {

                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;

                while ((line = br.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                }

                br.close();
            }
        }

        bw.close();
    }
}