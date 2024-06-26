import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    StartProgram();
    }

    public static void StartProgram() {
        boolean ProgramIsWork = true;
        Scanner sr = new Scanner(System.in);
        while (ProgramIsWork) {
            System.out.print("""
                                                                      
                                             Restaurant
                                             
                    Manage Menu(1),
                    Handle Reservations(2),
                    Process Orders(3),
                    Exit(4).
                    Select one of the options(int):""");
            int UserChoice = sr.nextInt();
            switch (UserChoice) {
                case 1 -> MenuMethods.manageMenu();
                case 2 -> ReservationMethods.handleReservations();
                case 3 -> OrderMethods.processOrder();
                case 4 -> {
                    System.out.println("Program is finished.");
                    ProgramIsWork = false;
                }
                default -> System.out.println("Wrong input! Please enter valid choice.");
            }
        }
    }
}