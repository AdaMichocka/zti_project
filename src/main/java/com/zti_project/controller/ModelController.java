package com.zti_project.controller;

import com.zti_project.nlp.ExtractData;
import com.zti_project.sparql.ResponseModel;
import com.zti_project.sparql.SparqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ModelController {

    private final SparqlQuery sparqlQuery;
    private final ExtractData extractData;

    public ModelController(SparqlQuery sparqlQuery, ExtractData extractData) {
        this.sparqlQuery = sparqlQuery;
        this.extractData = extractData;
    }

    @PostMapping("/api/result")
    @ResponseBody
    public ResponseEntity<List<ResponseModel>> result(@RequestBody RequestModel requestModel) throws Exception {
        String txt = requestModel.getTxt();

        if (StringUtils.isEmpty(txt)) {
            return ResponseEntity.notFound().build();
        }

        List<String> extractedWords = extractData.extractData(txt);

        List<ResponseModel> responseModelList = new ArrayList<>();
        for (var word : extractedWords) {
            ResponseModel model = sparqlQuery.createQuery(word, requestModel.getLang());
            if (!model.getTypeModel().isEmpty())
                responseModelList.add(model);
        }

        return ResponseEntity.ok(responseModelList);
    }
}
