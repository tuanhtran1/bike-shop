package com.shop.bike.entity.enumeration;

/**
 * The AuthorityType enumeration.
 */
public enum AuthorityType {
    ROLE_ADMIN,
    ROLE_CONSUMER
	;

    public static AuthorityType get(String authority) {
        AuthorityType[] values = AuthorityType.values();
        for (AuthorityType value : values) {
            if (value.toString().equals(authority)) return value;
        }
        return null;
    }
}
