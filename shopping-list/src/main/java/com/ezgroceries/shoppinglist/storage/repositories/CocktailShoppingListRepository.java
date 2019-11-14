package com.ezgroceries.shoppinglist.storage.repositories;

import com.ezgroceries.shoppinglist.storage.entities.CocktailShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CocktailShoppingListRepository extends JpaRepository<CocktailShoppingListEntity, UUID> {

    public List<CocktailShoppingListEntity> findByShoppingListId(UUID shoppingListId);

}
