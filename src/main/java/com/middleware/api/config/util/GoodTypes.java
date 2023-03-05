package com.middleware.api.config.util;

public enum GoodTypes {
	PARCEL("1"), 
	DOCUMENT("2");
	private String id;

    public String getId()
    {
        return this.id;
    }

	GoodTypes(String id) {
		 this.id = id;
	}
}
