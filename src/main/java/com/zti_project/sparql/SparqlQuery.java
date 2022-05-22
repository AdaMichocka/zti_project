package com.zti_project.sparql;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SparqlQuery {
    private static final String ENDPOINT = "http://dbpedia.org/sparql";

    /**
     * Get sparql with common prefixes
     *
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
     *
     * @param resource resource to search for e.g. Donald_Trump
     * @param lang     label language
     * @return {@link ResponseModel}
     */
    public ResponseModel createQuery(String resource, LanguageEnum lang) {
        resource = resource.substring(0,1).toUpperCase(Locale.ROOT) + resource.substring(1).toLowerCase(Locale.ROOT);

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
     * @param resultSet resultSet from query
     * @param resource  resource to search for e.g. Donald_Trump
     * @param language  {@link LanguageEnum}
     * @return {@link ResponseModel}
     */
    private ResponseModel retrieveFromResult(ResultSet resultSet, String resource, LanguageEnum language) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setLanguage(language);
        responseModel.setResource(resource);
        responseModel.setResourceUri(MessageFormat.format("http://dbpedia.org/resource/{0}", resource));
        List<TypeModel> typeModels = new ArrayList<>();

        while (resultSet.hasNext()) {
            var next = resultSet.next();
            TypeModel typeModel = new TypeModel();
            typeModel.setTypeUri(retrieveProperty(next, "type"));
            typeModel.setLabel(retrieveProperty(next, "label")
                    .replace(language.getLang(), StringUtils.EMPTY));

            typeModels.add(typeModel);
        }

        responseModel.setTypeModel(typeModels);
        return responseModel;
    }

    /**
     * Retrieve property by key
     *
     * @param querySolution query
     * @param property      key
     * @return value if exists
     */
    private String retrieveProperty(QuerySolution querySolution, String property) {
        if (querySolution.contains(property)) {
            return querySolution.get(property).toString();
        }
        return StringUtils.EMPTY;
    }
}
