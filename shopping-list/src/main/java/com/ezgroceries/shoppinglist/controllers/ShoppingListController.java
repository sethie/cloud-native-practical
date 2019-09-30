package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.models.ShoppingList;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList create(@RequestParam String name) {
        return new ShoppingList(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"), name);
    }

}
