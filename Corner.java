
/**
 * Write a description of class VoidFace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Corner extends Face
{
    /**
     * Constructor for objects of class VoidFace
     */
    public Corner(Point p, int x, float[] y)
    {
        super(p, x, y);
        setColor();
    }
    public void setColor(){
        float[] clr = new float[]{
            1 ,0 ,0
        };
        super.color = clr;
    }
    public boolean isLit(){
        return false;
    }
}
