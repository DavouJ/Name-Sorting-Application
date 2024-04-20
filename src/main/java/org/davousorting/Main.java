package org.davousorting;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<String> namesArray = new ArrayList<>();
    public static void main(String[] args){
        new Main(args);
    }

    public Main(String[] fileName){
        //System.out.println(fileName[0]);
        NameList nameList = new NameList(fileName);
    }


}

