
/**
 * Write a description of class VoidFace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Edge extends Face
{
    /**
     * Constructor for objects of class VoidFace
     */
    public Edge(Point p, int x, float[] y)
    {
        super(p, x, y);
        setColor();
    }
    public void setColor(){
        float[] clr = new float[]{
            0 ,0 ,0
        };
        super.color = clr;
    }
    public boolean isLit(){
        return false;
    }
}
