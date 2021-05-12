package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	// private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	/* Make Tomcat now which database driver to use */

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // logger.warn("Exception thrown adding oracle driver.", e);
		}
	}

//	/* Get connection to JDBC */
	public static Connection getConnection() throws SQLException {
//database-1.ctphcxxy40ud.us-east-1.rds.amazonaws.com-p 5432 
		String url = "jdbc:postgresql://database-1.ctphcxxy40ud.us-east-1.rds.amazonaws.com:5432/";
		String userName = "postgres";
		String password = "Printer2007";
		String dbName = "postgres";
		String driver = "com.postgresql.jdbc.Driver";
		return DriverManager.getConnection(url + dbName, userName, password);
//	}
//	  
	}

	// Test method for creating connection.
//	public static void main(String[] args) throws SQLException {
//		Connection conn = getConnection();
//		System.out.println("Connection created!");
//	}

}
/*
 * Here is some sample code for a JDBC connector for mysql: String url =
 * "jdbc:mysql://dbname.test.us-east-1.rds.amazonaws.com:3306/"; String userName
 * = "your_user_name"; String password = "your_password"; String dbName =
 * "your_db_name"; String driver = "com.mysql.jdbc.Driver"; Connection
 * connection = DriverManager.getConnection(url + dbName, userName, password);
 */
