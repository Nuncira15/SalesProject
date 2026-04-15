package com.sales.model;

/**
 * Representa un producto del sistema.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int totalSold;

    public Product(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalSold = 0;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getTotalSold() { return totalSold; }

    public void addSold(int quantity) {
        this.totalSold += quantity;
    }
}
