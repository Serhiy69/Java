package org.example;

public class Product {
    private long id;
    private String name;
    private ProductCategory category;
    private double price;



    public Product(long id, String name, ProductCategory category ,double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
