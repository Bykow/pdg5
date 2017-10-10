package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import db.DBConnection;

@RunWith(Suite.class)
@SuiteClasses({testGame.class, testTournament.class, testUser.class})
public class testDB {
	
	public static Connection con = null;
	
	@BeforeClass
	public static void initialize() {
		System.out.println("initialize");
		Sql2o sql2o = DBConnection.getConnection();
		try{
			con = sql2o.beginTransaction();
			System.out.println("connection open");
	    }
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void finish() {
		System.out.println("finish");
		con.rollback();
		con.close();
	}
}
