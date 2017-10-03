package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;

public class testDB {
	
	@Test
	public void sql2oConnection() {
		Sql2o sql2o = DBConnection.getConnection();
		try (Connection con = sql2o.open()) {
		    System.out.println("connection open");
		  }
	}
	
	public void cleanDatabase() {
		throw new UnsupportedOperationException("Not implemented yet");
		
		Sql2o sql2o = DBConnection.getConnection();
		
		try (Connection con = sql2o.open()) {
		  
		// drop database
		con.createQuery("DROP DATABASE pdg").execut
		
		// load from script
		
		}
	}
	
	public void insertRandomData() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
