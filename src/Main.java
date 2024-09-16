import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        initializeItems();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (loggedInUser == null) {

                displayLoginMenu();
                int choice = getUserChoice(scanner);

                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        loginUser(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting Library System. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {

                displayMainMenu();
                int choice = getUserChoice(scanner);

                switch (choice) {
                    case 1:
                        displayItems();
                        break;
                    case 2:
                        borrowItem(scanner);
                        break;
                    case 3:
                        returnItem(scanner);
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        loggedInUser = null;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    // Initialize some items in the library
    private static void initializeItems() {
        items.add(new Book("B001", "Sapiens"));
        items.add(new DVD("B001", "Mission Impossible"));
        items.add(new Magazine("M001", "Time Magazine"));
        items.add(new Book("B002", "Educated"));
        items.add(new DVD("D002", "Along With The God"));
        items.add(new Magazine("M002", "Forbes Magazine"));
    }


    private static void displayLoginMenu() {
        System.out.println("\n Welcome to Lib Library");
        System.out.println("1. Register User");
        System.out.println("2. Login User");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }


    private static void displayMainMenu() {
        System.out.println("\n1. View Available Items");
        System.out.println("2. Borrow Item");
        System.out.println("3. Return Item");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");
    }


    private static int getUserChoice(Scanner scanner) {
        int choice = -1;
        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String id = scanner.next();
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        User newUser = new User(id, name, username, password);
        newUser.register();
        users.add(newUser);
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        for (User user : users) {
            try {
                user.login(username, password);
                loggedInUser = user;
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("User not found or invalid credentials.");
    }




    private static void displayItems() {
        System.out.println("\nAvailable Items:");
        for (Item item : items) {
            item.getInfo();
        }
    }


    private static void borrowItem(Scanner scanner) {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        displayItems();
        System.out.print("Enter the item ID to borrow: ");
        String itemId = scanner.next();

        for (Item item : items) {
            if (item.getId().equals(itemId)) {
                try {
                    item.borrow();
                    return;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Item not found.");
    }

    private static void returnItem(Scanner scanner) {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        displayItems();
        System.out.print("Enter the item ID to return: ");
        String itemId = scanner.next();

        for (Item item : items) {
            if (item.getId().equals(itemId)) {
                item.returnItem();
                return;
            }
        }
        System.out.println("Item not found.");
    }
}
