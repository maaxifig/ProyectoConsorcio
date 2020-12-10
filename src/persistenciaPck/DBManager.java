package persistenciaPck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

	public class DBManager {
		
		private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
		private static final String DB_URL = "jdbc:hsqldb:file:sql/testdb;shutdown=true;hsqldb.default_table_type=cached";
		private static final String DB_USERNAME = "sa";
		private static final String DB_PASSWORD = "";
		private static DBManager s_instance;
		
	
	private DBManager () {
			
		}
		
	public Connection conectarse() {
	
		Connection c = null;
		 
	
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	try {
		c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		c.setAutoCommit(false);
	} catch (SQLException e) {
		e.printStackTrace();
		System.exit(0);
	}
	
	return c;
	}
		
	public void baja() throws Exception{
		Connection c = conectarse();
		Statement s = c.createStatement();
		s.execute("SHUTDOWN");
		c.close();
	}
	
	public static DBManager getInstance(){
	    if(s_instance==null){
	    	s_instance = new DBManager();
		}
		return s_instance;
	  }
	
	
	
	}
	
	
