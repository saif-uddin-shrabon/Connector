package chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class UserFour extends JFrame implements ActionListener, Runnable{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    
   //I/O
    BufferedWriter writer;
    BufferedReader reader;

    UserFour(){
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(60, 178, 156));
        p1.setBounds(0, 0, 450, 70);
        add(p1);
        
        //for picking icon image
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/3.png"));
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel l1 = new JLabel(i3);
       l1.setBounds(5, 17, 30, 30);
       p1.add(l1);
       
       //exit button
       l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
       
       //for picking profile image 
       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/1.png"));
       Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
       ImageIcon i6 = new ImageIcon(i5);
       JLabel l2 = new JLabel(i6);
       l2.setBounds(40, 5, 60, 60);
       p1.add(l2);
       
       //for picking video icon 
       ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/video.png"));
       Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i9 = new ImageIcon(i8);
       JLabel l5 = new JLabel(i9);
       l5.setBounds(290, 20, 30, 30);
       p1.add(l5);
        
       //for picking phone icone 
       ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/phone.png"));
       Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
       ImageIcon i13 = new ImageIcon(i12);
       JLabel l6 = new JLabel(i13);
       l6.setBounds(350, 20, 35, 30);
       p1.add(l6);
       
       //for picking 3dot icon 
       ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/3icon.png"));
       Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
       ImageIcon i16 = new ImageIcon(i15);
       JLabel l7 = new JLabel(i16);
       l7.setBounds(410, 20, 13, 25);
       p1.add(l7);
       
        //Adding Group name
       JLabel l3 = new JLabel("LU's Club");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);   
       
       
       JLabel l4 = new JLabel("Rayat, Saif, Ananna, Julfa,Tushar");
       //Font name
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 160, 20);
       p1.add(l4);   
       
       
        
       a1 = new JTextArea();
       a1.setBounds(5, 75, 440, 570);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       a1.setEditable(false);
       a1.setLineWrap(true);
       a1.setWrapStyleWord(true);
       add(a1);
       
        //Text Field
       t1 = new JTextField();
       t1.setBounds(5, 655, 310, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       add(t1);
       
       //Send Button
       b1 = new JButton("Send");
       b1.setBounds(320, 655, 123, 40);
       b1.setBackground(new Color(7, 94, 84));
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       add(b1);
        
       getContentPane().setBackground(Color.WHITE);
       setLayout(null);
       setSize(450, 745);
       setLocation(1440, 200); 
       //setUndecorated(true);
       setVisible(true);
       
       try{
           //Connection with the server
           Socket socketClient = new Socket("localhost", 2003);
           //Massage write and read
           writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
           reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
       }catch(Exception e){}
       
        
    }
    
    public void actionPerformed(ActionEvent ae){
        String str = "Tushar\n"+t1.getText();
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){}
        t1.setText("");
    }
    
    public void run(){
        try{
            String msg = "";
            //Read all massage
            while((msg = reader.readLine()) != null){
                a1.append(msg + "\n");
            }
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        UserFour four = new UserFour();
        //Starting Thread
        Thread t1 = new Thread(four);
        t1.start();
    }
    
}
