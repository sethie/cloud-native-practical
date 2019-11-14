package com.ezgroceries.shoppinglist.storage.repositories;

import com.ezgroceries.shoppinglist.storage.entities.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {
}
