package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.models.Cocktail;
import java.util.List;

import com.ezgroceries.shoppinglist.storage.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> get(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
    }
}
