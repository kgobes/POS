//Dan Soskey
//Last Updated: 11.1.15

//Dont use rn
public class RentalProduct extends Product{
  int duration; //Measured in number of days
  
  public RentalProduct(int productId, double price, String description, int duration){
    super(productId, price, description);
    this.duration = duration;
  }
  
  public int getDuration(){
    return duration;
  }
  
  public void setDuration(int duration){
    this.duration = duration;
  }
}