package org.davousorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class NameListTest {

    private String[] args = {"./src/main/java/org/davousorting/unsorted-names-list.txt"};

    @Test
    public void sortByLastNameShouldCreateSortedFile(){

        NameList nameList = new NameList(args);
        nameList.sortByLastName();
        Path path = nameList.getPath();
        Assertions.assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("Stop program if file was not entered in command line")
    public void throwRuntimeIfNoFileIsInputted(){
        var nameList = new NameList(args);
        Assertions.assertThrows(RuntimeException.class, ()->{
            nameList.validateFileEntered(null);
            throw new RuntimeException("NO TEXT FILE ENTERED, PLEASE ENTER THE FILE NAME YOU WISH TO SORT.");
        });
    }
    @Test
    @DisplayName("Stop program if file cannot be read")
    public void throwRuntimeIfFileCannotBeRead(){

        var nameList = new NameList(args);
        Exception e = new Exception();

        assertThrows(RuntimeException.class, ()->{
            nameList.validateFileExists(e);
        });
    }
    @Test
    @DisplayName("Stop program if a full name in the list has more than 3 given names")
    public void throwRuntimeIfNameLongerThanFour(){

        var nameList = new NameList(args);

        assertThrows(RuntimeException.class, ()->{
            nameList.validateNameLength(5);
        });
    }
}