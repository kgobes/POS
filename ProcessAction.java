import java.util.Scanner;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProcessAction{
  
   public static Register register = new Register();
  public static Scanner keyboard = new Scanner(System.in);
  public TwoFrames tf = new TwoFrames();
  
//--------------------------------------------------------------------------------------------------------------------
  
  public String validateUser(){
    String user, pass;
    System.out.print("Enter a username: ");
    //user = keyboard.next();
    user =  tf.username.getText();
    System.out.println(user);
    System.out.print("Enter your password: ");
    pass = keyboard.next();
    logOn(user, pass);//TODO add if statement
    System.out.println("Combination acceptable. Welcome aboard.");
    return user;
  }

//--------------------------------------------------------------------------------------------------------------------
  
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
  
//--------------------------------------------------------------------------------------------------------------------
  
  public static String getActionChoice(){
    System.out.println("Enter SALE to process sale.\nEnter RENTAL to process Rental.\nEnter RETURN to process Return.\nEnter ! to log out/log off.");
    return keyboard.next();
  }
 
//--------------------------------------------------------------------------------------------------------------------
  
  public static void processSale(){
    boolean orderComplete = false;
    int productID = 0, quantity = 0;
    double amountPaid = 0.0;
    System.out.println("Process Sale");
    register.makeOrder('S');
    while(!orderComplete){
      System.out.println(register.order());
      System.out.println("Enter an ID of an item to add or -1 to finish adding items: ");
      productID = keyboard.nextInt();
      if(productID >= 0){
        System.out.println("Enter a quantity: ");
        quantity = keyboard.nextInt();
        register.order().addLineItem(register.getProduct(productID), quantity, 0);
      }
      else
        orderComplete = true;
    }
    System.out.println(register.order());
    System.out.println("Displaying Total: $" + register.order().getSubtotal() * register.TAXRATE);
    System.out.println("Enter amount to pay: ");
    amountPaid = keyboard.nextDouble();
    System.out.println("Your change is $" + (amountPaid - register.order().getSubtotal() * register.TAXRATE));
    //Store transaction in the database
    System.out.println("Transaction complete. Returning to main menu.");
  }

//--------------------------------------------------------------------------------------------------------------------
  
  public static void processRental(){
    boolean orderComplete = false;
    int productID = 0, quantity = 0;
    double amountPaid = 0.0;
    int days = 0;
    System.out.println("Process Rental");
    register.makeOrder('R');
    while(!orderComplete){
      System.out.println(register.order());
      System.out.println("Enter an ID of an item to add or -1 to finish adding items: ");
      productID = keyboard.nextInt();
      if(productID >= 0){
        System.out.println("Enter a quantity: ");
        quantity = keyboard.nextInt();
        System.out.println("How many days would you like to rent for? ");
        days = keyboard.nextInt();
        register.order().addLineItem(register.getProduct(productID), quantity, days);
      }
      else
        orderComplete = true;
    }
    System.out.println(register.order());
    System.out.println("Displaying Total: $" + register.order().getSubtotal() * register.TAXRATE);
    System.out.println("Enter amount to pay: ");
    amountPaid = keyboard.nextDouble();
    System.out.println("Your change is $" + (amountPaid - register.order().getSubtotal() * register.TAXRATE));
    System.out.println("Please return in " + days + " days.");
    //Store transaction in the database
    System.out.println("Transaction complete. Returning to main menu.");
    
  }

//--------------------------------------------------------------------------------------------------------------------
  
  public static void processReturn(){
    register.makeOrder('E');
    System.out.print("Please enter the order number of the item(s) you are returning: ");
    int orderID = keyboard.nextInt(), itemID = -1, quantity = 0;
    double amountRet = 0.0;
    //statement to retrieve order from order history in DB
    //Display order
    boolean isDoneReturning = false;
    do{
      System.out.print("Enter an item ID to return an item or -1 to finish returning items: ");
      itemID = keyboard.nextInt();
      if(itemID == -1){
        isDoneReturning = true;
        System.out.println(quantity + " items returned. You have been refunded " + amountRet);
      }
      else{
        System.out.print("Enter an amount to return: ");
        quantity = keyboard.nextInt();
        amountRet += quantity * register.cat.Dictionary.get(itemID).getPrice();
        //!!!!!!!!!!!!!!!!!!
      }
    }while(!isDoneReturning);
  }
  
//--------------------------------------------------------------------------------------------------------------------
  
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
  
//--------------------------------------------------------------------------------------------------------------------
  
  public static void main(String []args){
    // SwingUtilities.invokeLater(new Runnable();
    
    tf.createAndDisplayGUI();
    String userChoice = "";
    System.out.println("Welcome to POSquared.");
    String user = validateUser();
    do{
      userChoice = getActionChoice();
      switch(userChoice){
        case "SALE": processSale(); break;
        case "RENTAL": processRental(); break;
        case "RETURN": processReturn(); break;
      }
    }while(!userChoice.equals("!"));
    logOut(user);
    System.out.println("Have a nice day!");
  }
}