package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StoreAndPermutationIntegrationTests extends DemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}


		@Test
		@DisplayName("Test storing an array of numbers and get a random permutation of that array ")

		public void testStoreAndPermutationValidRequest() throws Exception {
		   MvcResult storeAPIResponse = mockMvc.perform(MockMvcRequestBuilders.post("/store").queryParam("numbers", "2,1,3,4,6").accept(MediaType.ALL)).andReturn();		   
		   int status = storeAPIResponse.getResponse().getStatus();
		   String id = storeAPIResponse.getResponse().getContentAsString();
		   assertEquals(200, status);
		   assertNotNull(id);		   
		   MvcResult permutationAPIResponse = mockMvc.perform(MockMvcRequestBuilders.get("/permutation").queryParam("id", id).accept(MediaType.ALL)).andReturn();
		   System.out.println(" permutationAPIResponse "+ permutationAPIResponse.getResponse().getContentAsString() + "  length " + permutationAPIResponse.getResponse().getContentAsString().trim() .length());
		   assertTrue(permutationAPIResponse!=null && permutationAPIResponse.getResponse().getContentAsString().trim().length() == 9);
		
	}
		
		
		@Test
		@DisplayName("Test calling store api with array of numbers and strings ")

		public void testStoreBadParamData() throws Exception {
		   String uri = "/store";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).queryParam("numbers", "2,1,3,sdsd,6").accept(MediaType.ALL)).andReturn();		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(400, status);
		   
		  
	}
		
		
		@Test
		@DisplayName("Test  store api with HTTP GET method ")

		public void testStoreInvalidHttpMethod() throws Exception {
		   String uri = "/store";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.ALL)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(405, status);
		
	}
		@Test
		@DisplayName("Test  store api with invalid query param name ")

		public void testStoreInvalidQueryParam() throws Exception {
		   String uri = "/store";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).queryParam("asfa", "2,1,3,sdsd,6").accept(MediaType.ALL)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(400, status);
		
	}
		
		@Test
		@DisplayName("Test permutation api with some random number ")

		public void testPermutationValidRequest() throws Exception {			

		   String uri = "/permutation";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).queryParam("id", "100").accept(MediaType.ALL)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   assertNotNull(mvcResult.getResponse().getContentAsString().length() ==0);
		
	}
		
		
		@Test
		@DisplayName("Test permutation api with a random string ")

		public void testPermutationInvalidRequest() throws Exception {
		   String uri = "/permutation";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).queryParam("id", "asfdaf").accept(MediaType.ALL)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(400, status);
		
	}
		
		
		@Test
		@DisplayName("Test permutation api with HTTP POST method ")

		public void testPermutationInvalidHttpMethod() throws Exception {
		   String uri = "/permutation";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.ALL)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(405, status);
		
	}
		@Test
		@DisplayName("Test permutation api with invalid query param name ")

		public void testPermutationInvalidQueryParam() throws Exception {
		   String uri = "/permutation";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).queryParam("asfa", "sdsfsf").accept(MediaType.ALL)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(400, status);
		
	}


}