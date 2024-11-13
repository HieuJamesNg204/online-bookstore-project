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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User user = new User("John Doe", "johndoe@gmail.com", "204 Golden Avenue");
        List<Book> bookList = generateAvailableBooks();
        Queue<Order> orderQueue = new Queue<>();

        boolean done = false;
        int choice;

        while (!done) {
            System.out.println("OLINE BOOKSTORE");
            System.out.println("*************************************");
            System.out.println("*   1. Display available books      *");
            System.out.println("*   2. Search for a book by title   *");
            System.out.println("*   3. Order books                  *");
            System.out.println("*   4. Display current orders       *");
            System.out.println("*   5. Proceed to payment           *");
            System.out.println("*   6. Display order history        *");
            System.out.println("*   7. Search order history         *");
            System.out.println("*   8. Exit                         *");
            System.out.println("*************************************");
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

                    orderQueue.offer(new Order(user, booksToOrder));
                    System.out.println("Book(s) successfully added to order");

                    scanner.nextLine();
                    break;

                case 4:
                    if (orderQueue.isEmpty()) {
                        System.out.println("No orders made at the moment. Try adding some books to see the orders");
                    } else {
                        System.out.println(" ---Current orders--- ");
                        Queue<Order> tempOrderQueue = new Queue<>();
                        while (!orderQueue.isEmpty()) {
                            Order iteratedOrder = orderQueue.poll();
                            System.out.println(iteratedOrder);
                            System.out.println(" -------------------- ");
                            tempOrderQueue.offer(iteratedOrder);
                        }

                        while (!tempOrderQueue.isEmpty()) {
                            orderQueue.offer(tempOrderQueue.poll());
                        }
                    }

                    scanner.nextLine();
                    break;

                case 5:
                    if (orderQueue.isEmpty()) {
                        System.out.println("There's nothing to pay for. Try adding some books before proceeding!");
                    } else {
                        Order order = orderQueue.poll();
                        List<Book> orderedBooks = order.getBooks();
                        double totalPaid = 0;
                        for (int i = 0; i < orderedBooks.size(); i++) {
                            totalPaid += orderedBooks.get(i).getPrice();
                        }
                        order.setStatus("Paid");
                        user.getOrderHistory().add(order);
                        System.out.println("Payment is successful! Total paid: $" + totalPaid);
                    }

                    scanner.nextLine();
                    break;

                case 6:
                    System.out.println(" ---Order History---");
                    List<Order> orderList = user.getOrderHistory();
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
                    Order foundOrder = Searching.search(user.getOrderHistory(), orderToSearchFor);
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

        scanner.close();
    }
}