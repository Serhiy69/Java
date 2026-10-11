package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private Customer customer;
    private List<Product> products;
    private OrderStatus status;

    public Order(long id, Customer customer, List<Product> products, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.status = status;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            if (i > 0) names.append(", ");
            names.append(products.get(i).getName());
        }
        return String.format("Order #%d | %s | %-10s | [%s]",
                id, customer.getName(), status, names);
    }
}
