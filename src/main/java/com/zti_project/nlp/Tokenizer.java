package com.zti_project.nlp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ada
 */
public class Tokenizer {

    public Tokenizer() {
    }

    /**
     * Split sentence into array of tokens
     *
     * @param sentence
     * @return string array with result words
     */
    public String[] tokenizeSentence(String sentence) throws IOException {

        String[] tokens = removeSpecialCharsAndTokenize(sentence);
        String[] result = removeStopWords(tokens);

        return result;
    }

    /**
     * Removes tokens which are special characters
     * example: []()/\,.-!@#$^&*;"'
     *
     * @param sentence
     * @return string array without special characters
     */
    private String[] removeSpecialCharsAndTokenize(String sentence) {
        return sentence.replaceAll("[^a-zA-Z ]", "").split("\\s+");
    }

    /**
     * Removes eng stop words
     * example: and, of, a
     *
     * @param tokens
     * @return string array without stop words
     */
    private String[] removeStopWords(String[] tokens) throws IOException {
        Scanner s = new Scanner(new File("src/main/resources/english_stopwords.txt"));
        ArrayList<String> stopWords = new ArrayList<String>();
        while (s.hasNext()) {
            stopWords.add(s.next());
        }
        s.close();

        List<String> tokenList = new java.util.ArrayList<>(List.of(tokens));
        for (int i = 0; i < stopWords.size(); i++) {
            for (int j = 0; j < tokenList.size(); j++) {
                if (stopWords.get(i).equals(tokenList.get(j))) {
                    tokenList.remove(j);
                }
            }
        }
        return tokenList.toArray(String[]::new);
    }
}
