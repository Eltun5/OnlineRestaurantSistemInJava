import java.util.Scanner;

public class Reservation {
    private static final int numOfReservation = 15;
    private static String[][] nameReservation = new String[numOfReservation][2];
    private static int[][] dateAndTime = new int[numOfReservation][5];

    public static void addReservation() {
        if (getNumOfReservation() - getNumThereAreReservation() != 0) {
            System.out.print("\nThere is " + (getNumOfReservation() - getNumThereAreReservation()) + " empty reservation place.");
            for (int i = 0; i < numOfReservation; i++) {
                if (nameReservation[i][1] == null) {
                    addItem(i);
                    break;
                }
            }
        } else {
            System.out.println("""

                    There is not empty table.
                    Please cancel Reservation.""");
            ReservationMethods.handleReservations();
        }
        getReservations();
        go();
    }

    public static void getReservations() {
        boolean haveReservation = false;
        System.out.print("""
                                         
                                         Reservation
                                                                                                                  
                """);
        for (int i = 0; i < numOfReservation; i++) {
            if (nameReservation[i][0] != null) {
                if (dateAndTime[i][3] > 9 && dateAndTime[i][4] > 9) {
                    System.out.println((i + 1) + ". Name: " + nameReservation[i][0] +
                                       "\n   Surname: " + nameReservation[i][1] +
                                       "\n   Date: " + dateAndTime[i][0] + "-" + dateAndTime[i][1] + "-" + dateAndTime[i][2] +
                                       "\n   Time: " + dateAndTime[i][3] + ":" + dateAndTime[i][4] + ".");
                } else {
                    if (dateAndTime[i][3] < 9 && dateAndTime[i][4] < 9) {
                        System.out.println((i + 1) + ". Name: " + nameReservation[i][0] +
                                           "\n   Surname: " + nameReservation[i][1] +
                                           "\n   Date: " + dateAndTime[i][0] + "-" + dateAndTime[i][1] + "-" + dateAndTime[i][2] +
                                           "\n Time: 0" + dateAndTime[i][3] + ":0" + dateAndTime[i][4] + ".");
                    } else if (dateAndTime[i][3] < 9) {
                        System.out.println((i + 1) + ". Name: " + nameReservation[i][0] +
                                           "\n   Surname: " + nameReservation[i][1] +
                                           "\n   Date: " + dateAndTime[i][0] + "-" + dateAndTime[i][1] + "-" + dateAndTime[i][2] +
                                           "\n   Time: 0" + dateAndTime[i][3] + ":" + dateAndTime[i][4] + ".");
                    } else if (dateAndTime[i][4] < 9) {
                        System.out.println((i + 1) + ". Name: " + nameReservation[i][0] +
                                           "\n   Surname: " + nameReservation[i][1] +
                                           "\n   Date: " + dateAndTime[i][0] + "-" + dateAndTime[i][1] + "-" + dateAndTime[i][2] +
                                           "\n   Time: " + dateAndTime[i][3] + ":0" + dateAndTime[i][4] + ".");
                    }
                }
                haveReservation = true;
            } else {
                break;
            }
        }
        if (!haveReservation) {
            System.out.println("""
                                        
                    There is not any Reservation.
                    Please add.
                    """);
            go();
        }
    }

    public static void updateReservations() {
        if (getNumThereAreReservation() == 0) {
            getReservations();
            go();
        }
        Scanner sr1 = new Scanner(System.in);
        System.out.print("\nPlease enter the name you want to change:");
        String oldName = sr1.nextLine();
        while (oldName==null) {
            System.out.print("""
                    Please enter valid name you want to change(String(pay attention cannot be null or "")):""");
            oldName= sr1.nextLine();
        }
        System.out.print("\nPlease enter the surname you want to change:");
        String oldSurname = sr1.nextLine();
        while (oldSurname==null) {
            System.out.print("""
                    Please enter valid surname you want to change(String(pay attention cannot be null or "")):""");
            oldSurname= sr1.nextLine();
        }
        for (int i = 0; i < numOfReservation; i++) {
            if (nameReservation[i][0]!=null && nameReservation[i][1]!=null) {
                if (nameReservation[i][0].equals(oldName) && nameReservation[i][1].equals(oldSurname)) {
                    addItem(i);
                    break;
                }
            }
        }
        getReservations();
    }

    public static void cancelReservation() {
        if (getNumThereAreReservation() == 0) {
            getReservations();
            go();
        }
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        System.out.print("\nPlease enter the name you want to cancel:");
        String oldName = sr1.nextLine();
        while (oldName==null) {
            System.out.print("""
                    Please enter valid name you want to cancel(String(pay attention cannot be null or "")):""");
            oldName= sr1.nextLine();
        }
        System.out.print("\nPlease enter the surname you want to cancel:");
        String oldSurname = sr1.nextLine();
        while (oldSurname==null) {
            System.out.print("""
                    Please enter valid surname you want to cancel(String(pay attention cannot be null or "")):""");
            oldSurname= sr1.nextLine();
        }
        for (int i = 0; i < numOfReservation; i++) {
            if (nameReservation[i][0] != null && nameReservation[i][1] != null) {
                if (nameReservation[i][0].equals(oldName) && nameReservation[i][1].equals(oldSurname)) {
                    System.out.print("Please enter 1 to confirm you want to delete, any int is not equals 1 to cancel:");
                    if (sr.nextInt() == 1) {
                        for (int j = i; j < numOfReservation; j++) {
                            if (j == numOfReservation - 1) {
                                nameReservation[j][0] = null;
                                nameReservation[j][1] = null;
                                dateAndTime[j][0] = 0;
                                dateAndTime[j][1] = 0;
                                dateAndTime[j][2] = 0;
                                dateAndTime[j][3] = 0;
                                dateAndTime[j][4] = 0;
                            } else {
                                nameReservation[j][0] = nameReservation[j + 1][0];
                                nameReservation[j][1] = nameReservation[j + 1][1];
                                dateAndTime[j][0] = dateAndTime[j + 1][0];
                                dateAndTime[j][1] = dateAndTime[j + 1][1];
                                dateAndTime[j][2] = dateAndTime[j + 1][2];
                                dateAndTime[j][3] = dateAndTime[j + 1][3];
                                dateAndTime[j][4] = dateAndTime[j + 1][4];
                            }
                        }
                    } else {
                        ReservationMethods.handleReservations();
                    }
                    break;
                }
            } else if (i == numOfReservation - 1) {
                System.out.println("Cannot find item which name=" + oldName + " or surname=" + oldSurname +
                                   ".\nPlease enter an existing item name or surname.");
            }
        }
    }

