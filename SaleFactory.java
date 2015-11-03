//Dan Soskey

public class SaleFactory extends OrderFactory{
  private static SaleFactory instance;
  
  private SaleFactory(){}
  
  public static SaleFactory getInstance(){
    if(instance == null)
      instance = new SaleFactory();
    return instance;
  }
  
  public Order makeOrder(int orderID){
    return new SaleOrder(orderID);
  }
}