package by.epamlab.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderFile {

    public static List<String> linesFromFile =
            getListFromFile(Constants.PATH + Constants.FILE_AUTHORS_BOOKS);

    public static List<String> getListFromFile(String fileName){
        List<String> strings = new ArrayList<>();
        try(Scanner sc = new Scanner(new FileReader(fileName))){
            while (sc.hasNextLine()){
                strings.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return strings;
    }

}
