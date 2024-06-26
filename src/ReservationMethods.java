import java.util.Scanner;

public class ReservationMethods {
    public static void handleReservations() {
        Scanner sr = new Scanner(System.in);
        System.out.print("""
                
                                         Handle Reservation
                                
                Add Reservation(1),
                View Reservations(2),
                Change Reservations(3),
                Cancel Reservation(4),
                Move Back(5).
                Select one of the options(int):""");
        int UserChoice = sr.nextInt();
        switch (UserChoice) {
            case 1 -> Reservation.addReservation();
            case 2 -> Reservation.getReservations();
            case 3 -> Reservation.updateReservations();
            case 4 -> Reservation.cancelReservation();
            case 5 -> Main.StartProgram();
            default -> {
                System.out.println("Wrong input! Please enter valid choice.");
                handleReservations();
            }
        }
    }
}
