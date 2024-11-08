package gamedevelopment;

import java.awt.Color;
import java.awt.Font;

public class Text
{
    String   Msg;
    Color    MsgColor;
    int      MsgX;
    int      MsgY;
    Font     FontMsg;
    boolean  Show;
   
    public Text(String Msg,Color MsgColor,int MsgX,int MsgY,String Namefont,int Sizefont)
    {
        this.Msg        = Msg;
        this.MsgColor   = MsgColor;
        this.MsgX       = MsgX;
        this.MsgY       = MsgY;
        this.FontMsg    = new Font(Namefont,Font.BOLD,Sizefont);
        Show            = true;
    }
    public void SetMsg(String s)
    {
        Msg = s;
    }    
    public String GetMsg()
    {
        return Msg;
    }
}