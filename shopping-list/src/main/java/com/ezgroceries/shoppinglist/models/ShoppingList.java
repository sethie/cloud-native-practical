package com.ezgroceries.shoppinglist.models;

import com.ezgroceries.shoppinglist.storage.entities.ShoppingListEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;

    public ShoppingList(ShoppingListEntity shoppingListEntity) {
        this.shoppingListId = shoppingListEntity.getId();
        this.name = shoppingListEntity.getName();
        this.ingredients = new ArrayList<>();
    }

    public ShoppingList(ShoppingListEntity shoppingListEntity, List<String> ingredients) {
        this.shoppingListId = shoppingListEntity.getId();
        this.name = shoppingListEntity.getName();
        this.ingredients = ingredients;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addIngredient(String ingredient) {
        if (!this.getIngredients().contains(ingredient)) {
            this.ingredients.add(ingredient);
        }
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }
}
