package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Noor
 */

public class Calculator extends JFrame{
    private JTextField bar;
    private JButton[] keypad = new JButton[16];
    private String[] symbols={"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
    private GridLayout layout=new GridLayout(4,4,10,20);
    private Font font;
    private JPanel p1;
    private JButton clearButton=new JButton("CL");
    
    private double var1,var2;
    private int op,counter;
    private boolean on=false,f=false;
    private String eq;
    private String temp="";
    private String num="";
    
    
    private Calculator(){
        super("Calculator");
        font = new Font("Segoi UI",Font.PLAIN,25);
        getContentPane().setBackground(Color.WHITE);
        
        p1 = new JPanel();
        p1.setSize(200,180);
        p1.setLocation(20,70);
        p1.setBackground(Color.WHITE);
        p1.setLayout(layout);
        
        setLayout(new FlowLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        bar = new JTextField("0",10);
        bar.setFont(font);
        add(bar);
        
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                counter=0;
                op=0;
                on=false;
                eq="";
                num="";
                temp="";
                f=false;
                bar.setText("");
            }    
        });
        
        add(clearButton);
        
        
        numbers handler = new numbers();
        for(int i=0;i<symbols.length;i++){
            keypad[i] = new JButton(symbols[i]);
            keypad[i].addActionListener(handler);
            p1.add(keypad[i]);
        }
        
        bar.addActionListener(handler);
        
        add(p1);
        setSize(250,300);
        setLocationRelativeTo(null);  // *** this will center the app ***
        setVisible(true);
    }
    
    private class numbers implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event)
        {   
            if(event.getActionCommand().equals("."))
                f=true;
            
            if(on == false)
                counter++;
            
            if(event.getActionCommand().equals("="))
            {
                if(on == true)
                {
                    eq=bar.getText();
                    temp="";
                    for(int j=counter;j<eq.length();j++){
                        temp=temp+Character.toString(eq.charAt(j));  
                    }
                }
                var2=Double.parseDouble(temp);
                
                operation(var1,var2,op,f);
                
            }

            if(((event.getActionCommand().equals("/") || event.getActionCommand().equals("+") || event.getActionCommand().equals("-") || event.getActionCommand().equals("*")) && (!event.getActionCommand().equals("="))) && on == false)
            {
                on=true;
                num=bar.getText();
                var1=Double.parseDouble(num);
                num=num+event.getActionCommand();
                bar.setText(num);
                if(event.getActionCommand().equals("/"))
                    op=1;
                if(event.getActionCommand().equals("+"))
                    op=2;
                if(event.getActionCommand().equals("-"))
                    op=3;
                if(event.getActionCommand().equals("*"))
                    op=4;
            }
            
            if(!event.getActionCommand().equals("/") && !event.getActionCommand().equals("+") && !event.getActionCommand().equals("-") && !event.getActionCommand().equals("*") && !event.getActionCommand().equals("="))
            {
                //num=bar.getText();
                num=num+event.getActionCommand();
                bar.setText(num); 
            }
 
        }
    }
    
    static void run(){
        new Calculator();
        
    }

    private void operation(double val1, double val2,int op,boolean format)
    {
        double ans;
        String fin="";
        switch(op){
            case 1:
                ans=val1/val2;
                if(format==true){
                    bar.setText(Double.toString(ans));
                }
                else{
                    fin=String.format("%.0f",ans);
                    bar.setText(fin);
                }
                
                counter=0;
                this.op=0;
                on=false;
                eq="";
                num="";
                temp="";
                f=false;
                break;
                
            case 2:
                ans=val1+val2;
                if(format==true){
                    bar.setText(Double.toString(ans));
                }
                else{
                    fin=String.format("%.0f",ans);
                    bar.setText(fin);
                }
                counter=0;
                this.op=0;
                on=false;
                eq="";
                num="";
                temp="";
                f=false;
                break;
                
            case 3:
                ans=val1-val2;
                if(format==true){
                    bar.setText(Double.toString(ans));
                }
                else{
                    fin=String.format("%.0f",ans);
                    bar.setText(fin);
                }
                counter=0;
                this.op=0;
                on=false;
                eq="";
                num="";
                temp="";
                f=false;
                break;
            
            case 4:
                ans=val1*val2;
                if(format==true){
                    bar.setText(Double.toString(ans));
                }
                else{
                    fin=String.format("%.0f",ans);
                    bar.setText(fin);
                }
                    
                counter=0;
                this.op=0;
                on=false;
                eq="";
                num="";
                temp="";
                f=false;
                break;
        }
    }       
}

class runner{
    public static void main(String[] args){        
        /*try {    
            Calculator.run();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            
        }*/
        try {
            Calculator.run();
            for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
                if("Windows".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } 
            }                
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("LAF not found");
        } 
    }
}
//getCrossPlatformLookAndFeelClassName()