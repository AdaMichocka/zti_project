package com.zti_project;

import com.zti_project.sparql.LanguageEnum;
import com.zti_project.sparql.SparqlQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        //odpalenie algorytmu
        //1. zczytanie tekstu
        //2. tokenizacja ("ala ma kota!" -> ["ala", "ma", "kota", "!"]) -> openNLP
        //3. wyrzucenie stopwords (ma, a, i) i znaków , . !
        //4. parsery do dwuczłonowych nazw?
        // to wszystko zwracane jako lista

        // 5. zapytanie sparql na podstawie pojęć z listy
        //6. zapytanie do dbpedii
        // odpowiedź zapisywana jest w stringu

        SparqlQuery sparqlQuery = new SparqlQuery();
        sparqlQuery.createQuery("Google", LanguageEnum.EN);

    }

    @Bean
    public Docket get() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
}
