package com.ezgroceries.shoppinglist.storage.entities;

import com.ezgroceries.shoppinglist.storage.entities.converters.StringSetConverter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {
    @Id
    private UUID id;
    @Column(name="ID_DRINK")
    private String idDrink;
    private String name;
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getId_drink() {
        return idDrink;
    }

    public void setId_drink(String id_drink) {
        this.idDrink = id_drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
