package com.equinte.gotur.util;

import jakarta.validation.constraints.NotNull;

import java.util.Random;

public class ItemPriceUtil {
    private static final Random RANDOM = new Random();

    //To hide public constructor.
    private ItemPriceUtil() {
    }

    public static Float generateItemPrice() {
        return (float) (RANDOM.nextInt(1, 10) * 10); //Generates random item price in range between 10-100 and multiples of 10.
    }

    public static Float calculateDiscountedPrice(@NotNull Float itemPrice, @NotNull Float discountRate) {
        return itemPrice * (1 - discountRate);
    }
}
