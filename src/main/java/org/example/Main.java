package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        OrderService service = new OrderService();

        Customer ivan = new Customer(1, "Іван Петренко", "ivan@example.com");
        Customer olena = new Customer(2, "Олена Коваль", "olena@example.com");
        Customer taras = new Customer(3, "Тарас Шевчук", "taras@example.com");
        Customer maria = new Customer(4, "Марія Бойко", "maria@example.com");

        Product laptop = new Product(1, "Ноутбук", ProductCategory.ELECTRONICS, 32000.00);
        Product phone = new Product(2, "Смартфон", ProductCategory.ELECTRONICS, 18500.50);
        Product jacket = new Product(3, "Куртка", ProductCategory.CLOTHING, 2800.00);
        Product book = new Product(4, "Книга", ProductCategory.BOOKS, 650.00);
        Product lamp = new Product(5, "Лампа", ProductCategory.HOME, 450.00);
        Product gift = new Product(6, "Подарунковий набір", ProductCategory.OTHER, 999.99);


        service.addOrder(new Order(101, ivan, List.of(laptop, book), OrderStatus.NEW));
        service.addOrder(new Order(102, olena, List.of(phone, lamp), OrderStatus.PROCESSING));
        service.addOrder(new Order(103, ivan, List.of(jacket), OrderStatus.SHIPPED));
        service.addOrder(new Order(104, taras, List.of(book, lamp, gift), OrderStatus.DELIVERED));
        service.addOrder(new Order(105, olena, List.of(laptop, phone), OrderStatus.NEW));
        service.addOrder(new Order(106, taras, List.of(gift), OrderStatus.CANCELLED));

        System.out.println("=== 1. Додано 6 замовлень в сервіс ===");

        printHeader("2. findOrderById");
        System.out.println("id=102: " + service.findOrderById(102));
        System.out.println("id=999: " + service.findOrderById(999));

        printHeader("3. findOrdersByCustomer");
        System.out.println("Замовлення клієнта '" + ivan.getName() + "':");
        printList(service.findOrdersByCustomer(ivan.getId()));
        System.out.println("Замовлення клієнта '" + maria.getName() + "':");
        printList(service.findOrdersByCustomer(maria.getId()));

        printHeader("4. findOrdersByStatus");
        System.out.println("Статус NEW:");
        printList(service.findOrdersByStatus(OrderStatus.NEW));
        System.out.println("Статус DELIVERED:");
        printList(service.findOrdersByStatus(OrderStatus.DELIVERED));

        printHeader("5. findOrdersContainingProduct");
        System.out.println("Замовлення, що містять '" + laptop.getName() + "':");
        printList(service.findOrdersContainingProduct(laptop.getId()));
        System.out.println("Замовлення, що містять '" + lamp.getName() + "':");
        printList(service.findOrdersContainingProduct(lamp.getId()));

        printHeader("6. calculateOrderTotal");
        for (long id : new long[]{101, 102, 103, 104, 105, 106}) {
            System.out.println("Замовлення №" + id + ": " + String.format("%.2f", service.calculateOrderTotal(id)) + " грн");
        }

        printHeader("7. findCustomersWithOrders");
        List<Customer> allCustomers = List.of(ivan, olena, taras, maria);
        Set<Customer> withOrders = service.findCustomersWithOrders();

        System.out.println("Клієнти, які мають замовлення:");
        for (Customer c : withOrders) {
            System.out.println("  " + c);
        }

        System.out.println("Клієнти без замовлень:");
        boolean anyWithout = false;
        for (Customer c : allCustomers) {
            if (!withOrders.contains(c)) {
                System.out.println("  " + c);
                anyWithout = true;
            }
        }
        if (!anyWithout) {
            System.out.println("  (немає)");
        }

        printHeader("8. sortOrdersByTotalPrice (за зростанням)");
        for (Order o : service.sortOrdersByTotalPrice()) {
            System.out.println(" " + String.format("%.2f", service.calculateOrderTotal(o.getId())) + " -> " + o);
        }

        printHeader("9. countOrdersByStatus");
        for (Map.Entry<OrderStatus, Long> e : service.countOrdersByStatus().entrySet()) {
            System.out.println(" " + e.getKey() + ": " + String.format("%d", e.getValue()));
        }

        printHeader("10. removeOrder");
        service.removeOrder(106);
        System.out.println("Замовлення #106 видалено. Пошук: " + service.findOrderById(106));
        System.out.println("Статистика після видалення:");
        System.out.println("  " + service.countOrdersByStatus());



    }
        private static void printHeader(String title) {
            System.out.println();
            System.out.println("=== " + title + " ===");
        }

        private static void printList(List<Order> orders) {
            if (orders.isEmpty()) {
                System.out.println("  (нічого не знайдено)");
                return;
            }
            for (Order o : orders) {
                System.out.println("  " + o);
            }
        }
    }