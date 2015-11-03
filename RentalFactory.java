//Dan Soskey

public class RentalFactory extends OrderFactory{
  private static RentalFactory instance;
  
  private RentalFactory(){}
  
  public static RentalFactory getInstance(){
    if(instance == null)
      instance = new RentalFactory();
    return instance;
  }
  
  public Order makeOrder(int orderID){
    return new RentalOrder(orderID);
  }
}