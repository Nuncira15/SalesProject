package com.sales.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.sales.util.RandomDataUtil;

/**
 * Clase encargada de generar archivos planos de prueba.
 */
public class FileGenerator {

    /**
     * Genera archivo de productos con datos aleatorios.
     *
     * @param productsCount cantidad de productos a generar
     * @throws IOException si ocurre un error al generar archivo
     */
    public static void createProductsFile(int productsCount) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"));

        String[] products = {
                "Laptop", "Mouse", "Teclado", "Monitor", "Impresora"
        };

        for (int i = 0; i < products.length; i++) {
            int price = 1000 + new Random().nextInt(9000);

            writer.write((i + 1) + ";" + products[i] + ";" + price);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Genera archivo de vendedores.
     *
     * @param salesmanCount cantidad de vendedores
     * @throws IOException si ocurre un error al generar archivo
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("salesmen.txt"));

        for (int i = 1; i <= salesmanCount; i++) {
            String line = "CC;" + i + ";" +
                    RandomDataUtil.getRandomName() + ";" +
                    RandomDataUtil.getRandomLastName();

            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Genera archivo de ventas de un vendedor.
     *
     * @param randomSalesCount cantidad de ventas
     * @param name nombre del vendedor
     * @param id identificador unico del vendedor
     * @throws IOException si ocurre un error al generar archivo
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
                new FileWriter("sales_" + name + ".txt"));

        writer.write("CC;" + id);
        writer.newLine();

        Random random = new Random();

        for (int i = 0; i < randomSalesCount; i++) {
            int productId = random.nextInt(5) + 1;
            int quantity = random.nextInt(20) + 1;

            writer.write(productId + ";" + quantity);
            writer.newLine();
        }

        writer.close();
    }
}