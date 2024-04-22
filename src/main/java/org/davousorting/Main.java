package org.davousorting;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        new Main(args);
    }

    public Main(String[] userInput){

        NameList nameList = new NameList(userInput);
        nameList.sortByLastName();
        nameList.openFile();
    }



}

