package models;

import utils.List;
import utils.Queue;

public class User implements Comparable<User> {
    private String username;
    private String email;
    private String address;
    private String password;
    private String role;
    private List<Order> orderHistory;
    private Queue<Order> currentOrder;

    public User(String username, String email, String address, String password, String role) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.role = role;
        this.orderHistory = new List<>();
        this.currentOrder = new Queue<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Queue<Order> getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Queue<Order> currentOrder) {
        this.currentOrder = currentOrder;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nEmail: " + email + "\nAddress: " + address + "\nRole: " + role;
    }

    @Override
    public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }

    public boolean equals(User o) {
        return this.username.equals(o.username);
    }
}