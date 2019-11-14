package com.ezgroceries.shoppinglist.storage.services;

import com.ezgroceries.shoppinglist.feignclients.CocktailDB;
import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.models.cocktaildb.CocktailDBResponse;
import com.ezgroceries.shoppinglist.storage.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.storage.repositories.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailDB cocktailDB;

    public CocktailService(CocktailRepository cocktailRepository, CocktailDB cocktailDB) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailDB = cocktailDB;
    }

    public List<Cocktail> searchCocktails(String search) {
        return mergeCocktails(cocktailDB.searchCocktails(search).getDrinks());
    }

    public List<Cocktail> mergeCocktails(List<CocktailDBResponse.Drink> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.Drink::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream().collect(Collectors.toMap(CocktailEntity::getId_drink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setId(UUID.randomUUID());
                newCocktailEntity.setId_drink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                newCocktailEntity.setIngredients(new HashSet<>(Cocktail.convertIngredientsToList(drinkResource)));
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getId_drink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<Cocktail> mergeAndTransform(List<CocktailDBResponse.Drink> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new Cocktail(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), Cocktail.convertIngredientsToList(drinkResource))).collect(Collectors.toList());
    }
}
