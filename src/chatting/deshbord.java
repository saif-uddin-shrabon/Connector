
package chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class deshbord extends JFrame implements ActionListener {
    JPanel left,mid,right;
    JButton logout,single,multi;
    
    deshbord(){
        
        
        left=new JPanel();
        left.setLayout(null);
        left.setBackground(new Color(60, 178, 156));
        left.setBounds(0,0,1100,745);
        add(left);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/1.png"));
        Image  i5 = i4.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon (i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(450,30,200,200);
        left.add(l2);
        
        
        
       
        
        logout= new JButton("Logout");
        logout.setBounds(15, 600, 150, 70);
        logout.setBackground(new Color(7,94,84));
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("SAN_SERIF", Font.PLAIN, 24));
        logout.addActionListener(this);
        left.add(logout);
        
        
        
        single= new JButton("Single chat");
        single.setBounds(300, 300, 200, 70);
        single.setBackground(new Color(7,94,84));
        single.setForeground(Color.WHITE);
        single.setFont(new Font("SAN_SERIF", Font.PLAIN, 24));
        single.addActionListener(this);
        left.add(single);
        
        multi= new JButton("Group chat");
        multi.setBounds(600, 300, 200, 70);
        multi.setBackground(new Color(7,94,84));
        multi.setForeground(Color.WHITE);
        multi.setFont(new Font("SAN_SERIF", Font.PLAIN, 24));
        multi.addActionListener(this);
        left.add(multi);
        
        
        
        setLayout(null);
        setSize(1100,745);
        setLocation(400,200);
        //setUndecorated(true); //for removing header
        setVisible(true);
        setDefaultCloseOperation(3);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==single){
//             deshbord db = new deshbord();
//             db.show();
             Server sv = new Server();
             Client cl = new Client();
            // sv.f1.setVisible(true);;
             dispose();
         }
        if(ae.getSource()==multi){
             new UserOne();
              new UserTwo();
             new UserThree();
              new UserFour();
            
            // Client cl = new Client();
          
             //dispose();
         }
        if(ae.getSource()==logout){
            System.exit(0);
        }
         
        
         }
    public static void main(String[] args) {
        new deshbord().setVisible(true);
    }
}
