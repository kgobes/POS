public class WhiteBoxTesting1{
  public static void main(String[] args){
    //Testing for Rental Product
    System.out.println("Creating new rental product:");
    RentalProduct rp = new RentalProduct(123, 20, "Red Canoe", 5);
    System.out.println("Description: " + rp.getDescription() + " Should display Red Canoe");
    System.out.println("Price: $" + rp.getPrice() + " Should display $20");
    System.out.println("Duration: " + rp.getDuration() + " days Should display 5 days");
    rp.setDuration(7); //change duration 
    System.out.println("New duration: " + rp.getDuration() + " days Should display 7 days");
    System.out.println("Valid product? " + rp.validate() +" Should display false");
  }

}