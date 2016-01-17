package spellcheck;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author Noor
 */
@SuppressWarnings("rawtypes")

public class SpellCheckGUI {
    private JLabel name;
    private JTextField field;
    private JButton button;
    private Font font;
    private JTextArea answer;
    private JFrame frame1,frame2;
    private JPanel bPanel,tPanel;
    private AutoCorrect correct = new AutoCorrect();
    
    
    private SpellCheckGUI(){
        JMenuBar menu=new JMenuBar();
        
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.setFont(new Font(file.getFont().getFontName(),Font.PLAIN,14));
        
        JMenu help= new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem abtHelp=new JMenuItem("About          Ctrl+A");
        abtHelp.setMnemonic(KeyEvent.VK_A);
        abtHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame2.setVisible(true);
                //frame1.setVisible(false);
            }
        });
        help.add(abtHelp);
        
        JMenuItem exitItem = new JMenuItem("Exit           Ctrl+ESC");
        exitItem.setMnemonic(KeyEvent.VK_ESCAPE);
        exitItem.addActionListener(new ActionListener(){
            @Override
           public void actionPerformed(ActionEvent e){
               System.exit(1);
           }
        });
        file.add(exitItem);
        menu.add(file);
        menu.add(help);
        
        frame2 = new JFrame("About");
        frame2.getContentPane().setBackground(Color.white);
        frame2.setSize(500,500);
        frame2.setVisible(false);
        frame2.setLayout(new FlowLayout());
        ImageIcon image=new ImageIcon(getClass().getResource("cap.PNG"));
        
        JButton ok=new JButton("OK");
        ok.setFont(new Font("Tahoma",Font.PLAIN,18));
        ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame2.setVisible(false);
                //frame1.setVisible(true);
            }
        });
        JLabel label=new JLabel(image);
        label.setText("<html><hr/>This app has been developed by Noor ul Haq<br/><br/>That's it</html>");
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma",Font.PLAIN,18));
        label.setPreferredSize(new Dimension(500,400));
        frame2.setLocationRelativeTo(frame1);
        //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.add(label);
        frame2.add(ok);
        
        frame1 = new JFrame("Spailing chayk aypp");
        frame1.setSize(300,500);
        frame1.setBackground(Color.white);
        frame1.setJMenuBar(menu);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tPanel = new JPanel();
        tPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tPanel.setPreferredSize(new Dimension(50,70));
        tPanel.setBackground(Color.white);
        
        name=new JLabel("Spell Check App".toUpperCase());
        name.setFont(new Font("Tahoma",Font.PLAIN,25));
        
        field = new JTextField("");
        field.setPreferredSize(new Dimension(275,28));
        field.setFont(new Font("Tahoma",Font.PLAIN,18));
        field.addActionListener(new Handle());
        
        tPanel.add(name,BorderLayout.NORTH);
        tPanel.add(field,BorderLayout.SOUTH);
        frame1.getContentPane().add(tPanel,BorderLayout.NORTH);
        
        answer = new JTextArea(10,10);
        answer.setLineWrap(true);
        answer.setAutoscrolls(true);
        answer.setBackground(Color.white);
        answer.setPreferredSize(new Dimension(200,200));
        answer.setEditable(false);
        answer.setFont(new Font("Tahoma",Font.PLAIN,15));
        
        frame1.add(answer,BorderLayout.CENTER);
        
        button = new JButton("Check");
        button.setFont(new Font("Tahoma",Font.PLAIN,18));
        button.addActionListener(new Handle());
        button.setPreferredSize(new Dimension(80,40));
        bPanel = new JPanel();
        bPanel.setLayout(new FlowLayout());
        bPanel.add(button);
        bPanel.setBackground(Color.white);
        
        frame1.add(bPanel,BorderLayout.SOUTH);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
    }
    
    private class Handle implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String s="";
                ArrayList<String> list=new ArrayList<String>();
                list.addAll(correct.checkSpelling(field.getText()));
                if(list.contains("spelling is correct"))
                    s="Spelling is correct";
                else{
                    if(list.contains("it must be some sort of elvish that you're writting!! ._.")){
                        s="it must be some sort of elvish that you're writting!! ._.";
                    }
                    else{
                        s="did you mean : \n";
                        for(String p : list)
                            s+=p+" || ";
                    }
                }
                answer.setText(s);
                s=null;
                list.clear();
            }catch(Exception exc){
                
            }
        }
    }
    
    
    public static void run(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new SpellCheckGUI();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            
        }
    }
}


