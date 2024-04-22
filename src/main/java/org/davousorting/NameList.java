package org.davousorting;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.util.Scanner;

/**
 * This class handles the .txt file given by the user via the command line
 */
public class NameList{
    private ArrayList<String> namesArray = new ArrayList<>();
    private Path sortedFile = Paths.get("sorted-names-list.txt");
    private String userInput;

    /**
     * Creates an ArrayList containing the names
     * within the given .txt file.
     * Once an instance of NameList is created,
     * it can be sorted and written to a new file.
     *
     * @param userInput the .txt file passed as an argument from the command line
     */
    public NameList(String[] userInput){

        if(userInput.length != 0) {
            this.userInput= userInput[0];
        }else{
            userInput= null;
        }

        this.userInput = validateFile(this.userInput);
        this.namesArray = readNameList(namesArray, this.userInput );
    }

    /**
     * Validates whether the given .txt file
     * exists.
     *
     * @param userInput the .txt file passed as an argument from the command line.
     * @return the .txt file passed as an argument from the command line OR
     * a new userInput from the command line.
     */
    public static String validateFile(String userInput){
        Scanner scan = new Scanner(System.in);
        while (userInput == null ){
            System.out.println("Enter a valid .txt file:");
            userInput = scan.nextLine();
        }
        return userInput;
    }

    /**
     * Reads the names from the given .txt file
     * and stores them in an ArrayList.
     *
     * @param namesArray empty ArrayList.
     * @param userInput the .txt file passed as an argument from the command line.
     * @return ArrayList of names.
     */
    private ArrayList<String> readNameList(ArrayList<String> namesArray, String userInput){
        try (BufferedReader br = new BufferedReader(new FileReader(userInput))) {
            String line = br.readLine();
            while(line != null){
                namesArray.add(line);
                line= br.readLine();
            }

        } catch (IOException | NullPointerException e) {
            userInput = null;
            userInput = validateFile(userInput);
            namesArray = readNameList(namesArray, userInput );
        }
        return namesArray;
    }

    /**
     * Reverses the names in the ArrayList
     * e.g. <i>John Doe -> Doe John</i>
     *
     * @param userInput the .txt file passed as an argument from the command line.
     */
    private void flipNames(String userInput){
        for(int i = 0;i< namesArray.size(); i++) {
            String[] tempArr = namesArray.get(i).split(" ");
            int n = tempArr.length;
            if(n>4){
                System.out.println("File contains Name with more than 3 given names, cannot sort.");
                userInput=null;
                break;
            }
            int j;
            String t;

            for (j = 0; j < n / 2; j++) {
                t = tempArr[j];
                tempArr[j] = tempArr[n - j - 1];
                tempArr[n - j - 1] = t;
            }
            namesArray.set(i, String.join(" ",tempArr));
        }
        if(userInput == null) {
            namesArray.clear();
            userInput = validateFile(userInput);
            namesArray = readNameList(namesArray, userInput);
        }
    }

    /**
     * Overwrites/generates a .txt file
     * with the ArrayList of sorted names.
     *
     * @param namesArray an ArrayList of names.
     * @param sortedFile the destination of the sorted names file.
     */
    private void writeNames(ArrayList<String> namesArray, Path sortedFile){
        try{
            Files.write(sortedFile, namesArray, StandardCharsets.UTF_8);

        }catch(IOException e){
            System.out.println("write to file failed");
        }
    }

    /**
     * Sorts names alphabetically.
     */
    public void sort(){
        Collections.sort(namesArray);
        writeNames(namesArray, sortedFile);
    }

    /**
     * Sorts names by surname, alphabetically.
     */
    public void sortByLastName(){
        flipNames(userInput);
        Collections.sort(namesArray);
        flipNames(userInput);
        writeNames(namesArray, sortedFile);

    }

    /**
     * Opens the .txt file containing the
     * sorted list of names.
     */
    public void openFile(){

        File sortedFile = new File(this.sortedFile.toFile().toString());
        try {
            Desktop.getDesktop().open(sortedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printList(){
        for(int i = 0; i<namesArray.size();i++){
            System.out.println(namesArray.get(i));
        }
    }

}
