package org.davousorting;

import java.util.Scanner;

/**
 * Driver class
 */
public class Main {
    public static void main(String[] args){
        new Main(args);
    }

    /**
     * @param args the .txt file entered by the user in the command line
     */
    public Main(String[] args){

        NameList nameList = new NameList(args);
        nameList.sortByLastName();
        nameList.printList();
        nameList.openFile();
    }

}


