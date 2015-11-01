//Last Updated: 11.1.15

import java.sql.*;
import java.sql.Connection;

public class Product{
  protected int productId;
  protected double price;
  protected String description;
  
  public String toString(){
    return description + " $" + price;
  }
  
  public Product(int productId, double price, String description){
    this.productId = productId;
    this.price = price;
    this.description = description;
  }
  
  public boolean validate(){
    String sql = "{ ? = call VALIDATEPRODUCT(?)";
    int value=-1;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.setLong(2, productId);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.execute();
      value = statement.getInt(1);
    }catch(Exception e){
      DBManager.closeConnection();
    }
    if(value>=0){
      return true;
    }
    else{
      return false;
    }
  }
  
  public int getProductId(){
    return productId;
  }
  
  public double getPrice(){
    return price;
  }
  
  public String getDescription(){
    return description;
  }
}