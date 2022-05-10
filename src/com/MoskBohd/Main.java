package com.MoskBohd;

// Final course task Java Development
// Bohdan Moskalskyi is work author

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        FilesManager filesManagerHarry = new FilesManager("harry.txt");
        filesManagerHarry.getTextFromFile();
        filesManagerHarry.getDistinctWordsWithAmount();
    }
}
