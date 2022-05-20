package com.zti_project.sparql;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LanguageEnum {
    PL("@pl"),
    EN("@en");

    private String lang;
}
