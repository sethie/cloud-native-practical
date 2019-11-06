package com.ezgroceries.shoppinglist.storage.services;

import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.storage.repositories.CocktailRepository;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public void storeCocktail(String something) {
        if (cocktailRepository.findById_Drink(something) == null) {
            System.out.println("We shall save it");
        } else {
            System.out.println("We shant save it");
        }
    }
}
