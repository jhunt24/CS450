import java.sql.*; //JDBC packages
import java.math.*;
import java.io.*;
import oracle.jdbc.driver.*;
import java.util.Scanner;

/*
1. View table content: Give user a list of existing tables so that he/she can select one to view the tuples.
 DONE(5 points) Show list of tables
 (10 points) Show table content when the user selects a table. This feature needs to work for the next step as well (so that you can verify the content after insert/update/deletion).
2. Add records: Enter information for new customers, hotels, room types, reservations, etc. Update/delete information.
    Verify the updated table content. Test this for different tables.
 (15 points) Insert new tuples (check for primary key constraint & foreign key constraint)
 (15 points) Update tuples (check for primary key & foreign key constraint)
 (15 points) Delete tuples
 (10 points) Test for trigger to calculate the total price for a new reservation.
 (5 points) Test for trigger to check that the party size does not exceed the capacity of the room type for a new reservation.
 (10 points) Test for trigger to update available room count. A new reservation should not be allowed if the room count is zero for the specified room type and any date within the date range.
3.Search database: search for a given user’s reservation(s), search availability and prices for hotels in a given location, for a certain party size (or room type), on given check-in and check-out dates
 (10 points) Retrieve a given user’s reservation
 (10 points) Find availability and prices based on input parameters (location, dates, party size/room type)
 */

public class Main {

    public static void main(String[] args) {

        String option;//for user input
        String userid;
        String passwd;
        Scanner scanner = new Scanner(System.in);
        if(args.length != 2){
            System.out.println("Username: ");
            userid = scanner.nextLine();
            System.out.println("Password: ");
            passwd = scanner.nextLine();
        }
        else {
            userid = args[0];
            passwd = args[1];
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", userid, passwd);
            //…Do Actual Work …

            do {//infinite loop for menu, user can exit with q for quit
                Database.displayMenu();//displays the menu after every command
                option = scanner.nextLine();//get user input
                Database.executeOption(option, conn, userid);//execute given command
            }while(true);


            /*
            close stuff
            rset.close();
            pStmt.close();
            conn.close();*/
        } catch (SQLException sqle) {
            System.out.println("SQLException : " + sqle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}