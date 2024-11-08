package gamedevelopment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class Screen extends JPanel 
{
    public Color            color = Color.BLACK;
    public BufferedImage    background;
    public int              SideScreen;
    public int              StartX;
    public int              StartY;
    public int              EndX;
    public int              EndY;
    public int              SlideX;
    public int              SlideY;
    public int              MaxSlideX;
    public int              MaxSlideY;
    public int              plus_X = 0;
    public int              plus_Y = 0;
    public int              MoveScreen;
    public boolean          op;
    
    public int              option; // 1=background color, 2=picture, 3=picture+slide
    public int              delay_screen;
    public int              Tdelay_screen = 0;
    public BufferedImage    image[];
    public boolean          menu = false;     

    public Vector<Text>     text         = new Vector<Text>();
    public boolean          Font_option  = false;    
    public Vector<Picture>  pic          = new Vector<Picture>();
    public boolean          Image_option = false;
    public String           sound;
//------------------------------------------------------------------------------ HP   
    public boolean          HP_option    = false;
    public int              HP_now       = 0;
//------------------------------------------------------------------------------        
    public Screen(String background)
    {
        try
        {         
            this.background = ImageIO.read(new File(background) );
            option = 2; 
        } 
        catch(Exception e){ }        
    }    
//------------------------------------------------------------------------------    
    public int GetM()
    {
        if(plus_X != 0)
        {
            return SlideX+SlideX;
        }
        if(plus_Y != 0)
        {
            return StartY+SlideY;
        }
        return 0;
    }
//------------------------------------------------------------------------------       
    public void SetSound(String sound)
    {
        this.sound = sound;
        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(this.sound)));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception ex) {}  
    }   
//------------------------------------------------------------------------------      
    public void SetImage(Vector<Picture> pic)
    {
        this.pic = pic;
        Image_option = true;
    }
    
    public void SetImage(Vector<Picture> pic,Vector<Text> text)
    {
        this.pic = pic;
        Image_option = true;        
        this.text = text;
        Font_option = true;        
    }
//------------------------------------------------------------------------------    
    @Override
    public void paintComponent(Graphics g) 
    {
        try
        {
            if(menu) 
            {
                g.drawImage(background, 0, 0,this.getWidth(), this.getHeight(), null); 
            }
            else   
            {
                //----------------------------------------------------------------------
                if(option == 1)
                {
                    g.setColor(color);
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());  
                }
                else if(option == 2)
                {
                    g.drawImage(background, 0, 0,this.getWidth(), this.getHeight(), null); 
                }
                else if(option == 3)
                {
                    if(delay_screen < Tdelay_screen)
                    {
                        Tdelay_screen = 0;
                        if( (EndX+SlideX <= MaxSlideX && plus_X < 0) || (EndX+SlideX >= MaxSlideX && plus_X > 0) )
                        {
                            plus_X = 0;
                        }
                        if( (EndY+SlideY <= MaxSlideY && plus_Y < 0) || (EndY+SlideY >= MaxSlideY && plus_Y > 0) )
                        {
                            plus_Y = 0;
                        }                

                        SlideX = SlideX + plus_X;
                        SlideY = SlideY + plus_Y;
                    }
                    Tdelay_screen = Tdelay_screen + 1;
                    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), StartX+SlideX, StartY+SlideY, EndX+SlideX, EndY+SlideY, null);
                }
                else if(option == 4)
                {                   
                    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), StartX+SlideX, StartY+SlideY, EndX+SlideX, EndY+SlideY, null);
                }
                //----------------------------------------------------------------------
                if(Image_option)
                {        
                    for(int i=0;i<pic.size();i++)
                    {            
                        if(pic.get(i).Show)
                        {
                            if(pic.get(i).AutoImage)
                            {
                                if(pic.get(i).delay_screen < pic.get(i).Tdelay_screen)
                                {                      
                                    pic.get(i).Tdelay_screen = 0;
                                    pic.get(i).state = pic.get(i).state + 1;
                                    if(pic.get(i).state > 2)
                                    {
                                        pic.get(i).state = 0;
                                    }
                                }
                                pic.get(i).Tdelay_screen = pic.get(i).Tdelay_screen + 1;
                            }

                            g.drawImage(pic.get(i).pic, 
                                        pic.get(i).X, 
                                        pic.get(i).Y, 
                                        pic.get(i).X + pic.get(i).SizeX, 
                                        pic.get(i).Y + pic.get(i).SizeY,                             
                                        (pic.get(i).SizeX*pic.get(i).state), 
                                        ((pic.get(i).Direction-1)*pic.get(i).SizeY), 
                                        (pic.get(i).SizeX*pic.get(i).state)        + pic.get(i).SizeX, 
                                        ((pic.get(i).Direction-1)*pic.get(i).SizeY)+ pic.get(i).SizeY, 
                                        null);
                        }
                    }                
                }
                //----------------------------------------------------------------------
                if(Font_option)
                {
                    for(int i=0 ; i < text.size() ; i++)
                    {
                        if(text.get(i).Show)
                        {
                            g.setColor(text.get(i).MsgColor);
                            g.setFont(text.get(i).FontMsg);
                            g.drawString(text.get(i).Msg,text.get(i).MsgX,text.get(i).MsgY);   
                        }
                    }                
                }
                //--------------------------------------------------------------------- HP
                if( HP_option )
                {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.blue);
                    g2d.setStroke(new BasicStroke(40));
                    int temp = ((int)( (1100 - 850) * (HP_now / 100.0) )) + 850; // ..calculate HP..
                    g2d.drawLine(850, 50, temp, 50);
                }
            }
        }
        catch(Exception ex){}
    }
}      