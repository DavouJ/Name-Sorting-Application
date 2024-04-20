package org.davousorting;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        new Main(args);
    }

    public Main(String[] fileName){
        //System.out.println(fileName[0]);
        NameList nameList = new NameList(fileName);
        openFile(nameList);
    }

    private void openFile(NameList nameList){

        File sortedFile = new File(nameList.getSortedFile().toFile().toString());
        try {
            Desktop.getDesktop().open(sortedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

