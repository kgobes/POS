//Dan Soskey

public class ReturnFactory extends OrderFactory{
  private static ReturnFactory instance;
  
  private ReturnFactory(){}
  
  public static ReturnFactory getInstance(){
    if(instance == null)
      instance = new ReturnFactory();
    return instance;
  }
  
  public Order makeOrder(int orderID){
    return new ReturnOrder(orderID);
  }
  
}