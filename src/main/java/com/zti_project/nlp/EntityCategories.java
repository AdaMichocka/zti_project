package com.zti_project.nlp;

/**
 * Entity categories
 *
 * @author Ada
 */
public enum EntityCategories {

    DATE("Date"),
    LOCATION("Location"),
    MONEY("Money"),
    ORGANIZATION("Organization"),
    PERCENTAGE("Percentage"),
    PERSON("Person"),
    TIME("Time"),
    TITLE("Title");

    private String category = "";

    EntityCategories(String category) {
        this.category = category;
    }

    public String getName() {
        return category;
    }
}
