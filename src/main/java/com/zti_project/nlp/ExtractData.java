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
    PartOfSpeech pos = new PartOfSpeech();

    public ExtractData() {
    }

    public List<String> extractData(String sentence) throws Exception {

        String[] tokens = tokenizer.tokenizeSentence(sentence);
        Span[] ners = ner.getAllNers(tokens);
        String[] spansWith_between = ner.add_toMultipleWordsSpan(tokens, ners);
        //pos

        return List.of(spansWith_between);
    }

    //TODO
    //rozpoznawanie czasownik/rzeczownik etc i wywalenie czasowników

}
