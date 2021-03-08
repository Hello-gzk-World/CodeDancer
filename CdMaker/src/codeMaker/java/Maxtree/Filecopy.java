package codeMaker.java.Maxtree;

import java.io.*;

public class Filecopy {
    public static void copy(File src, File dest) throws IOException {
        InputStream input=null;
        OutputStream output=null;
        input=new FileInputStream(src);
        output=new FileOutputStream(dest);
        byte[]buf=new byte[1024];
        int bytesRead;
        while ((bytesRead=input.read(buf))>0){
            output.write(buf,0,bytesRead);
        }
        input.close();
        output.close();
    }
}
