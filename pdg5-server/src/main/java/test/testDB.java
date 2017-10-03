package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import db.DBConnection;

public class testDB {

	@Test
	public void simpleConnection() {
		 DBConnection dbc=new DBConnection();
         dbc.init();
         Connection conn=dbc.getMyConnection();
         Statement stmt= null;
		String query = "DESCRIBE user;";
		ResultSet rs=null;
        
        try{
        	stmt= conn.createStatement();
            rs=stmt.executeQuery(query);
            while(rs.next()){
				String field = rs.getString("Field");
		        String type = rs.getString("Type");
		        
		        System.out.println(field + ":" + type);
			}
        
        }
        catch(SQLException e){}
        finally {
        	dbc.close(rs);
        	dbc.close(stmt);
			dbc.destroy();
		}
        
	}
	
	@Test
	public void testSelectQuery(){
		String[] colName = {"ID","email"};
		ArrayList<String[]> results = DBConnection.selectQuery(colName, "user");
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			System.out.println(toString(strings));
		}
	}
	
	private String toString(String[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			sb.append(",");
			
		}
		return sb.toString();
	}

}
