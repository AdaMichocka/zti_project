package com.zti_project.nlp;

import opennlp.tools.util.Span;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) throws Exception {

        String sentence = "Google CEO John Smith responded today to the firing of [ ] ( ) / \\ , . - ! @ # $ ^ & * ; \" ' employee James Damore over his controversial memo on workplace diversity in London.";
        Tokenizer tokenizer = new Tokenizer();
        String[] tokens = tokenizer.tokenizeSentence(sentence);
        System.out.println(Arrays.asList(tokens));

        System.out.println("---------------------");

        NamedEntityRecognizer ner = new NamedEntityRecognizer();
        Span[] personNer = ner.getAllNers(tokens);

        for (Span s : personNer) {
            System.out.println(s.toString() + "  " + tokens[s.getStart()]);
        }

        String[] spanswith_ = ner.add_toMultipleWordsSpan(tokens, personNer);
        System.out.println(Arrays.asList(spanswith_));


    }
}
