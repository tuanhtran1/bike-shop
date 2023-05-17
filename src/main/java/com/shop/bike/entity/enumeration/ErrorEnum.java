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
	CART_NOT_FOUND("Cart","NotFound" ,"Cart is not found"),
	BRAND_NOT_FOUND("Brand","NotFound" , "Brand is not found" ),
	TRADING_PRODUCT_EXISTED("TradingProduct","Existed" ,"Trading product is existed" ),
	TRADING_PRODUCT_IS_EMPTY("TradingProduct", "Empty", "Trading product is empty" ),
	TRADING_PRODUCT_NOT_FOUND("TradingProduct","NotFound" , "Trading product is not found" ),
	STOCK_QUANTITY_NOT_ENOUGH("TradingProduct","NotEnough" , "Stock quantity trading is not enough"),
	TYPE_NOT_SUPPORT("Media","NotSupported" ,"Media type is not supported" ),
	COUPON_CODE_EXIST("CouponDiscount","Existed" ,"Coupon is existed" ),
	COUPON_NOT_FOUND("CouponDiscount", "NotFound" , "Coupon discount is not found"),
	BRAND_NAME_DUPLICATE("Brand","Duplicate" , "Brand name is duplicate"),
	TRADING_PRODUCT_ATTRIBUTE("TradingProduct","Existed" , "Trading product attribute is existed"),
	ORDER_NOT_FOUND("MyOrder", "NotFound", "Order is not found"),
	CAN_NOT_CANCEL_DONE_ORDER("DoneOrder","CanNotCancel" , "Cannot cancel done order" ),
	CAN_NOT_CANCEL_ACCEPTED_ORDER("AcceptedOrder","CanNotCancel" , "Cannot cancel accepted order"),
	DISTRICT_NOT_FOUND("District","NotFound" ,"District is not found"),
	PROVINCE_NOT_FOUND("Province","NotFound" ,"Province is not found"),
	WARDS_NOT_FOUND("Wards","NotFound" ,"Wards is not found"),
	COUNTRY_NOT_FOUND("Country","NotFound" ,"Country is not found"),
	COUPON_IS_DEACTIVATED("Coupon", "IsDeactivated", "Coupon is deactivated"),
	COUPON_IS_NOT_AVAILABLE("Coupon", "IsNotAvailable", "Coupon is not available"),
	INVALID_COUPON("Coupon", "Invalid", "Invalid coupon"),
	COUPON_IS_USED_UP("Coupon", "IsUsedUp", "Coupon is used up"),
	
	USERNAME_IS_EXISTED("Username", "Existed", "Username is existed"),
	ACCOUNT_NOT_FOUND("Account", "AccountNotFound", "The Account is not found"),
	USER_NOT_ACTIVATED_BY_KEY("User","NotActivatedByKey" ,"User is not activated by key" );
	
	//add more...
	private final String entityName;
	private final String errorKey;
	private String message;

	public String getMessage() {
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
