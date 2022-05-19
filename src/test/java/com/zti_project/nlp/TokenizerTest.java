package com.zti_project.nlp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Captor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


public class TokenizerTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    public void shouldTokenizeSentence() {
        String sentence = "Google CEO Sundar Pichai responded today to the firing of employee James Damore over his controversial memo on workplace diversity.";
        List<String> res = new ArrayList<>();

        res = tokenizer.tokenizeSentence(sentence);

        assertEquals(21, res.size());
    }

}
