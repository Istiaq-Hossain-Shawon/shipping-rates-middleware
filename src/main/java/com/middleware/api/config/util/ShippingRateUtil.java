package com.middleware.api.config.util;



import com.middleware.api.response.MiddlewareResponse;


public class ShippingRateUtil
{
	private ShippingRateUtil() {
		throw new IllegalStateException("Shipping Rate Utility class");
	}

	public static Boolean checkIfNull(String... arguments)
	{
		for (int i = 0; i < arguments.length; i++)
		{
			if (arguments[i] == null || arguments[i].equals(""))
			{
				return true;
			}
		}
		return false;
	}
	public static MiddlewareResponse createResponseSuccess()
	{
		return new MiddlewareResponse();		
	}
	public static MiddlewareResponse createResponseFalied(String message)
	{
		MiddlewareResponse responseDTO = new MiddlewareResponse();
		responseDTO.setMessage(message);
		return responseDTO;
	}
	
}
