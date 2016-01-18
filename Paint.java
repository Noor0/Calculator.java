package Gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Noor
 */
public class Paint extends JFrame{
    private String mess;
    private int x,y,x1,y1,width,height,condition;
    private final Color[] colors = {Color.black,Color.white,Color.yellow,Color.green,Color.red,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.LIGHT_GRAY,Color.MAGENTA,Color.orange};
    private final String[] colorNames = {"Black","White","Yellow","Green","Red","Blue","Cyan","Dark Gray","Gray","Light Gray","Magenta","Orange"};
    private final String[] shapes = {"Line","Rectangle/Square","Oval/Circle"};
    private Point[] circle=new Point[10000];
    private Point[] rect=new Point[10000];
    private Point[] line=new Point[10000];
    private int[] lineColor=new int[5000];
    private int[] rectColor=new int[5000];
    private int[] circleColor=new int[5000];
    private boolean[] boolCircle=new boolean[5000];
    private boolean[] boolRect=new boolean[5000];
    private boolean clearAct=false;
    //loop variables
    private int bc=0,c,r,l,k,ci,loop,br;
    private int ll=0,cl=0,rl=0,i,circleBoolCaller=0,rectBoolCaller=0,lci,rci,cci;
    private int lineColorCaller,circleColorCaller,rectColorCaller;
    private int lastAction=-1;
    
    
    private JLabel lab;
    private JPanel labelPanel;
    private JPanel menu;
    private final JButton clear;
    private final JButton undo;
    private JComboBox colorBox;
    private JComboBox shapeBox;
    private JCheckBox chk;
    PArea canvas= new PArea();

    
    Paint(){
        labelPanel=new JPanel();
        chk = new JCheckBox("Fill");
        shapeBox = new JComboBox(shapes);
        colorBox = new JComboBox(colorNames);
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        menu = new JPanel();
        lab = new JLabel("[X,Y]");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                clearAct=true;
                circle=null;
                circle=new Point[10000];
                rect=null;
                rect=new Point[10000];
                line=null;
                line=new Point[10000];
                lineColor=null;
                lineColor=new int[5000];
                rectColor=null;
                rectColor=new int[5000];
                circleColor=null;
                circleColor=new int[5000];
                boolCircle=null;
                boolCircle=new boolean[5000];
                boolRect=null;
                boolRect=new boolean[5000];
                bc=0;
                c=0;
                r=0;
                l=0;
                k=0;
                ci=0;
                loop=0;
                br=0;
                ll=0;
                cl=0;
                rl=0;
                i=0;
                circleBoolCaller=0;
                rectBoolCaller=0;
                lci=0;
                rci=0;
                cci=0;
                lineColorCaller=0;
                circleColorCaller=0;
                rectColorCaller=0;
                lastAction=-1;
                canvas=null;
                canvas=new PArea();
                repaint();
                clearAct=false;
            }
        });
        menu.add(clear);
        
        undo.addActionListener((ActionEvent evnt)->{
            if(lastAction==0){
                l--;
                line[l]=null;
                l--;
                line[l]=null;
                lci--;
                
            }
            if(lastAction==1){
                r--;
                rect[r]=null;
                r--;
                rect[r]=null;
                rci--;
                br--;
            }
            if(lastAction==2){
                c--;
                circle[c]=null;
                c--;
                circle[c]=null;
                cci--;
                bc--;
            }
            lastAction=-1;
            canvas.repaint();
        });
        menu.add(undo);
        colorBox.setName("colorBox");
        menu.add(colorBox);
        
        shapeBox.setName("shapeBox");
        menu.add(shapeBox);
        menu.add(chk);
        labelPanel.add(lab);
        
        add(menu,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        add(labelPanel,BorderLayout.SOUTH);
        setSize(1280,920);
        setLocationRelativeTo(null);
        setVisible(true);
    }
   
    class PArea extends JPanel{
        PArea(){
            setBackground(Color.white);
            addMouseMotionListener(new MouseAdapter(){ 
                @Override
                public void mouseMoved(MouseEvent e){
                    mess=String.format("Co-ordinates=[%d,%d]",e.getX(),e.getY());
                    lab.setText(mess);
                    
                }
                
            });
            addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent event){
                    if(shapeBox.getSelectedIndex()==0)
                    {
                        line[l]=new Point(event.getX(),event.getY());
                        l++;
                    }
                    if(shapeBox.getSelectedIndex()==1)
                    {
                        rect[r]=new Point(event.getX(),event.getY());
                        r++;
                    }
                    if(shapeBox.getSelectedIndex()==2)
                    {
                        circle[c]=new Point(event.getX(),event.getY());
                        c++;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent event){
                    if(shapeBox.getSelectedIndex()==0)
                    {
                        line[l]=new Point(event.getX(),event.getY());
                        l++;
                        lineColor[lci]=colorBox.getSelectedIndex();
                        lci++;
                        lastAction=0;
                    }
                    if(shapeBox.getSelectedIndex()==1)
                    {
                        rect[r]=new Point(event.getX(),event.getY());
                        r++;
                        rectColor[rci]=colorBox.getSelectedIndex();
                        rci++;
                        boolRect[br]=chk.isSelected();
                        br++;
                        lastAction=1;
                    }
                    if(shapeBox.getSelectedIndex()==2)
                    {
                        circle[c]=new Point(event.getX(),event.getY());
                        c++;
                        circleColor[cci]=colorBox.getSelectedIndex();
                        cci++;
                        boolCircle[bc]=chk.isSelected();
                        bc++;
                        lastAction=2;
                    }

                    loop++;
                    k++;
                    repaint(); 
                }
            });
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(clearAct==false){
                for(i=0;i<loop;i++){
                    circleBoolCaller=0;
                    rectBoolCaller=0;
                    lineColorCaller=0;
                    rectColorCaller=0;
                    circleColorCaller=0;
                    for(ll=0;ll<l-1;ll+=2){
                        x=line[ll].x;
                        y=line[ll].y;
                        x1=line[ll+1].x;
                        y1=line[ll+1].y;
                        g.setColor(colors[lineColor[lineColorCaller]]);
                        g.drawLine(x,y,x1,y1);
                        lineColorCaller++;
                    }

                    for(rl=0;rl<r-1;rl+=2)
                    {                    
                        x=rect[rl].x;
                        y=rect[rl].y;
                        x1=rect[rl+1].x;
                        y1=rect[rl+1].y;

                        if(x1>=x){
                            width=x1-x;;
                            
                        }
                        else{
                            width=x-x1;
                            x=x1;
                        }
                        if(y1>=y){
                            height=y1-y;
                            
                        }
                        else{
                            height=y-y1;
                            y=y1;
                        }
                        g.setColor(colors[rectColor[rectColorCaller]]);
                        if(!boolRect[rectBoolCaller])
                            g.drawRect(x,y,width,height);
                        else
                            g.fillRect(x,y,width,height);
                        rectColorCaller++;
                        rectBoolCaller++;
                    }
                    for(cl=0;cl<c-1;cl+=2)
                    {
                        x=circle[cl].x;
                        y=circle[cl].y;
                        x1=circle[cl+1].x;
                        y1=circle[cl+1].y;

                        if(x1>=x){
                            width=x1-x;
                        }
                        else{
                            width=x-x1;
                            x=x1;
                        }
                        if(y1>=y){
                            height=y1-y;
                        }
                        else{
                            height=y-y1;
                            y=y1;
                        }
                        g.setColor(colors[circleColor[circleColorCaller]]);
                        if(!boolCircle[circleBoolCaller])    
                            g.drawOval(x,y,width,height);
                        else
                            g.fillOval(x,y,width,height);
                        circleColorCaller++;
                        circleBoolCaller++;
                    }
                }
            }
        }
    }
}
class sss{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(sss.class.getName()).log(Level.SEVERE, null, ex);
        }
        Paint d=new Paint();        
    }
}
