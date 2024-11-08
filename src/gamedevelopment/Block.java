package gamedevelopment;
import java.util.Vector;

public class Block 
{
    public Picture  ObjPic;
    public int      OutboundXL;
    public int      OutboundXM;
    public int      OutboundYL;
    public int      OutboundYM;
    
    public Block(Picture ObjPic,Hero hero)
    {
        this.ObjPic         = ObjPic;
        this.ObjPic.Show    = true;
        this.OutboundXL     = ObjPic.X-(ObjPic.SizeX-10);
        this.OutboundXM     = ObjPic.X+(ObjPic.SizeX-10);
        this.OutboundYL     = ObjPic.Y-(ObjPic.SizeY-10);
        this.OutboundYM     = ObjPic.Y+(ObjPic.SizeY-10);
    }
}