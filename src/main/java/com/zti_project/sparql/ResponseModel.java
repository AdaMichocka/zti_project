package com.zti_project.sparql;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseModel {
    private String resource;
    private String resourceUri;
    private List<TypeModel> typeModel = new ArrayList<>();
    private LanguageEnum language;
}

