package com.zti_project.nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

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

    /**
     * Get NERs basing on trained models
     *
     * @param tokens
     * @return spans array
     * @throws Exception
     */
    public Span[] getAllNers(String[] tokens) throws Exception {
        List<Span> allSpans = new ArrayList<>();

        InputStream inputStreamPersonNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-person.bin");
        TokenNameFinderModel personModel = new TokenNameFinderModel(inputStreamPersonNameFinder);
        NameFinderME namePersonFinderME = new NameFinderME(personModel);

        InputStream inputStreamLocationNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-location.bin");
        TokenNameFinderModel locationModel = new TokenNameFinderModel(inputStreamLocationNameFinder);
        NameFinderME nameLocationFinderME = new NameFinderME(locationModel);

        InputStream inputStreamDateNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-date.bin");
        TokenNameFinderModel dateModel = new TokenNameFinderModel(inputStreamDateNameFinder);
        NameFinderME nameDateFinderME = new NameFinderME(dateModel);

        InputStream inputStreamMoneyNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-money.bin");
        TokenNameFinderModel moneyModel = new TokenNameFinderModel(inputStreamMoneyNameFinder);
        NameFinderME nameMoneyFinderME = new NameFinderME(moneyModel);

        InputStream inputStreamOrganizationNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-organization.bin");
        TokenNameFinderModel organizationModel = new TokenNameFinderModel(inputStreamOrganizationNameFinder);
        NameFinderME nameOrganizationFinderME = new NameFinderME(organizationModel);

        InputStream inputStreamPercentageNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-percentage.bin");
        TokenNameFinderModel percentageModel = new TokenNameFinderModel(inputStreamPercentageNameFinder);
        NameFinderME namePercentageFinderME = new NameFinderME(percentageModel);

        InputStream inputStreamTimeNameFinder = getClass()
                .getResourceAsStream("/models/en-ner-time.bin");
        TokenNameFinderModel timeModel = new TokenNameFinderModel(inputStreamTimeNameFinder);
        NameFinderME nameTimeFinderME = new NameFinderME(timeModel);

        allSpans.addAll(List.of(namePersonFinderME.find(tokens)));
        allSpans.addAll(List.of(nameLocationFinderME.find(tokens)));
        allSpans.addAll(List.of(nameDateFinderME.find(tokens)));
        allSpans.addAll(List.of(nameMoneyFinderME.find(tokens)));
        allSpans.addAll(List.of(nameOrganizationFinderME.find(tokens)));
        allSpans.addAll(List.of(namePercentageFinderME.find(tokens)));
        allSpans.addAll(List.of(nameTimeFinderME.find(tokens)));

        return allSpans.toArray(Span[]::new);
    }


    /**
     * Concat multiple words spans and add them to token list
     *
     * @param tokens
     * @param ners
     * @return string array with concatenated multiple words spans
     */
    public String[] add_toMultipleWordsSpan(String[] tokens, Span[] ners) {
        List<String> tokenList = new ArrayList<>(List.of(tokens));
        List<String> concatPersons = new ArrayList<>();
        List<String> tokensToRemove = new ArrayList<>();

        for (Span s : ners) {
            List<String> temp = new ArrayList<>();
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            temp.addAll(Arrays.asList(tokens).subList(s.getStart(), s.getEnd()));
            if (temp.size() > 1) {
                StringJoiner stringJoiner = new StringJoiner("_");
                for (String value : temp) {
                    tokensToRemove.add(value);
                    stringJoiner.add(value);
                }
                concatPersons.add(stringJoiner.toString());
                stringJoiner = null;
            }
        }
        tokenList.removeAll(tokensToRemove);
        tokenList.addAll(concatPersons);
        return tokenList.toArray(String[]::new);
    }


}
