package com.MoskBohd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FilesManager {

    private String textPaths;
    private List<String> textContent = new ArrayList<String>();

    public String getTextPaths() {
        return textPaths;
    }

    public List<String> getTextContent() {
        return textContent;
    }

    public void setTextContent(List<String> textContent) {
        this.textContent.addAll(textContent);
    }

    public void setTextPaths(String textPaths) {
        this.textPaths = textPaths;
    }

    public FilesManager() {
    }

    public FilesManager(String textPaths) {
        this.textPaths = textPaths;
    }

    //    1.1. Download a text about Harry Potter.
    public void getTextFromFile () throws IOException {
        Files.lines(Paths.get(getTextPaths()))
                .forEach(line->setTextContent(Collections.singletonList(line)));
    }
    //    1.2. For each distinct word in the text calculate the number of occurrence.
    public void getDistinctWordsWithAmount() throws IOException {
        String[] arrayOfWords = String.valueOf(getTextContent())
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^A-Za-z ]", "")
                .split(" +");
        for (int i =0; i<arrayOfWords.length; i++){
            System.out.println(arrayOfWords[i]);
        }
    }
    //
    //    1.3. Use RegEx..
    //
    //    1.4. Sort in the DESC mode by the number of occurrence..
    //
    //    1.5. Find  the first 20 pairs.
    //
    //    1.6  Find all the proper names
    //
    //    1.7.  Count them and arrange in alphabetic order.
    //
    //    1.8.   First 20 pairs and names write into to a file test.txt
    //
    //    1.9.  Create a fine header for the file
    //
    //    1.10  Use Java  Collections to demonstrate your experience (Map, List )
}
