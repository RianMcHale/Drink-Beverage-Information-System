package com.example.drinkbeverageinfosystem.controller;
import com.example.drinkbeverageinfosystem.models.*;
import com.example.drinkbeverageinfosystem.models.Recipes;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DrinkController {

    @FXML private TextField drinkName, countryOrigin;
    @FXML private TextArea drinkDescription;
    @FXML private Button addDrink, deleteDrink, resetDrinks;
    @FXML private ListView<String> drinkList;

    @FXML private TextField ingredientName, alcoholContent;
    @FXML private TextArea ingredientDescription;
    @FXML private Button addIngredient, deleteIngredient, resetIngredients;
    @FXML private ListView<String> ingredientList;

    @FXML private TextField recipeName;
    @FXML private Button addRecipe, addIngredientRecipe, addDrinkRecipe, resetSelectedItems, deleteRecipe, resetRecipes;
    @FXML private TextArea instructionsArea;
    @FXML private ListView<String> availableIngredientList, availableDrinksList, selectedDrink, selectedIngredients, savedRecipesList;

    @FXML private TextField drinkSearchBar;
    @FXML private ListView<String> drinkSearchResults;

    @FXML private TextField recipeSearchBar;
    @FXML private ListView<String> recipeSearchResults;

    @FXML private TextField ingredientSearchBar;
    @FXML private ListView<String> ingredientsSearchResults;




    private final HashTable<Drinks> drinksTable = new HashTable<>();
    private final HashTable<Ingredients> ingredientsTable = new HashTable<>();
    private final HashTable<Recipes> recipesTable = new HashTable<>();



    //  -------------- DRINKS SECTION -------------- \\

    @FXML
    private void addDrink() {
        String name = drinkName.getText().trim();
        String country = countryOrigin.getText().trim();
        String description = drinkDescription.getText().trim(); // gathers info from text fields

        if (name.isEmpty() || country.isEmpty() || description.isEmpty()) {
            System.out.println("Please fill in all the fields to add a drink.");
            return; // prints the above if any of the fields are blank
        }

        Drinks newDrink = new Drinks(name, country, description);  // creates new object with input of text fields
        drinksTable.put(name, newDrink); // add to drinksTable (hash table for drinks)

        drinkList.getItems().add(newDrink.toString()); // add to listview
        availableDrinksList.getItems().add(newDrink.toString()); // add to list view in recipes

        System.out.println("Drink added: " + newDrink);
    }


    @FXML
    private void deleteDrink() {
        String selectedDrink = drinkList.getSelectionModel().getSelectedItem(); // gets selected drink from ListView
        if (selectedDrink == null) {
            System.out.println("Please select a drink to delete.");
            return; // prints out the above if no drink is selected
        }

        // key = unique identifier (label, nametag)
        String keyToDelete = null; // holds the "key" of the drink thats to be deleted
        for (String key : drinksTable.getAllKeys()) { // searches through all keys in the hashtable
            Drinks drink = drinksTable.get(key); // gets the drink using the key
            if (drink.toString().equals(selectedDrink)) { // checks if it matches the selected drink
                keyToDelete = key; // saves the matching key
                break; // stops searching after finding the key
            }
        }

        if (keyToDelete != null) { // if matching key is found then remove from both hashtable and listview
            drinksTable.remove(keyToDelete); // removes from hashtable
            drinkList.getItems().remove(selectedDrink); // removes drink from listview
            availableDrinksList.getItems().remove(selectedDrink);
            System.out.println("Drink removed: " + selectedDrink); // output to confirm deletion
        } else {
            System.out.println("Drink not found."); // if theres no matching key then this outputs
        }
    }


    @FXML
    private void resetDrinks() {
        drinkName.clear();
        countryOrigin.clear();
        drinkDescription.clear(); // clears all text fields

        drinkList.getItems().clear(); // clears list view
        availableDrinksList.getItems().clear(); // clears list view in recipes

        System.out.println("Drink input fields and drinks list reset");
    }


    // -------------- INGREDIENTS SECTION -------------- \\

    @FXML
    private void addIngredient() {
        String name = ingredientName.getText().trim();
        String alcoholLevel = alcoholContent.getText().trim();
        String description = ingredientDescription.getText().trim(); // gets all text inputs from text fields

        if (name.isEmpty() || alcoholLevel.isEmpty() || description.isEmpty()) {
            System.out.println("Please fill in all the fields to add an ingredient.");
            return;
        }

        Ingredients newIngredient = new Ingredients(name, alcoholLevel, description);
        ingredientsTable.put(name, newIngredient);

        ingredientList.getItems().add(newIngredient.toString());
        availableIngredientList.getItems().add(newIngredient.toString());

        System.out.println("Ingredient added: " + newIngredient);
    } // same as add drinks

    @FXML
    private void deleteIngredient() {     // identical to delete drink, just altered the naming
        String selectedIngredient = ingredientList.getSelectionModel().getSelectedItem();
        if (selectedIngredient == null) {
            System.out.println("Please select an ingredient to delete.");
            return;
        }


        String keyToDelete = null;
        for (String key : ingredientsTable.getAllKeys()) {
            Ingredients ingredient = ingredientsTable.get(key);
            if (ingredient.toString().equals(selectedIngredient)) {
                keyToDelete = key;
                break;
            }
        }

        if (keyToDelete != null) {
            ingredientsTable.remove(keyToDelete); //
            ingredientList.getItems().remove(selectedIngredient);
            availableIngredientList.getItems().remove(selectedIngredient);
            System.out.println("Ingredient removed: " + selectedIngredient);
        } else {
            System.out.println("Ingredient not found.");
        }


    }


    @FXML
    private void resetIngredients() {
        ingredientName.clear();
        alcoholContent.clear();
        ingredientDescription.clear();

        ingredientList.getItems().clear();
        availableIngredientList.getItems().clear();

        System.out.println("Ingredient input fields and ingredients list reset");
    }



    // -------------- RECIPE SECTION -------------- \\


    @FXML
    private void addRecipe() {
        String recipeNameText = recipeName.getText().trim();
        String instructionsText = instructionsArea.getText().trim(); // gets the inputs from text fields

        String ingredientsText = ""; // creates a string to store the ingredients
        for (int i = 0; i < selectedIngredients.getItems().size(); i++) { // for loop to go through all ingredients in the list
            if (i > 0) {
                ingredientsText += ", "; // adds a comma between each ingredient
            }
            ingredientsText += selectedIngredients.getItems().get(i);  // adds current ingredient to the ingredients text
        }

        if (recipeNameText.isEmpty() || instructionsText.isEmpty()) {
            System.out.println("Please provide a recipe name and instructions.");
            return;
        }
        if (selectedDrink.getItems().isEmpty()) {
            System.out.println("Please ensure there is a drink selected.");
            return;
        }
        if (selectedIngredients.getItems().isEmpty()) {
            System.out.println("Please ensure there is at least one ingredient selected.");
            return;
        }
        if (recipesTable.get(recipeNameText) != null) {
            System.out.println("A recipe with this name already exists. Please choose a unique name.");
            return;
        } // all just checks to see if text fields are filled, a drink is selected, ingredient selected, and recipe name is unique.

        String selectedDrinkText = selectedDrink.getItems().get(0); // selects only ONE drink to add to the recipe -> must be below the "if (selectedDrink.getItems().isEmpty())" check or else it causes an error

        Recipes newRecipe = new Recipes(recipeNameText, selectedDrinkText, ingredientsText, instructionsText); // new recipe object
        recipesTable.put(recipeNameText, newRecipe); // add to recipe table

        savedRecipesList.getItems().add(newRecipe.toString()); // show new recipe in the list view

        recipeName.clear();
        instructionsArea.clear();
        selectedDrink.getItems().clear();
        selectedIngredients.getItems().clear(); // clears the fields

        System.out.println("Recipe added successfully: " + newRecipe);
    }

    @FXML
    private void addDrinkRecipe() {
        String selectedDrinkRecipe = availableDrinksList.getSelectionModel().getSelectedItem(); // get the selected drink in the drinks listview
        if (selectedDrinkRecipe == null) {
            System.out.println("Please select a drink to add.");
            return; // print the above if button pressed and no drink selected
        }

        selectedDrink.getItems().clear(); // clears the selected drink list view to ensure only one drink can be selected at a time
        selectedDrink.getItems().add(selectedDrinkRecipe); // adds selected drink to the selectedDrink list view
        System.out.println("Drink added to recipe: " + selectedDrinkRecipe);
    }


    @FXML
    private void addIngredientRecipe() {
        String selectedIngredientRecipe = availableIngredientList.getSelectionModel().getSelectedItem(); // gets selected ingredient from ingredients list view
        if (selectedIngredientRecipe == null) {
            System.out.println("Please select ingredients to add.");
            return;
        } // print above if button pressed and no drink selected

        if (selectedIngredients.getItems().contains(selectedIngredientRecipe)) {
            System.out.println("The ingredient is already added to the recipe.");
            return;
        } // print above if an already added ingredient is attempted to be added again

        selectedIngredients.getItems().add(selectedIngredientRecipe); // adds selected ingredient to selectedIngredients listview
        System.out.println("Ingredient added to recipe: " + selectedIngredientRecipe);
    }

    @FXML
    private void resetSelectedItems() {
        selectedIngredients.getItems().clear();
        selectedDrink.getItems().clear(); // clears both selected ingredients and drinks

        System.out.println("Selected items have been reset");
    }

    @FXML
    private void deleteRecipe() {  // identical to delete drink and ingredients, just altered the naming
        String selectedRecipe = savedRecipesList.getSelectionModel().getSelectedItem();

        if (selectedRecipe == null) {
            System.out.println("Please select a recipe to delete.");
            return;
        }

        String keyToDelete = null;
        for (String key : recipesTable.getAllKeys()) {
            Recipes recipe = recipesTable.get(key);
            if (recipe.toString().equals(selectedRecipe)) {
                keyToDelete = key;
                break;
            }
        }

        if (keyToDelete != null) {
            recipesTable.remove(keyToDelete);
            savedRecipesList.getItems().remove(selectedRecipe);
            System.out.println("Recipe removed: " + selectedRecipe);
        } else {
            System.out.println("Recipe not found.");
        }
    }


    @FXML
    private void resetRecipes() {
        savedRecipesList.getItems().clear();
        System.out.println("All recipes cleared.");
    }


    // -------------- SEARCH FUNCTION -------------- \\


    @FXML
    private void searchDrink() {
        drinkSearchResults.getItems().clear(); // clear previous results

        String searchBox = drinkSearchBar.getText().trim(); // gets text entered

        if (searchBox.isEmpty()) {
            System.out.println("Please enter a drink name to search.");
            return;
        } // check to see if search box is empty

        String[] allKeys = drinksTable.getAllKeys(); // gets all the keys (drink names) from the hash table
        boolean found = false; // checks if any matching drinks are found

        for (String key : allKeys) { // loops through all the drinks
            if (key.toLowerCase().contains(searchBox.toLowerCase())) { // checks to see if drink name CONTAINS the text in the search bar
                Drinks drink = drinksTable.get(key); // retrieve drink found with matching drink name in search bar
                if (drink != null) {
                    drinkSearchResults.getItems().add(drink.toString()); // ensures that the drink that contains the text in the search bar is added to the listview
                    found = true; // then sets flag to... true.
                }
            }
        }

        if (!found) {
            System.out.println("No drinks match the search."); // if no drinks were found with text in the search bar then print in console
            drinkSearchResults.getItems().add("No drinks found."); // if no drinks were found with text in the search bar then print this in the list view
        }
    }

    @FXML
    private void searchIngredients() { // copy and paste from searchDrinks
        ingredientsSearchResults.getItems().clear();

        String searchBox = ingredientSearchBar.getText().trim();

        if (searchBox.isEmpty()) {
            System.out.println("Please enter an ingredient name to search.");
            return;
        }

        String[] allKeys = ingredientsTable.getAllKeys();
        boolean found = false;

        for (String key : allKeys) {
            if (key.toLowerCase().contains(searchBox.toLowerCase())) { // Partial match
                Ingredients ingredient = ingredientsTable.get(key);
                if (ingredient != null) {
                    ingredientsSearchResults.getItems().add(ingredient.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No ingredients match the search.");
            ingredientsSearchResults.getItems().add("No ingredients found.");
        }
    }

    @FXML
    private void searchRecipes() { // copy and paste from searchDrinks & searchIngredients
        recipeSearchResults.getItems().clear();

        String searchBox = recipeSearchBar.getText().trim();

        if (searchBox.isEmpty()) {
            System.out.println("Please enter a recipe name to search.");
            return;
        }

        String[] allKeys = recipesTable.getAllKeys();
        boolean found = false;

        for (String key : allKeys) {
            if (key.toLowerCase().contains(searchBox.toLowerCase())) { // Partial match
                Recipes recipe = recipesTable.get(key);
                if (recipe != null) {
                    recipeSearchResults.getItems().add(recipe.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No recipes match the search.");
            recipeSearchResults.getItems().add("No recipes found.");
        }
    }
}
