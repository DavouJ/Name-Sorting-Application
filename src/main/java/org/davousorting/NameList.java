package org.davousorting;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.*;

public class NameList{
    private ArrayList<String> namesArray = new ArrayList<>();
    private String userInput;
    private Path sortedFile = Paths.get("sorted-names-list.txt");
    //create a function that takes an array of strings(names) and sorts them alphabetically
    public NameList(String[] fileName){
        Scanner scan = new Scanner(System.in);


        if(fileName.length != 0) {
            userInput= fileName[0];
        }else{
            userInput= null;
        }

        while (!doesFileExist() ){
            System.out.println("Enter a valid .txt file:");
            userInput = scan.nextLine();
        }

        fileToArray();
        sortList();
        writeSortedNames();
        System.out.println("Names sorted and stored in sorted-names-list.txt");
    }

    private Boolean doesFileExist(){
        if(userInput != null){
            return true;
        }else{
            return false;
        }
    }

    private void fileToArray(){
        try (BufferedReader br = new BufferedReader(new FileReader(userInput))) {
            String line = br.readLine();
            while(line != null){
                namesArray.add(line);
                line= br.readLine();
                //name = namesListFile.readLine();
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e);
        }
    }

    private void sortList(){
        Collections.sort(namesArray);

    }

    private void writeSortedNames(){
        try{
            Files.write(sortedFile, namesArray, StandardCharsets.UTF_8);

        }catch(IOException e){
            System.out.println(e);
        }
    }

    public Path getSortedFile(){
        return sortedFile;
    }
}
