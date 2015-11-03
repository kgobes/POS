import junit.framework.TestCase;
import java.sql.*;
/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class CatalogTest extends TestCase {
  public static void main(String [] args){
    try{
      testFill(DBManager.getConnection());
      DBManager.closeConnection();
    }catch(Exception e){}
  }
  public static void testFill(Connection con) {
    try{
      Catalog c = new Catalog(con);      
    }
    catch(Exception e){
      System.out.println("TEST FAILED");
    }
    System.out.println("TEST SUCCESS");
  }
}
