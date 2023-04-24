package com.shop.bike.utils;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GenerateIDUtil {

    // TODO: not support multiple instant

    private static final AtomicLong sequence = new AtomicLong(Instant.now().toEpochMilli());
    private static final int prefix = new Random().nextInt(89) + 10;
    private static final AtomicLong sequenceProfile = new AtomicLong(Instant.now().getEpochSecond() - 1583885333);
	
	private static final AtomicLong sequencePromotion = new AtomicLong(Instant.now().getEpochSecond() - 158388432);
	
	public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static String shortUniqueCode() {
        return String.valueOf(sequence.incrementAndGet());
    }

    public static String getItemCode(){return "ITEM"+"_"+prefix+shortUniqueCode();}
    public static String getOrderCode(){return "ORDER"+"_"+prefix+shortUniqueCode();}

    private static String convert7(Long num) {
        String str = String.valueOf(num);
        while (str.length() < 7)
            str = "0" + str;
        return str;
    }
	
	public static String getProductCode(){return "P"+"_"+prefix+shortUniqueCode();}
}
