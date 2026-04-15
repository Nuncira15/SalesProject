package com.sales.service;

import com.sales.model.Product;
import com.sales.model.SalesMan;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.*;

/**
 * Servicio encargado de procesar la información de ventas
 *
 * Funcionalidades:
 * - Leer archivos
 * - Procesar ventas
 * - Generar reportes
 */
public class SalesService {
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Long, SalesMan> salesmen = new HashMap<>();

    /**
     * Metodo principal que ejecuta todo el flujo.
     */
    public void processSales() throws IOException {
        loadProducts();
        loadSalesmen();
        processSalesFiles();
        generateSalesmenReport();
        generateProductsReport();
    }

    /**
     * Carga los productos desde archivo.
     */
    private void loadProducts() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
        String line;

        while ((line = reader.readLine()) != null){
            String[] parts = line.split(";");

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);

            products.put(id, new Product(id, name, price));
        }

        reader.close();
    }

    /**
     * Carga los vendedores desde archivo.
     */
    private void loadSalesmen() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("salesmen.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");

            String docType = parts[0];
            long docNumber = Long.parseLong(parts[1]);
            String name = parts[2];
            String lastName = parts[3];

            salesmen.put(docNumber, new SalesMan(docType, docNumber, name, lastName));
        }

        reader.close();
    }

    /**
     * Procesa todos los archivos de ventas.
     */
    private void processSalesFiles() throws IOException {

        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("sales_"));

        for (File file : files){
            processSingleSalesFile(file);
        }
    }

    /**
     * Procesa un archivo de ventas individual.
     */
    private void processSingleSalesFile(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        // Primera linea: info vendedor
        line = reader.readLine();
        String[] sellerInfo = line.split(";");

        long sellerId = Long.parseLong(sellerInfo[1]);
        SalesMan salesman = salesmen.get(sellerId);

        if (salesman == null) {
            System.out.println("⚠️ Vendedor no encontrado: " + sellerId);
            return; // evita que el programa se rompa
        }

        // Procesar ventas
        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(";");
            int productId = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);

            Product product = products.get(productId);

            if (product == null) {
                System.out.println("⚠️ Producto no encontrado: " + productId);
                continue;
            }

            if (product != null){
                double total = quantity * product.getPrice();

                product.addSold(quantity);
                salesman.addSales(total);
            }
        }

        reader.close();
    }

    /**
     * Genera reporte de vendedores.
     */
    private void generateSalesmenReport() throws IOException {

        List<SalesMan> list = new ArrayList<>(salesmen.values());

        list.sort((a, b) -> Double.compare(b.getTotalSales(), a.getTotalSales()));

        BufferedWriter writer = new BufferedWriter(new FileWriter("report_salesmen.csv"));

        for (SalesMan s : list) {
            writer.write("Nombre;TotalVentas");
            writer.newLine();
            writer.write(s.getFullName() + ";" + String.format("%.2f", s.getTotalSales()));
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Genera reporte de productos.
     */
    private void generateProductsReport() throws IOException {

        List<Product> list = new ArrayList<>(products.values());

        list.sort((a, b) -> Integer.compare(b.getTotalSold(), a.getTotalSold()));

        BufferedWriter writer = new BufferedWriter(new FileWriter("report_products.csv"));

        for (Product p : list) {
            writer.write("Producto;CantidadVendida");
            writer.newLine();
            writer.write(p.getName() + ";" + p.getTotalSold());
            writer.newLine();
        }

        writer.close();
    }
}
