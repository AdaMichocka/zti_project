package com.zti_project.nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * The goal of NER is to find named entities like people, locations, organizations and other named things in a given text.
 *
 * @author Ada
 */
public class NamedEntityRecognizer {


    public NamedEntityRecognizer() {
    }

    public List<Span> getNamedEntities(String[] tokens) throws IOException {

        InputStream inputStreamNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(
                inputStreamNameFinder);
        NameFinderME nameFinderME = new NameFinderME(model);
        return Arrays.asList(nameFinderME.find(tokens));
    }

}
