//Dan and Ro make a code-thing
//Version 0.0.2 Date 10.4.15
//Process Sale class: runs a sample iteration of a processed sale

public class ProcessSale{
  
  public static void main(String args[]){
    System.out.println("Process Sale");
    Register register = new Register();
    register.makeOrder();
    System.out.println(register.order());
    System.out.println("Adding 4 Red Canoes");
    register.order().addLineItem(register.getProduct(11), 4);
    System.out.println(register.order());
    System.out.println("Adding 1 Blue Canoe");
    register.order().addLineItem(register.getProduct(12), 1);
    System.out.println(register.order());
    System.out.println("Displaying Total: $" + register.order().getSubtotal() * register.TAXRATE);
    //System.out.println("Paying with $$$$$
  }
}