package com.zti_project.controller;

import com.zti_project.sparql.LanguageEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestModel {
    private String txt;
    private LanguageEnum lang;
}
