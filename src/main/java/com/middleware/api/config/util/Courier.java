package com.middleware.api.config.util;

public enum Courier {
	CITYLINK("citylink"), JTEXPRESS("jtexpress");
	
    private String name;
  
    public String getName()
    {
        return this.name;
    }
      
    private Courier(String action)
    {
        this.name = action;
    }
}
