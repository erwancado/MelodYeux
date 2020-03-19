package sample;

import converter.FileManager;
import converter.MusicXMLParser;
import partition.Part;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main{
    public static void main(String[] args) {
        System.setProperty("http.agent", "Mozilla/5.0 (X11; Linux x86_64; rv:47.0) Gecko/20100101 Firefox/47.0");
        String downloadPath=System.getProperty("user.home")+"/Downloads/";
        JFileChooser dialogue = new JFileChooser(downloadPath);
        dialogue.showOpenDialog(null);
        File toUnzip = dialogue.getSelectedFile();
        File destination = new File(downloadPath+"converter_tmp");
        try {
            File musicxmlFile = FileManager.unzip(toUnzip,destination);
            Part partition=MusicXMLParser.ParseThis(musicxmlFile.getPath(),1);
            System.out.println(partition.toString());
            String outputPath = toUnzip.getPath().replaceFirst(".mxl",".txt");
            System.out.println(outputPath);
            PrintWriter writer = new PrintWriter(outputPath, "ISO8859_1");
            writer.println(partition.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        FileManager.deleteDirectory(destination);
    }
}
