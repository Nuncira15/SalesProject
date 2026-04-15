package com.sales.main;

import com.sales.service.SalesService;

/**
 * Clase principal que ejecuta el procesamiento de ventas.
 */
public class Main {

    public static void main(String[] args) {

        try {
            SalesService service = new SalesService();
            service.processSales();

            System.out.println("Proceso completado correctamente ✅");
        } catch (Exception e) {
            System.out.println("Error en ejecucion ❌");
            e.printStackTrace();
        }
    }
}
