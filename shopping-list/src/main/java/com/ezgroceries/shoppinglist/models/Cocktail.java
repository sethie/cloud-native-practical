package com.ezgroceries.shoppinglist.models;

import java.util.List;
import java.util.UUID;

public class Cocktail {

    private UUID uuid;
    private String name;
    private String servedIn;
    private String instructions;
    private String imageUrl;
    private List<String> ingredients;

    public Cocktail() {}

    public Cocktail(UUID uuid, String name, String servedIn, String instructions, String imageUrl, List ingredients) {
        this.uuid = uuid;
        this.name = name;
        this.servedIn = servedIn;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServedIn() {
        return servedIn;
    }

    public void setServedIn(String servedIn) {
        this.servedIn = servedIn;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List ingredients) {
        this.ingredients = ingredients;
    }

    public static class CocktailIdClass {

        private UUID cocktailId;

        public CocktailIdClass() {}

        public CocktailIdClass(UUID cocktailId) {
            this.cocktailId = cocktailId;
        }

        public UUID getCocktailId() {
            return cocktailId;
        }

        public void setCocktailId(UUID cocktailId) {
            this.cocktailId = cocktailId;
        }
    }

}
