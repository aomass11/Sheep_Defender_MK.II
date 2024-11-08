package gamedevelopment;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.event.MouseInputAdapter;

abstract public class MouseAction implements MouseListener
{
    public Vector<Item>     item    = new Vector<Item>();
    public  int             bullet;
    public  int             Maxbullet;
    public  int             hp;       
    public  int             damage;   
    public  Picture         pic_dead;
    public  Picture         pic_cursor;
    public  String          soundL;
    public  String          soundR;
    public  Frame           frame;
    
    public class MouseMove extends MouseInputAdapter
    {
        @Override
        public void mouseMoved(MouseEvent e)
        {
            pic_cursor.Y      = e.getY()-(pic_cursor.SizeY/2);
            pic_cursor.X      = e.getX()-(pic_cursor.SizeX/2);   
        }
    }
    
    public MouseAction(int bullet,int hp,int damage,String soundL,String soundR,Picture pic_dead,Picture pic_cursor,Frame frame)
    {
        this.bullet     = bullet;
        Maxbullet       = bullet;
        this.hp         = hp;
        this.damage     = damage;
        this.soundL     = soundL;
        this.soundR     = soundR;
        this.pic_dead   = pic_dead;
        this.pic_cursor = pic_cursor;
        pic_cursor.Show = true;
        
        MouseMove m = new MouseMove();
        this.frame = frame;
        this.frame.addMouseListener(this);
        this.frame.addMouseMotionListener(m);      
    }
    
    public void Action()
    {
        AnimateShoot();
    }
    
    public void AnimateShoot()
    {
        if(pic_dead.state == 2)
        {
            pic_dead.Show  = false;
            pic_dead.state = 0;
        }         
    }    
    
    public void Additem(Vector item)
    {
        this.item = item;
    }
    
    public void playsound_L()
    {
        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(soundL)));
            clip.start();
        }
        catch (Exception ex) {}         
    }
    
    public void playsound_R()
    {
        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(soundR)));
            clip.start();
        }
        catch (Exception ex) {}          
    }
    
    public void ShowEffectShoot(MouseEvent e)
    {
        pic_dead.Y      = e.getY()-(pic_dead.SizeY/2);
        pic_dead.X      = e.getX()-(pic_dead.SizeX/2);
        pic_dead.Show   = true;              
    }
    
    abstract public void action_L(MouseEvent e,int j);
    abstract public void action_R(MouseEvent e);
    
    @Override
    public void mouseClicked(MouseEvent e){ }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            playsound_L();
            for(int j=0 ; j < item.size() ; j++)
            {
                int XL = item.get(j).ObjPic.X-(item.get(j).ObjPic.SizeX-10);
                int XM = item.get(j).ObjPic.X+(item.get(j).ObjPic.SizeX-10);
                int YL = item.get(j).ObjPic.Y-(item.get(j).ObjPic.SizeY-10);
                int YM = item.get(j).ObjPic.Y+(item.get(j).ObjPic.SizeY-10);                            

                if(e.getY() < YM && e.getY() > YL && e.getX() < XM && e.getX() > XL)
                {
                    item.get(j).affect();
                }
            }
            ShowEffectShoot(e);
        } 
    }

    @Override
    public void mouseReleased(MouseEvent e){ }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}