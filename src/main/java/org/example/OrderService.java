package org.example;

import java.util.*;


public class OrderService {

    private List<Order> orders = new ArrayList<Order>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(long orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                orders.remove(order);
                break;
            }
        }
    }

    public Order findOrderById(long orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public List<Order> findOrdersByCustomer(long customerId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().getId() == customerId) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == status) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> findOrdersContainingProduct(long productId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            for (Product p : order.getProducts()) {
                if (p.getId() == productId) {
                    result.add(order);
                    break;
                }
            }
        }
        return result;
    }

    public double calculateOrderTotal(long orderId) {
        Order order = findOrderById(orderId);
        if (order == null) {
            return 0.0;
        }
        double total = 0;
        for (Product product : order.getProducts()) {
            total += product.getPrice();
        }
        return total;
    }

    public Set<Customer> findCustomersWithOrders() {
        Set<Customer> customers = new HashSet<>();
        for (Order order : orders) {
            customers.add(order.getCustomer());
        }
        return customers;
    }

    public List<Order> sortOrdersByTotalPrice() {
        List<Order> sortedList = new ArrayList<>(orders);
        sortedList.sort(Comparator.comparingDouble(order -> calculateOrderTotal(order.getId())));
        return sortedList;
    }

    public Map<OrderStatus, Long> countOrdersByStatus() {
        Map<OrderStatus, Long> map = new HashMap<>();
        for (Order order : orders) {
            OrderStatus status = order.getStatus();
            if (map.containsKey(status)) {
                map.put(status, map.get(status) + 1);
            } else {
                map.put(status, 1L);
            }
        }
        return map;
    }

}


