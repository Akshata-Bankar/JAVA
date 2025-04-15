//Marvellousmain.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Clock;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;

class MarvellousLogin extends Template implements ActionListener,Runnable
{
    JButton SUBMIT;
    JLabel lable1,lable2,lable3,TopLable;
    final JTextField text1,text2;
    private static int attempt=3;

    MarvellousLogin()
    {
        TopLable=new JLable();
        TopLevel.setHorizontalAlignment(SwingConstants.CENTER);
        TopLable.setText("Marvellous Packer unpacker:Login");
        TopLevel.setForeground(Color.BLUE);

        Dimension topSize=TopLable.getPreferredSize();
        TopLable.setBounds(50,40,topsize.width,topsize.height);
        _header.add(TopLable);

        lable1=new JLabel();
        lable1.setText("Username:");
        lable1.setForeground(Color.white);

        Dimension size=lable1.getPreferredSize();

        lable1.setBounds(50,135,size.width,size,size.height);
        lable1.setHorizontalAlignment(SwingConstants.CENTER);

        text1=new JTextField(15);
        Dimension tsize=text1.getPreferredSize();
        text1.setBounds(200,135,tsize.wdith,tsize.height);

        text1.setToolTipText("ENTER USERNAME");

        lable2=newJLable();
        lable1.setText("Password:");
        lable1.setBounds(50,175,size.wdith,size.height);
        lable1.setForeground(Color.white);
        lable2.setHorizontalAlignment(SwingConstants.CENTER);

        text2=new JPasswordField(15);
        text2.setBounds(200,175,tsize.width,tsize.height);

        text2.setToolTipText("ENTER PASSWORD");

        text2.addFocusListener(new FocusListener()
        {
            public void focusGained(FocusEvent e)
            {

            }
            public void focusLost(FocusEvent  e)
            {
                lable3.setText("");
            }
        });

        SUBMIT=new JButton("SUBMIT");
        SUBMIT.setHorizontalAlignment(SwingConstants.CENTER);

        Dimension ssize=SUBMIT.getPreferredSize();

        SUBMIT.setBounds(50,220,ssize.width,ssize.height);

        lable3=new JLable();
        lable3.setText("");

        Dimension l3size=lable3.getPreferredSize();

        lable3.setForeground(Color.RED);
        lable3.setForeground(50,250,l3size.width,l3size.height);

        Thread t=new Thread(this);
        t.start();

        _content.add(lable1);
        _content.add(text1);

        _content.add(lable2);
        _content.add(text2);

        _content.add(lable3);
        _content.add(SUBMIT);

        pack();
        validate();

        ClockHome();
        setVisible(true);
        this.setSize(500,500);
        this.setResizeable(false);
        setLocationRelativeTo(null);
        SUBMIT.addActionListener(this);
    }
    public boolean validate(String Username,String password)
    {
        if((Username.length()<8) ||(password.length()<8))
        return false;
        else
        return true;
    }
    public  void actionPerformed(ActionEvent ae)
    {
        String value1=text1.getText();
        String value2=text2.getText();

        if (ae.getSource()==exit)
        {
            this.setVisible(false);
            System.exit(0);
        }

        if(ae.getSource()==minimize)
        {
            this.setState(this.ICONIFIED);
        }

        if(ae.getSource()==SUBMIT)
        {
           if(Validate(value1,value2)==false)
           {
            text1.setText("");
            text2.setText("");
            JOptionPane.showMessageDialog(this, "Short username",JOptionPane.ERROR_MESSAGE);
           }
           if(value1.equals("MarvellousSAdmin") && value2.equals("MarvellousAdmin"))
           {
            NextPage page=new NextPage(value1);
            this.setVisible(false);
            page.pack();
            page.setVisible(true);
            page.setSize(500,500);
           }
           else
           {
            attempt--;

            if(attempt==0)
            {
                JOptionPane.showMessageDialog(this, "Number of attempts finished", "Marvellous Packer Unpacker",JOptionPane.ERROR_MESSAGE);
                this.dispose();
                System.exit(0);
            }

            JOptionPane.showMessageDialog(this,"Incorrect login or password",
             "ERROR", JOptionPane.ERROR_MESSAGE);
           }
        }     
    }

    public void run()
    {
        for(;;)
        {
            if(text2.isFocusOwner())
            {
            if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK))
            {
                text2.setToolTipText("Warning:CAPS Lock is on");
            }
            else
                text2.setToolTipText("");
            
                if((text2.getText()).length()<8)
                    lable3.setText("Weak Password");
                else
                    lable3.setText(""); 
        }  
        }
    }
}

class MarvellousMain
{
    public static  void main(String arg[])
    {
        try
        {
            MarvellousLogin frame=new MarvellousLogin();
            freame.setVisible(true);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e, getMessage());}
        }
    }

//Template.java

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import jabva.util.Date;
class ClockLable extends JLable implements ActionListener
{
    String type;
    SimpleDateFormat sdf;

    publiuc ClockLable(String type)
    {
        this.type=type;
        setForeground(Color.green);

        switch(type)
        {
            case "date":sdf=new SimpleDateFormat("MMMM dd yyyy");
            setFont(new Font("sans-serif",Font.PLAIN,12));
            setHorizontalAlignment(SwingConstants.LEFT);
            break;
            case "time":sdf=new SimpleDateFormat("hh:mm:ss a");
            setFont(new Font("sans-serif",Font.PLAIN,40));
            setHorizontalAlignment(SwingConstants.CENTER);
            break;
            case "day":sdf=new SimpleDateFormat("EEEE");
            setFont(new Font("sans-serif",Font.PLAIN,16));
            setHorizontalAlignment(SwingConstants.RIGHT);
            break;
            default :sdf=new SimpleDateFormat();
            break;
        }

        Timer t=new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae)
    {
        Date d=new Date();
        setText(sdf.format(d));
    }
}

class Template extends JFrame implements Serializable,ActionListener
{
    JPanel_Header;
    JPanel_content;
    JPanel_top;

    ClockLable dayLable;
    ClockLable timeLable;
    ClockLabel

}

class Marvellous_File_Packer_Unpacker {
    
}
