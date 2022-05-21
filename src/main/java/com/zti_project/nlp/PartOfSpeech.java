package com.zti_project.nlp;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A part-of-speech (POS) identifies the type of a word
 *
 * @author Ada
 */
public class PartOfSpeech {

    public PartOfSpeech() {
    }


    /**
     * identifies and deletes parts of speech
     * e.g. verbs, adjectives, adverbs
     *
     * @param tokens
     * @return list of tokens ready to query
     * @throws IOException
     */
    public List<String> identifyAndDeletePartsOfSpeech(String[] tokens) throws IOException {

        List<String> listOfPosToDelete = List.of("JJ", "JJR", "JJS", "RB", "RBR", "RBS", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ");

        InputStream inputStreamPOSTagger = getClass()
                .getResourceAsStream("/models/en-pos-maxent.bin");
        POSModel posModel = new POSModel(inputStreamPOSTagger);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);

        Map<String, String> mapOfTokensAndTags = new HashMap<>();
        for (int i = 0; i < tokens.length; i++) {
            mapOfTokensAndTags.put(tokens[i], tags[i]);
        }
        System.out.println(mapOfTokensAndTags.toString());

        for (String tag : listOfPosToDelete) {
            if (mapOfTokensAndTags.containsValue(tag)) {
                mapOfTokensAndTags.values().remove(tag);
            }
        }

        return new ArrayList<>(mapOfTokensAndTags.keySet());
    }


}
