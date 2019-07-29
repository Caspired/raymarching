
/**
 * Write a description of class BlockFace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Face
{
    public static int NORTH = 0;
    public static int SOUTH = 1;
    public static int EAST = 2;
    public static int WEST = 3;
    public static int TOP = 4;
    public static int BOTTOM = 5;
    
    private Point point;
    
    private boolean lit;
    protected float[] color;
    private int faceOrientation;
    /**
     * Constructor for objects of class BlockFace
     */
    public Face(Point point, int face, float[] color)
    {
        this.color = color;
        this.faceOrientation = face;
        this.point = point;
    }
    public Point getPoint(){
        return this.point;
    }
    public boolean getCollision(double x, double y, double z){
        switch(this.faceOrientation){
            case 0: if(x>=4.5){
                return true;
            }
            break;
            case 1: if(x<=0.5){
                return true;
            }
            break;
            case 2: if(z>=4.5){
                return true;
            }
            break;
            case 3: if(z<=0.5){
                return true;
            }
            break;
            case 4: if(y>=4.5){
                return true;
            }
            break;
            case 5: if(y<=0.5){
                return true;
            }
            break;
        }
        return false;
    }
    public boolean isLit(){
        return lit;
    }
    public void isLit(boolean b){
        if(b){
            lit=true;
        }else{
            lit=false;
        }
    }
    public float[] getColor(){
        return color;
    }
    public int getOrientation(){
        return this.faceOrientation;
    }
}
