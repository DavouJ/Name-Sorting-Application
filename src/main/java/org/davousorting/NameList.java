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



        if(fileName.length != 0) {
            userInput= fileName[0];
        }else{
            userInput= null;
        }


        validateFile();
        sortList();
        writeSortedNames();
        System.out.println("Names sorted and stored in sorted-names-list.txt");
    }

    private void validateFile(){
        Scanner scan = new Scanner(System.in);
        while (userInput == null ){
            System.out.println("Enter a valid .txt file:");
            userInput = scan.nextLine();
        }
    }
    private Boolean doesFileExist(){
        if(userInput != null){
            return true;
        }else{
            return false;
        }
    }

    private void sortList(){
        try (BufferedReader br = new BufferedReader(new FileReader(userInput))) {
            String line = br.readLine();
            while(line != null){

                namesArray.add(line);
                line= br.readLine();
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e);
        }
        flipName();
        //Collections.sort(namesArray);
    }

    private void flipName(){
        System.out.println(namesArray.get(0));
        String[] tempArr = namesArray.get(0).split("");
        int n = namesArray.size();
        int i;
        String  t;
        for(i=0;i< n/2;i++){
            t = tempArr[i];
            tempArr[i] = tempArr[n-i-1];
            tempArr[n-i-1]=t;
        }
        String flippedLine = tempArr.toString();


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
