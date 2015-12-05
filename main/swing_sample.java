import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.sql.*;
public class swing_sample extends JFrame
{
  public static Register register = new Register();
  public static Scanner keyboard = new Scanner(System.in);
    //declaring our swing components
    JLabel l_name,l_pass;
    JTextField t_name;
    JPasswordField t_pass;     //A special JTextField but hides input text
    JButton button;
    Container c;
    JFrame frame2;
    JPanel panel2;
    
    //Sale Stuff:
    JFrame processSaleFrame;
    JPanel processSale;
    JLabel title, enterId, enterQuan;
    JTextField id, quan;
    JButton enter;
    JLabel item;
    JButton sale;

    JPanel panel4, panel3;

 
    //a inner class to handling ActionEvents
    handler handle;
 
    //a separate class for processing database connection and authentication
    DBManager db;
 
    swing_sample()
    {
        super("Login");
 
        c=getContentPane();
        c.setLayout(new FlowLayout());
 
        //extra classes
        db= new DBManager();
        handle =new handler();
 
                //swing components
        l_name=new JLabel("Username");
        l_pass=new JLabel("Password");
        t_name=new JTextField(10);
        t_pass=new JPasswordField(10);
        button=new JButton("Login");
        frame2=new JFrame("Menu");
 
        //adding actionlistener to the button
        button.addActionListener(handle);
 
        //add to contaienr
        c.add(l_name);
        c.add(t_name);
        c.add(l_pass);
        c.add(t_pass);
        c.add(button);
        //visual
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,300);
 
    }
    public static void main(String args[])
    {
        swing_sample sample=new swing_sample();
    }
 
    //an inner class .You can also write as a separate class
    class handler implements ActionListener
    {
        //must implement method
        //This is triggered whenever the user clicks the login button
        public void actionPerformed(ActionEvent ae)
        {
            //checks if the button clicked
            if(ae.getSource()==button)
            {
                char[] temp_pwd=t_pass.getPassword();
                String pwd=null;
                pwd=String.copyValueOf(temp_pwd);
                System.out.println("Username,Pwd:"+t_name.getText()+","+pwd);
 
                //The entered username and password are sent via "checkLogin()" which return boolean
                if(db.checkLogin(t_name.getText(), pwd)) 
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "You have logged in successfully","Success",
                                        JOptionPane.INFORMATION_MESSAGE); 
                    frame2 = new JFrame("Menu Screen");
                    panel2= new JPanel();
                    //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.setLocationByPlatform(true);

                    sale = new JButton("Sale");
                    JButton rental = new JButton("Rental");
                    JButton returnItems = new JButton("Return");
                    frame2.add(panel2);
                    panel2.add(sale);
                    panel2.add(rental);
                    panel2.add(returnItems);
                    frame2.setSize(200,300);

                    sale.addActionListener(new ActionListener()
                    {
                       public void actionPerformed(ActionEvent ae)
                          {
                         panel2.setVisible(false);
                         panel3= new JPanel();
                         frame2.add(panel3);
                         
                         /*boolean orderComplete = false;
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
                         */
                         
                         //if clicked then sale things should happen

                          processSaleFrame= new JFrame("Process Sale");
                          processSale= new JPanel();
                          title = new JLabel("Process Sale");
                          enterId = new JLabel("Enter an ID of an item ");
                          enterQuan = new JLabel("Quantity: ");
                          id =new JTextField(10);
                          quan = new JTextField(10);
                          enter = new JButton("Enter");
                          
                          processSaleFrame.add(processSale);
                          processSale.add(title);
                          processSale.add(enterId);
                          processSale.add(id);
                          processSale.add(enterQuan);
                          processSale.add(quan);
                          processSale.add(enter);
                          
                          processSaleFrame.setSize(200,300);
                          processSaleFrame.setVisible(true);
                          frame2.setVisible(true);
                          c.setVisible(false);
                          
                          
                       }
                        });
                 
                    //when an item gets entered:
                      enter.addActionListener(new ActionListener()
                    {
                       public void actionPerformed(ActionEvent ae)
                          {
                         item = new JLabel(id.getText() + "    " + quan.getText());
                         processSale.add(item);
                       }
            }); 
                    
                    
                    
                    
                    
                }         
                    
        //});
                    frame2.setVisible(true);
                    c.setVisible(false);
        

        // Adding the button to the South side of the frame1.
        //frame1.add(LoginButton, BorderLayout.PAGE_END);
        //frame1.pack();
        //frame1.setVisible(true);
                }
                else

                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "Login failed!","Failed!!",
                                        JOptionPane.ERROR_MESSAGE);
                }

        // Adding the button to the South side of the frame1.
        //frame1.add(LoginButton, BorderLayout.PAGE_END);
        //frame1.pack();
        //frame1.setVisible(true);
                
            }
        }
 
    }
