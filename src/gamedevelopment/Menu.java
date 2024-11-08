
package gamedevelopment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import static gamedevelopment.Game.gamestart;


class Menu implements MouseListener
{   
    public Game             game;
    public String           background;
    public String           menu;
     
    public Menu(Game game,String background,String menu)
    {
        try
        {   
            this.menu = menu;
            this.background = background;
            this.game  = game;
            game.screen.background = ImageIO.read(new File(menu) );
            game.frame.addMouseListener(this);
            game.screen.menu = true;
        } 
        catch(Exception ex){ }         
    }
    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(gamestart == 0)
        {
            if(SwingUtilities.isLeftMouseButton(e))
            {
                if(e.getY() > 400 && e.getY() < 500 && e.getX() > 400 && e.getX() < 750) //menu start button
                {
                    try
                    {  
                        gamestart = 1;
                        game.screen.menu = false;
                        game.screen.background = ImageIO.read(new File(background) );
                    } 
                    catch(Exception ex){}                    
                }
            }
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) { }    
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}