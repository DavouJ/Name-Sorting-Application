package org.davousorting;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NameList{
    private ArrayList<String> namesArray = new ArrayList<>();
    //create a function that takes an array of strings(names) and sorts them alphabetically
    public NameList(String[] fileName){
        Scanner scan = new Scanner(System.in);
        String userInput = null;
        String namesList;

        if(fileName.length != 0) {
            userInput= fileName[0];
        }else{
            userInput= null;
        }

        while (doesFileExist(userInput) != true ){
            System.out.println("Enter a valid .txt file:");
            userInput = scan.nextLine();
        }

        fileToArray(userInput);
        sortList();
    }

    private Boolean doesFileExist(String userInput){
        if(userInput != null){
            return true;
        }else{
            return false;
        }
    }

    private void fileToArray(String userInput){
        String name;
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
        System.out.println(namesArray);
    }
}
