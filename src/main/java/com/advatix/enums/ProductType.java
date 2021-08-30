package com.advatix.enums;

public enum ProductType {

	COLD("Cold"), AMBIENT("Ambient"), FROZEN("forzen"), PRODUCT("product");

	private String name;

	private ProductType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
