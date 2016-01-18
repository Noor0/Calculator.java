package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Noor
 */
 
class App extends JFrame{
    private JButton[] keypad;
    private JTextArea screen;
    private JLabel text,messageLabel;
    private JPanel p1,p2,p3;
    private String s,str;
    private String[] lines={"Sometimes we have to accept change if we want to move forward.","We did acquire a blurry video image and a pretty good visual descripting of the perp.","Either let the land lie fallow every other year or else let spelt follow pulse, vetches or lupine."};
    private Font font;
    private int counter;

    private App(){
        super("Typing Tutor");
        font=new Font("Consolas",Font.PLAIN,15);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1=new JPanel();
        p1.setLayout(new BorderLayout());
        p2=new JPanel();
        p2.setLayout(new BorderLayout());
        p3=new JPanel();
        GridLayout layout=new GridLayout(2,2);
        layout.setColumns(7);
        layout.setRows(3);
        p3.setLayout(layout);
        keypad = new JButton[26];

        text = new JLabel(lines[counter]);
        text.setFont(font);
        messageLabel = new JLabel();
        messageLabel.setFont(font);

        p1.add(text,BorderLayout.NORTH);
        p1.add(messageLabel,BorderLayout.SOUTH);
        add(p1,BorderLayout.NORTH);

        screen=new JTextArea(10,10);
        screen.setMargin( new Insets(5,5,5,5) );
        screen.setLineWrap(true);
        screen.addKeyListener(new KeyHandle());
        p2.add(screen,BorderLayout.CENTER);
        add(p2,BorderLayout.CENTER);
        handle h=new handle();

        for(int i=0;i<26;i++){
            s=String.format("%c",(i+65));
            keypad[i]=new JButton(s);
            keypad[i].addActionListener(h);
            p3.add(keypad[i]);
        }

        add(p3,BorderLayout.SOUTH);
        //setLocationRelativeTo(null);
        setVisible(true);
        setSize(700,300);
    }

    public static void run(){
        new App();
    }

    private final class KeyHandle extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            if(screen.getText().equals(lines[counter])){
                counter++;
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Correct!");
                screen.setText("");
                if(counter<lines.length)
                text.setText(lines[counter]);
            }
            else{
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Incorrect");
            }
            
            if(counter==lines.length){
                screen.setFont(new Font("Consolas",Font.PLAIN,19));
                screen.setText("CONGRATULATIONS YOU ARE A TYPING EXPERT NOW! get lost");
                screen.setEnabled(false);
            }
        }
    }

    private final class handle implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            screen.setText((screen.getText()+e.getActionCommand().toLowerCase()));
        }  
    }
}

class main{
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            App.run();
        }
        catch(Exception e){
            
        }
    }
}
