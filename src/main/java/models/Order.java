package models;

import utils.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements Comparable<Order> {
    private static int orderCounter = 100;
    private int orderNumber;
    private User user;
    private List<Book> books;
    private String status;
    private String orderDate;

    public Order(User user, List<Book> books) {
        this.orderNumber = ++orderCounter;
        this.user = user;
        this.books = books;
        this.status = "Unpaid";
        this.orderDate = formatOrderDate(LocalDateTime.now());
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    private String formatOrderDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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

    public static int getOrderCounter() {
        return orderCounter;
    }

    public static void setOrderCounter(int orderCounter) {
        Order.orderCounter = orderCounter;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(this.orderNumber, o.orderNumber);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                " - Order number: " + orderNumber + "\n"
                + " - User: " + user.getUsername() + "\n"
                + " - Books: [\n"
        );

        for (int i = 0; i < books.size(); i++) {
            if (i == books.size() - 1) {
                builder.append("        {").append(books.get(i)).append("}\n");
            } else {
                builder.append("        {").append(books.get(i)).append("},\n");
            }
        }
        builder.append("   ]\n")
                .append(" - Status: ").append(status).append("\n")
                .append(" - Order date: ").append(orderDate);

        return builder.toString();
    }
}