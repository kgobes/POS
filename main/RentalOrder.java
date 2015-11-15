//Dan Soskey

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;

public class RentalOrder extends Order{
  public RentalOrder(int orderID){
    super(orderID);
  }
  
  @Override
  public void storeOrder(){
    for(LineItem line : list)
      addNewRentalLine(orderID, line.getProduct().getProductId(), line.getQuantity(), line.getDuration());
  }
  
  private int addNewRentalLine(int orderId, int prodId, int quantity, int duration){
    
    String sql = "{?=call ADDNEWRENTALLINE(?,?,?,?)}";
    int value= 0;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setLong(2, orderId);
      statement.setLong(3, prodId);
      statement.setLong(4, quantity);
      statement.setLong(5, duration);
     // System.out.println("here.");
      statement.execute();
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened: " + e);
      DBManager.closeConnection();
    }
    return value;
  }
}