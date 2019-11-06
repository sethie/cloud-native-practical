package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.models.ShoppingList;

import java.util.List;

import com.ezgroceries.shoppinglist.storage.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping(value = "/{shoppingListId}/cocktails")
//    public List<CocktailIdClass> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<CocktailIdClass> cocktailIds) {
//        for (CocktailIdClass cocktailId: cocktailIds) {
//            for (Cocktail cocktail: CocktailController.getDummyCocktails()) {
//                if (cocktail.getCocktailId().equals(cocktailId.getCocktailId())) {
//                    for (String ingredient: cocktail.getIngredients()) {
//                        dummyStephanieShoppinglist.addIngredient(ingredient);
//                    }
//                }
//            }
//        }
//        return cocktailIds;
//    }

    @GetMapping()
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.getAll();
    }

//    @GetMapping(value = "/{shoppingListId}")
//    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId) {
//        // TODO Filter shopping list based on ID
//        return dummyStephanieShoppinglist;
//    }
}
