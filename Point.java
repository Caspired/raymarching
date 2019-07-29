import java.lang.Math;
/**
 * A point with collision that occupies that point and everything within a
 * cube with side length -1
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Point
{
    private int pointX;
    private int pointY;
    private int pointZ;
    
    private Face[] faces;
    
    //Collision size
    public int size;
    
    private float[] colour;
    /**
     * Creates an point with three coordinates
     * Sets a random colour string to that object
     */
    public Point(int x, int y, int z)
    {
        pointX = x;
        pointY = y;
        pointZ = z;
        
        size= 5;
        colour = setColour();
        
        setFaces();
        
    }
    private void setFaces(){
        faces = new Face[]{
            new Face(this, Face.NORTH, colour),
            new Face(this, Face.SOUTH, colour),
            new Face(this, Face.EAST, colour),
            new Face(this, Face.WEST, colour),
            new Face(this, Face.TOP, colour),
            new Face(this, Face.BOTTOM, colour)
        };
    }
    public Face[] getFaces(){
        return this.faces;
    }
    /**
     * returns the point's coordinates as an array
     */
    public int[] getCoords(){
        int[] coords = {
            pointX, pointY, pointZ
        };
        return coords;
    }
    /**
     * returns true if all three coordinates of the ray are within the 
     * coordinates of the a box that 
     */
    public Point getCollision(double x, double y, double z){
        if(x>=(pointX)&&(x<=pointX+size)){
            if(y>=(pointY)&&(y<=pointY+size)){
                if(z>=(pointZ)&&(z<=pointZ+size)){
                    return this;
                }
            }
        }
        return null;
    }
    public Face getCollisionFace(double x, double y, double z){
        int n = 0;
        Face[] temp = new Face[6];
        for(Face faces:this.faces){
            if(faces.getCollision(x-pointX, y-pointY, z-pointZ)){
                temp[n] = faces;
                n++;
            }
        }
        if(n==1){
            return temp[0];
        }else{
            if(n==3){
                //return new Corner(Face.NORTH, colour);
            }else{
                //return new Edge(Face.NORTH, colour);
            }
            return new Edge(this, Face.NORTH, colour);
        }
    }
    /**
     * Returns a random six-digit hex code for use as a colour value
     */
    private float[] setColour(){
        float[] rgb = new float[3];
        for(int i=0;i<3;i++){
            //generates a six-digit hex code to be used as a colour value
            Double colorCode = (Math.random()*0.9);
            rgb[i] = colorCode.floatValue();
            //System.out.println(rgb[i]);
        }
        return rgb;
    }
    public float[] getColour(){
        return colour;
    }
}
