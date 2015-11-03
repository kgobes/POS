import junit.framework.TestCase;
import java.sql.*;
/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class WhiteBoxTesting extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testProduct() {
    Product p= new Product(973, 100.00, "Blue Canoe");
    assertEquals(p.getProductId(), 973);
    assertEquals(p.getPrice(), 100.0);
    assertEquals(p.getDescription(), "Blue Canoe");
    assertEquals(p.validate(), false);
    assertEquals(p.toString(), "Blue Canoe $100.0");
  }
  public void testLineItem(){   
    Product p= new Product(973, 100.00, "Blue Canoe");
    LineItem li= new LineItem(p, 3, 7);
    assertEquals(li.getProduct(), p);
    assertEquals(li.getQuantity(), 3);
    assertEquals(li.getDuration(), 7);
    li.addQuantity(3);
    assertEquals(li.getQuantity(), 6);
    li.removeQuantity(4);
    assertEquals(li.getQuantity(), 2);
    li.increment();
    assertEquals(li.getQuantity(), 3);
    li.decrement();
    assertEquals(li.getQuantity(), 2);
    assertEquals(li.getTotalPrice(), 200.0);
  }
  
}
