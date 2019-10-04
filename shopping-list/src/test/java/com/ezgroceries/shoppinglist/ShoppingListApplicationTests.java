package com.ezgroceries.shoppinglist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.feignclients.CocktailDB;
import com.ezgroceries.shoppinglist.models.cocktaildb.CocktailDBResponse;
import com.ezgroceries.shoppinglist.models.cocktaildb.CocktailDBResponse.Drink;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ShoppingListApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public CocktailDB cocktailDB;

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void getCocktail() throws Exception {
        mockCocktailFeignClient();
        this.mockMvc.perform(get("http://localhost:8080/cocktails?search=russian")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content()
                .json("[{\"name\":\"Margerita\",\"glass\":\"Cocktail glass\",\"instructions\":\"Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..\",\"imageUrl\":\"https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg\",\"ingredients\":[\"Salt\"]}]"));

    }

    private void mockCocktailFeignClient() {
        CocktailDBResponse response = new CocktailDBResponse();
        Drink drink = new Drink();
        drink.setIdDrink("23b3d85a-3928-41c0-a533-6538a71e17c4");
        drink.setStrDrink("Margerita");
        drink.setStrGlass("Cocktail glass");
        drink.setStrInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        drink.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");
        drink.setStrIngredient1("Tequila");
        drink.setStrIngredient1("Triple sec");
        drink.setStrIngredient1("Lime juice");
        drink.setStrIngredient1("Salt");
        List<Drink> list = new ArrayList<>();
        list.add(drink);
        response.setDrinks(list);

        Mockito.doReturn(response).when(cocktailDB).searchCocktails("russian");
    }

    @Test
    public void createShoppingList() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/shopping-lists?name=testlist")).
                andDo(print()).andExpect(status().isCreated()).
                andExpect(header().string("Content-Type", "application/json;charset=UTF-8")).
                andExpect(content().json("{\"shoppingListId\":\"eb18bb7c-61f3-4c9f-981c-55b1b8ee8915\",\"name\":\"Stephanie's birthday\"}"));
    }

    @Test
    public void getShoppingLists() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/shopping-lists")).andDo(print()).andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"));

    }

    @Test
    public void getShoppingList() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/shopping-lists/97c8e5bd-5353-426e-b57b-69eb2260ace3")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"));

    }

}
