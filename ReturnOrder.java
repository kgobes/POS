//Dan Soskey

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;

public class ReturnOrder extends Order{
  public ReturnOrder(int orderID){
    super(orderID);
  }
  
  @Override
  public void storeOrder(){
    for(LineItem line : list)
      productReturn(orderID, line.getProduct().getProductId());
  }
  
  public boolean productReturn(int orderId, int prodId){
    
    String sql = "{?=call ADDNEWRENTALLINE(?,?)}";//change func name
    int value= 0;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setLong(2, orderId);
      statement.setLong(3, prodId);
     // System.out.println("here.");
      statement.execute();
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened: " + e);
      DBManager.closeConnection();
    }
    
   if(value == 0){
      return true;
    }
    else{
      return false;
    }
  }
}