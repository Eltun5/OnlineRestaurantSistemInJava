import java.util.Scanner;

public class Order {
    private static final int numOfOrder = 100;
    private static int[][] orderIdAndQuantity = new int[numOfOrder][2];
    private static String[] orderName = new String[numOfOrder];
    private static double totalPrice;

    public static void addOrderItem(int a) {
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        System.out.print("Please enter food name:");
        String newName = sr1.nextLine();
        while (newName==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            newName= sr1.nextLine();
        }
        for (int i = 0; i < numOfOrder; i++) {
            if (orderName[i] != null) {
                if (orderName[i].equals(newName)) {
                    System.out.print("Please enter food quantity(Positive(not 0) int num):");
                    int newQuantity = sr.nextInt();
                    while (newQuantity <= 0) {
                        System.out.print("Invalid input.\n" +
                                         "Please enter food quantity(Positive(not 0) int num):");
                        newQuantity = sr.nextInt();
                    }
                    orderIdAndQuantity[i][1] += newQuantity;
                    totalPriceCalculator();
                    break;
                }
            }
            if (orderName[i] == null) {
                if (Menu.checkHaveItem(a, newName)) {
                    orderName[i] = newName;
                } else {
                    System.out.print("Please enter correct name(pay attention space , upper and lower case).");
                    addOrderItem(a);
                }
                orderIdAndQuantity[i][0] = i + 1;
                System.out.print("Please enter food quantity(Positive(not 0) int num):");
                int newQuantity = sr.nextInt();
                while (newQuantity <= 0) {
                    System.out.print("Invalid input.\n" +
                                     "Please enter food quantity(Positive(not 0) int num):");
                    newQuantity = sr.nextInt();
                }
                orderIdAndQuantity[i][1] = newQuantity;
                totalPriceCalculator();
                break;
            } else if (i == numOfOrder - 1 && orderName[i] != null) {
                System.out.println("This list is full.Please delete or update one of them.");
                MenuMethods.manageMenu();
            }
        }
        getOrder();
        go();
    }

    public static void getOrder() {
        boolean haveOrder = false;
        System.out.print("""
                                         
                                         Order
                                                                                                                  
                """);
        for (int i = 0; i < numOfOrder; i++) {
            if (orderName[i] != null) {
                System.out.println(orderIdAndQuantity[i][0] + ". " + orderName[i] +
                                   "---------------------------------------------------" +
                                   orderIdAndQuantity[i][1] + "(" +
                                   (orderIdAndQuantity[i][1] * Menu.getPrice(orderName[i])) + " Azn)");
                haveOrder = true;
            } else {
                break;
            }
        }

        if (!haveOrder) {
            System.out.println("""
                                        
                    There is not any Order.
                    Please add.
                    """);
            go();
        } else {
            System.out.print("\nTotal :" + totalPrice + " Azn.\n");
        }
    }

    public static void updateOrderItem() {
        if (getNumThereAreOrder() == 0) {
            getOrder();
            go();
        }
        Scanner sr1 = new Scanner(System.in);
        Scanner sr = new Scanner(System.in);
        System.out.print("\nPlease enter the name of order:");
        String oldName = sr1.nextLine();
        while (oldName==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            oldName= sr1.nextLine();
        }
        for (int i = 0; i < numOfOrder; i++) {
            if (orderName[i] != null) {
                if (orderName[i].equals(oldName)) {
                    System.out.print("\nPlease enter the name of new order:");
                    orderName[i] = sr1.nextLine();
                    while (orderName[i]==null) {
                        System.out.print("""
                    Please enter valid new name(String(pay attention cannot be null or "")):""");
                        orderName[i]= sr1.nextLine();
                    }
                    System.out.print("\nPlease enter the quantity of new order:");
                    orderIdAndQuantity[i][1] = sr.nextInt();
                    while (orderIdAndQuantity[i][1] <= 0) {
                        System.out.print("Invalid input.\n" +
                                         "Please enter food quantity(Positive(not 0) int num):");
                        orderIdAndQuantity[i][1] = sr.nextInt();
                    }
                    totalPriceCalculator();
                }
            } else if (orderName[i] == null) {
                System.out.println("""
                        This order cannot find.
                        Please create order or enter correct name of order.
                        """);
                go();
                OrderMethods.processOrder();
            } else {
                System.out.println("""
                        This order cannot find.
                        Please cancel order or enter correct name of order.
                        """);
                OrderMethods.processOrder();
            }
        }
    }

