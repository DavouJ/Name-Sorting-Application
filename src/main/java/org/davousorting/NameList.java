package org.davousorting;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.*;

public class NameList{
    private ArrayList<String> namesArray = new ArrayList<>();
    private Path sortedFile = Paths.get("sorted-names-list.txt");
    private String userInput;

    //create a function that takes an array of strings(names) and sorts them alphabetically
    public NameList(String[] userInput){

        if(userInput.length != 0) {
            this.userInput= userInput[0];
        }else{
            userInput= null;
        }

        validateFile();
        readNameList();
        //writeSortedNames();
        System.out.println("Name list read");
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

    private void readNameList(){
        try (BufferedReader br = new BufferedReader(new FileReader(userInput))) {
            String line = br.readLine();
            while(line != null){
                namesArray.add(line);
                line= br.readLine();
            }
        } catch (IOException | NullPointerException e) {
            userInput = null;
            validateFile();
            readNameList();
        }
    }

    public void sortByLastName(){
        flipNames();
        Collections.sort(namesArray);
        flipNames();
        writeSortedNames();
    }

    private void flipNames(){
        for(int i = 0;i< namesArray.size(); i++) {
            String[] tempArr = namesArray.get(i).split(" ");
            int n = tempArr.length;
            int j;
            String t;

            for (j = 0; j < n / 2; j++) {
                t = tempArr[j];
                tempArr[j] = tempArr[n - j - 1];
                tempArr[n - j - 1] = t;
            }
            namesArray.set(i, String.join(" ",tempArr));
        }
    }
    public void sort(){
        Collections.sort(namesArray);
        writeSortedNames();
    }

    private void writeSortedNames(){
        try{
            Files.write(sortedFile, namesArray, StandardCharsets.UTF_8);

        }catch(IOException e){
            System.out.println(e);
        }
    }

    private Path getSortedFile(){
        return sortedFile;
    }

    public void openFile(){

        File sortedFile = new File(this.sortedFile.toFile().toString());
        try {
            Desktop.getDesktop().open(sortedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
