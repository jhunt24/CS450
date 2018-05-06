import java.sql.*;
import java.util.Scanner;

class Database {

    private static void TableList(Connection conn, String userid) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();//database meta data
        ResultSet rs = md.getTables(null, userid, "%", null);
        System.out.println("Table List:");
        while(rs.next()){
            if (rs.getString(4).equalsIgnoreCase("TABLE")) {//prints list of all tables attached to userid
                System.out.println(rs.getString(3));
            }
        }
    }

    private static void ViewTable(Connection conn) throws SQLException {

        String table;//for user input of table
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Table? ");
        table = scanner.nextLine();
        table = table.toUpperCase();//need to be uppercase for oracle

        Statement st = conn.createStatement();//query db for table data
        String query = "select *" +
                "from " + table;
        ResultSet rs = st.executeQuery(query);

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();//number of columns of table

        for(int i = 1 ; i <= columnsNumber; i++){//prints labels for table
            System.out.format("%-15s", rsmd.getColumnName(i));
        }
        System.out.println();//next line

        while (rs.next()) {
            for(int i = 1 ; i <= columnsNumber; i++){//prints data from table
                System.out.format("%-15s", rs.getString(i));
            }
            System.out.println();//Move to the next line to print the next row.
        }
    }

    static void executeOption(String option, Connection conn, String userid) throws SQLException {
        switch(option){
            case "tl":
                TableList(conn, userid);
                break;
            case "vt":
                ViewTable(conn);
                break;
            case "it":
                break;
            case "ut":
                break;
            case "dt":
                break;
            case "gr":
                break;
            case "fa":
                break;
            case "q":
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.exit(0);
        }
    }
    static void displayMenu(){
        System.out.format("%-15s\n","Hotel Options");
        System.out.format("  %-15s %-15s %-15s %-15s\n", "Table List", "tl","View Table", "vt");
        System.out.format("  %-15s %-15s %-15s %-15s\n", "Insert Tuple", "it","Update Tuple", "ut");
        System.out.format("  %-15s %-15s %-15s %-15s\n", "Delete Tuple", "dt","Get Reservation", "gr");
        System.out.format("  %-15s %-15s %-15s %-15s\n", "Find Availability", "fa","Quit", "q");
    }
}
