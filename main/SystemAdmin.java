import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemAdmin{
  
  public static boolean addNewProductLine(int prodId, int orderId, int quantity){
    
    String sql = "{?=call ADDNEWPRODUCTLINE(?,?,?)}";
    int value= 0;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setLong(2, prodId);
      statement.setLong(3, orderId);
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
  
  public static int addNewRentalLine(int orderId, int prodId, int quantity, int duration){
    
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
  
  
  public static int removeRental(int rentalId){
    
    String sql = "{?=call REMOVERENTAL(?)}";
    int value= 0;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setLong(2, rentalId);
     // System.out.println("here.");
      statement.execute();
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened: " + e);
      DBManager.closeConnection();
    }
    return value;
  }
  
  
  
  public static boolean addNewUser(String username, String password, String newUser, String newPass, int lvl, String name){
    
    String sql = "{?=call ADDNEWUSER(?,?,?,?,?,?)}";
    int value= 0;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setString(2, username);
      statement.setString(3, password);
      statement.setString(4, newUser);
      statement.setString(5, newPass);
      statement.setLong(6, lvl);
      statement.setString(7, name);
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
  
  public static boolean logOut(String username){
    //int value = 0;
    
    String sql = "{?=call RECORDSYSTEMLOGOUT(?)}";
    int value=-1;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
      statement.setString(2, username);
      //System.out.println("here.");
      statement.execute();
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened.");
      DBManager.closeConnection();
    }
    
    if(value == 0){
      return true;
    }
    else{
      return false;
    }
  }
  
  public static boolean logOn(String username, String password){
    
    String sql = "{?=call RECORDSYSTEMLOGONATTEMPT(?,?)}";
    int value=-1;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);      
      statement.setString(2, username);
      statement.setString(3, password);
      statement.execute();
      //System.out.println("here.");
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened:"+e);
      DBManager.closeConnection();
    }
    
    if(value > 1){
      return true;
    }
    else{ 
      return false;
    }
  }
  
  public static void main(String[] args){
    System.out.println(removeRental(2));
    
    //addNewUser();
    //
    //logOn("admin", "password")
    //
    //logOff();
  }
}
