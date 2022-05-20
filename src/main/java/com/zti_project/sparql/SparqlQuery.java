package com.zti_project.sparql;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.query.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SparqlQuery {
    private static final String ENDPOINT = "http://dbpedia.org/sparql";

    /**
     * Get sparql with common prefixes
     * @return sparql with common prefixes
     */
    private ParameterizedSparqlString getSparqlWithPrefixes() {
        ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString();

        parameterizedSparqlString.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        parameterizedSparqlString.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
        parameterizedSparqlString.setNsPrefix("res", "http://dbpedia.org/resource/");
        parameterizedSparqlString.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

        return parameterizedSparqlString;
    }

    /**
     * create query
     * @param resource resource to search for e.g. Donald_Trump
     * @param lang label language
     *
     * @return list of {@link ResponseModel}
     */
    public List<ResponseModel> createQuery(String resource, LanguageEnum lang) {

        ParameterizedSparqlString sparqlString = getSparqlWithPrefixes();

        sparqlString.setCommandText(" SELECT DISTINCT * WHERE {\n" +
                "res:" + resource + " rdf:type ?type.\n" +
                "?type rdfs:label ?label.\n" +
                "FILTER (langMatches(lang(?label),\"" + lang.name() + "\") && REGEX ( STR (?type), \"http://dbpedia.org/ontology/\") )\n" +
                "}\n" +
                "LIMIT 10");

        QueryExecution exec = QueryExecution.service(ENDPOINT).query(sparqlString.asQuery()).build();
        ResultSet results = exec.execSelect();

        return retrieveFromResult(results, resource, lang);
    }

    /**
     *
     * @param resultSet resultSet from query
     * @param resource resource to search for e.g. Donald_Trump
     * @param language {@link LanguageEnum}
     * @return list of {@link ResponseModel}
     */
    private List<ResponseModel> retrieveFromResult(ResultSet resultSet, String resource, LanguageEnum language) {
        List<ResponseModel> modelList = new ArrayList<>();

        while (resultSet.hasNext()) {
            ResponseModel responseModel = new ResponseModel();
            var next = resultSet.next();
            responseModel.setTypeUri(retrieveProperty(next,"type"));
            responseModel.setLabel(retrieveProperty(next,"label")
                    .replace(language.getLang(), StringUtils.EMPTY));
            responseModel.setLanguage(language);
            responseModel.setResource(resource);
            responseModel.setResourceUri(MessageFormat.format("http://dbpedia.org/resource/{0}", resource));
            modelList.add(responseModel);
        }

        return modelList;
    }

    /**
     * Retrieve property by key
     * @param querySolution query
     * @param property key
     * @return value if exists
     */
    private String retrieveProperty(QuerySolution querySolution, String property) {
        if (querySolution.contains(property)) {
            return querySolution.get(property).toString();
        }
        return StringUtils.EMPTY;
    }
}
