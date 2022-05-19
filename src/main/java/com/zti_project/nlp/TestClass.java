package com.zti_project.nlp;

import java.util.Arrays;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        String sentence = "Google CEO Sundar Pichai responded today to the firing of employee James Damore over his controversial memo on workplace diversity.";
        Tokenizer tokenizer = new Tokenizer();
        List<String> tokens = tokenizer.tokenizeSentence(sentence);

        System.out.println(tokens);
        System.out.println(tokens.size());
    }
}
