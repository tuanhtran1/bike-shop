/**
 * 
 */
package com.shop.bike.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

public class JsonConverter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119696482071508541L;

    public static boolean isJsonValid(String json) {
    	if(json != null) {
    		try {
    			final ObjectMapper mapper = new ObjectMapper();
    			mapper.registerModule(new JavaTimeModule());
    	        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    			mapper.readTree(json);
    			return true;
    		} catch (IOException e) {
    			return false;
    		}
    	}
    	return false;
    }

    public static String toJson(Object object) {
    	Logger log = LoggerFactory.getLogger(JsonConverter.class);
        if(object != null) {
        	StringWriter sw = new StringWriter();
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.enable(SerializationFeature.INDENT_OUTPUT);
        	mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        	try {
        		mapper.registerModule(new JavaTimeModule());
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        		mapper.writeValue(sw, object);
        		return sw.toString();
        	} catch (IOException e) {
        		log.error("Convert Json to ObjectArray error: {}", e.getMessage());
        	}
        }
        return null;
    }

    public static String toJson(Object object, boolean indent) {
    	Logger log = LoggerFactory.getLogger(JsonConverter.class);
        ObjectMapper mapper = new ObjectMapper();
        if (indent == true) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        }

        if(object != null) {
        	StringWriter stringWriter = new StringWriter();
        	try {
        		mapper.registerModule(new JavaTimeModule());
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        		mapper.writeValue(stringWriter, object);
        		return stringWriter.toString();
        	} catch (IOException e) {
        		log.error("Convert Json to ObjectArray error: {}", e.getMessage());
        	}
        }
        return null;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
    	Logger log = LoggerFactory.getLogger(JsonConverter.class);
        T obj = null;
        if(json != null)
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			obj = mapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			log.error("Convert Json to Object error: {}", e.getMessage());
		}
        return obj;
    }
	
	@SuppressWarnings("rawtypes")
    public static <T> T toObjectArray(String json){
		Logger log = LoggerFactory.getLogger(JsonConverter.class);
        T obj = null;
        if(json != null)
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			obj = (T) mapper.readValue(json, new TypeReference<List>() {});
		} catch (JsonProcessingException e) {
			log.error("Convert Json to ObjectArray error: {}", e.getMessage());
		}
        return obj;
    }
	
    public static <T> T toObjectArray(String json, Class<T> clazz) {
    	Logger log = LoggerFactory.getLogger(JsonConverter.class);
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        if(json != null)
        try {
        	//mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			obj = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (JsonProcessingException e) {
			log.error("Convert Json to ObjectArray error: {}", e.getMessage());
		} 
        return obj;
    }
}
