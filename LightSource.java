import java.util.ArrayList;
/**
 * Write a description of class LightSource here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LightSource implements Entity
{
    private double x;
    private double y;
    private double z;
    
    private double rotHoriz;
    private double rotVert;
    
    private ArrayList<Point> relevantObjects;
    /**
     * Constructor for objects of class LightSource
     */
    public LightSource(double x, double y, double z, ArrayList<Point> objects)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        
        relevantObjects = new ArrayList<Point>();
        for(Point point:(ArrayList<Point>)objects){
            if(point.getCollision(x,y,z) !=null){
                relevantObjects.add(point);
            }
        }
    }
    public double[] getCoords(){
        double[] coords = {
            x, y, z
        };
        return coords;
    }
    public double[] getRotation(){
        double[] rotation = {
            rotHoriz, rotVert
        };
        return rotation;
    }
}
