package models;

import utils.List;

public class User {
    private String username;
    private String email;
    private String address;
    private List<Order> orderHistory;

    public User(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.orderHistory = new List<>();
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

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrders(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nEmail: " + email + "\nAddress: " + address;
    }
}