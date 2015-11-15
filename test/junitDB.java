import junit.framework.TestCase;
import java.sql.*;
import java.io.*;
import java.sql.Driver;
import java.sql.Connection;
/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class junitDB extends TestCase{
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testgetConnection() {
    try{
     Connection con = DBManager.getConnection();
     boolean actual = con.isClosed();
     boolean expected = false;
     assertEquals("DBManager.getConnection();",expected, actual);
    }
    catch(Exception e){
     System.out.println("Something happened.");
     DBManager.closeConnection();
   }
  }
  
   public void testCloseConnection() {
    try{
     Connection con = DBManager.getConnection();
     DBManager.closeConnection();
     boolean actual = con.isClosed();
     boolean expected = true;
     assertEquals("DBManager.getConnection();",expected, actual);
    }
    catch(Exception e){
     System.out.println("Something happened.");
     DBManager.closeConnection();
   }
  }
  
}
