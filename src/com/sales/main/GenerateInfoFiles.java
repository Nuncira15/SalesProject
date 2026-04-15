package com.sales.main;

import com.sales.generator.FileGenerator;

/**
 * Clase principal encargada de generar archivos de prueba
 * para el sistema de ventas.
 *
 * Entrega 1 - Generación de datos.
 */
public class GenerateInfoFiles {

    /**
     * Metodo principal del programa.
     *
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        try {

            // Generar archivos
            FileGenerator.createProductsFile(10);
            FileGenerator.createSalesManInfoFile(5);

            for (int i = 1; i <= 5; i++) {
                FileGenerator.createSalesMenFile(20, "Seller" + i, i);
            }

            System.out.println("Archivos generados correctamente");

        } catch (Exception e) {
            System.out.println("Error al generar archivos");
            e.printStackTrace();
        }
    }
}