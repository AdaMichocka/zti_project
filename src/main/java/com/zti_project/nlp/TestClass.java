package com.zti_project.nlp;

import opennlp.tools.util.Span;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestClass {
    public static void main(String[] args) throws Exception {
        //expected result:
        //"Sundar Pichai"
        //"James Damore"
        //"Google"
        //CEO
        //employee

        String sentence = "Google CEO John Smith responded today to the firing of [ ] ( ) / \\ , . - ! @ # $ ^ & * ; \" ' employee James Damore over his controversial memo on workplace diversity.";
        Tokenizer tokenizer = new Tokenizer();
        String[] tokens = tokenizer.tokenizeSentence(sentence);

        System.out.println(Arrays.asList(tokens));

        System.out.println("---------------------");

        NamedEntityRecognizer ner = new NamedEntityRecognizer();
        Span[] personNer = ner.getPersonNers(tokens);

        System.out.println(Arrays.asList(personNer));
    }
}
