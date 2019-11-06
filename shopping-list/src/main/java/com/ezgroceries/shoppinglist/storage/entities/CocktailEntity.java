package com.ezgroceries.shoppinglist.storage.entities;

import com.ezgroceries.shoppinglist.storage.entities.converters.StringSetConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {
    @Id
    private UUID id;
    private String id_drink;
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
        return id_drink;
    }

    public void setId_drink(String id_drink) {
        this.id_drink = id_drink;
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
