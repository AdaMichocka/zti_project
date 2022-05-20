package com.zti_project.sparql;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseModel {
    private String typeUri;
    private String label;
    private String resource;
    private String resourceUri;
    private LanguageEnum language;
}
