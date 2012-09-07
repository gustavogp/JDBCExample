import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCExample {

	public static void main() {
		String url = "jdbc:mysql://mysql1000.mochahost.com:3306/gustavo_jdbcexample";
		Connection con;
		Statement stmt;
		String getBooks = "SELECT * FROM `book`";
	//	List<List<String,String>> returnArray;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException in main(): ");
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, "gustavo_br", "donner26");
			System.out.println("connected");
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(getBooks);
			while (rs.next()) {
				String s = rs.getString("title");
				String tp = rs.getNString("author");
				System.out.println(s + ",   " + tp);
				GuiJDBCE.output(s, tp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
