package com.middleware.api.config.util;

import java.util.Calendar;

import com.middleware.api.response.ResponseDTO;


public class ShippingRateUtil
{

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
	public static ResponseDTO createResponseSuccess()
	{
		ResponseDTO responseDTO = new ResponseDTO();
		return responseDTO;
	}
	public static ResponseDTO createResponseFalied(String message)
	{
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage(message);
		return responseDTO;
	}
}
