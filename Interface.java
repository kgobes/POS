//Dan Soskey

import java.util.Scanner;

public class Interface{
  public static Register register = new Register();
  public static Scanner keyboard = new Scanner(System.in);
  
  public static void validateUser(){
    //TODO
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
    register.makeOrder();
    System.out.println("Transaction complete. Returning to main menu.");
  }
  
  public static String getActionChoice(){
    System.out.println("Enter SALE to process sale.\nEnter RENTAL to process Rental.\nEnter RETURN to process Return.\nEnter ! to quit.");
    return keyboard.next();
  }
  
  public static void main(String []args){
    String userChoice = "";
    System.out.println("Welcome to POSquared.");
    validateUser();
    do{
      userChoice = getActionChoice();
      switch(userChoice){
        case "SALE": processSale();
      }
    }while(!userChoice.equals("!"));
    System.out.println("Have a nice day!");
  }
}