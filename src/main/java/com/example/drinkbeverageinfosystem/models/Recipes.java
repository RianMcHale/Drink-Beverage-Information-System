package com.example.drinkbeverageinfosystem.models;

public class Recipes {
    private String name;
    private String drinks;
    private String ingredients;
    private String instructions;

    public Recipes(String name, String drinksName, String ingredientsName, String instructions) {
        this.name = name;
        this.ingredients = ingredientsName;
        this.drinks = drinksName;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDrinks() {
        return drinks;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String drinksToString() {
        return drinks == null ? "No Drinks" : String.join(", ", drinks);
    }

    public String ingredientsToString() {
        return ingredients == null ? "No Ingredients" : String.join(", ", ingredients);
    }


    @Override
    public String toString() {
        return "Recipe Name: " + name + "\n" +
                "Drinks: " + drinksToString() + "\n" +
                "Ingredients: " + ingredientsToString() + "\n" +
                "Instructions: " + instructions;
    }
}