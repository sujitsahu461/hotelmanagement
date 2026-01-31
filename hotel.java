import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class hotel {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/hotelmanagement";
        String user = "root";
        String password = "ROOT";

        try (Scanner sc = new Scanner(System.in)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver Loaded");

                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection Established");

                Statement statement = connection.createStatement();
                System.out.println("Statement Created");

                while (true) {
                    System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
                    System.out.println("1. Allocate Room (Check-in)");
                    System.out.println("2. Retrieve Reservation");
                    System.out.println("3. Get Room Status");
                    System.out.println("4. Checkout");
                    System.out.println("5. Exit");
                    System.out.print("Enter choice: ");

                    int choice = sc.nextInt();

                    switch (choice) {

                        // 1. Allocate Room
                        case 1:
                            System.out.print("Enter Guest Name: ");
                            String name = sc.next();

                            System.out.print("Enter Contact No: ");
                            String contact = sc.next();

                            System.out.print("Enter Room No: ");
                            int room = sc.nextInt();

                            String insertQuery =
                                    "INSERT INTO reservations (guest_name, contact_no, room_no) " +
                                    "VALUES ('" + name + "','" + contact + "'," + room + ")";
                            statement.executeUpdate(insertQuery);
                            System.out.println("Room Allocated Successfully!");
                            break;

                        // 2. Retrieve Reservation
                        case 2:
                            System.out.print("Enter Reservation ID: ");
                            int id = sc.nextInt();

                            String selectQuery =
                                    "SELECT * FROM reservations WHERE reservation_id=" + id;
                            ResultSet rs = statement.executeQuery(selectQuery);

                            if (rs.next()) {
                                System.out.println("Guest Name : " + rs.getString("guest_name"));
                                System.out.println("Contact    : " + rs.getString("contact_no"));
                                System.out.println("Room No    : " + rs.getInt("room_no"));
                                System.out.println("Check-in   : " + rs.getTimestamp("check_in_time"));
                                System.out.println("Check-out  : " + rs.getTimestamp("check_out_time"));
                            } else {
                                System.out.println("Reservation not found");
                            }
                            break;

                        // 3. Get Room Status
                        case 3:
                            System.out.print("Enter Room No: ");
                            int roomNo = sc.nextInt();

                            String roomQuery =
                                    "SELECT * FROM reservations WHERE room_no=" + roomNo +
                                    " AND check_out_time IS NULL";
                            ResultSet rs2 = statement.executeQuery(roomQuery);

                            if (rs2.next()) {
                                System.out.println("Room occupied by: " + rs2.getString("guest_name"));
                            } else {
                                System.out.println("Room is available");
                            }
                            break;

                        // 4. Checkout
                        case 4:
                            System.out.print("Enter Reservation ID: ");
                            int rid = sc.nextInt();

                            String checkoutQuery =
                                    "UPDATE reservations SET check_out_time=NOW() " +
                                    "WHERE reservation_id=" + rid;
                            int rows = statement.executeUpdate(checkoutQuery);

                            if (rows > 0) {
                                System.out.println("Checkout Successful!");
                            } else {
                                System.out.println("Invalid Reservation ID");
                            }
                            break;

                        // Exit
                        case 5:
                            System.out.println("Thank you!");
                            connection.close();
                            System.exit(0);

                        default:
                            System.out.println("Invalid Choice");
                    }
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
