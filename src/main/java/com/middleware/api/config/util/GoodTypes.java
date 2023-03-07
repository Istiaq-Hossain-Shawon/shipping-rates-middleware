package com.middleware.api.config.util;

public enum GoodTypes {
	PARCEL(1), 
	DOCUMENT(2);
	private int id;

    public int getId()
    {
        return this.id;
    }

	GoodTypes(int id) {
		 this.id = id;
	}
}
