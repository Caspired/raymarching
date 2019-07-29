import java.util.ArrayList;
/**
 * Write a description of class Ray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ray
{
    private double x;
    private double y;
    private double z;
    
    private double xOld;
    private double yOld;
    private double zOld;
    //Rotation of the Ray in degrees
    private double rX;
    private double rY;
    
    public int distance;
    /**
     * Sets initial position and rotation to the camera position,
     * starts distance from origin at 0.
     */
    public Ray(double[] initCoords, double rotation[])
    {
        distance = 0;
        setInitCoords(initCoords);
        setInitRotation(rotation);
    }
    /**
     * Sets the initial position of the ray using the camera's coords
     */
    private void setInitCoords(double[] coords){
        x = coords[0];
        y = coords[1];
        z = coords[2];
    }
    public void back(){
        x=xOld;
        y=yOld;
        z=zOld;
    }
    /**
     * sets the initial angle of the ray using the camera's rotation
     */
    private void setInitRotation(double[] rotation){
        rX = rotation[0];
        rY = rotation[1];
    }
    public void setRotationOffset(int offsetX, int offsetY){
        rX += offsetX;
        rY += offsetY;
        //System.out.println(rX+","+rY);
    }
    /**
     * runs throught an array of points(cubes) and checks the collision 
     * with each one
     */
    public Point getCollision(ArrayList<Point> points){
        for(Point point : points){
            Point object = point.getCollision(x,y,z);
            if(object != null){
                //System.out.println("ray hit point"); 
                return point;
            }
        }
        return null;
    }
    public Face getCollisionFace(Point point){
        Face face = point.getCollisionFace(x,y,z);
        if(face != null){
            //System.out.println("ray hit point"); 
            return face;
        }else{
            return null;
        }
    } 
    /**
     * returns the coordinates of the ray as an array
     */
    public double[] getCoords(){
        double[] coords = {
            x, y, z
        };
        return coords;
    }
    /**
     * moves the ray along the angle
     * !needs to be made to work with three dimensions (two angles)
     */
    public void moveRay(double displacement){
        //System.out.println(x+""+y+""+z);
        xOld = x;
        yOld = y;
        zOld = z;
        
        distance += displacement;
        y += (Math.sin(Math.toRadians(rY)) * displacement);
        
        //Temporary variable storing the xz displacement seperate from the y
        double xz = (Math.cos(Math.toRadians(rY)) * displacement);
        x += (Math.cos(Math.toRadians(rX)) * xz);
        z += (Math.sin(Math.toRadians(rX)) * xz);
    }
}
