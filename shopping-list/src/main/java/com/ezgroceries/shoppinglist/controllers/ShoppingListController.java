package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.models.ShoppingList;

import java.util.List;
import java.util.UUID;

import com.ezgroceries.shoppinglist.storage.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {
    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList create(@RequestParam String name) {
        return shoppingListService.create(name);
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    public List<Cocktail.CocktailIdClass> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<Cocktail.CocktailIdClass> cocktailIds) {
        shoppingListService.addCocktailsToShoppingList(shoppingListId, cocktailIds);
        return cocktailIds;
    }

    @GetMapping()
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.getAll();
    }

    @GetMapping(value = "/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId) {
        return shoppingListService.getShoppingList(shoppingListId);
    }
}
