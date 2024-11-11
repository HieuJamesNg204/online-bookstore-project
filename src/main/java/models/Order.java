package models;

import data_structures.List;

public class Order implements Comparable<Order> {
    private int id;
    private User user;
    private List<Book> books;
    private String status;

    public Order(int id, User user, List<Book> books) {
        this.id = id;
        this.user = user;
        this.books = books;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nUser: " + user.getUsername() + "\nBooks: " + books + "\nStatus: " + status;
    }
}