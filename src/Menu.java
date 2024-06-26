import java.util.Scanner;

public class Menu {
    private static final int numOfItem = 10;
    private static String[][] nameOfItem = new String[3][numOfItem];
    private static double[][] priceOfItem = new double[3][numOfItem];

    public static void setMenuItem(int a, String c, double d) {
        Scanner sr = new Scanner(System.in);
        while (d <= 0) {
            System.out.print("Please enter valid price(Positive(not 0) int):");
            d = sr.nextDouble();
        }
        Scanner sr1 = new Scanner(System.in);
        while (c==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            c = sr1.nextLine();
        }
        existItem(0, c);
        existItem(1, c);
        existItem(2, c);
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[a][i] == null) {
                nameOfItem[a][i] = c;
                priceOfItem[a][i] = d;
                break;
            }
        }
        getMenu();
        go();
    }

    public static void getMenu() {
        boolean haveItem = false;
        System.out.print("""
                                            Menu
                """);
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[0][i] != null) {
                if (i == 0) {
                    System.out.print("""
                                                     
                                                     Appetizer
                                                    
                            """);
                    haveItem = true;
                }
                System.out.println((i + 1) + ". " + nameOfItem[0][i] +
                                   "---------------------------------------------------" + priceOfItem[0][i] + " Azn");
            } else {
                break;
            }
        }
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[1][i] != null) {
                if (i == 0) {
                    System.out.print("""     
                                                               
                                                    Main Course
                                                                            
                            """);
                    haveItem = true;
                }
                System.out.println((i + 1) + ". " + nameOfItem[1][i] +
                                   "---------------------------------------------------" + priceOfItem[1][i] + " Azn");
            } else {
                break;
            }
        }

        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[2][i] != null) {
                if (i == 0) {
                    System.out.print("""   
                                                             
                                                    Desert
                                                                            
                            """);
                    haveItem = true;
                }
                System.out.println((i + 1) + ". " + nameOfItem[2][i] +
                                   "---------------------------------------------------" + priceOfItem[2][i] + " Azn");
            } else {
                break;
            }
        }
        if (!haveItem) {
            System.out.println("""
                                        
                    There is not any menu item.
                    Please add.
                    """);
        }
        MenuMethods.manageMenu();
    }

    public static void updateMenuItem(int a, String oldName, String newName, double newPrice) {
        Scanner sr = new Scanner(System.in);
        while (newPrice <= 0) {
            System.out.print("Please enter valid price(Positive(not 0) int):");
            newPrice = sr.nextDouble();
        }
        Scanner sr1 = new Scanner(System.in);
        while (oldName==null) {
            System.out.print("""
                    Please enter valid name you want to change(old)(String(pay attention cannot be null or "")):""");
            oldName = sr1.nextLine();
        }
        while (newName==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            newName = sr1.nextLine();
        }
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[a][i] != null) {
                if (nameOfItem[a][i].equals(oldName)) {
                    nameOfItem[a][i] = newName;
                    priceOfItem[a][i] = newPrice;
                    break;
                }
            } else if (nameOfItem[a][i] == null) {
                System.out.println("\nCannot find item which name=" + oldName +
                                   ".\nThere is empty space.\nIf you want you can add item.");
                MenuMethods.manageMenu();
                break;
            } else if (i == numOfItem - 1) {
                System.out.println("\nCannot find item which name=" + oldName +
                                   ".\nPlease enter an existing item name.");
                MenuMethods.manageMenu();
            }
        }
        getMenu();
    }

    public static void deleteMenuItem(int a, String name) {
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        while (name==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            name = sr1.nextLine();
        }
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[a][i] != null) {
                if (nameOfItem[a][i].equals(name)) {
                    System.out.print("Please enter 1 to confirm you want to delete, any int is not equals 1 to cancel:");
                    if (sr.nextInt() == 1) {
                        for (int j = i; j < numOfItem; j++) {
                            if (j == numOfItem - 1) {
                                nameOfItem[a][j] = null;
                                priceOfItem[a][j] = 0;
                            } else {
                                nameOfItem[a][j] = nameOfItem[a][j + 1];
                                priceOfItem[a][j] = priceOfItem[a][j + 1];
                            }
                        }
                    } else {
                        MenuMethods.manageMenu();
                    }
                    break;
                }
            } else if (i == numOfItem - 1) {
                System.out.println("Cannot find item which name=" + name +
                                   ".\nPlease enter an existing item name.");
            }
        }
        getMenu();
    }

    public static int appetizerNum() {
        return getNumThereAreMenuItem(0);
    }

    public static int mainCourserNum() {
        return getNumThereAreMenuItem(1);
    }

    public static int dessertNum() {
        return getNumThereAreMenuItem(2);
    }

    public static int getNumThereAreMenuItem(int a) {
        int num = 0;
        for (String name : nameOfItem[a]) {
            if (name != null) {
                num++;
            }
        }
        return num;
    }

    public static int getNumOfItem() {
        return numOfItem;
    }

    public static boolean checkHaveItem(int a, String newName) {
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[a][i] != null) {
                if (nameOfItem[a][i].equals(newName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static double getPrice(String newName) {
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[0][i] != null) {
                if (nameOfItem[0][i].equals(newName)) {
                    return priceOfItem[0][i];
                }
            }
        }
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[1][i] != null) {
                if (nameOfItem[1][i].equals(newName)) {
                    return priceOfItem[1][i];
                }
            }
        }
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[2][i] != null) {
                if (nameOfItem[2][i].equals(newName)) {
                    return priceOfItem[2][i];
                }
            }
        }
        return 0;
    }

    public static void go() {
        Scanner sr = new Scanner(System.in);
        System.out.print("""
                                

                Main Menu(1),
                Manage Menu(2),
                Add Menu Item(3)
                Select one of the options(int):""");
        int UserChoice = sr.nextInt();
        switch (UserChoice) {
            case 1 -> Main.StartProgram();
            case 2 -> MenuMethods.manageMenu();
            case 3 -> MenuMethods.addItem();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                go();
            }
        }
    }

    public static void existItem(int a, String c) {
        Scanner sr = new Scanner(System.in);
        for (int i = 0; i < numOfItem; i++) {
            if (nameOfItem[a][i] != null) {
                if (nameOfItem[a][i].equals(c)) {
                    if (a == 0) {
                        System.out.print("There is item in the Appetizer category which name is " + c + ".\n" +
                                         "\nAdd Item(1)," +
                                         "\nUpdate Item(2)." +
                                         "\nManage Menu(3)." +
                                         "\nPlease enter what do you want(int):");
                    } else if (a == 1) {
                        System.out.print("There is item in the Main Course category which name is " + c + ".\n" +
                                         "\nAdd Item(1)," +
                                         "\nUpdate Item(2)." +
                                         "\nManage Menu(3)." +
                                         "\nPlease enter what do you want(int):");
                    } else if (a == 2) {
                        System.out.print("There is item in the Dessert category which name is " + c + ".\n" +
                                         "\nAdd Item(1)," +
                                         "\nUpdate Item(2)." +
                                         "\nManage Menu(3)." +
                                         "\nPlease enter what do you want(int):");
                    }
                    switch (sr.nextInt()) {
                        case 1 -> MenuMethods.addItem();
                        case 2 -> MenuMethods.updateItem();
                        case 3 -> MenuMethods.manageMenu();
                        default -> {
                            System.out.print("""

                                    Your enter is not 1,2 or 3.
                                    Move manage menu.""");
                            MenuMethods.manageMenu();
                        }

                    }

                }
            }
        }
    }
}