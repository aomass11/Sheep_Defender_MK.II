package gamedevelopment;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

abstract public class Item extends Block
{
    public String sound; 
    public int timeshow = 0;
    public int timeend = 0;
    public int Steptime = 0;
    public TimeGame timegame;

    public Item(Picture ObjPic,Hero hero,String sound)
    {
        super(ObjPic,hero);
        this.sound = sound;
    }
    
    public void Clear()
    {
        ObjPic.Show = false;
        ObjPic.X = -300;
        ObjPic.Y = -300;         
    }
    
    public void playsound()
    {
        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(sound)));
            clip.start();
        }
        catch (Exception ex) { }         
    }
    
    abstract public void affect();
      
    public void action(){}  
}