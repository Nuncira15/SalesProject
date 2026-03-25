package com.sales.util;

import java.util.Random;

/**
 * Clase utilitaria para generación de datos aleatorios.
 */
public class RandomDataUtil {

    private static final String[] NAMES = {
            "Juan", "Maria", "Carlos", "Ana", "Luis"
    };

    private static final String[] LAST_NAMES = {
            "Perez", "Gomez", "Rodriguez", "Lopez"
    };

    private static final String[] PRODUCTS = {
            "Laptop", "Mouse", "Teclado", "Monitor", "Impresora"
    };

    public static String getRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }

    public static String getRandomLastName() {
        return LAST_NAMES[new Random().nextInt(LAST_NAMES.length)];
    }

    public static String getRandomProductName() {
        return PRODUCTS[new Random().nextInt(PRODUCTS.length)];
    }
}