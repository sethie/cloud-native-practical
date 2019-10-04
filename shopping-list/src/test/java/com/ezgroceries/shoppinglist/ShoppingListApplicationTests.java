package com.ezgroceries.shoppinglist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ShoppingListApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
	}

	@Test
	public void getCocktail() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cocktails?search=russian")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));

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
		this.mockMvc.perform(get("http://localhost:8080/shopping-lists/97c8e5bd-5353-426e-b57b-69eb2260ace3")).andDo(print()).andExpect(status().isOk())
				.andExpect(header().string("Content-Type", "application/json;charset=UTF-8"));

	}



}
