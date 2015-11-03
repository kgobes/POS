//Dan Soskey
//Version 0.0.1, Date: 10.4.15

import java.util.ArrayList;
import java.sql.*;

/**
 * Order class: handles the aggregation of LineItems in an order.
 */
public class Order {
  private ArrayList<LineItem> list = new ArrayList<LineItem>();
  private boolean isSubtotalUpdated = true;
  private double subtotal = 0;
  private int orderID;
  
  public String toString(){
    StringBuilder s = new StringBuilder();
    s.append("Order No: " + orderID + "\n\n");
    for(LineItem line : list)
      s.append(line.getProduct().toString() + " x" + line.getQuantity() + " = $" + line.getTotalPrice() + "\n");
    s.append("Subtotal: $" + this.getSubtotal());
    return s.toString();
  }
  
  /**
   * Constructor, assigns orderID
   */
  public Order(int orderID){
    this.orderID = orderID;
  }
  
  /**
   * Searches list for a current LineItem with a matching productId to the product to add
   * if found, adds quantity to that LineItem's quantity. if not, creates a new LineItem
   */
  public void addLineItem(Product product, int quantity, int duration){
    boolean isFound = false;
    for(LineItem line : list){
      if(product.getProductId() == line.getProduct().getProductId() && duration == line.getDuration()){
        line.addQuantity(quantity);
        isFound = true;
      }
    }
    if(!isFound)
      list.add(new LineItem(product, quantity, duration));
    isSubtotalUpdated = false;
  }
  
  /**
   * Removes a line item matching productId. If none found, nothing is done
   * TODO: Display a message if productId not found
   */
  public void removeLineItem(int productId){
    for(int i = 0; i < list.size(); i++){
      if(productId == list.get(i).getProduct().getProductId())
        list.remove(i);
    }
  }
  
  /**
   * returns subtotal, which is recalculated if needed
   */
  public double getSubtotal(){
    if(!isSubtotalUpdated){
      subtotal = 0;
      for(LineItem line : list)
        subtotal += line.getTotalPrice();
      isSubtotalUpdated = true;
    }
    return subtotal;
  }
  
  /**
   * stores Order info to the database established in con
   * Not used in this use case. Implemented for order history use case
   */
  public void storeOrder(Connection con){
  }
    
}
