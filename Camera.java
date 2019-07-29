
/**
 * Write a description of class Camera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Camera implements Entity
{
    private double pointX;
    private double pointY;
    private double pointZ;
    private double rotHoriz;
    private double rotVert;
    /**
     * Sets the position and rotation of the camera, after verifying
     * that the rotation is from 0-360 degrees
     */
    public Camera(double x, double y, double z, double rotationX, double rotationY)
    {
        pointX = x;
        pointY = y;
        pointZ = z;
        this.rotHoriz = verifyRotation(rotationX);
        this.rotVert = verifyRotation(rotationY);
        
        System.out.println("Camera's rotation is "+rotHoriz+" horizontal, and "+rotVert+" vertical");
    }
    /**
     * verifies that the rotation is within 360 degrees, if not, adds or 
     * subtracts 360 degrees until it is
     * !should be a better way to do this
     */
    private double verifyRotation(double rotation){
        while(rotation < 0 || rotation >= 360){
            if(rotation < 0){
                rotation += 360;
            }
            if(rotation >= 360){
                rotation -= 360;
            }
        }
        return rotation;
    }
    /**
     * returns the coordinates as an array
     */
    public double[] getCoords(){
        double[] coords = {
            pointX, pointY, pointZ
        };
        return coords;
    }
    /**
     * returns the rotation values as an array
     */
    public double[] getRotation(){
        double[] rotation = {
            rotHoriz, rotVert
        };
        return rotation;
    }
    /**
     * @param x displacement on the X axis
     * @param y displacement on the Y axis
     * @param z displacement on the Z axis
     */
    public void moveCamera(double x,double y,double z){
        pointX += x;
        pointY += y;
        pointZ += z;
    }
    /**
     * @param rotateHoriz rotation on the Horizontal Axis
     * @param rotateVert rotation on the Vertical Axis
     */
    public void rotateCamera(double rotateHoriz,double rotateVert){
        rotHoriz += rotateHoriz;
        rotVert += rotateVert;
    }
    
}
