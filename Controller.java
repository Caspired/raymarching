import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
/**
 * Write a description of class Controls here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Controller extends JFrame
{
    private Environment environment;
    /**
     * Constructor for objects of class Controls
     */
    public Controller(java.awt.Point origin, Environment e)
    {
        super("Ray Marching Attempt 1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 150);
        this.setLocation(origin);
        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        
        environment = e;
        this.add(new movementPanel(), BorderLayout.LINE_START);
        this.add(new rotationPanel(), BorderLayout.LINE_END);
        this.add(new verticalPanel(), BorderLayout.PAGE_END);
        this.pack();
        
        this.setVisible(true);
    }
    class movementPanel extends JPanel{
        JButton fwdBtn;
        JButton bkwdBtn;
        JButton rightBtn;
        JButton leftBtn;
        public movementPanel(){
            this.setLayout(new BorderLayout());
            
            fwdBtn = new JButton("⇧");
            bkwdBtn = new JButton("⇩");
            rightBtn = new JButton("⇨");
            leftBtn = new JButton("⇦");
            this.add(fwdBtn, BorderLayout.PAGE_START);
            this.add(bkwdBtn, BorderLayout.PAGE_END);
            this.add(leftBtn, BorderLayout.LINE_START);
            this.add(rightBtn, BorderLayout.LINE_END);
            fwdBtn.addActionListener(environment);
            fwdBtn.setActionCommand("move forward");
            bkwdBtn.addActionListener(environment);
            bkwdBtn.setActionCommand("move backward");
            leftBtn.addActionListener(environment);
            leftBtn.setActionCommand("move left");
            rightBtn.addActionListener(environment);
            rightBtn.setActionCommand("move right");
        }
    }
    class rotationPanel extends JPanel{
        JButton rightBtn;
        JButton leftBtn;
        public rotationPanel(){
            setLayout(new BorderLayout());
            
            rightBtn = new JButton("↻");
            leftBtn = new JButton("↺");
            this.add(leftBtn, BorderLayout.LINE_START);
            this.add(rightBtn, BorderLayout.LINE_END);
            leftBtn.addActionListener(environment);
            leftBtn.setActionCommand("rotate left");
            rightBtn.addActionListener(environment);
            rightBtn.setActionCommand("rotate right");
        }
    }
    class verticalPanel extends JPanel{
        JButton upBtn;
        JButton downBtn;
        JButton rUpBtn;
        JButton rDownBtn;
        public verticalPanel(){
            setLayout(new BorderLayout());
            
            JPanel lPanel = new JPanel(new BorderLayout());
            JPanel rPanel = new JPanel(new BorderLayout());
            
            upBtn = new JButton("⇧");
            downBtn = new JButton("⇩");
            
            lPanel.add(upBtn, BorderLayout.LINE_START);
            lPanel.add(downBtn, BorderLayout.LINE_END);
            
            upBtn.addActionListener(environment);
            upBtn.setActionCommand("move up");
            downBtn.addActionListener(environment);
            downBtn.setActionCommand("move down");
            
            rUpBtn = new JButton("⌃");
            rDownBtn = new JButton("⌄");
            
            rPanel.add(rUpBtn, BorderLayout.LINE_START);
            rPanel.add(rDownBtn, BorderLayout.LINE_END);
            
            // rUpBtn.addActionListener(environment);
            // rUpBtn.setActionCommand("rotate up");
            // rDownBtn.addActionListener(environment);
            // rDownBtn.setActionCommand("rotate down");
            
            this.add(lPanel, BorderLayout.LINE_START);
            this.add(rPanel, BorderLayout.LINE_END);
        }
    }
}
