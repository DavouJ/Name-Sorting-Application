package org.davousorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameListTest {

    private String[] input = {"./unsorted-names-list.txt"};

    @Test
    public void sortByLastNameShouldCreateSortedFile(){
        NameList test1 = new NameList(this.input);
    }
}