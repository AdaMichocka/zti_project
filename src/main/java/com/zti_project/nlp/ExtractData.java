package com.zti_project.nlp;

import opennlp.tools.util.Span;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracting and processing of data from given sentence.
 *
 * @author Ada
 */
public class ExtractData {

    Tokenizer tokenizer = new Tokenizer();
    NamedEntityRecognizer ner = new NamedEntityRecognizer();

    public ExtractData() {
    }

    public List<String> extractData(String sentence) throws IOException {
        List<String> resultList = new ArrayList<>();

        String[] tokens = tokenizer.tokenizeSentence(sentence);
        //ner

        return resultList;
    }

    //TODO
    //rozpoznawanie czasownik/rzeczownik etc i wywalenie czasowników
    //kategorie



}
