package gamedevelopment;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Picture
{
   public int X;
   public int Y;
   public int Direction;          //UP=1,Down=2,LEFT=3,RIGHT=4
   public int state;              //state 0,1,2
   public boolean Show;
   public int SizeX;
   public int SizeY;
   public BufferedImage pic;
   public boolean AutoImage;      //play animation auto
   public int delay_screen;
   public int Tdelay_screen = 0;
   
   public Picture(String pic,int SizeX,int SizeY,int Direction)
   {
        try
        {
            this.pic = ImageIO.read(new File(pic));
            this.X = -300;
            this.Y = -300;
            this.SizeX = SizeX;
            this.SizeY = SizeY;
            this.Show = false;
            this.state = 0; 
            this.Direction = Direction;
            this.AutoImage = false;
        }
        catch(Exception ex){}
   }       
   
   public Picture(String pic,int SizeX,int SizeY,int Direction,int delay_screen)
   {
        try
        {
            this.pic = ImageIO.read(new File(pic));
            this.SizeX = SizeX;
            this.SizeY = SizeY;
            this.state = 0; 
            this.Direction = Direction;
            this.AutoImage = true;
            this.delay_screen = delay_screen;
        }
        catch(Exception ex){}
   }  
   
   public Picture(String pic,int X, int Y,int SizeX,int SizeY,int Direction,boolean Show,int delay_screen)
   {
        try
        {
            this.pic = ImageIO.read(new File(pic));
            this.X = X;
            this.Y = Y;
            this.SizeX = SizeX;
            this.SizeY = SizeY;
            this.Show = Show;
            this.state = 0; 
            this.Direction = Direction;
            this.AutoImage = true;
            this.delay_screen = delay_screen;
        }
        catch(Exception ex){}
   }    
   
   public Picture(String pic,int X, int Y,int SizeX,int SizeY,int Direction,boolean Show)
   {
        try
        {
            this.pic = ImageIO.read(new File(pic));
            this.X = X;
            this.Y = Y;
            this.SizeX = SizeX;
            this.SizeY = SizeY;
            this.Show = Show;
            this.state = 0; 
            this.Direction = Direction;
            this.AutoImage = false;
        }
        catch(Exception ex){}
   }   
}  