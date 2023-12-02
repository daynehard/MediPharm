package com.example.medipharm;

public class Drug {
    private String name;
    private String description;
    private double price;
    private String usage;

    public Drug() {
        // Default constructor required for Firebase Firestore
    }

    public Drug(String name, String description, double price, String usage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}
