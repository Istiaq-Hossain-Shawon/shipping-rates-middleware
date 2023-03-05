package com.middleware.api.config.util;

public enum Courier {
	CITYLINK("citylink","https://www.citylinkexpress.com/wp-json/wp/v2/getShippingRate"), 
	JTEXPRESS("jtexpress","https://www.jtexpress.my/shipping-rates");
	
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
      
    private Courier(String action)
    {
        this.name = action;
    }
    private Courier(String action,String url)
    {
        this.name = action;
        this.url = url;
    }
}
