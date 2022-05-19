package com.zti_project.nlp;


import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Ada
 */
public class Tokenizer {

    public Tokenizer() {
    }

    /**
     * Split sentence into array of tokens
     *
     * @param sentence
     * @return String[]
     */
    public List<String> tokenizeSentence(String sentence) {
        InputStream inputStream = getClass()
                .getResourceAsStream("/models/en-token.bin");
        TokenizerModel model = null;
        try {
            model = new TokenizerModel(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TokenizerME tokenizer = new TokenizerME(model);

        return List.of(tokenizer.tokenize(sentence));
    }


}
