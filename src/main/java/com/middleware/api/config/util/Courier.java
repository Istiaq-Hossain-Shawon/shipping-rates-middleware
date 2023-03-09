package com.middleware.api.config.util;

public enum Courier {
	CITYLINK("citylink","https://www.citylinkexpress.com/wp-json/wp/v2/getShippingRate"), 
	JTEXPRESS("jt","https://www.jtexpress.my/shipping-rates");
	
    private String name;
    
    private String url;
  
    public String getName()
    {
        return this.name;
    }
    public String getUrl()
    {
        return this.url;
    }
    Courier(String name,String url)
    {
        this.name = name;
        this.url = url;
    }
}
