
package chatting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;


public class LoginPage extends JFrame  {
    JPanel left,right;
    JTextField user,repass;
    JTextField pass;
    JButton but1,but2,but3;
    
    
    
    LoginPage(Connection con, Statement st){
        left = new JPanel();
        left.setLayout(null);
        left.setBackground(new Color(60, 178, 156));
         left.setBounds(0,0,400,745);
         add(left);
         
//         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/c.png"));
//        Image  i2 = i1.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon (i2);
//        JLabel l1 = new JLabel(i3);
//        l1.setBounds(5,17,40,40);
//        left.add(l1);
        
        JLabel l2 =new JLabel ("Connect");
        //Font name
        l2.setFont(new Font("SAN_SERIF", Font.BOLD,18));
        //Font color
        l2.setForeground(Color.WHITE);
        l2.setBounds(55,25,100,20);
        left.add(l2);
        
        JLabel l3 =new JLabel ("Create Account");
        //Font name
        l3.setFont(new Font("SAN_SERIF", Font.BOLD,30));
        //Font color
        l3.setForeground(Color.WHITE);
        l3.setBounds(90,100,300,300);
        left.add(l3);
        
        JLabel l4 =new JLabel ("If you are new user create account first");
        //Font name
        l4.setFont(new Font("SAN_SERIF", Font.BOLD,18));
        //Font color
        l4.setForeground(Color.WHITE);
        l4.setBounds(35,250,400,100);
        left.add(l4);
        
        but1= new JButton("Sign Up");
        but1.setBounds(100, 400, 200, 40);
        but1.setBackground(new Color(255,255,255));
        but1.setForeground(Color.BLACK);
        but1.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
       // but1.setBorder(new RoundBtn1(15));
        but1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                  
                
                 dispose();
            }
        
        });
        left.add(but1);
     
         right = new JPanel();
         right.setLayout(null);
         right.setBackground(new Color(255, 255, 255));
         right.setBounds(400,0,700,745);
         add(right);
         
         JLabel l5 =new JLabel ("Login");
        //Font name
        l5.setFont(new Font("SAN_SERIF", Font.BOLD,50));
        //Font color
        l5.setForeground(new Color(60, 178, 156));
        l5.setBounds(250,50,400,150);
        right.add(l5);
        
        user =new JTextField("User name");
        user.setBounds(160,300,350, 40);
        user.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        user.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if(user.getText().equals("User name")){
                    user.setText("");
                }   
            }
            @Override
            public void focusLost(FocusEvent fe) {
                 if(user.getText().equals("")){
                    user.setText("User name");
                }
            }
        });
        right.add(user);
        
        pass =new JTextField();
        pass.setText("Password");
        pass.setBounds(160,360,350, 40);
        pass.setFont(new Font("SAN_SERIF", Font.BOLD,16));
         pass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if(pass.getText().equals("Password")){
                    pass.setText("");
                }   
            }
            @Override
            public void focusLost(FocusEvent fe) {
                 if(pass.getText().equals("")){
                    pass.setText("Password");
                   // pass.setEchoChar();
                }
            }
        });
        right.add(pass);
        
        but2= new JButton("Login");
        but2.setBounds(240, 420, 200, 40);
        but2.setBackground(new Color(60, 178, 156));
        but2.setForeground(Color.WHITE);
        but2.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
       // but1.setBorder(new RoundBtn1(15));
        but2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               try{
                 String u = user.getText();
                 String p = pass.getText();
                 String q = "select * from connectauth where username='"+u+"' and password='"+p+"'";
                 ResultSet rs = st.executeQuery(q);
                 if(rs.next()){
                     new Server();
                    
                 }
                 else{
                JOptionPane.showMessageDialog(null, "Invalid login");
                
               }
             }catch(Exception e){}
               dispose();
            }
            
        });
        right.add(but2);
        
        
        setLayout(null);
        setSize(1100,745);
        setLocation(400,200);
        //setUndecorated(true); //for removing header
        setVisible(true);
        setDefaultCloseOperation(3);
    }
 
 
         
     
    
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/connectstorage", "root", "");
		
         Statement st = con.createStatement();
        new LoginPage(con,st).setVisible(true);
    }
    
}
