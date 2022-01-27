
package chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

public class RegPage extends JFrame {
    
    JPanel left,right;
    JTextField user,pass,repass,email;
    JButton but1,but2,but3;
    
    RegPage(Connection con , Statement st){
        
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
        
        JLabel l3 =new JLabel ("Welcome Back");
        //Font name
        l3.setFont(new Font("SAN_SERIF", Font.BOLD,30));
        //Font color
        l3.setForeground(Color.WHITE);
        l3.setBounds(100,100,300,300);
        left.add(l3);
        
        JLabel l4 =new JLabel ("Please Login with your information");
        //Font name
        l4.setFont(new Font("SAN_SERIF", Font.BOLD,18));
        //Font color
        l4.setForeground(Color.WHITE);
        l4.setBounds(50,250,400,100);
        left.add(l4);
        
        but1= new JButton("Login");
        but1.setBounds(100, 400, 200, 40);
        but1.setBackground(new Color(255,255,255));
        but1.setForeground(Color.BLACK);
        but1.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
       // but1.setBorder(new RoundBtn1(15));
       but1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
              new LoginPage(con,st).setVisible(true);
                

            }
       
       });
        left.add(but1);
     
         right = new JPanel();
         right.setLayout(null);
         right.setBackground(new Color(255, 255, 255));
         right.setBounds(400,0,700,745);
         add(right);
         
         JLabel l5 =new JLabel ("Create Account");
        //Font name
        l5.setFont(new Font("SAN_SERIF", Font.BOLD,50));
        //Font color
        l5.setForeground(new Color(60, 178, 156));
        l5.setBounds(150,50,400,150);
        right.add(l5);
        
        user =new JTextField();
        user.setText("User name");
        user.setBounds(160,250,350,40);
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
        
        email =new JTextField();
        email.setText("Email");
        email.setBounds(160,300,350,40);
        email.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if(email.getText().equals("Email")){
                    email.setText("");
                }   
            }
            @Override
            public void focusLost(FocusEvent fe) {
                 if(email.getText().equals("")){
                    email.setText("Email");
                }
            }
        });
        right.add(email);
        
        pass =new JTextField("Set Password");
        pass.setBounds(160,350,350, 40);
        pass.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        pass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if(pass.getText().equals("Set Password")){
                    pass.setText("");
                }   
            }
            @Override
            public void focusLost(FocusEvent fe) {
                 if(pass.getText().equals("")){
                    pass.setText("Set Password");
                }
            }
        });
        right.add(pass);
        
        repass =new JTextField("Set Password again");
        repass.setBounds(160,400,350, 40);
        repass.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        repass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if(repass.getText().equals("Set Password again")){
                    repass.setText("");
                }   
            }
            @Override
            public void focusLost(FocusEvent fe) {
                 if(repass.getText().equals("")){
                    repass.setText("Set Password again");
                }
            }
        });
        right.add(repass);
        
        but2= new JButton("Sign Up");
        but2.setBounds(240, 550, 200, 40);
        but2.setBackground(new Color(60, 178, 156));
        but2.setForeground(Color.WHITE);
        but2.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
       // but1.setBorder(new RoundBtn1(15));
        but2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
              try{
                  String us = user.getText();
                  String em = email.getText();
                  String ps = pass.getText();
                  String cmps = repass.getText();
                  if(ps.equals(cmps)){
                       String q = "insert into connectauth values ('" + us +"', '"+ em +"', '"+ ps +"')";
                       st.executeUpdate(q);
                       JOptionPane.showMessageDialog(left, "Registation Succsessfull");
                  }
                  else{
                      JOptionPane.showMessageDialog(right, "Password didn't matched");
                  }
                  user.setText("User name");
                  email.setText("Email");
                  pass.setText("Set Password");
                  repass.setText("Set Password again");
                 
              }catch(Exception e){}
            
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
    
    
    
 
    
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/connectstorage", "root", "");
		
         Statement st = con.createStatement();
        new RegPage(con,st).setVisible(true);
    }

  
}
