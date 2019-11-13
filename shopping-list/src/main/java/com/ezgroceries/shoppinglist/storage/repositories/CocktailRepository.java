package com.ezgroceries.shoppinglist.storage.repositories;

import com.ezgroceries.shoppinglist.storage.entities.CocktailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {

//    @Query("SELECT c FROM CocktailEntity c WHERE c.id_drink = ?1")
//    public CocktailEntity findById_Drink(String id_drink);

    Collection<CocktailEntity> findByIdDrinkIn(Collection<String> ids);

}
