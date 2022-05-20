package com.zti_project.nlp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ada
 */
public class TokenizerTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    public void shouldTokenizeSentence() throws IOException {
        String sentence = "Google CEO Sundar Pichai responded today to the firing of employee James Damore over his controversial memo on workplace diversity.";

        String[] res = tokenizer.tokenizeSentence(sentence);

        assertEquals(21, res.length);
    }

}
