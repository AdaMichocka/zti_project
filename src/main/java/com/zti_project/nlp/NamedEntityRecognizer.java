package com.zti_project.nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * The goal of NER is to find named entities like people, locations, organizations and other named things in a given text.
 *
 * @author Ada
 */
public class NamedEntityRecognizer {


    public NamedEntityRecognizer() {
    }

    public Span[] getPersonNers(String[] tokens) throws Exception {

        InputStream inputStreamNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        NameFinderME nameFinderME = new NameFinderME(model);
        return nameFinderME.find(tokens);
    }


    public String[] add_toMultipleWrodSpan(String[] tokens, Span[] ners) {
        String[] result;
        List<String> tempList = new ArrayList<>();

        for (Span s : ners) {
            List<String> temp = new ArrayList<>();
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for (int index = s.getStart(); index < s.getEnd(); index++) {
                temp.add(tokens[index]);
                System.out.print(tokens[index] + " ");
            }
            if (temp.size()>1){
                StringJoiner stringJoiner = new StringJoiner("_");
                for (int i = 0; i<temp.size();i++){
                    stringJoiner.add(temp.get(i));
                }
                tempList.add(stringJoiner.toString());
                stringJoiner = null;
            }


        }

        return null;
    }


}
