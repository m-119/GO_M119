package to_db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connector {
    
    String url = "jdbc:derby://localhost:1527/M119";
    String username = "M119";
    String password = "M119";
    String driver = "org.apache.derby.jdbc.ClientDriver";
    Statement statement;
    Connection conn;

    public Connector(String url, String username, String password, String driver){
	
	try{
	    this.url = url;
	    this.username = username;
	    this.password = password;
	    this.driver = driver;
	    Class.forName(this.driver).getDeclaredConstructor().newInstance();
	    
	    conn = DriverManager.getConnection(this.url, this.username, this.password);
	    statement = conn.createStatement();
	    
	}catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
         }
	
    }
    public Connector(){
	try{
	    Class.forName(this.driver).getDeclaredConstructor().newInstance();
	    
	    conn = DriverManager.getConnection(url, username, password);
	    statement = conn.createStatement();
	    
	}catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
         }
    }
    
    public boolean update(String sql) throws SQLException {
	try (Connection conn = DriverManager.getConnection(url, username, password)){
	    Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
	    return true;
	    
	}catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
	     return false;
         }
    }
    
    public void reset()
    {
	Default def = new Default();
	for(String s : def.DROP().split(";"))
	    {
	try {
	    statement.execute(s);
	} catch (SQLException ex) {
	    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
	}
	}
	for(String s : def.CREATE().split(";"))
	    {try {
	    statement.execute(s);
	} catch (SQLException ex) {
	    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
	}}
	for(String s : def.ALTER().split(";"))
	    {try {
	    statement.execute(s);
	} catch (SQLException ex) {
	    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
	}}
	for(String s : def.INSERT().split(";"))
	    {try {
	    statement.executeUpdate(s);
	} catch (SQLException ex) {
	    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
	}}
    }
}
