package com.example.drinkbeverageinfosystem.models;

public class Ingredients {
    private String name;
    private String alcoholContent;
    private String description;

    public Ingredients(String name, String alcoholContent, String description) {
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ingredient Name: " + name + ", Alcohol Content: " + alcoholContent + ", Description: " + description;
    }
}

