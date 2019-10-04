package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.models.Cocktail.CocktailIdClass;
import com.ezgroceries.shoppinglist.models.ShoppingList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class ShoppingListController {
    private ShoppingList dummyStephanieShoppinglist = new ShoppingList(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"), "Stephanie's birthday");
    private ShoppingList dummyMyShoppinglist = new ShoppingList(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"), "My birthday");
    private List<ShoppingList> dummyShoppingLists = new ArrayList<>(Arrays.asList(dummyStephanieShoppinglist, dummyMyShoppinglist));

    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList create(@RequestParam String name) {
        return dummyStephanieShoppinglist;
    }

    // TODO: JSON VIEW?
    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    public List<CocktailIdClass> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<CocktailIdClass> cocktailIds) {
        for (CocktailIdClass cocktailId: cocktailIds) {
            for (Cocktail cocktail: CocktailController.getDummyCocktails()) {
                if (cocktail.getCocktailId().equals(cocktailId.getCocktailId())) {
                    for (String ingredient: cocktail.getIngredients()) {
                        dummyStephanieShoppinglist.addIngredient(ingredient);
                    }
                }
            }
        }
        return cocktailIds;
    }

    @GetMapping(value = "/shopping-lists")
    public List<ShoppingList> getAllShoppingLists() {
        return dummyShoppingLists;
    }

    @GetMapping(value = "/shopping-lists/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId) {
        // TODO Filter shopping list based on ID
        return dummyStephanieShoppinglist;
    }
}
