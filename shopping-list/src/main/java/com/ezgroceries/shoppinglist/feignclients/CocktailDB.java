package com.ezgroceries.shoppinglist.feignclients;

import com.ezgroceries.shoppinglist.models.cocktaildb.CocktailDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "CocktailDB", url = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailDB {

    @GetMapping(value= "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

}