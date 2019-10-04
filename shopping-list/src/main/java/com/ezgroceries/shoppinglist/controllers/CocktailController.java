package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.feignclients.CocktailDB;
import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.models.cocktaildb.CocktailDBResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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

    @Autowired
    public CocktailController(CocktailDB cocktailDB) {
        this.cocktailDB = cocktailDB;
        this.cocktails = new ArrayList<>();
    }

    @GetMapping
    public List<Cocktail> get(@RequestParam String search) {
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

    public static List<Cocktail> getDummyCocktails() {
        return Arrays.asList(
                new Cocktail(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new Cocktail(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }

}
