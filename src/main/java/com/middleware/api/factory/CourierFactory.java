package com.middleware.api.factory;

import com.middleware.api.config.util.Courier;
import com.middleware.api.service.CourierRate;
import com.middleware.api.service.impl.CityExpressRate;
import com.middleware.api.service.impl.JtExpressRate;

public class CourierFactory {
	   public CourierRate getCourier(String courierName){
	      if(courierName == null){
	         return null;
	      }		
	      if(courierName.equalsIgnoreCase(Courier.CITYLINK.getName())){
	         return new CityExpressRate();
	         
	      } else if(courierName.equalsIgnoreCase(Courier.JTEXPRESS.getName())){
	         return new JtExpressRate();	         
	      }
	      
	      return null;
	   }
}
