//Dan Soskey
//Version 0.0.1, Date: 10.4.15

/**
 * LineItem Class: holds info regarding product and quantity of a line item in an order
 */
public class LineItem {
  private Product product;
  private int quantity;
  
  /**
   * LineItem constructor
   */
  public LineItem(Product product, int quantity) { 
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * getter for product
   */
  public Product getProduct(){
    return product;
  }
  
  /**
   * getter for quantity
   */
  public int getQuantity(){
    return quantity;
  }
  
  /**
   * adds toAdd to current quantity
   */
  public void addQuantity(int toAdd){
    this.quantity += toAdd;
  }
  
  /**
   * removes up to toRemove from quantity
   */
  public void removeQuantity(int toRemove){
    if(toRemove >= quantity)
      quantity = 0;
    else
      this.quantity -= toRemove;
  }
  
  /**
   * adds 1 to quantity
   */
  public void increment(){
    this.quantity++;
  }
  
  /**
   * removes 1 from quantity if greater than 0
   */
  public void decrement(){
    if(quantity > 0)
      this.quantity--;
  }
  
  /**
   * returns total price of the LineItem
   */
  public double getTotalPrice(){
    return /*product.getPrice() */ (double) quantity;
  }
}