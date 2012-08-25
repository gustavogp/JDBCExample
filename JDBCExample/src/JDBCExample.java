import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://mysqladmin.ipage.com/mysqladmin/tbl_properties_structure.php?db=testing_data_1";
		Connection con;
		Statement stmt;
		String getBooks = "SELECT * FROM 'book'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException in main(): ");
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, "gustavobr", "donner25");
			System.out.println("connected");
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(getBooks);
			while (rs.next()) {
				String s = rs.getString("title");
				String tp = rs.getNString("totalpages");
				System.out.println(s + ",   " + tp + " pages");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
