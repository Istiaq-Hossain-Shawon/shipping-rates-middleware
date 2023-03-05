package com.middleware.api.factory;

import com.middleware.api.config.util.Courier;
import com.middleware.api.service.IShippingRate;
import com.middleware.api.service.impl.CityExpressRateImpl;
import com.middleware.api.service.impl.JtExpressRateImpl;

public class CourierFactory {
	   public IShippingRate getCourierRate(String courierName){
	      if(courierName == null){
	         return null;
	      }		
	      if(courierName.equalsIgnoreCase(Courier.CITYLINK.getName())){
	         return new CityExpressRateImpl();
	         
	      } else if(courierName.equalsIgnoreCase(Courier.JTEXPRESS.getName())){
	         return new JtExpressRateImpl();	         
	      }
	      
	      return null;
	   }
}
