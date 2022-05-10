package com.MoskBohd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class FilesManager {

    //    1.10  Use Java  Collections to demonstrate your experience (Map, List )
    private String textPaths;
    private List<String> textContent = new ArrayList<String>();
    private String[] arrayOfWords;
    private List<String> listOfDistinctWords;
    List<Map.Entry<String, Long>> listOfDistinctWordsWithTheirCount = new ArrayList<>();
    List<Map.Entry<String, Long>> properNamesMap  = new ArrayList<>();


    // GETTERS AND SETTERS

    public List<Map.Entry<String, Long>> getListOfDistinctWordsWithTheirCount() {
        return listOfDistinctWordsWithTheirCount;
    }

    public void setListOfDistinctWordsWithTheirCount(List<Map.Entry<String, Long>> listOfDistinctWordsWithTheirCount) {
        this.listOfDistinctWordsWithTheirCount = listOfDistinctWordsWithTheirCount;
    }

    public List<Map.Entry<String, Long>> getProperNamesMap() {
        return properNamesMap;
    }

    public void setProperNamesMap(List<Map.Entry<String, Long>> properNamesMap) {
        this.properNamesMap = properNamesMap;
    }

    public List<String> getListOfDistinctWords() {
        return listOfDistinctWords;
    }

    public void setListOfDistinctWords(List<String> listOfDistinctWords) {
        this.listOfDistinctWords = listOfDistinctWords;
    }

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

    public String[] getArrayOfWords() {
        return arrayOfWords;
    }

    public void setArrayOfWords(String[] arrayOfWords) {
        this.arrayOfWords = arrayOfWords;
    }

    // CONSTRUCTORS
    public FilesManager() {
    }

    public FilesManager(String textPaths) {
        this.textPaths = textPaths;
    }

    // METHODS

    //    1.1. Download a text about Harry Potter.
    public void getTextFromFile () throws IOException {
        Files.lines(Paths.get(getTextPaths()))
                .forEach(line->setTextContent(Collections.singletonList(line)));
    }

    //    1.2. For each distinct word in the text calculate the number of occurrence.
    public void findDistinctWordsWithAmount() throws IOException {
        setArrayOfWords(String.valueOf(getTextContent())
    //    1.3. Use RegEx..
                .replaceAll("[^A-Za-z ]", "")
                .split(" +"));
    //    1.4. Sort in the DESC mode by the number of occurrence...
        List<String> words = List.of(getArrayOfWords());
        List<Map.Entry<String, Long>> result = words.stream()
                        .collect(groupingBy(identity(), counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Long> comparingByValue(reverseOrder())
                                .thenComparing(Map.Entry.comparingByKey()))
    //    1.5. Find  the first 20 pairs.
                        .limit(20)
                        .collect(toList());
        setListOfDistinctWordsWithTheirCount(result);
    }

    //    1.6  Find all the proper names "Named-entity recognition"
    public void findAllTheProperNames() throws IOException {
        List<String> words = List.of(getArrayOfWords());
        List<Map.Entry<String, Long>> result = words.stream()
                .collect(groupingBy(identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByKey()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(toList());

    //    1.7.  Count them and arrange in alphabetic order.
        List<Map.Entry<String, Long>> properName = new ArrayList<>();
        for (Map.Entry<String, Long> word:result){
            if(word.getKey().matches("[A-Z]\\w*")){
                System.out.println(word);
                properName.add(word);
            }
        }
        setProperNamesMap(properName);
    }

    //    1.8.   First 20 pairs and names write into to a file test.txt
    public void createTextFileForResults() throws IOException {
        String path = "test.txt";
    //    1.9.  Create a fine header for the file

        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("\"HELLO \\n\" +\n" +
                "                \"THIS IS FILE WITH RESULT OF FINAL COURSE TASK OF JAVA DEVELOPMENT \\n\" +\n" +
                "                \"THE AUTHOR IS BOHDAN MOSKALSKYI\\n\"");
        printWriter.printf(getListOfDistinctWordsWithTheirCount().toString());
        printWriter.printf(getProperNamesMap().toString());

        printWriter.close();
    }
}
