package com.zti_project.nlp;

import opennlp.tools.util.Span;

import java.util.Arrays;
import java.util.List;

public class TestClass {
    public static void main(String[] args) throws Exception {

        String sentence = "Google CEO John Smith responded today to May 5 2022 and 50% the firing of employee James Damore over his 10.10.2011 controversial memo on workplace diversity in New Zealand  at 11:37:00.";
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

        System.out.println("---------------------");

        PartOfSpeech pos = new PartOfSpeech();
        List<String> tokensReadyToQuer = pos.identifyAndDeletePartsOfSpeech(spanswith_);
        System.out.println(tokensReadyToQuer);

    }
}
