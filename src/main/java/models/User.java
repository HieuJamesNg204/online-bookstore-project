package models;

import data_structures.List;

public class User {
    private String username;
    private String email;
    private String address;
    private List<Order> orders;

    public User(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.orders = new List<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nEmail: " + email + "\nAddress: " + address;
    }
}