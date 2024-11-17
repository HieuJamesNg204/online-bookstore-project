import models.Book;
import models.Order;
import models.User;
import utils.List;
import utils.Queue;
import utils.Searching;
import utils.Sorting;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static List<Book> generateAvailableBooks() {
        List<Book> bookList = new List<>();

        bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", 15.99, 120));
        bookList.add(new Book("1984", "George Orwell", 12.45, 80));
        bookList.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99, 200));
        bookList.add(new Book("Pride and Prejudice", "Jane Austen", 8.99, 150));
        bookList.add(new Book("Moby Dick", "Herman Melville", 13.50, 90));
        bookList.add(new Book("The Catcher in the Rye", "J.D. Salinger", 9.99, 100));
        bookList.add(new Book("The Hobbit", "J.R.R. Tolkien", 14.95, 75));
        bookList.add(new Book(
                "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 20.00, 300
        ));
        bookList.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 25.00, 50));
        bookList.add(new Book("The Alchemist", "Paulo Coelho", 11.20, 180));

        return bookList;
    }

    public static User login(Scanner scanner, List<User> userList) {
        Sorting.quickSort(userList, 0, userList.size() - 1);

        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = Searching.search(userList, new User(username, password));

            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Logged in successfully!");
                return user;
            }

            System.out.println("Wrong username or password");
        }
    }

    public static User register(Scanner scanner, List<User> userList) {
        Sorting.quickSort(userList, 0, userList.size() - 1);

        String username, email, address, password, confirmedPassword, role;
        while (true) {
            System.out.print("Username: ");
            username = scanner.nextLine();
            if (Searching.search(userList, new User(username, ">w9ZYb&$g%Gq")) == null) {
                break;
            }
            System.out.println("Username already taken");
        }

        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.print("Address: ");
        address = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();

        do {
            System.out.print("Confirm Password: ");
            confirmedPassword = scanner.nextLine();

            if (!confirmedPassword.equals(password)) {
                System.out.println("Passwords do not match. Please try again!");
            }
        } while (!confirmedPassword.equals(password));

        System.out.print("Role (Leaving this empty will default to 'Customer'): ");
        role = scanner.nextLine();

        if (role.isEmpty()) {
            role = "Customer";
        }

        System.out.println("Registered successfully!");
        return new User(username, email, address, password, role);
    }

    public static void appCustomer(Scanner scanner, List<Book> bookList, Queue<Order> allOrders, User loggedInUser) {
        boolean done = false;
        int choice;
        while (!done) {
            System.out.println("OLINE BOOKSTORE");
            System.out.println("********************************************");
            System.out.println("*   1. Display available books             *");
            System.out.println("*   2. Search for a book by title          *");
            System.out.println("*   3. Order books                         *");
            System.out.println("*   4. Display current orders              *");
            System.out.println("*   5. Proceed to payment for all orders   *");
            System.out.println("*   6. Display order history               *");
            System.out.println("*   7. Search order history                *");
            System.out.println("*   8. Log out                             *");
            System.out.println("********************************************");
            while (true) {
                try {
                    System.out.print("Your choice: ");
                    choice = scanner.nextInt();

                    if (1 <= choice && choice <= 8) {
                        break;
                    }
                    System.out.println("Your choice was invalid. Please try again!");
                } catch (InputMismatchException e) {
                    System.out.println("Your choice was not a valid positive integer from 1 to 8. Please try again!");
                    scanner.next();
                } catch (Exception e) {
                    System.out.println("There was an unexpected exception thrown.");
                    e.printStackTrace();
                }
            }

            scanner.nextLine();

            String title;
            switch (choice) {
                case 1:
                    if (bookList.isEmpty()) {
                        System.out.println("Oops! No books available at the moment. Check back soon for new listings");
                    } else {
                        Sorting.quickSort(bookList, 0, bookList.size() - 1);
                        System.out.println(" ---Available Books--- ");
                        for (int i = 0; i < bookList.size(); i++) {
                            System.out.println(" - " + bookList.get(i));
                        }
                    }

                    scanner.nextLine();
                    break;

                case 2:
                    System.out.print("Book title to search for: ");
                    title = scanner.nextLine();
                    Book bookToSearchFor = new Book(title);
                    Sorting.quickSort(bookList, 0, bookList.size() - 1);
                    Book foundBook = Searching.search(bookList, bookToSearchFor);
                    if (foundBook != null) {
                        System.out.println(" ---Book info--- ");
                        System.out.println(foundBook);
                    } else {
                        System.out.println("No such book available in the store!");
                    }

                    scanner.nextLine();
                    break;

                case 3:
                    boolean isAddingMoreBooks = true;
                    List<Book> booksToOrder = new List<>();
                    while (isAddingMoreBooks) {
                        Book chosenBook;
                        while (true) {
                            System.out.print("Book title: ");
                            title = scanner.nextLine();

                            chosenBook = Searching.search(bookList, new Book(title));

                            if (chosenBook != null) {
                                break;
                            }
                            System.out.println("Book not found! Please enter another book!");
                        }

                        int quantity;
                        while (true) {
                            try {
                                System.out.print("Quantity: ");
                                quantity = scanner.nextInt();

                                if (quantity <= 0) {
                                    System.out.println("The quantity wasn't positive as expected. Please try again!");
                                } else if (quantity > chosenBook.getQuantity()) {
                                    System.out.println("The quantity of the chosen book is not sufficient. " +
                                            "Please try again!"
                                    );
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Your choice was not a valid positive integer. " +
                                        "Please try again!"
                                );
                                scanner.next();
                            } catch (Exception e) {
                                System.out.println("There was an unexpected exception thrown.");
                                e.printStackTrace();
                            }
                        }

                        scanner.nextLine();

                        chosenBook.setQuantity(chosenBook.getQuantity() - quantity);
                        booksToOrder.add(new Book(
                                chosenBook.getTitle(),
                                chosenBook.getAuthor(),
                                chosenBook.getPrice() * quantity,
                                quantity
                        ));

                        if (chosenBook.getQuantity() == 0) {
                            bookList.remove(Searching.searchReturningIndex(bookList, chosenBook));
                        }

                        System.out.print("Add more book (y/n)? ");
                        String confirm = scanner.nextLine();

                        if (confirm.toLowerCase().contains("n")) {
                            isAddingMoreBooks = false;
                        }
                    }

                    Order addedOrder = new Order(loggedInUser, booksToOrder);
                    loggedInUser.getCurrentOrder().offer(addedOrder);
                    allOrders.offer(addedOrder);
                    System.out.println("Book(s) successfully added to order");

                    scanner.nextLine();
                    break;

                case 4:
                    if (loggedInUser.getCurrentOrder().isEmpty()) {
                        System.out.println("No orders made at the moment. Try adding some books to see the orders");
                    } else {
                        System.out.println(" ---Current orders--- ");
                        Queue<Order> tempOrderQueue = new Queue<>();
                        while (!loggedInUser.getCurrentOrder().isEmpty()) {
                            Order iteratedOrder = loggedInUser.getCurrentOrder().poll();
                            System.out.println(iteratedOrder);
                            System.out.println(" -------------------- ");
                            tempOrderQueue.offer(iteratedOrder);
                        }

                        while (!tempOrderQueue.isEmpty()) {
                            loggedInUser.getCurrentOrder().offer(tempOrderQueue.poll());
                        }
                    }

                    scanner.nextLine();
                    break;

                case 5:
                    String paymentRes = "There's nothing to pay for. Try adding some books before proceeding!";
                    if (loggedInUser.getCurrentOrder().isEmpty()) {
                        System.out.println(paymentRes);
                    } else {
                        double totalPaid = 0;
                        Queue<Order> tempQueue = new Queue<>();

                        while (!loggedInUser.getCurrentOrder().isEmpty()) {
                            Order order = loggedInUser.getCurrentOrder().poll();
                            if (!order.isPaid()) {
                                List<Book> orderedBooks = order.getBooks();
                                for (int i = 0; i < orderedBooks.size(); i++) {
                                    totalPaid += orderedBooks.get(i).getPrice();
                                }
                                order.setPaid(true);
                                paymentRes = "Payment is successful! Total paid: $" + totalPaid;
                            }
                            tempQueue.offer(order);
                        }

                        while (!tempQueue.isEmpty()) {
                            loggedInUser.getCurrentOrder().offer(tempQueue.poll());
                        }

                        System.out.println(paymentRes);
                    }

                    scanner.nextLine();
                    break;

                case 6:
                    System.out.println(" ---Order History--- ");
                    List<Order> orderList = loggedInUser.getOrderHistory();
                    if (orderList.isEmpty()) {
                        System.out.println("No orders have been made so far.");
                    } else {
                        for (int i = 0; i < orderList.size(); i++) {
                            System.out.println(orderList.get(i));
                            System.out.println(" -------------------- ");
                        }
                    }

                    scanner.nextLine();
                    break;

                case 7:
                    int orderNumber;
                    while (true) {
                        try {
                            System.out.print("Order number to search for: ");
                            orderNumber = scanner.nextInt();

                            if (orderNumber > 0) {
                                break;
                            }
                            System.out.println("The input was not an positive integer as expected. Please try again!");
                        } catch (InputMismatchException e) {
                            System.out.println("Your choice was not a valid positive integer. Please try again!");
                            scanner.next();
                        } catch (Exception e) {
                            System.out.println("There was an unexpected exception thrown.");
                            e.printStackTrace();
                        }
                    }

                    scanner.nextLine();

                    Order orderToSearchFor = new Order(orderNumber);
                    Order foundOrder = Searching.search(loggedInUser.getOrderHistory(), orderToSearchFor);
                    if (foundOrder != null) {
                        System.out.println(" ---Order Info--- ");
                        System.out.println(foundOrder);
                    } else {
                        System.out.println("This order hasn't been made before");
                    }

                    scanner.nextLine();
                    break;

                case 8:
                    done = true;
                    break;
            }
        }
    }

    public static void appAdmin(Scanner scanner, List<Book> bookList,
                                Queue<Order> allOrders, List<User> registeredUser, User loggedInUser) {
        boolean done = false;
        int choice;
        while (!done) {
            System.out.println("ONLINE BOOKSTORE");
            System.out.println("*********************************************");
            System.out.println("*   1. Add a new book                       *");
            System.out.println("*   2. Get all books                        *");
            System.out.println("*   3. Search for books by title            *");
            System.out.println("*   4. Update books                         *");
            System.out.println("*   5. Delete books                         *");
            System.out.println("*   6. View orders by different customers   *");
            System.out.println("*   7. Process next customer order          *");
            System.out.println("*   8. Log out                              *");
            System.out.println("*********************************************");
            while (true) {
                try {
                    System.out.print("Your choice: ");
                    choice = scanner.nextInt();

                    if (1 <= choice && choice <= 8) {
                        break;
                    }
                    System.out.println("Your choice was invalid. Please try again!");
                } catch (InputMismatchException e) {
                    System.out.println("Your choice was not a valid positive integer from 1 to 7. Please try again!");
                    scanner.next();
                } catch (Exception e) {
                    System.out.println("There was an unexpected exception thrown.");
                    e.printStackTrace();
                }
            }

            scanner.nextLine();

            String title;
            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();

                    double price;
                    while (true) {
                        try {
                            System.out.print("Price: ");
                            price = scanner.nextDouble();

                            if (price >= 0) {
                                break;
                            }
                            System.out.println("The price was negative. Please try again!");
                        } catch (InputMismatchException e) {
                            System.out.println("Your choice was not a valid decimal number. Please try again!");
                            scanner.next();
                        } catch (Exception e) {
                            System.out.println("There was an unexpected exception thrown.");
                            e.printStackTrace();
                        }
                    }
                    scanner.nextLine();

                    int quantity;
                    while (true) {
                        try {
                            System.out.print("Quantity: ");
                            quantity = scanner.nextInt();

                            if (quantity > 0) {
                                break;
                            }
                            System.out.println("The input was not a positive number as expected.");
                        } catch (InputMismatchException e) {
                            System.out.println("Your choice was not a valid positive integer. Please try again!");
                            scanner.next();
                        } catch (Exception e) {
                            System.out.println("There was an unexpected exception thrown.");
                            e.printStackTrace();
                        }
                    }
                    scanner.nextLine();

                    bookList.add(new Book(title, author, price, quantity));
                    System.out.println("Book added");

                    scanner.nextLine();
                    break;

                case 2:
                    Sorting.quickSort(bookList, 0, bookList.size() - 1);
                    System.out.println(" ---All available books---");
                    for (int i = 0; i < bookList.size(); i++) {
                        System.out.println(" - " + bookList.get(i));
                    }
                    break;

                case 3:
                    System.out.print("Book title to search for: ");
                    title = scanner.nextLine();
                    Book bookToSearchFor = new Book(title);
                    Sorting.quickSort(bookList, 0, bookList.size() - 1);
                    Book foundBook = Searching.search(bookList, bookToSearchFor);
                    if (foundBook != null) {
                        System.out.println(" ---Book info--- ");
                        System.out.println(foundBook);
                    } else {
                        System.out.println("No such book available!");
                    }

                    scanner.nextLine();
                    break;

                case 4:
                    System.out.print("Book title to search for: ");
                    title = scanner.nextLine();
                    Sorting.quickSort(bookList, 0, bookList.size() - 1);
                    Book bookToUpdate = Searching.search(bookList, new Book(title));

                    if (bookToUpdate != null) {
                        System.out.print("New title: ");
                        bookToUpdate.setTitle(scanner.nextLine());
                        System.out.print("New author: ");
                        bookToUpdate.setAuthor(scanner.nextLine());

                        double newPrice;
                        while (true) {
                            try {
                                System.out.print("New price: ");
                                newPrice = scanner.nextDouble();
                                if (newPrice >= 0) {
                                    break;
                                }
                                System.out.println("The price was negative. Please try again!");
                            } catch (InputMismatchException e) {
                                System.out.println("Your choice was not a valid decimal number. Please try again!");
                                scanner.next();
                            } catch (Exception e) {
                                System.out.println("There was an unexpected exception thrown.");
                                e.printStackTrace();
                            }
                        }
                        bookToUpdate.setPrice(newPrice);
                        scanner.nextLine();

                        int newQuantity;
                        while (true) {
                            try {
                                System.out.print("New quantity: ");
                                newQuantity = scanner.nextInt();

                                if (newQuantity > 0) {
                                    break;
                                }
                                System.out.println("The input was not a positive number as expected.");
                            } catch (InputMismatchException e) {
                                System.out.println("Your choice was not a valid positive integer. Please try again!");
                                scanner.next();
                            } catch (Exception e) {
                                System.out.println("There was an unexpected exception thrown.");
                                e.printStackTrace();
                            }
                        }
                        bookToUpdate.setQuantity(newQuantity);
                        scanner.nextLine();

                        System.out.println("Book updated!");
                    } else {
                        System.out.println("Book not found!");
                    }

                    scanner.nextLine();
                    break;

                case 5:
                    System.out.print("Book title to search for: ");
                    title = scanner.nextLine();
                    Sorting.quickSort(bookList, 0, bookList.size() - 1);
                    int index = Searching.searchReturningIndex(bookList, new Book(title));
                    if (index == -1) {
                        System.out.println("Book not found!");
                    } else {
                        bookList.remove(index);
                        System.out.println("Book removed!");
                    }

                    scanner.nextLine();
                    break;

                case 6:
                    if (allOrders.isEmpty()) {
                        System.out.println("No orders made by any users at the moment.");
                    } else {
                        System.out.println(" ---Current orders--- ");
                        Queue<Order> tempOrderQueue = new Queue<>();
                        while (!allOrders.isEmpty()) {
                            Order iteratedOrder = allOrders.poll();
                            System.out.println(iteratedOrder);
                            System.out.println(" -------------------- ");
                            tempOrderQueue.offer(iteratedOrder);
                        }

                        while (!tempOrderQueue.isEmpty()) {
                            allOrders.offer(tempOrderQueue.poll());
                        }
                    }

                    scanner.nextLine();
                    break;

                case 7:
                    if (allOrders.isEmpty()) {
                        System.out.println("There are no orders to process.");
                    } else {
                        if (allOrders.peek().isPaid()) {
                            Order processedOrder = allOrders.poll();
                            User userMakingTheOrder = processedOrder.getUser();
                            processedOrder.setProcessed(true);
                            userMakingTheOrder.getCurrentOrder().poll();
                            userMakingTheOrder.getOrderHistory().add(processedOrder);
                            System.out.println("Order successfully processed");
                        } else {
                            System.out.println("Failed to process the next order because the customer hasn't paid for " +
                                    "it yet.");
                        }
                    }

                    scanner.nextLine();
                    break;

                case 8:
                    done = true;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<User> registeredUsers = new List<>();
        User loggedInUser;

        List<Book> bookList = generateAvailableBooks();
        Queue<Order> orderQueue = new Queue<>();

        boolean done = false;
        int choice;

        while (!done) {
            System.out.println("ONLINE BOOKSTORE");
            System.out.println("*******************");
            System.out.println("*   1. Log in     *");
            System.out.println("*   2. Register   *");
            System.out.println("*   3. Exit       *");
            System.out.println("*******************");
            while (true) {
                try {
                    System.out.print("Your choice: ");
                    choice = scanner.nextInt();

                    if (1 <= choice && choice <= 3) {
                        break;
                    }
                    System.out.println("Your choice was invalid. Please try again!");
                } catch (InputMismatchException e) {
                    System.out.println("Your choice was not a valid positive integer from 1 to 3. Please try again!");
                    scanner.next();
                } catch (Exception e) {
                    System.out.println("There was an unexpected exception thrown.");
                    e.printStackTrace();
                }
            }

            scanner.nextLine();

            switch (choice) {
                case 1:
                    loggedInUser = login(scanner, registeredUsers);
                    if (loggedInUser.getRole().equalsIgnoreCase("customer")) {
                        appCustomer(scanner, bookList, orderQueue, loggedInUser);
                    } else {
                        appAdmin(scanner, bookList, orderQueue, registeredUsers, loggedInUser);
                    }
                    break;

                case 2:
                    registeredUsers.add(register(scanner, registeredUsers));
                    break;

                case 3:
                    done = true;
                    break;
            }
        }

        scanner.close();
    }
}