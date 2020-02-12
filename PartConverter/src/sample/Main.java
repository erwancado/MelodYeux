package sample;

import converter.MusicXMLParser;
import partition.Part;

public class Main{
    public static void main(String[] args) {
        Part partition=MusicXMLParser.ParseThis("src/musicxml_files/SchbAvMaSample.musicxml",1);
        System.out.println(partition.id);
    }
}
