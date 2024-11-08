package gamedevelopment;
import java.util.Vector;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game 
{   
    Menu                menu;
    Frame               frame;
    Screen              screen; 
    Hero                hero;
    TimeGame            timegame;
    Vector<Picture>     ObjPic          = new Vector<Picture>();
    Vector<Text>        text            = new Vector<Text>();
    Vector<Item>        item            = new Vector<Item>();
    //
    public static int   show_item       = 0;
    public static int   my_delay        = 20000000;
    public static int   my_hp           = 5;
    public static int   select          = 0;
    public static int   level           = 0;
    public static int   gamestart       = 0;
    public static int   boss_on         = 0;
    public static int   save_time       = 0;
    //
    public void clearall()
    {
       for(int i=0;i < ObjPic.size();i++)    
       {
        ObjPic.get(i).X = -1000;
        ObjPic.get(i).Y = -1000;
       }
    }
    
    public void NewGame()
    {
        screen = new Screen("menu.jpg");   //menu
        //screen = new Screen("bg1.jpg");  //No menu
        frame = new Frame("Defend the sheep mk.2",1200,800,screen);
        menu = new Menu(this,"bg1.jpg","menu.jpg"); //menu        
        ObjPic.add( new Picture("sheep.png",-1000,-1000,200,200,1,false) );
        ObjPic.add( new Picture("posion.png",-1000,-1000,200,200,1,false) );
        ObjPic.add( new Picture("wolf.png",-1000,-1000,200,200,1,false) );
        ObjPic.add( new Picture("heart.png",0,0,100,99,1,true) );
        ObjPic.add( new Picture("boss1.png",-1000,-1000,200,200,1,false) );
        ObjPic.add( new Picture("boss2.png",-1000,-1000,200,200,1,false) );
        ObjPic.add( new Picture("boss3.png",-1000,-1000,200,200,1,false) );
        ObjPic.add( new Picture("shoots.png",0,0,64,64,1,false,20) );
        ObjPic.add( new Picture("cross.png",200,200,100,100,1,true) );
        //----------------------------------------------------------------------
        text.add( new Text("-",Color.red,100,80,"JasmineUPC",120) );
        text.add( new Text("-",Color.red,300,80,"JasmineUPC",120) );
        text.add( new Text("HP BOSS:",Color.blue,700,60,"JasmineUPC",40) );
        screen.SetImage(ObjPic,text);  
        timegame = new TimeGame();  
        //----------------------------------------------------------------------
        hero = new Hero() 
        {
            @Override
            public void key5() {}
            @Override
            public void key6() {}
            @Override
            public void key7() {}
            @Override
            public void key8() {}
        };
        item.add(  new Item(ObjPic.get(0), hero,"sheep.wav" ) 
        {
            @Override
            public void affect() 
            {
                my_hp = my_hp - 1;
                playsound();
                item.get(0).Clear();
            }
        });
        item.add(  new Item(ObjPic.get(1), hero,"life_up.wav" ) 
        {
            @Override
            public void affect() 
            {
                my_hp = my_hp + 1;
                playsound();
                item.get(1).Clear();
            }
        });
        item.add(  new Item(ObjPic.get(2), hero,"woft.wav" ) 
        {
            @Override
            public void affect() 
            {
                playsound();
                item.get(2).Clear();
            }
        });
        item.add(  new Item(ObjPic.get(4), hero,"woft.wav" ) 
        {
            @Override
            public void affect() 
            {
                screen.HP_now  = screen.HP_now   - 25;
                playsound();
                item.get(3).Clear();
            }
        });
        item.add(  new Item(ObjPic.get(5), hero,"woft.wav" ) 
        {
            @Override
            public void affect() 
            {
                screen.HP_now  = screen.HP_now   - 20;
                playsound();
                item.get(4).Clear();
            }
        });
        item.add(  new Item(ObjPic.get(6), hero,"woft.wav" ) 
        {
            @Override
            public void affect() 
            {
                screen.HP_now  = screen.HP_now   - 10;
                playsound();
                item.get(5).Clear();
            }
        });
        //----------------------------------------------------------------------
        MouseAction mc = new MouseAction( 10, 5, 10000, "Pistol.wav","OK.wav", ObjPic.get(7), ObjPic.get(8), frame) 
        {
            @Override
            public void action_L(MouseEvent e, int j) {}
            @Override
            public void action_R(MouseEvent e) {}
        };
        mc.Additem(item);
        gamestart = 0;
        //----------------------------------------------------------------------
        while(true)
        {   
            //mouse
            mc.Action();
            //GAME
            if(gamestart == 1)
            {
                BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg,new Point(0, 0),"blank cursor");
                frame.getContentPane().setCursor(blankCursor); 
                //
                boss_on = 0;   //donot show boss = 0
                screen.HP_option = false;
                timegame.T = 0;
                save_time = timegame.T;
                level = 1;
                my_delay = 10000000;   //Speed Monster
                text.get(2).Show = false;
                gamestart = 2; 
                //
            }
            if(gamestart == 2)
            {
                if( level == 6 && screen.HP_now <= 0) //WIN GAME
                {
                    try 
                    {
                        boss_on = 0;
                        screen.HP_now    = 0;
                        screen.HP_option = false;
                        text.get(2).Show = false;
                        screen.background = ImageIO.read(new File("win.png")); 
                        text.get(0).Show = false;
                        text.get(1).Show = false;
                        clearall();
                        frame.repaint(); 
                        break;
                    } 
                    catch (Exception e) {}
                }
                if(  (timegame.T == save_time + 20) && level == 5 ) //5 sec + Boss 3 + set time 20 sec per MAP
                {
                    try 
                    {
                        for(int i=0;i < item.size();i++) { item.get(i).Clear(); }  
                        level = 6;
                        my_delay = 7000000;    //Speed Boss 
                        boss_on = 5;
                        screen.HP_now    = 100;
                        screen.HP_option = true;
                        text.get(2).Show = true;
                        frame.repaint();
                    }
                    catch (Exception e) {}
                }   
                else if ( level == 4 && screen.HP_now <= 0)  //CHANGE MAP level 3 
                {
                    save_time = timegame.T;
                    level = 5;
                    boss_on = 0;
                    screen.HP_now    = 0;
                    screen.HP_option = false;
                    text.get(2).Show = false;
                    try 
                    {
                        screen.background = ImageIO.read(new File("bg3.jpg")); 
                        my_delay = 8000000; //Speed Monster
                    } 
                    catch (Exception e) {}
                    frame.repaint();
                }
                else if(  (timegame.T == save_time + 20) && level == 3 ) //5 sec + Boss 2 + set time 20 sec per MAP
                {
                    try 
                    {
                        for(int i=0;i < item.size();i++) { item.get(i).Clear(); }  
                        level = 4;
                        my_delay = 9000000;    //Speed Boss 
                        boss_on = 4;
                        screen.HP_now    = 100;
                        screen.HP_option = true;
                        text.get(2).Show = true;
                        frame.repaint();
                    }
                    catch (Exception e) {}
                }   
                else if ( level == 2 && screen.HP_now <= 0)  //CHANGE MAP level 2  
                {
                    save_time = timegame.T;
                    level = 3;
                    boss_on = 0;
                    screen.HP_now    = 0;
                    screen.HP_option = false;
                    text.get(2).Show = false;
                    try 
                    {
                        screen.background = ImageIO.read(new File("bg2.jpg")); 
                        my_delay = 9000000; //Speed Monster
                    } 
                    catch (Exception e) {}
                    frame.repaint();
                }
                else if(  (timegame.T == save_time + 20) && level == 1 ) //3 sec + Boss 1 + set time 20 sec per MAP
                {
                    try 
                    {
                        for(int i=0;i < item.size();i++) { item.get(i).Clear(); }  
                        level = 2;
                        my_delay = 10000000;    //Speed Boss 
                        boss_on = 3;
                        screen.HP_now    = 100;
                        screen.HP_option = true;
                        text.get(2).Show = true;
                        frame.repaint();
                    }
                    catch (Exception e) {}
                }                
                //item----------------------------------------------------------
                if( boss_on == 0 )                                              //Normal
                {
                    for(int i=0;i < item.size();i++)    
                    { 
                        item.get(i).action(); 
                    }
                    if( show_item > my_delay) 
                    {
                        show_item = 0;
                        if( select == 2 && item.get(2).ObjPic.Show == true)  // NO shoot dog
                        {
                            my_hp = my_hp - 1;
                            try 
                            {
                                Clip clip = AudioSystem.getClip();
                                clip.open(AudioSystem.getAudioInputStream(new File("woft_attack.wav")));
                                clip.start();
                            }
                            catch (Exception ex) { }  
                        }
                        select = (int)(Math.random() * 3);
                        for(int i=0;i < item.size();i++) { item.get(i).Clear(); }     
                        item.get(select).ObjPic.Show = true;
                        item.get(select).ObjPic.X = (int)(Math.random() * 1000) + 10; //range random limit X
                        item.get(select).ObjPic.Y = (int)(Math.random() * 550) + 80; //range random limit Y
                    }
                }
                else if( boss_on == 3 || boss_on == 4 || boss_on == 5 )         //Boss Show
                {
                    item.get(boss_on).action(); 
                    if( show_item > my_delay ) 
                    {
                        show_item = 0;
                        if(item.get(boss_on).ObjPic.Show == true)  // NO shoot dog
                        {
                            my_hp = my_hp - 1;
                            try 
                            {
                                Clip clip = AudioSystem.getClip();
                                clip.open(AudioSystem.getAudioInputStream(new File("woft_attack.wav")));
                                clip.start();
                            }
                            catch (Exception ex) { }  
                        }
                        item.get(boss_on).Clear();
                        item.get(boss_on).ObjPic.Show = true;
                        item.get(boss_on).ObjPic.X = (int)(Math.random() * 1000) + 10; //range random limit X
                        item.get(boss_on).ObjPic.Y = (int)(Math.random() * 550) + 80; //range random limit Y
                    }
                }
                //text----------------------------------------------------------
                text.get(0).SetMsg("X " + my_hp);
                text.get(1).SetMsg("Time : " + timegame.T);
                //GAME OVER-----------------------------------------------------
                if( my_hp == 0 )
                {
                    try 
                    {
                        screen.background = ImageIO.read(new File("over.jpg"));
                        clearall();
                        frame.repaint();
                        break;
                    } 
                    catch (Exception e) {}
                }
                //--------------------------------------------------------------
                show_item++;
            }
            frame.repaint(); 
        } 
    }
    public static void main(String[] args) 
    {
        Game game = new Game();
        game.NewGame();
    }      
}