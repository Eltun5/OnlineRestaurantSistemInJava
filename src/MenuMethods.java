import java.util.Scanner;

public class MenuMethods {
    public static void manageMenu() {
        Scanner sr = new Scanner(System.in);
        System.out.print("""
                                        
                                         Manage Menu
                                         
                Add Menu Item(1),
                Display Menu(2),
                Update Menu Item(3),
                Delete Menu Item(4),
                Move Back(5).
                Select one of the options(int):""");
        int UserChoice = sr.nextInt();
        switch (UserChoice) {
            case 1 -> addItem();
            case 2 -> Menu.getMenu();
            case 3 -> updateItem();
            case 4 -> deleteItem();
            case 5 -> Main.StartProgram();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                manageMenu();
            }
        }
    }

    public static void addItem() {
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        System.out.print("\n                         Add Item In Menu\n" +
                         "\nAppetizer(1)(There are " + (Menu.getNumOfItem() - Menu.appetizerNum()) + " empty space)," +
                         "\nMain Courser(2)(There are " + (Menu.getNumOfItem() - Menu.mainCourserNum()) + " empty space)," +
                         "\nDessert(3)(There are " + (Menu.getNumOfItem() - Menu.dessertNum()) + " empty space)," +
                         "\nMove Back(4)." +
                         "\nSelect one of the options(int):");
        int UserChoice = sr.nextInt();
        if (UserChoice == 1 && Menu.getNumOfItem() - Menu.appetizerNum() == 0) {
            System.out.print("""
                    This category is full.
                    Please select another category or delete some items in Appetizer category.
                    """);
            addItem();
        } else if (UserChoice == 2 && Menu.getNumOfItem() - Menu.mainCourserNum() == 0) {
            System.out.print("""
                    This category is full.
                    Please select another category or delete some items in Main Courser category.
                    """);
            addItem();
        } else if (UserChoice == 3 && Menu.getNumOfItem() - Menu.dessertNum() == 0) {
            System.out.print("""
                    This category is full.
                    Please select another category or delete some items in Dessert category.
                    """);
            addItem();
        }
        if (UserChoice == 1 || UserChoice == 2 || UserChoice == 3) {
            System.out.print("""

                    Please enter Name(String)
                    Then enter Price(double or int):""");
        }
        switch (UserChoice) {
            case 1 -> Menu.setMenuItem(0, sr1.nextLine(), sr.nextDouble());
            case 2 -> Menu.setMenuItem(1, sr1.nextLine(), sr.nextDouble());
            case 3 -> Menu.setMenuItem(2, sr1.nextLine(), sr.nextDouble());
            case 4 -> manageMenu();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                addItem();
            }
        }
    }

    public static void updateItem() {
        if(Menu.getNumThereAreMenuItem(0)==0&&
           Menu.getNumThereAreMenuItem(1)==0&&
           Menu.getNumThereAreMenuItem(2)==0){
            Menu.getMenu();
            Menu.go();
        }
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        System.out.print("""
                
                                         Update Item In Menu
                                
                Appetizer (1),
                Main Courser(2),
                Dessert(3),
                Move Back(4).
                Select one of the options(int):""");
        int UserChoice = sr.nextInt();
        if (UserChoice == 1 || UserChoice == 2 || UserChoice == 3) {
            System.out.print("""

                    Please enter the name you want to change(old)(String)
                    Then new name(String)
                    Then new price(double or int):""");
        }
        switch (UserChoice) {
            case 1 -> Menu.updateMenuItem(0, sr1.nextLine(), sr1.nextLine(), sr.nextDouble());
            case 2 -> Menu.updateMenuItem(1, sr1.nextLine(), sr1.nextLine(), sr.nextDouble());
            case 3 -> Menu.updateMenuItem(2, sr1.nextLine(), sr1.nextLine(), sr.nextDouble());
            case 4 -> manageMenu();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                updateItem();
            }
        }
    }

    public static void deleteItem() {
        if(Menu.getNumThereAreMenuItem(0)==0&&
           Menu.getNumThereAreMenuItem(1)==0&&
           Menu.getNumThereAreMenuItem(2)==0){
            Menu.getMenu();
            Menu.go();
        }
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        System.out.print("""
                
                                         Delete Item In Menu
                                
                Appetizer (1),
                Main Courser(2),
                Dessert(3),
                Move Back(4).
                Select one of the options(int):""");
        int UserChoice = sr.nextInt();
        if (UserChoice == 1 || UserChoice == 2 || UserChoice == 3) {
            System.out.print("\nPlease enter the name of the item you want to delete:");
        }
        switch (UserChoice) {
            case 1 -> Menu.deleteMenuItem(0, sr1.nextLine());
            case 2 -> Menu.deleteMenuItem(1, sr1.nextLine());
            case 3 -> Menu.deleteMenuItem(2, sr1.nextLine());
            case 4 -> manageMenu();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                updateItem();
            }
        }
    }
}