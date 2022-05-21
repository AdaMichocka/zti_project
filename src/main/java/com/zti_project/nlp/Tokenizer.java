package com.zti_project.nlp;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The goal of tokenization is to divide a sentence into smaller parts called tokens
 *
 * @author Ada
 */
public class Tokenizer {

    public Tokenizer() {
    }

    /**
     * Split sentence into array of tokens and remove stopwords
     *
     * @param sentence
     * @return string array with result words
     */
    public String[] tokenizeSentence(String sentence) throws IOException {

        InputStream inputStream = getClass()
                .getResourceAsStream("/models/en-token.bin");
        TokenizerModel model = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(model);
        String[] tokens = tokenizer.tokenize(sentence);

        return removeStopWords(tokens);
    }

    /**
     * Removes tokens which are special characters
     * example: []()/\,.-!@#$^&*;"'
     *
     * @param tokens
     * @return string array without special characters
     */
    protected String[] removeSpecialCharsAndTokenize(String[] tokens) {
        List<String> tokenList = new ArrayList<>(List.of(tokens));
        List<String> specialChars = List.of("[", "]", "(", ")", "/", "\\", ",", ".", "-", "!", "@", "#", "$", "^", "&", "*", ";", "\"", "'");
        tokenList.removeAll(specialChars);
        return tokenList.toArray(String[]::new);
    }


    /**
     * Removes eng stopwords
     * example: and, of, a
     *
     * @param tokens
     * @return string array without stopwords
     */
    protected String[] removeStopWords(String[] tokens) throws IOException {
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
