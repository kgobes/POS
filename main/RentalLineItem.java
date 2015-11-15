//Dan Soskey
//Last Updated: 11.1.15

//Dont Use RN
public class RentalLineItem extends LineItem{
  int duration; //Measured in number of days
  
  public RentalLineItem(Product product, int quantity , int duration){
    super(product, quantity);
    this.duration = duration;
  }
  
  public int getDuration(){
    return duration;
  }
  
  public void setDuration(int duration){
    this.duration = duration;
  }
}