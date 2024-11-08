package gamedevelopment;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class Frame extends JFrame
{
    public  Frame(String s1, int x, int y, Screen Jp)
    {
    
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        getContentPane().add(BorderLayout.CENTER, Jp);
        setTitle(s1 + " Width : " + Jp.background.getWidth() + " Height : " + Jp.background.getHeight());
        setSize(x, y);  
        setUndecorated(true);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
