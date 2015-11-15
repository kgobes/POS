//Dan Soskey

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;

public class SaleOrder extends Order{
  public SaleOrder(int orderID){
    super(orderID);
  }
  
  @Override
  public void storeOrder(){
    for(LineItem line : list)
      addNewProductLine(orderID, line.getProduct().getProductId(), line.getQuantity());
  }
  
  private boolean addNewProductLine(int orderID, int prodID, int quantity){
    
    String sql = "{?=call ADDNEWPRODUCTLINE(?,?,?)}";
    int value= 0;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setLong(2, orderID);
      statement.setLong(3, prodID);
      statement.setLong(4, quantity);
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