package gamedevelopment;

import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TimeGame 
{
    private ActionListener  ac;
    private Timer           t;
    public int              T; 
    
    public TimeGame()
    {
        T = 0;  
        ActionListener ac = new ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
		T = T + 1;
            }
        };   
        t = new Timer(1000,ac);
        t.start(); 
    }            
}