package lk.ijse.orm.util;

import java.io.*;

public class FileManager {
    private static FileManager fileManager;
    private final String path;
    private FileReader reader;
    private FileWriter writer;

    private FileManager() throws IOException {
        path = "src/lk/ijse/orm/util/UserPw.txt";
    }

    public static FileManager getInstance() throws IOException {
        return null == fileManager ? fileManager = new FileManager() : fileManager;
    }

    public String readFile() throws IOException {
        reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        String out = "";

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            out = out + line;
        }
        reader.close();
        return out;
    }

    public void writeFile(String in) throws IOException {
        writer = new FileWriter(path, false);

        writer.write(in);
        writer.close();
    }
}
