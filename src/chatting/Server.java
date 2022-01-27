
package chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.EmptyBorder;


public class Server  implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static JFrame f1 = new JFrame();
    
    //Creat Box layte that help msg varticaly aline
        static Box vertical = Box.createVerticalBox();

    //Server Socket
      static ServerSocket skt;
      static Socket s;
      static DataInputStream din;
      static DataOutputStream dout;
    
      //typing animation
      Boolean typing;
    //Constructor 
    Server (){
        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         p1= new JPanel ();
         p1.setLayout(null);
         p1.setBackground(new Color(60, 178, 156));
         p1.setBounds(0,0,1100,70);
         f1.add(p1);
        
        //for picking icon image 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/3.png"));
        Image  i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon (i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5,17,30,30);
        p1.add(l1);
        //exit button
        l1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ae){
                 deshbord db = new deshbord();
                 db.show();
                
                //System.exit(0);
            }
        });
        
        //for picking profile image 
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/1.png"));
        Image  i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon (i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(50,4,60,60);
        p1.add(l2);
        
        //for picking video icon 
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/video.png"));
        Image  i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon (i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(950,20,30,30);
        p1.add(l5);
        
        //for picking phone icone 
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/phone.png"));
        Image  i12 = i11.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon (i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(1000,20,35,30);
        p1.add(l6);
        
        //for picking 3dot icon 
        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/icons/3icon.png"));
        Image  i15 = i14.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon (i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(1050,20,13,25);
        p1.add(l7);
        
        
        //Adding Profile name
        JLabel l3 =new JLabel ("Rayat");
        //Font name
        l3.setFont(new Font("SAN_SERIF", Font.BOLD,18));
        //Font color
        l3.setForeground(Color.WHITE);
        l3.setBounds(120,15,100,20);
        p1.add(l3);
        
        //Adding Active Status
        JLabel l4 =new JLabel ("Active Now");
        //Font name
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN,14));
        //Font color
        l4.setForeground(Color.WHITE);
        l4.setBounds(120,35,100,20);
        p1.add(l4);
        
        //Typing Animation timer
             Timer t = new Timer(1, new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
               }
           }
       });
       
       t.setInitialDelay(2000);
        
        //Text Area
        a1= new JPanel();
//        a1.setBounds(5, 75,440,570);
        a1.setFont(new Font("SAN_SERIF", Font.BOLD,16));
//        f1.add(a1);
        
        JScrollPane sp = new JScrollPane(a1);
        sp.setBounds(5, 75,1070,570);
        sp.setBorder(BorderFactory.createEmptyBorder());
        f1.add(sp);
        //Text Field
        t1 =new JTextField();
        t1.setBounds(5, 655,945, 40);
        t1.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        f1.add(t1);
        
        t1.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent ke){
               l4.setText("typing...");
               
               t.stop();
               
               typing = true;
           }
           
           public void keyReleased(KeyEvent ke){
               typing = false;
               
               if(!t.isRunning()){
                   t.start();
               }
           }
       });
        
        //Send Button
        b1= new JButton("Send");
        b1.setBounds(950, 655, 123, 40);
        b1.setBackground(new Color(60, 178, 156));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        f1.add(b1);
        
        f1.getContentPane().setBackground(Color.WHITE);
        f1.setLayout(null);
        f1.setSize(1100,745);
        f1.setLocation(400,200);
        //f1.setUndecorated(true); //for removing header
        f1.setVisible(true);
        f1.setDefaultCloseOperation(3);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        try{
         String out = t1.getText();
            
            JPanel p2 = formatLabel(out);
            
            a1.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            
            a1.add(vertical, BorderLayout.PAGE_START);
            
            //a1.add(p2);
            dout.writeUTF(out);
            t1.setText("");
        }catch(Exception e){
             System.out.println(e);
        }
        
    }
    
    public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
    }
    
    public static void main(String[] args) throws IOException {
        new Server().f1.setVisible(true);
        String msginput = "";
        try{
            skt = new ServerSocket(6001);
            while(true){
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout= new DataOutputStream(s.getOutputStream());
            
            while(true){
            msginput = din.readUTF();

             JPanel p2 = formatLabel(msginput);
                        
              JPanel left = new JPanel(new BorderLayout());
                left.add(p2, BorderLayout.LINE_START);
                vertical.add(left);
               f1.validate();
            }

            }
        }catch(Exception e){}
      
    }

    void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    }

