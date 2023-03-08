package com.middleware.api;

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
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.dto.ApiError;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.AuthenticationRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.AuthenticationResponse;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.LogisticShippingService;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ShippingRateMiddlewareApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private LogisticShippingService logisticShippingService;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getJwtToken_When_ValidUserNamePasswordIsUsed_Then_ShouldGetValidJwtToken() throws Exception {

		AuthenticationRequest user = new AuthenticationRequest();
		user.setUsername("user1");
		user.setPassword("123456");

		String JsonRequest = objectMapper.writeValueAsString(user);

		MvcResult result = mockMvc
				.perform(post("/requesttoken").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		AuthenticationResponse response = objectMapper.readValue(resultContext, AuthenticationResponse.class);

		boolean isJwt = isJWT(response.getJwt());

		Assert.assertEquals(Boolean.TRUE, isJwt);

	}
	
	
	@Test
	void getJwtTokenWithWrongUserNameAndPassword_When_WrongUserNameAndPasswordProvided_Then_ShouldGetInCorrectUserNamePasswordResponse() throws Exception {

		AuthenticationRequest user = new AuthenticationRequest();
		user.setUsername("wronguser");
		user.setPassword("wronguser123456");

		String JsonRequest = objectMapper.writeValueAsString(user);

		MvcResult result = mockMvc
				.perform(post("/requesttoken").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		String resultContext = result.getResponse().getContentAsString();

		ApiError response = objectMapper.readValue(resultContext, ApiError.class);

		String validationMessage = "Incorrect username or password";

		Assert.assertEquals(validationMessage, response.getErrorMessage());

	}
	 

	// JUnit test case Fetching All Logistics Companies Rates
	@Test
	void getCompaniesRates_When_ValidParameterIsUsed_Then_OutputSizeWillBeTwo() throws Exception {

		ShippingRateRequestDto shippingRateRequest = new ShippingRateRequestDto();

		shippingRateRequest.setDestinationCountry("AW");
		shippingRateRequest.setDestinationPostcode("50000");
		shippingRateRequest.setDestinationState("Aruba");
		shippingRateRequest.setOriginCountry("MY");
		shippingRateRequest.setOriginPostcode("40000");
		shippingRateRequest.setOriginState("Selangor");

		shippingRateRequest.setGoodsSelectedType(GoodTypes.PARCEL.getId());

		shippingRateRequest.setWeight(3);
		shippingRateRequest.setHeight(12);
		shippingRateRequest.setLength(32);
		shippingRateRequest.setWidth(20);

		shippingRateRequest.setShippingRatesType("domestic");
		shippingRateRequest.setShippingType("EZ");
		shippingRateRequest.setItemValue(0);

		String JsonRequest = objectMapper.writeValueAsString(shippingRateRequest);

		String token = getToken();

		Principal mockPrincipal = Mockito.mock(Principal.class);
		Mockito.when(mockPrincipal.getName()).thenReturn("me");

		MvcResult result = mockMvc.perform(
				post("/shipping-rates").principal(mockPrincipal).header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
						.content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		MiddlewareResponse response = objectMapper.readValue(resultContext, MiddlewareResponse.class);

		int expectedSizeOfData = 2;

		Assert.assertEquals(expectedSizeOfData, response.getData().size());

	}
	
	// JUnit test case Fetching All Logistics Companies Rates with Wrong Goods Type
		@Test
		void getCompaniesRatesWithWrongGoodsType_When_GoodsTypeIsNotOneOrTwo_Then_OutputWillBeValidationFailed() throws Exception {

			ShippingRateRequestDto shippingRateRequest = new ShippingRateRequestDto();

			shippingRateRequest.setDestinationCountry("AW");
			shippingRateRequest.setDestinationPostcode("50000");
			shippingRateRequest.setDestinationState("Aruba");
			shippingRateRequest.setOriginCountry("MY");
			shippingRateRequest.setOriginPostcode("40000");
			shippingRateRequest.setOriginState("Selangor");

			shippingRateRequest.setGoodsSelectedType(3);

			shippingRateRequest.setWeight(3);
			shippingRateRequest.setHeight(12);
			shippingRateRequest.setLength(32);
			shippingRateRequest.setWidth(20);

			shippingRateRequest.setShippingRatesType("domestic");
			shippingRateRequest.setShippingType("EZ");
			shippingRateRequest.setItemValue(0);

			String JsonRequest = objectMapper.writeValueAsString(shippingRateRequest);

			String token = getToken();

			Principal mockPrincipal = Mockito.mock(Principal.class);
			Mockito.when(mockPrincipal.getName()).thenReturn("me");

			MvcResult result = mockMvc.perform(
					post("/shipping-rates").principal(mockPrincipal).header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
							.content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			String resultContext = result.getResponse().getContentAsString();

			ApiError response = objectMapper.readValue(resultContext, ApiError.class);

			String Custommessage = "[goodsSelectedType: must be less than or equal to 2]";

			Assert.assertEquals(Custommessage, response.getCustomMessage());
			
			String validationMessage = "Validation failed";

			Assert.assertEquals(validationMessage, response.getErrorMessage());

		}
		
		// JUnit test case Fetching All Logistics Companies Rates without origin Postcode
		@Test
		void getCompaniesRatesWithOutOriginPostcode_When_OriginPostCodeIsNotProvided_Then_OutputWillBeValidationFailed() throws Exception {

			ShippingRateRequestDto shippingRateRequest = new ShippingRateRequestDto();

			shippingRateRequest.setDestinationCountry("AW");
			shippingRateRequest.setDestinationPostcode("50000");
			shippingRateRequest.setDestinationState("Aruba");
			shippingRateRequest.setOriginCountry("MY");
//			shippingRateRequest.setOriginPostcode("40000");
			shippingRateRequest.setOriginState("Selangor");
			shippingRateRequest.setGoodsSelectedType(2);
			shippingRateRequest.setWeight(3);
			shippingRateRequest.setHeight(12);
			shippingRateRequest.setLength(32);
			shippingRateRequest.setWidth(20);
			shippingRateRequest.setShippingRatesType("domestic");
			shippingRateRequest.setShippingType("EZ");
			shippingRateRequest.setItemValue(0);

			String JsonRequest = objectMapper.writeValueAsString(shippingRateRequest);

			String token = getToken();

			Principal mockPrincipal = Mockito.mock(Principal.class);
			Mockito.when(mockPrincipal.getName()).thenReturn("me");

			MvcResult result = mockMvc.perform(
					post("/shipping-rates").principal(mockPrincipal).header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
							.content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			String resultContext = result.getResponse().getContentAsString();

			ApiError response = objectMapper.readValue(resultContext, ApiError.class);

			String Custommessage = "[originPostcode: must not be null]";

			Assert.assertEquals(Custommessage, response.getCustomMessage());
			
			String validationMessage = "Validation failed";

			Assert.assertEquals(validationMessage, response.getErrorMessage());

		}
	 

	private String getToken() throws Exception {
		AuthenticationRequest user = new AuthenticationRequest();
		user.setUsername("user1");
		user.setPassword("123456");

		String JsonRequest = objectMapper.writeValueAsString(user);

		MvcResult result = mockMvc
				.perform(post("/requesttoken").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		AuthenticationResponse response = objectMapper.readValue(resultContext, AuthenticationResponse.class);

		return response.getJwt();

	}

	private boolean isJWT(String jwt) {
		String[] jwtSplitted = jwt.split("\\.");
		if (jwtSplitted.length != 3) // The JWT is composed of three parts
			return false;
		try {
			String jsonFirstPart = new String(Base64.getDecoder().decode(jwtSplitted[0]));
			JSONObject firstPart = new JSONObject(jsonFirstPart); // The first part of the JWT is a JSON
			if (!firstPart.has("alg")) // The first part has the attribute "alg"
				return false;
			String jsonSecondPart = new String(Base64.getDecoder().decode(jwtSplitted[1]));
			JSONObject secondPart = new JSONObject(jsonSecondPart); // The first part of the JWT is a JSON
			// Put the validations you think are necessary for the data the JWT should take
			// to validate
		} catch (JSONException err) {
			return false;
		}
		return true;
	}

}
