package gamedevelopment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

abstract public class Hero extends KeyAdapter
{
    abstract public void key5();  
    abstract public void key6();
    abstract public void key7();
    abstract public void key8();

    @Override
    public synchronized void keyPressed(KeyEvent e) 
    {
    }
    
    @Override
    public synchronized void keyReleased(KeyEvent e) 
    {
    }      
}