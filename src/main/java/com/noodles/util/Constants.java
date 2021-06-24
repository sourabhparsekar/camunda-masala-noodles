package com.noodles.util;

/**
 * @implSpec Constants class used to maintain constants used in project
 */
public class Constants {

    public static final String INGREDIENTS_AVAILABLE = "IngredientsAvailable";
    public static final String IS_IT_COOKING = "IsItCooking";
    public static final String DID_WE_EAT_NOODLES = "DidWeEat";
    public static final String NOODLES_COOKED = "noodles cooked";
    public static final String NOODLES = "noodles";
    public static final String WATER = "water";
    public static final String PAN_SPATULA = "pan_and_spatula";
    public static final String ONION = "onion";
    public static final String TOMATO = "tomato";
    public static final String CHEESE = "cheese";
    public static final String CARROT = "carrot";
    public static final String CAPSICUM = "capsicum";
    public static String ORDER_ONLINE = "OrderOnline";

    private Constants() {
        throw new IllegalStateException("Constants Utility Class. Cannot be instantiated.");
    }

}
