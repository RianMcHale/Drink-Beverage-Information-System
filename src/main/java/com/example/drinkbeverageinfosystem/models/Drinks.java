package com.example.drinkbeverageinfosystem.models;

public class Drinks {
    private String name;
    private String countryOrigin;
    private String description;

    public Drinks(String name, String countryOrigin, String description) {
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Origin: " + countryOrigin + ", Description: " + description;
    }
}
