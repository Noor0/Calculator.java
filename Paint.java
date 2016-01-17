package Gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;


public class Paint extends JFrame implements Serializable{
    private transient String mess;
    private transient int x,y,x1,y1,width,height,condition;
    private transient final Color[] colors = {Color.black,Color.white,Color.yellow,Color.green,Color.red,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.LIGHT_GRAY,Color.MAGENTA,Color.orange};
    private transient final String[] colorNames = {"Black","White","Yellow","Green","Red","Blue","Cyan","Dark Gray","Gray","Light Gray","Magenta","Orange"};
    private transient final String[] shapes = {"Line","Rectangle/Square","Oval/Circle"};
    Point[] circle=new Point[10000];
    Point[] rect=new Point[10000];
    Point[] line=new Point[10000];
    int[] lineColor=new int[5000];
    int[] rectColor=new int[5000];
    int[] circleColor=new int[5000];
    boolean[] boolCircle=new boolean[5000];
    boolean[] boolRect=new boolean[5000];
    private boolean clearAct=false;
    //loop variables
    private transient int bc=0,c,r,l,k,ci,loop,br;
    private transient int ll=0,cl=0,rl=0,i,circleBoolCaller=0,rectBoolCaller=0,lci,rci,cci;
    private transient int lineColorCaller,circleColorCaller,rectColorCaller;
    private transient int lastAction=-1;
    
    
    private transient JLabel lab;
    private transient JPanel labelPanel;
    transient JPanel menu;
    private transient final JButton clear;
    //private transient final JButton load;
    //private transient final JButton save;
    private final transient JButton undo;
    private transient JComboBox colorBox;
    private transient JComboBox shapeBox;
    private transient JCheckBox chk;
    transient PArea canvas= new PArea();
    
    Point[] getCircle(){
        System.out.println("getCircle");
        return circle;
    }
    
    int[] getCircleColor(){
        return circleColor;
    }
    
    boolean[] getCircleBool(){
        return boolCircle;
    }
    
    Point[] getRect(){
        return rect;
    }
    
    int[] getRectColor(){
        return rectColor;
    }
    
    boolean[] getRectBool(){
        return boolRect;
    }
    
    Point[] getline(){
        return line;
    }
    
    int[] getLineColor(){
        return lineColor;
    }
    
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
                clear();
            }
        });
        //menu.add(load);
        //menu.add(save);
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
        //setVisible(true);
    }
    
    void clear(){
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
    static Paint d=new Paint();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(sss.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        JButton load = new JButton("Load");
        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fc = new JFileChooser();
                int val=fc.showOpenDialog(null);
                if(val==JFileChooser.APPROVE_OPTION){
                    File opener = fc.getSelectedFile();
                    try {
                        //if(fc.getTypeDescription(opener).equals(".ser"))
                        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(opener));
                        Paint object =(Paint)oos.readObject();
                        d=object;
                        /*d.clear();
                        d.circle = object.getCircle();
                        d.circleColor = object.getCircleColor();
                        d.boolCircle = object.getCircleBool();
                        d.rect = object.getRect();
                        d.rectColor = object.getRectColor();
                        d.boolRect = object.getRectBool();
                        d.line = object.getline();
                        d.lineColor = object.getLineColor();*/
                        d.canvas.repaint();
                        d.repaint();
                        oos.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String name = JOptionPane.showInputDialog("Enter File Name")+".ser";
                System.out.println(name);
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //int val =  fc.showSaveDialog(null);
                if(!name.equals("null.ser")){
                    try{
                        System.out.println("approved");
                        File file = new File(fc.getCurrentDirectory().getPath()+"\\"+name);
                        file.createNewFile();
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(d);
                        oos.close();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        d.menu.add(load);
        d.menu.add(save);
        d.setVisible(true);
    }
}