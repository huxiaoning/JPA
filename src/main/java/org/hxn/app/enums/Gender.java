package org.hxn.app.enums;

public enum Gender {
	/**
	 * 男
	 */
	MALE("男"),
	/**
	 * 女
	 */
	FEMALE("男");
	
	private String value;
	private Gender(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
