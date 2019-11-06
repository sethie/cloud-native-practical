package com.ezgroceries.shoppinglist.storage.services;

import com.ezgroceries.shoppinglist.storage.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.models.ShoppingList;
import com.ezgroceries.shoppinglist.storage.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingList> getAll() {
        List<ShoppingList> list = new ArrayList<>();
        List<ShoppingListEntity> shoppingListEntities = shoppingListRepository.findAll();
        for (ShoppingListEntity shoppingListEntity: shoppingListEntities) {
            list.add(new ShoppingList(shoppingListEntity));
        }
        return list;
    }

    public ShoppingList create(String shoppingListName) {
        final ShoppingListEntity shoppingListEntity = shoppingListRepository.save(new ShoppingListEntity(shoppingListName));
        return new ShoppingList(shoppingListEntity);
    }

}
