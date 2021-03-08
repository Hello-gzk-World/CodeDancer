package codeMaker.java.Maxtree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fileclear {
    public static void Fileclear(String filepath) throws IOException {
        File file=new File(filepath);
        if(!file.exists())file.createNewFile();
        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();

    }
}
