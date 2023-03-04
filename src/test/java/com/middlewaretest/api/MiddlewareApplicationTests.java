package com.middlewaretest.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.AuthenticationRequest;
import com.middleware.api.response.AuthenticationResponse;
import com.middleware.api.response.ResponseDTO;


import org.junit.Assert;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class MiddlewareApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	

	
//	ObjectMapper objectMapper = new ObjectMapper();
//	
//	
//	@BeforeEach
//	void setUp()
//	{
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
//	// JUnit test for request token
//	@Test
//	void getRequestTokenTest() throws Exception {
//		AuthenticationRequest user = new AuthenticationRequest();
//		user.setUsername("user1");
//		user.setPassword("123456");
//		
//		String JsonRequest = objectMapper.writeValueAsString(user);
//		
//		MvcResult result = mockMvc.perform(post("/requesttoken").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		
//		String resultContext = result.getResponse().getContentAsString();
//		
//		AuthenticationResponse response = objectMapper.readValue(resultContext, AuthenticationResponse.class);
//		
//		System.out.println(response.getJwt());
//		
//		boolean isJwt=isJWT(response.getJwt());
//		
//		Assert.assertEquals(Boolean.TRUE, isJwt);
//		
//			
//	}
//	
//	 
//	
//	private String getToken() throws Exception {
//		AuthenticationRequest user = new AuthenticationRequest();
//		user.setUsername("user1");
//		user.setPassword("123456");
//		
//		String JsonRequest = objectMapper.writeValueAsString(user);
//		
//		MvcResult result = mockMvc.perform(post("/requesttoken").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		
//		String resultContext = result.getResponse().getContentAsString();
//		
//		AuthenticationResponse response = objectMapper.readValue(resultContext, AuthenticationResponse.class);
//		
//		return response.getJwt();
//		
//			
//	}
//	
//	
//	
//	private boolean isJWT(String jwt) {
//        String[] jwtSplitted = jwt.split("\\.");
//        if (jwtSplitted.length != 3) // The JWT is composed of three parts
//            return false;
//        try {
//            String jsonFirstPart = new String(Base64.getDecoder().decode(jwtSplitted[0]));
//            JSONObject firstPart = new JSONObject(jsonFirstPart); // The first part of the JWT is a JSON
//            if (!firstPart.has("alg")) // The first part has the attribute "alg"
//                return false;
//            String jsonSecondPart = new String(Base64.getDecoder().decode(jwtSplitted[1]));
//            JSONObject secondPart = new JSONObject(jsonSecondPart); // The first part of the JWT is a JSON
//            //Put the validations you think are necessary for the data the JWT should take to validate
//        }catch (JSONException err){
//            return false;
//        }
//        return true;
//    }
//
//

}
