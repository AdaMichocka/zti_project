package com.zti_project.nlp;

import opennlp.tools.util.Span;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Extracting and processing of data from given sentence.
 *
 * @author Ada
 */
@Service
public class ExtractData {

    Tokenizer tokenizer = new Tokenizer();
    NamedEntityRecognizer ner = new NamedEntityRecognizer();
    PartOfSpeech pos = new PartOfSpeech();

    public ExtractData() {
    }

    /**
     * Extracting data method:
     * 1. tokenizes sentence
     * 2. finds NERs - categories
     * 3. concats multiple words spans e.g. John Smith -> John_Smith
     * 4. removes duplicates
     * 5. removes special characters
     * 6. detect and delete part of speech e.g. verbs
     *
     * @param sentence
     * @return list of tokens
     * @throws Exception
     */
    public List<String> extractData(String sentence) throws Exception {

        String[] tokens = tokenizer.tokenizeSentence(sentence);

        Span[] ners = ner.getAllNers(tokens);
        String[] spansWith_between = ner.add_toMultipleWordsSpan(tokens, ners);
        String[] tokensWithoutDuplicates = removeDuplicated(spansWith_between);
        String[] tokensWithousSpecialChars = tokenizer.removeSpecialCharsAndTokenize(tokensWithoutDuplicates);
        List<String> tokensReadyToQuer = pos.identifyAndDeletePartsOfSpeech(tokensWithousSpecialChars);

        return tokensReadyToQuer;
    }

    /**
     * removes duplicates from list
     *
     * @param tokens
     * @return list without duplicates
     */
    private String[] removeDuplicated(String[] tokens) {
        List<String> tokenList = new ArrayList<>(List.of(tokens));
        tokenList.stream().distinct().collect(Collectors.toList());
        return tokenList.toArray(String[]::new);
    }

}
