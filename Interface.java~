//Dan Soskey

import java.util.Scanner;

public class Interface{
  public static Register register = new Register();
  public static Scanner keyboard = new Scanner(System.in);
  
  public static void validateUser(){
    //TODO add Ro's validation here
  }
  
  public static String getActionChoice(){
    System.out.println("Enter SALE to process sale.\nEnter RENTAL to process Rental.\nEnter RETURN to process Return.\nEnter ! to log out/log off.");
    return keyboard.next();
  }
    
  public static void processSale(){
    boolean orderComplete = false;
    int productID = 0, quantity = 0;
    double amountPaid = 0.0;
    System.out.println("Process Sale");
    register.makeOrder();
    while(!orderComplete){
      System.out.println(register.order());
      System.out.println("Enter a UPC of an item to add or -1 to finish adding items: ");
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
  
  public static void processRental(){
  }
  
  public static void processReturn(){
    register.makeOrder();
    System.out.print("Please enter the order number of the item(s) you are returning: ");
    int orderID = keyboard.nextInt(), itemID = -1, quantity = 0;
    //statement to retrieve order from order history in DB
    //Display order
    boolean isDoneReturning = false
    do{
      System.out.print("Enter an item ID to return an item or -1 to finish returning items: ");
      itemID = keyboard.nextInt();
      if(itemID == -1)
        isDoneReturning = true;
      else{
        System.out.print("Enter an amount to return: ");
        quantity = keyboard.nextInt();
        
      }
    }while(!isDoneReturning);
  }
  
  public static void main(String []args){
    String userChoice = "";
    System.out.println("Welcome to POSquared.");
    validateUser();
    do{
      userChoice = getActionChoice();
      switch(userChoice){
        case "SALE": processSale();
        case "RENTAL": processRental();
        case "RETURN": processReturn();
      }
    }while(!userChoice.equals("!"));
    System.out.println("Have a nice day!");
  }
}