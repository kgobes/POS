import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TwoFrames 
{
    // Making our first JFrame.
    JFrame frame1 = new JFrame("Login Screen");
    // Declaring our second JFrame.
    JFrame frame2 ;

    public void createAndDisplayGUI()
    {               
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       

        // Used to position the JFrame at the middle of the screen.
        //frame1.setLocationRelativeTo(null);    
        JPanel panel= new JPanel();
        JLabel title= new JLabel("Employee Login");
        JTextField username=  new JTextField ("Enter username", 15);
        //username.setColumns(20);
        JTextField password= new JTextField ("Enter password", 15);
        frame1.add(panel);
        panel.add(title);
        panel.add(username);
        panel.add(password);

        // Use this instead for placing windows, as determined by the OS.
        frame1.setLocationByPlatform(true);     

        // Calling this method to create our frame2.
        makeMenu();

        // Button to show the second JFrame.
        JButton LoginButton = new JButton("Login");
        
        JButton newEmployee= new JButton("New Employee Sign Up");
        panel.add(LoginButton);
        panel.add(newEmployee);
        
        
        LoginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                
                if (!(frame2.isShowing()))
                {   

                    makeMenu();
                    frame2.setVisible(true);
                }
                System.out.print("pressed");
            }
        });
    }
    
    //MAKE MENU SCREEN
    private void makeMenu(){       
        frame2 = new JFrame("Menu Screen");
        //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationByPlatform(true);

        JButton hideButton = new JButton("HIDE FRAME");
        
        hideButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // On the click of this button, frame2 will 
                // disappear and HAI will be displayed on the console.
                frame2.dispose();
                System.out.println("HAI");
            }
        });
        

        // Adding the button to the South side of the frame1.
        //frame1.add(LoginButton, BorderLayout.PAGE_END);
        frame1.pack();
        frame1.setVisible(true);
    }

    private void makeNewEmployeeFrame()
    {       
        frame2 = new JFrame("New Employee");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationByPlatform(true);

        // Creating a JButton to be shown on the JFrame.
        JButton hideButton = new JButton("Register Employee");
        hideButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // On the click of this button, frame2 will 
                // disappear and HAI will be displayed on the console.
              JPanel panel2= new JPanel();
              JLabel title2= new JLabel("New Employee Sign Up");
              JTextField username2=  new JTextField ("Enter username", 15);
              //username.setColumns(20);
              JTextField password2= new JTextField ("Enter password", 15);
              panel2.add(title2);
              panel2.add(username2);
              panel2.add(password2);  
              //frame2.dispose();
              System.out.println("HAI");
            }
        });

        // Adding the button to the South side of the frame1.
        frame2.add(hideButton, BorderLayout.PAGE_END);
        frame2.pack();
    }

   /* public static void main(String... args)
    {
        /* Here we are Secheduling a JOB for 
         * Event Dispatcher Thread, since Swing
         * is not Thread Safe. This is used to place
         * the code which is responsible for 
         * creating and diaplaying your GUI.
         
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                TwoFrames tf = new TwoFrames();
                tf.createAndDisplayGUI();
            }
        });
    
    } */
}