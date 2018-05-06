import java.sql.*;

class Database {

    private static void TableList(Connection conn) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while(rs.next()){
            if (rs.getString(4).equalsIgnoreCase("TABLE") && rs.getString(2).equalsIgnoreCase("JHUNT11")) {
                System.out.println(rs.getString(3));
            }
        }
    }

    static void executeOption(String option, Connection conn) throws SQLException {
        switch(option){
            case "tl":
                TableList(conn);
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
