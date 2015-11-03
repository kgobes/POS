import junit.framework.TestCase;
import java.sql.*;
/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestRentalProduct extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testRentalProd() {
     RentalProduct rp = new RentalProduct(123, 20, "Red Canoe", 5);
    assertEquals(rp.getDescription(), "Red Canoe");
    assertEquals(rp.getPrice(), 20.0);
    assertEquals(rp.getDuration(), 5);
    rp.setDuration(7);
    assertEquals(rp.getDuration(), 7);
    assertEquals(rp.validate(), false);
   
  }
  
}
