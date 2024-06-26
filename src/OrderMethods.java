import java.util.Scanner;

public class OrderMethods {
    public static void processOrder() {
        Scanner sr = new Scanner(System.in);
        System.out.print("\nNew Orders(1)(There are " + (Order.getNumOfOrder()-Order.getNumThereAreOrder()) + " empty space)," +
                         "\nView Orders(2)," +
                         "\nUpdate Order(3)," +
                         "\nCancel Order(4)," +
                         "\nFinalize Order(5)," +
                         "\nMove Back(6)." +
                         "\nSelect one of the options(int):");
        int UserChoice = sr.nextInt();
        if (UserChoice == 1 && Order.getNumOfOrder()-Order.getNumThereAreOrder() == 0) {
            System.out.print("""
                    Order is full.
                    Please select another option or delete some order.
                    """);
            processOrder();
        }
        switch (UserChoice) {
            case 1 -> createOrder();
            case 2 -> Order.getOrder();
            case 3 -> Order.updateOrderItem();
            case 4 -> Order.deleteOrderItem();
            case 5 -> Order.finalizeOrder();
            case 6 -> Main.StartProgram();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                processOrder();
            }
        }
    }

    public static void createOrder() {
        Scanner sr = new Scanner(System.in);
        Menu.getMenu();
        System.out.print("""
                
                                         Process Order
                                
                Appetizer (1),
                Main Courser(2),
                Dessert(3),
                Move Back(4).
                Select one of the options(int):""");
        switch (sr.nextInt()) {
            case 1 -> Order.addOrderItem(0);
            case 2 -> Order.addOrderItem(1);
            case 3 -> Order.addOrderItem(2);
            case 4 -> processOrder();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                createOrder();
            }
        }
    }
}


