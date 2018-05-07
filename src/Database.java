import java.sql.*;
import java.util.Scanner;

class Database {

    private static void tableList(Connection conn, String userid) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();//database meta data
        ResultSet rs = md.getTables(null, userid, "%", null);
        System.out.println("Table List:");
        while(rs.next()){
            if (rs.getString(4).equalsIgnoreCase("TABLE")) {//prints list of all tables attached to userid
                System.out.println(rs.getString(3));
            }
        }
        rs.close();
    }

    private static void viewTable(Connection conn) throws SQLException {

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
        rs.close();
        st.close();
    }

    private static void insertTable(Connection conn) throws SQLException {

        PreparedStatement preparedStatement;
        String table;//for user input of table
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Table? ");
        table = scanner.nextLine();
        table = table.toUpperCase();

        switch(table){//insert for each possible table
            case "CUSTOMER": {
                String var1;
                String var2;
                Integer var3;
                String var4;
                System.out.println("c_id? ");
                var1 = scanner.nextLine();
                System.out.println("c_name? ");
                var2 = scanner.nextLine();
                System.out.println("age? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("gender? ");
                var4 = scanner.nextLine();
                String sql = "INSERT INTO CUSTOMER VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setInt(3, var3);
                preparedStatement.setString(4, var4);
                break;
            }
            case "RESERVATION": {
                Integer var1;
                String var2;
                Integer var3;
                Integer var4;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("c_id? ");
                var2 = scanner.nextLine();
                System.out.println("party_size? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("total? ");
                var4 = Integer.valueOf(scanner.nextLine());
                String sql = "INSERT INTO RESERVATION VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setInt(3, var3);
                preparedStatement.setInt(4, var4);
                break;
            }
            case "DATE_LIST": {
                String var1;
                System.out.println("my_date? ");
                var1 = scanner.nextLine();
                String sql = "INSERT INTO DATE_LIST VALUES (?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                break;
            }
            case "HOTEL": {
                String var1;
                String var2;
                String var3;
                String var4;
                Integer var5;
                String var6;
                Integer var7;
                String var8;
                System.out.println("hotel_name? ");
                var1 = scanner.nextLine();
                System.out.println("branch_id? ");
                var2 = scanner.nextLine();
                System.out.println("city? ");
                var3 = scanner.nextLine();
                System.out.println("state? ");
                var4 = scanner.nextLine();
                System.out.println("zip? ");
                var5 = Integer.valueOf(scanner.nextLine());
                System.out.println("phone? ");
                var6 = scanner.nextLine();
                System.out.println("street_num? ");
                var7 = Integer.valueOf(scanner.nextLine());
                System.out.println("street_name? ");
                var8 = scanner.nextLine();
                String sql = "INSERT INTO HOTEL VALUES (?,?,?,?,?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setString(3, var3);
                preparedStatement.setString(4, var4);
                preparedStatement.setInt(5, var5);
                preparedStatement.setString(6, var6);
                preparedStatement.setInt(7, var7);
                preparedStatement.setString(8, var8);
                break;
            }
            case "ROOM": {
                String var1;
                Integer var2;
                System.out.println("room_type? ");
                var1 = scanner.nextLine();
                System.out.println("capacity? ");
                var2 = Integer.valueOf(scanner.nextLine());
                String sql = "INSERT INTO ROOM VALUES (?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setInt(2, var2);
                break;
            }
            case "RESERVATION_ON": {
                Integer var1;
                String var2;
                String var3;
                String var4;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("hotel_name? ");
                var2 = scanner.nextLine();
                System.out.println("branch_id? ");
                var3 = scanner.nextLine();
                System.out.println("room_type? ");
                var4 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_ON VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setString(3, var3);
                preparedStatement.setString(4, var4);
                break;
            }
            case "RESERVATION_CHECK_IN": {
                Integer var1;
                String var2;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("my_date? ");
                var2 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_CHECK_IN VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                break;
            }
            case "RESERVATION_CHECK_OUT": {
                Integer var1;
                String var2;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("my_date? ");
                var2 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_CHECK_OUT VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                break;
            }
            case "DATE_LIST_INFO": {
                String var1;
                Integer var2;
                Integer var3;
                String var4;
                String var5;
                String var6;
                System.out.println("my_date? ");
                var1 = scanner.nextLine();
                System.out.println("price? ");
                var2 = Integer.valueOf(scanner.nextLine());
                System.out.println("num_avail? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("hotel_name? ");
                var4 = scanner.nextLine();
                System.out.println("branch_id? ");
                var5 = scanner.nextLine();
                System.out.println("room_type? ");
                var6 = scanner.nextLine();
                String sql = "INSERT INTO DATE_LIST_INFO VALUES (?,?,?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setInt(2, var2);
                preparedStatement.setInt(3, var3);
                preparedStatement.setString(4, var4);
                preparedStatement.setString(5, var5);
                preparedStatement.setString(6, var6);
                break;
            }
            case "HAS": {
                String var1;
                String var2;
                Integer var3;
                String var4;
                System.out.println("hotel_name? ");
                var1 = scanner.nextLine();
                System.out.println("branch_id? ");
                var2 = scanner.nextLine();
                System.out.println("quantity? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("room_type? ");
                var4 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_CHECK_IN VALUES (?,?,?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setInt(3, var3);
                preparedStatement.setString(4, var4);
                break;
            }
            default:
                System.out.println("Table does not exist");
                return;
        }
        preparedStatement.executeUpdate();//execute insert
        preparedStatement.close();//close statement
    }

    private static void updateTable(Connection conn) throws SQLException {

        PreparedStatement preparedStatement;
        String table;//for user input of table
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Table? ");
        table = scanner.nextLine();
        table = table.toUpperCase();

        switch(table){//insert for each possible table
            case "CUSTOMER": {
                String var1;
                String var2;
                Integer var3;
                String var4;
                System.out.println("c_id? ");
                var1 = scanner.nextLine();
                System.out.println("c_name? ");
                var2 = scanner.nextLine();
                System.out.println("age? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("gender? ");
                var4 = scanner.nextLine();
                String sql = "UPDATE CUSTOMER SET c_name = ?, age = ?, gender = ? WHERE c_id = ?";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(4, var1);
                preparedStatement.setString(1, var2);
                preparedStatement.setInt(2, var3);
                preparedStatement.setString(3, var4);
                break;
            }
            case "RESERVATION": {
                Integer var1;
                String var2;
                Integer var3;
                Integer var4;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("c_id? ");
                var2 = scanner.nextLine();
                System.out.println("party_size? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("total? ");
                var4 = Integer.valueOf(scanner.nextLine());
                String sql = "INSERT INTO RESERVATION VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setInt(3, var3);
                preparedStatement.setInt(4, var4);
                break;
            }
            case "DATE_LIST": {
                String var1;
                System.out.println("my_date? ");
                var1 = scanner.nextLine();
                String sql = "INSERT INTO DATE_LIST VALUES (?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                break;
            }
            case "HOTEL": {
                String var1;
                String var2;
                String var3;
                String var4;
                Integer var5;
                String var6;
                Integer var7;
                String var8;
                System.out.println("hotel_name? ");
                var1 = scanner.nextLine();
                System.out.println("branch_id? ");
                var2 = scanner.nextLine();
                System.out.println("city? ");
                var3 = scanner.nextLine();
                System.out.println("state? ");
                var4 = scanner.nextLine();
                System.out.println("zip? ");
                var5 = Integer.valueOf(scanner.nextLine());
                System.out.println("phone? ");
                var6 = scanner.nextLine();
                System.out.println("street_num? ");
                var7 = Integer.valueOf(scanner.nextLine());
                System.out.println("street_name? ");
                var8 = scanner.nextLine();
                String sql = "INSERT INTO HOTEL VALUES (?,?,?,?,?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setString(3, var3);
                preparedStatement.setString(4, var4);
                preparedStatement.setInt(1, var5);
                preparedStatement.setString(2, var6);
                preparedStatement.setInt(3, var7);
                preparedStatement.setString(4, var8);
                break;
            }
            case "ROOM": {
                String var1;
                Integer var2;
                System.out.println("room_type? ");
                var1 = scanner.nextLine();
                System.out.println("capacity? ");
                var2 = Integer.valueOf(scanner.nextLine());
                String sql = "INSERT INTO ROOM VALUES (?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setInt(2, var2);
                break;
            }
            case "RESERVATION_ON": {
                Integer var1;
                String var2;
                String var3;
                String var4;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("hotel_name? ");
                var2 = scanner.nextLine();
                System.out.println("branch_id? ");
                var3 = scanner.nextLine();
                System.out.println("room_type? ");
                var4 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_ON VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setString(2, var3);
                preparedStatement.setString(2, var4);
                break;
            }
            case "RESERVATION_CHECK_IN": {
                Integer var1;
                String var2;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("my_date? ");
                var2 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_CHECK_IN VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                break;
            }
            case "RESERVATION_CHECK_OUT": {
                Integer var1;
                String var2;
                System.out.println("r_num? ");
                var1 = Integer.valueOf(scanner.nextLine());
                System.out.println("my_date? ");
                var2 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_CHECK_OUT VALUES (?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, var1);
                preparedStatement.setString(2, var2);
                break;
            }
            case "DATE_LIST_INFO": {
                String var1;
                Integer var2;
                Integer var3;
                String var4;
                String var5;
                String var6;
                System.out.println("my_date? ");
                var1 = scanner.nextLine();
                System.out.println("price? ");
                var2 = Integer.valueOf(scanner.nextLine());
                System.out.println("num_avail? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("hotel_name? ");
                var4 = scanner.nextLine();
                System.out.println("branch_id? ");
                var5 = scanner.nextLine();
                System.out.println("room_type? ");
                var6 = scanner.nextLine();
                String sql = "INSERT INTO DATE_LIST_INFO VALUES (?,?,?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setInt(2, var2);
                preparedStatement.setInt(2, var3);
                preparedStatement.setString(1, var4);
                preparedStatement.setString(1, var5);
                preparedStatement.setString(1, var6);
                break;
            }
            case "HAS": {
                String var1;
                String var2;
                Integer var3;
                String var4;
                System.out.println("hotel_name? ");
                var1 = scanner.nextLine();
                System.out.println("branch_id? ");
                var2 = scanner.nextLine();
                System.out.println("quantity? ");
                var3 = Integer.valueOf(scanner.nextLine());
                System.out.println("room_type? ");
                var4 = scanner.nextLine();
                String sql = "INSERT INTO RESERVATION_CHECK_IN VALUES (?,?,?,?,?,?)";

                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, var1);
                preparedStatement.setString(2, var2);
                preparedStatement.setInt(2, var3);
                preparedStatement.setString(1, var4);
                break;
            }
            default:
                System.out.println("Table does not exist");
                return;
        }
        preparedStatement.executeUpdate();//execute insert
        preparedStatement.close();//close statement
    }

    private static void DeleteTuple(Connection conn) throws SQLException {
    	String table;
    	String user_input;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Which Table?");
    	table = scanner.nextLine();
    	table = table.toUpperCase();
    	if (table.equals("RESERVATION")) {
    		System.out.println("What is the r_num?");
    		user_input = scanner.nextLine();
    		int r_num_int = Integer.parseInt(user_input);
    		String hold = "DELETE FROM " +table+ " WHERE r_num = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setInt(1, r_num_int);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("CUSTOMER")) {
    		System.out.println("What is the CID?");
    		user_input = scanner.nextLine();
    		String cid = user_input;
    		String hold = "DELETE FROM " +table+ " WHERE c_id = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setString(1, cid);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("HOTEL")) {
    		System.out.println("What is the branch id?");
    		user_input = scanner.nextLine();
    		String b_id = user_input;
    		System.out.println("What is the hotel name?");
    		user_input = scanner.nextLine();
    		String hotel_name = user_input;
    		String hold = "DELETE FROM " +table+ " WHERE branch_id = ? and hotel_name = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setString(1, b_id);
    		st.setString(2, hotel_name);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("ROOM")) {
    		System.out.println("What is the room type?");
    		user_input = scanner.nextLine();
    		String hold = "DELETE FROM " +table+ " WHERE room_type = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setString(1, user_input);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("DATE_LIST")) {
    		System.out.println("What is the date?");
    		user_input = scanner.nextLine();
    		String hold = "DELETE FROM " +table+ " WHERE my_date = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setString(1, user_input);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("RESERVATION_ON")) {
    		System.out.println("What is the r_num?");
    		user_input = scanner.nextLine();
    		int r_num_int = Integer.parseInt(user_input);
    		System.out.println("What is the branch id?");
    		user_input = scanner.nextLine();
    		int id_int = Integer.parseInt(user_input);
    		System.out.println("What is the hotel name?");
    		user_input = scanner.nextLine();
    		String hotel_name = user_input;
    		System.out.println("What is the room type?");
    		user_input = scanner.nextLine();
    		String room_type = user_input;
    		String hold = "DELETE FROM " +table+ " WHERE r_num = ? and branch_id = ? and hotel_name = ? and room_type = ? ";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setInt(1, r_num_int);
    		st.setInt(2, id_int);
    		st.setString(3, hotel_name);
    		st.setString(4, room_type);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("RESERVATION_CHECK_IN")) {
    		System.out.println("What is the r_num?");
    		user_input = scanner.nextLine();
    		int r_num_int = Integer.parseInt(user_input);
    		String hold = "DELETE FROM " +table+ " WHERE r_num = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setInt(1, r_num_int);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("RESERVATION_CHECK_OUT")) {
    		System.out.println("What is the r_num?");
    		user_input = scanner.nextLine();
    		int r_num_int = Integer.parseInt(user_input);
    		String hold = "DELETE FROM " +table+ " WHERE r_num = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setInt(1, r_num_int);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("DATE_LIST_INFO")) {
    		System.out.println("What is the branch id?");
    		user_input = scanner.nextLine();
    		int id_int = Integer.parseInt(user_input);
    		System.out.println("What is the hotel name?");
    		user_input = scanner.nextLine();
    		String hotel_name = user_input;
    		String hold = "DELETE FROM " +table+ " WHERE branch_id = ? and hotel_name = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setInt(1, id_int);
    		st.setString(2, hotel_name);
    		st.executeUpdate();
    		st.close();
    	}
    	if (table.equals("HAS")) {
    		System.out.println("What is the branch id?");
    		user_input = scanner.nextLine();
    		int id_int = Integer.parseInt(user_input);
    		System.out.println("What is the hotel name?");
    		user_input = scanner.nextLine();
    		String hotel_name = user_input;
    		String hold = "DELETE FROM " +table+ " WHERE branch_id = ? and hotel_name = ?";
    		PreparedStatement st = conn.prepareStatement(hold);
    		st.setInt(1, id_int);
    		st.setString(2, hotel_name);
    		st.executeUpdate();
    		st.close();
    	}
    }

    static void executeOption(String option, Connection conn, String userid) throws SQLException {
        switch(option){
            case "tl":
                tableList(conn, userid);
                break;
            case "vt":
                viewTable(conn);
                break;
            case "it":
                insertTable(conn);
                break;
            case "ut":
                updateTable(conn);
                break;
            case "dt":
            	DeleteTuple(conn);
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
