/*
 * MyDBConnection.java
 *
 * Created on 2005/01/16, 10:50
 */

package db;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Tony Clavien
 * @date 03.10.2017
 */
public class DBConnection {
   
    private Connection myConnection;
    
    // JDBC driver name and database URL
 	 private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 	 private static final String DB_URL = "jdbc:mysql://localhost/pdg";

 	   //  Database credentials
 	 //TODO add your stuff
 	 private static final String USER = "username";
 	 private static final String PASS = "password";
    
    /** Creates a new instance of MyDBConnection */
    public DBConnection() {

    }

    public void init(){
    
       try{
        
        Class.forName(JDBC_DRIVER);
        myConnection=DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }
    
    
    public Connection getMyConnection(){
        return myConnection;
    }
    
    //TODO do query class
    public static ArrayList<String[]> selectQuery(String[] colName, String dbTable) {
    	DBConnection dbc=new DBConnection();
        dbc.init();
        Connection conn=dbc.getMyConnection();
        Statement stmt= null;
        
		String query = constructQuery(colName, dbTable);
		System.out.println("Query");
		ResultSet rs=null;
		
		ArrayList<String[]> results = new ArrayList<>();  
       
       try{
       	stmt= conn.createStatement();
           rs=stmt.executeQuery(query);
           while(rs.next()){
				String[] row = new String[colName.length];
				for (int i = 0; i < colName.length; i++) {
					row[i] = rs.getString(colName[i]);
				}
				
				results.add(row);
				
			}
       
       }
       catch(SQLException e){}
       finally {
       	dbc.close(rs);
       	dbc.close(stmt);
			dbc.destroy();
		}
       
       return results;
    }
    
    //TODO add where clause
    private static String constructQuery(String[] colName, String dbTable) {
    	StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        for (int i = 0; i < colName.length; i++) {
			sb.append(colName[i]);
			if(i != colName.length-1){
			sb.append(",");
			}
		}
        sb.append(" FROM ");
        sb.append(dbTable);
        return sb.toString();
    }
    
    
    public void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
     public void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
  public void destroy(){
  
    if(myConnection !=null){
    
         try{
               myConnection.close();
            }
            catch(Exception e){}
        
        
    }
  }
    
}