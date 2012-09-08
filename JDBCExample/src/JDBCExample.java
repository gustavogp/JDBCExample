import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.swing.SwingUtilities;


public class JDBCExample {
	static List<ArrayList<String>> returnArray;
	static List<String> titleArray;
	static List<String> authorArray;
	
	public static void main() {
		String url = "jdbc:mysql://mysql1000.mochahost.com:3306/gustavo_jdbcexample";
		Connection con;
		Statement stmt;
		String getBooks = "SELECT * FROM `book`";
		ResultSet rs;
		
		con = null;
		rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			GuiJDBCE.driverLoadedMsg();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException in main(): ");
			GuiJDBCE.errorDriver(e.getMessage());
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, "gustavo_br", "donner26");
			System.out.println("connected");
			GuiJDBCE.connectedMsg();
			
		} catch (SQLException e) {
			e.printStackTrace();
			GuiJDBCE.errorConnecting(e.getMessage());
			while (e.getNextException() != null) {
				GuiJDBCE.errorConnecting(e.getNextException().getMessage());
			}
		}
		
			try {
				stmt = con.createStatement();
				titleArray = new ArrayList<String>();
				authorArray = new ArrayList<String>();
				returnArray = new ArrayList<ArrayList<String>>();
				
				rs = stmt.executeQuery(getBooks);
				while (rs.next()) {
					String s = rs.getString("title");
					String tp = rs.getNString("author");
					System.out.println(s + ",   " + tp);
					titleArray.add(s);
					authorArray.add(tp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			returnArray.add((ArrayList<String>) titleArray);
			returnArray.add((ArrayList<String>) authorArray);
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					GuiJDBCE.output((ArrayList<ArrayList<String>>) returnArray);
				}
			});
			
	}

}
