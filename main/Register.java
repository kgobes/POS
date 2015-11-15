import java.sql.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.io.*;

public class Register{
  public final double TAXRATE = 1.07;
  private Order order;
  public Catalog cat;
  public Register(){
    try{
      Statement s = null;
      Connection con = null;
      Scanner krb = new Scanner(System.in);
      con= DBManager.getConnection();
      s=con.createStatement();
      
      cat = new Catalog(con);
      
    }catch(Exception e){
      System.out.println (e.getMessage());
    }   
  }
  
  public Product getProduct(int id){
    return cat.Dictionary.get(id);
  }
  
  public void makeOrder(char orderType){
    String sql = "{?=call GETNEXTORDERID()}";
    int value=-1;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.execute();
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened.");
      DBManager.closeConnection();
    }
    switch(orderType){
      case 'S': order = SaleFactory.getInstance().makeOrder(value); break;
      case 'R': order = RentalFactory.getInstance().makeOrder(value); break;
      case 'E': order = ReturnFactory.getInstance().makeOrder(value); break;
    }
  }
  
  public void finishOrder(){
    
  }
  
  public Order order(){
    return order;
  }       
}