package com.ezgroceries.shoppinglist.storage.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    private UUID id;
    private String name;

    public ShoppingListEntity() {}

    public ShoppingListEntity(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
