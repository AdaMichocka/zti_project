package com.zti_project.controller;

import com.zti_project.sparql.ResponseModel;
import com.zti_project.sparql.SparqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class ModelController {

    private final SparqlQuery sparqlQuery;

    public ModelController(SparqlQuery sparqlQuery) {
        this.sparqlQuery = sparqlQuery;
    }

    @PostMapping("/api/result")
    @ResponseBody
    public ResponseEntity<List<ResponseModel>> result(@RequestBody RequestModel requestModel) {
        String txt = requestModel.getTxt();

        if (StringUtils.isEmpty(txt)) {
            return ResponseEntity.notFound().build();
        }

        // TODO: Rozbijac txt od uzytkownika + zwracać listę ResponseModel
        List<String> extractedWords = Arrays.asList("Donald_Trump", "Adam_Małysz");

        List<ResponseModel> responseModelList = new ArrayList<>();
        for (var word : extractedWords) {
            responseModelList.add(sparqlQuery.createQuery(word, requestModel.getLang()));
        }

        return ResponseEntity.ok(responseModelList);
    }
}
