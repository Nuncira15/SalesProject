package com.sales.model;

/**
 * Representa un vendedor del sistema.
 */
public class SalesMan {
    private String documentType;
    private long documentNumber;
    private String name;
    private String lastName;
    private double totalSales;

    public SalesMan(String documentType, long documentNumber, String name, String lastName) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.name = name;
        this.lastName = lastName;
        this.totalSales = 0;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void addSales(double amount) {
        this.totalSales += amount;
    }
}
