package com.ezgroceries.shoppinglist.storage.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "COCKTAIL_SHOPPING_LIST")
@IdClass(CocktailShoppingListEntity.class)
public class CocktailShoppingListEntity implements Serializable {

    @Id
    private UUID cocktail_id;
    @Id
    @Column(name="SHOPPING_LIST_ID")
    private UUID shoppingListId;

    public CocktailShoppingListEntity() {
        super();
    }

    public CocktailShoppingListEntity(UUID cocktail_id, UUID shopping_list_id) {
        super();
        this.cocktail_id = cocktail_id;
        this.shoppingListId = shopping_list_id;
    }

    public UUID getCocktail_id() {
        return cocktail_id;
    }

    public void setCocktail_id(UUID cocktail_id) {
        this.cocktail_id = cocktail_id;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