    public static void deleteOrderItem() {
        if (getNumThereAreOrder() == 0) {
            getOrder();
            go();
        }
        Scanner sr1 = new Scanner(System.in);
        Scanner sr = new Scanner(System.in);
        System.out.print("\nPlease enter the name you want to cancel:");
        String oldName = sr1.nextLine();
        while (oldName==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            oldName= sr1.nextLine();
        }
        for (int i = 0; i < numOfOrder; i++) {
            if (orderName[i] != null) {
                if (orderName[i].equals(oldName)) {
                    System.out.print("Please enter 1 to confirm you want to delete, any int is not equals 1 to cancel:");
                    if (sr.nextInt() == 1) {
                        for (int j = i; j < numOfOrder; j++) {
                            if (j == numOfOrder - 1) {
                                orderName[j] = null;
                                orderIdAndQuantity[j][1] = 0;
                            } else {
                                orderName[j] = orderName[j + 1];
                                orderIdAndQuantity[j][1] = orderIdAndQuantity[j + 1][1];
                            }
                        }
                        totalPriceCalculator();
                    } else {
                        OrderMethods.processOrder();
                    }
                    break;
                }
            } else {
                System.out.println("This order cannot find.");
                OrderMethods.processOrder();
            }
        }
    }

    public static void finalizeOrder() {
        if (getNumThereAreOrder() == 0) {
            getOrder();
            go();
        }
        Scanner sr = new Scanner(System.in);
        getOrder();
        System.out.print("Please enter money:");
        double money = sr.nextDouble();
        while (totalPrice > money) {
            System.out.print("Please enter money grater than or equals " + totalPrice + " : ");
            money = sr.nextDouble();
        }
        if (money > totalPrice) {
            System.out.print("Would you like to give the change as a bet?\n yes-1 \n no-any int is not equals 1 \n ");
            if (sr.nextInt() == 1) {
                System.out.print("Thank you so much.");
            } else {
                System.out.println("Change:" + (money - totalPrice));
            }
        }
        for (int i = 0; i < numOfOrder; i++) {
            orderName[i] = null;
            orderIdAndQuantity[i][1] = 0;
        }
        totalPriceCalculator();
    }

    public static void totalPriceCalculator() {
        totalPrice = 0;
        for (int i = 0; i < numOfOrder; i++) {
            if (orderName[i] != null) {
                totalPrice += orderIdAndQuantity[i][1] * Menu.getPrice(orderName[i]);
            } else {
                break;
            }
        }
    }

    public static int getNumThereAreOrder() {
        int num = 0;
        for (String name : orderName) {
            if (name != null) {
                num++;
            }
        }
        return num;
    }

    public static int getNumOfOrder() {
        return numOfOrder;
    }

    public static void go() {
        Scanner sr = new Scanner(System.in);
        System.out.print("\nMain Menu(1)," +
                         "\nProcess Order(2)," +
                         "\nNew Orders(3)(There are " + (Order.getNumOfOrder() - Order.getNumThereAreOrder()) +
                         " empty space)," +
                         "\nSelect one of the options(int):");
        int UserChoice = sr.nextInt();
        if (UserChoice == 3 && Order.getNumOfOrder() - Order.getNumThereAreOrder() == 0) {
            System.out.print("""
                    Order is full.
                    Please select another option or delete some order.
                    """);
            go();
        }
        switch (UserChoice) {
            case 1 -> {
            }
            case 2 -> OrderMethods.processOrder();
            case 3 -> OrderMethods.createOrder();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                go();
            }
        }
    }
}
