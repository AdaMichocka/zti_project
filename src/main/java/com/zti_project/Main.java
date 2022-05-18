package com.zti_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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


    }
}
