package com.shop.bike.entity.enumeration;
public enum ErrorEnum {
	UNAUTHORIZED("UsernameOrPassword", "Incorrect", ""),
	USER_ACCESS_DENIED("User", "AccessDenied", ""),
	USER_NOT_AUTHENTICATED("User", "NotAuthenticated", ""),
	
	USER_NOT_FOUND("User", "NotFound", "User is not found"),
	OTP_INCORRECT("OTP", "Incorrect", "Wrong OTP"),
	RESET_KEY_INCORRECT("ResetKey", "Incorrect", "Wrong reset key"),
	USERNAME_NON_EXISTING("Username", "NonExisting", "Non existing username"),
	PHONE_NUMBER_FORMATTED_INCORRECTLY("Phonenumber", "FormattedIncorrectly", "Phone number formatted incorrectly"),
	EMAIL_FORMATTED_INCORRECTLY("Email", "FormattedIncorrectly", "Email formatted incorrectly"),
	ACCOUNT_HAS_JUST_BEEN_LOGIN_ANOTHER_DEVICE("Account", "HasJustBeenLoginAnotherDevice", "The Acount has been login on another device"),
	USER_NOT_ACTIVATED("User", "NotActivated", "User not activated"),
	USER_BLOCKED("User", "Blocked", "User is blocked"),
	ERROR_ENUM("a", "b", "c"),
	
	//
	CATEGORY_NOT_FOUND("Category","NotFound" ,"Category is not found"),
	PRODUCT_NOT_FOUND("Product", "NotFound","Product is not found" ),
	CART_EXIST_PRODUCT("Cart","Exist" ,"Cart is exist product" ),
	CART_NOT_FOUND("Cart","NotFound" ,"Cart is not found");
	
	//add more...
	private final String entityName;
	private final String errorKey;
	private String message;

	public String getMessage() {
		message = getEntityName() + "." + getErrorKey();
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getErrorKey() {
		return errorKey;
	}

	ErrorEnum(String entityName, String errorKey, String message) {
		this.entityName = entityName;
		this.errorKey = errorKey;
		this.message = message;
	}
}
