import junit.framework.TestCase;
import java.sql.*;

public class WhiteBoxTesting extends TestCase {

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
