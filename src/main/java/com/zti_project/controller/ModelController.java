package com.zti_project.controller;

import com.zti_project.sparql.LanguageEnum;
import com.zti_project.sparql.ResponseModel;
import com.zti_project.sparql.SparqlQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ModelController {

    private final SparqlQuery sparqlQuery;

    public ModelController(SparqlQuery sparqlQuery) {
        this.sparqlQuery = sparqlQuery;
    }

    @PostMapping("/api/result")
    @ResponseBody
    public List<ResponseModel> result(@RequestBody String txt) {
        return sparqlQuery.createQuery(txt, LanguageEnum.EN);
    }
}
