package com.ezgroceries.shoppinglist.storage.services;

import com.ezgroceries.shoppinglist.models.Cocktail;
import com.ezgroceries.shoppinglist.storage.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.storage.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.storage.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.models.ShoppingList;
import com.ezgroceries.shoppinglist.storage.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.storage.repositories.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.storage.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailShoppingListRepository cocktailShoppingListRepository;
    private final CocktailRepository cocktailRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository,
                               CocktailShoppingListRepository cocktailShoppingListRepository,
                               CocktailRepository cocktailRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailShoppingListRepository = cocktailShoppingListRepository;
        this.cocktailRepository = cocktailRepository;
    }

    public ShoppingList create(String shoppingListName) {
        final ShoppingListEntity shoppingListEntity = shoppingListRepository.save(new ShoppingListEntity(shoppingListName));
        return new ShoppingList(shoppingListEntity);
    }

    public List<ShoppingList> getAll() {
        List<ShoppingList> list = new ArrayList<>();
        List<ShoppingListEntity> shoppingListEntities = shoppingListRepository.findAll();
        for (ShoppingListEntity shoppingListEntity: shoppingListEntities) {
            list.add(new ShoppingList(shoppingListEntity));
        }
        return list;
    }

    public void addCocktailsToShoppingList(UUID shoppingListId, List<Cocktail.CocktailIdClass> cocktailIds) {
        List<CocktailShoppingListEntity> cocktailShoppingList = cocktailShoppingListRepository.findByShoppingListId(shoppingListId);
        List<Cocktail.CocktailIdClass> matchedCocktails = new ArrayList<>();
        for (CocktailShoppingListEntity cocktailShoppingListEntity: cocktailShoppingList) {
            for (Cocktail.CocktailIdClass cocktailId: cocktailIds) {
                if (cocktailShoppingListEntity.getShoppingListId() == cocktailId.getCocktailId()) {
                    matchedCocktails.add(cocktailId);
                }
            }
        }
        cocktailIds.removeAll(matchedCocktails);
        for (Cocktail.CocktailIdClass cocktailId: cocktailIds) {
            cocktailShoppingListRepository.save(new CocktailShoppingListEntity(cocktailId.getCocktailId(), shoppingListId));
        }
    }

    public ShoppingList getShoppingList(UUID shoppingListId) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.getOne(shoppingListId);
        List<String> ingredients = new ArrayList<>();
        List<CocktailShoppingListEntity> cocktailShoppingList = cocktailShoppingListRepository.findByShoppingListId(shoppingListId);
        for (CocktailShoppingListEntity cocktailShoppingListEntity: cocktailShoppingList) {
            CocktailEntity cocktail = cocktailRepository.getOne(cocktailShoppingListEntity.getCocktail_id());
            cocktail.getIngredients().removeAll(ingredients);
            ingredients.addAll(cocktail.getIngredients());
        }
        return new ShoppingList(shoppingListEntity, ingredients);
    }

}
