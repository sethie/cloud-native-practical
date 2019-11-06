package com.ezgroceries.shoppinglist.models;

import com.ezgroceries.shoppinglist.storage.entities.CocktailEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cocktail {

    private UUID cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String imageUrl;
    private List<String> ingredients;

    public Cocktail() {}

    public Cocktail(CocktailEntity cocktailEntity) {
        this.cocktailId = cocktailEntity.getId();
        this.name = cocktailEntity.getName();
        this.ingredients = new ArrayList<>();
    }

    public Cocktail(UUID cocktailId, String name, String glass, String instructions, String imageUrl, List<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID uuid) {
        this.cocktailId = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
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
