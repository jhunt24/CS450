import java.sql.*;

class Database {
    static void executeOption(String option, Connection conn){
        switch(option){
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
        System.out.format("  %-15s %-15s\n", "Quit", "q");
    }
}