    public static void addItem(int i) {
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        System.out.print("\nPlease enter your name:");
        nameReservation[i][0] = sr1.nextLine();
        while (nameReservation[i][0]==null) {
            System.out.print("""
                    Please enter valid name(String(pay attention cannot be null or "")):""");
            nameReservation[i][0]= sr1.nextLine();
        }
        System.out.print("Please enter your surname:");
        nameReservation[i][1] = sr1.nextLine();
        while (nameReservation[i][1]==null) {
            System.out.print("""
                    Please enter valid surname(String(pay attention cannot be null or "")):""");
            nameReservation[i][1]= sr1.nextLine();
        }
        System.out.print("Please enter reservation year:");
        dateAndTime[i][0] = sr.nextInt();
        while (dateAndTime[i][0] < 2024) {
            System.out.print("Please enter correct year(>2024):");
            dateAndTime[i][0] = sr.nextInt();
        }
        System.out.print("Please enter reservation month:");
        dateAndTime[i][1] = sr.nextInt();
        while (dateAndTime[i][1] < 1 || dateAndTime[i][1] > 12) {
            System.out.print("Please enter correct month(1-12):");
            dateAndTime[i][1] = sr.nextInt();
        }
        System.out.print("Please enter reservation day:");
        dateAndTime[i][2] = sr.nextInt();
        switch (dateAndTime[i][1]) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                while (dateAndTime[i][2] < 1 || dateAndTime[i][2] > 31) {
                    System.out.print("Please enter correct day(this month have 31 day):");
                    dateAndTime[i][2] = sr.nextInt();
                }
            }
            case 4, 6, 9, 11 -> {
                while (dateAndTime[i][2] < 1 || dateAndTime[i][2] > 30) {
                    System.out.print("Please enter correct day(this month have 30 day):");
                    dateAndTime[i][2] = sr.nextInt();
                }
            }
            case 2 -> {
                if (dateAndTime[i][0] % 400 == 0 || (dateAndTime[i][0] % 100 != 0 && dateAndTime[i][0] % 4 == 0)) {
                    while (dateAndTime[i][2] < 1 || dateAndTime[i][2] > 29) {
                        System.out.print("Please enter correct day(this month have 29 day):");
                        dateAndTime[i][2] = sr.nextInt();
                    }
                } else {
                    while (dateAndTime[i][2] < 1 || dateAndTime[i][2] > 28) {
                        System.out.print("Please enter correct day(this month have 28 day):");
                        dateAndTime[i][2] = sr.nextInt();
                    }
                }
            }
        }
        while (dateAndTime[i][0] == 2024 && dateAndTime[i][1] <= 4 && dateAndTime[i][2] <= 24) {
            System.out.println("Please enter valid date (min 1 day before).");
            addItem(i);
        }
        System.out.print("Please enter reservation hour:");
        dateAndTime[i][3] = sr.nextInt();
        while (dateAndTime[i][3] < 0 || dateAndTime[i][3] > 24) {
            System.out.print("Please enter correct hour(0-24):");
            dateAndTime[i][3] = sr.nextInt();
        }
        System.out.print("Please enter reservation minute:");
        dateAndTime[i][4] = sr.nextInt();
        while (dateAndTime[i][4] < 0 || dateAndTime[i][4] > 60) {
            System.out.print("Please enter correct hour(0-60):");
            dateAndTime[i][4] = sr.nextInt();
        }
    }

    public static int getNumThereAreReservation() {
        int num = 0;
        for (int i = 0; i < numOfReservation; i++) {
            if (nameReservation[i][0] != null) {
                num++;
            } else {
                break;
            }
        }
        return num;
    }

    public static int getNumOfReservation() {
        return numOfReservation;
    }

    public static void go() {
        Scanner sr = new Scanner(System.in);
        System.out.print("\nMain Menu(1)," +
                           "\nHandle Reservations(2)," +
                           "\nNew Reservation(3)(There are " + (getNumOfReservation() - getNumThereAreReservation()) +
                           " empty reservation place)." +
                           "\nSelect one of the options(int):");
        int UserChoice = sr.nextInt();
        if (UserChoice == 3 && getNumOfReservation() - getNumThereAreReservation() == 0) {
            System.out.print("""
                    Reservation place is full.
                    Please select another option or delete some Reservation.
                    """);
            go();
        }
        switch (UserChoice) {
            case 1 -> {
            }
            case 2 -> ReservationMethods.handleReservations();
            case 3 -> addReservation();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                go();
            }
        }
    }
}

