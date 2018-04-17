package utils;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UtilFaces {
    public static void readAndPrintHtml(String filePath, ServletContext context, PrintWriter out) throws IOException {
        String fullPath = context.getRealPath(filePath);
        FileReader fileReader = new FileReader(fullPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readedLine;
        while((readedLine = bufferedReader.readLine()) != null){
            out.println(readedLine);
        }
    }
}
