package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.feignclients.CocktailDB;
import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.models.cocktaildb.CocktailDBResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ezgroceries.shoppinglist.storage.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private CocktailDB cocktailDB;
    private List<Cocktail> cocktails;
    private CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailDB cocktailDB, CocktailService cocktailService) {
        this.cocktailDB = cocktailDB;
        this.cocktails = new ArrayList<>();
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> get(@RequestParam String search) {
        System.out.println("testing......");
        cocktailService.storeCocktail("something boyyyy");
        CocktailDBResponse cocktailDBResponse = this.cocktailDB.searchCocktails(search);
        if (cocktailDBResponse.getDrinks() != null && !cocktailDBResponse.getDrinks().isEmpty()) {
            for(CocktailDBResponse.Drink drink: cocktailDBResponse.getDrinks()) {
                cocktails.add(new Cocktail(UUID.randomUUID(), drink.getStrDrink(),
                        drink.getStrGlass(), drink.getStrInstructions(), drink.getStrDrinkThumb(), convertIngredientsToList(drink)));
            }
        }
        return cocktails;
    }

    private List<String> convertIngredientsToList(CocktailDBResponse.Drink drink) {
        ArrayList<String> ingredients = new ArrayList<>();
        addToListIfNotEmpty(drink.getStrIngredient1(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient2(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient3(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient4(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient5(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient6(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient7(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient8(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient9(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient10(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient11(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient12(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient13(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient14(), ingredients);
        addToListIfNotEmpty(drink.getStrIngredient15(), ingredients);

        return ingredients;
    }

    private void addToListIfNotEmpty(String item, List list) {
        if (item != null && !item.isEmpty()) {
            list.add(item);
        }
    }
}
