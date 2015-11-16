import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class swing_sample extends JFrame
{
    //declaring our swing components
    JLabel l_name,l_pass;
    JTextField t_name;
    JPasswordField t_pass;     //A special JTextField but hides input text
    JButton button;
    Container c;
    JFrame frame2;
    JPanel panel2;
 
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

                    JButton sale = new JButton("Sale");
                    JButton rental = new JButton("Rental");
                    JButton returnItems = new JButton("Return");
                    frame2.add(panel2);
                    panel2.add(sale);
                    frame2.setSize(200,300);
                    
                    
                    
        
                    sale.addActionListener(new ActionListener()
                    {
                       public void actionPerformed(ActionEvent ae)
                          {
                         //if clicked then sale things should happen
                          System.out.println("HAI");
            }
        });
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
            }
        }
 
    }
}