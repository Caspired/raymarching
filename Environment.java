import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Contains references to the camera, display and lights.
 * Holds an ArrayList of all objects in the world.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Environment implements ActionListener
{
    private static int width = 200;
    private static int height = 120;
    //Add Slider for Field of view
    private static int fovModifier = 2;
    
    
    private Camera camera;
    private LightSource light;
    private Display display;
    private Controller controller;
    private ArrayList<Point> objects;
    /**
     * Creates a camera object with coordinates and rotation
     * Creates a display object with a width and height in "pixels"
     * !each pixel should be one degree for now
     */
    public Environment()
    {
        camera = new Camera(0.00, 0.00, 0.00, 90.00, 0.00);
        display = new Display(width, height);
        //Positions the controller window at the bottom of the display window
        java.awt.Point p = display.getLocation();
        p.translate(0, display.getPreferredSize().height);
        controller = new Controller(p, this);
    }
    public void actionPerformed(ActionEvent e){
        String[] action = e.getActionCommand().split(" ");
        if(action[0].equals("move")){
            // double[] rotation = camera.getRotation();
            // double rotHoriz = rotation[0];
            // double rotVert = rotation[1];
            
            double[] v;
            double d = 1.00;
            if((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK){
                d = 5.00;
            }
            switch(action[1]){
                case "forward": v = getMovementVectors(0,0,d);
                camera.moveCamera(v[0],v[1],v[2]);
                break;
                case "backward": v = getMovementVectors(180,0,d);
                camera.moveCamera(v[0],v[1],v[2]);
                break;
                case "right": v = getMovementVectors(90,0,d);
                camera.moveCamera(v[0],v[1],v[2]);
                break;
                case "left": v = getMovementVectors(-90,0,d);
                camera.moveCamera(v[0],v[1],v[2]);
                break;
                // case "up": v = getMovementVectors(0,90,d);
                // camera.moveCamera(v[0],v[1],v[2]);
                // break;
                // case "down": v = getMovementVectors(0,-90,d);
                // camera.moveCamera(v[0],v[1],v[2]);
                // break;
            }
            generateRays();
        }
        if(action[0].equals("rotate")){
            double r = 5.00;
            if((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK){
                r = 45.00;
            }
            switch(action[1]){
                case "right": camera.rotateCamera(r,0.00);
                break;
                case "left": camera.rotateCamera(-r,0.00);
                break;
                case "up": camera.rotateCamera(0.00,r);
                break;
                case "down": camera.rotateCamera(0.00,-r);
                break;
            }
            generateRays();
        }
    }
    private double[] getMovementVectors(double offsetHoriz, double offsetVert, double displacement){
        double[] rotation = camera.getRotation();
        double rotHoriz = rotation[0] + offsetHoriz;
        //double rotVert = rotation[1];
        
        double[] vectors = new double[3];
        
        //Temporary variable storing the xz displacement seperate from the y
        double xz = (Math.cos(Math.toRadians(0))*displacement);
        //x axis
        vectors[0] = (Math.cos(Math.toRadians(rotHoriz)) * xz);
        //y axis
        vectors[1] = (Math.sin(Math.toRadians(0))*displacement);
        //z axis
        vectors[2] = (Math.sin(Math.toRadians(rotHoriz)) * xz);
        return vectors;
    }
    /**
     * generates an ArrayList populated with points
     */
    public void createTestPoints(){
        objects = new ArrayList<Point>();
        
        objects.add(new Point(20, 20, 30));
        objects.add(new Point(-20, -20, 30));
        
        objects.add(new Point(0,0, 3));
        objects.add(new Point(-5,0,10));
        
        
        objects.add(new Point(10, -10, 30));
        objects.add(new Point(10, 10, 30));
        objects.add(new Point(-10, 0, 30));
        objects.add(new Point(-10, 10, 30));
        
        objects.add(new Point(200, 100, 100));
        objects.add(new Point(0,50,0));
        System.out.println("Loaded "+objects.size()+" objects.");
    }
    private ArrayList<Point> getVisibleObjects(Entity src, int range){
        ArrayList<Point> visibleObjects = new ArrayList<Point>();
        double[] srcCoords = src.getCoords();
        for(Point point:objects){
            int[] pointCoords = point.getCoords();
            boolean inRange = true;
            for(int i=0;i<3;i++){
                if((pointCoords[i] > (srcCoords[i]+range)) || (pointCoords[i] < (srcCoords[i]-range))){
                    inRange = false;
                }
            }
            if(inRange){
                visibleObjects.add(point);
            }
        }
        
        if(visibleObjects.size() != 0){
            return visibleObjects;
        }else{
            return null;
        }
    }
    private Face sendRay(int rotationOffsetX, int rotationOffsetY, Entity src, ArrayList<Point> objects){
        Ray ray = new Ray(src.getCoords(), src.getRotation());
        ray.setRotationOffset(rotationOffsetX, rotationOffsetY);
        Point rayContactPoint = null;
        for(int i=0;i<80;i++){
            rayContactPoint = ray.getCollision(objects);
            if(rayContactPoint != null){
                ray.back();
                Face rayContactFace = null;
                //Something goes wrong here!!
                for(int j=0;j<30;j++){
                    ray.moveRay(0.1);
                    rayContactFace = ray.getCollisionFace(rayContactPoint);
                    if(rayContactFace != null){
                        //ray.back();
                        return rayContactFace;
                    }
                }
                return rayContactFace;
            }
            ray.moveRay(0.5);
        }
        //double[] coords = ray.getCoords();
        return null;
    }
    public void generateRays(){
        long timer = System.currentTimeMillis();
        ArrayList<Point> visibleObjects = getVisibleObjects(camera, 40);
        if(visibleObjects == null){
            return;
        }
        System.out.println(visibleObjects.size()+" objects in range of camera.");
        
        display.clearDisplay();
        int test = 0;
        int test2 = 0;
        for(int i = -(height - (height/2));i<(height/2);i++){
            for(int n = -(width - (width/2));n<(width/2);n++){
                Face contactPoint = sendRay(n, i, camera, visibleObjects);
                if(contactPoint!=null){
                    display.addPixel(contactPoint, (n+(width/2)),(i+(height/2)));
                    test2++;
                }
                test ++;
            }
        }
        
        System.out.println("Generated "+test+" rays.");
        System.out.println("Hit "+test2+" objects.");
        System.out.println("Took "+(System.currentTimeMillis() - timer)+"ms");
    }
    
    private void getLighting(){
        ArrayList<Point> visibleObjects = getVisibleObjects(light, 60);
        if(visibleObjects == null){
            return;
        }
        System.out.println(visibleObjects.size()+" objects in range of the light.");
        
        int test = 0;
        int test2 = 0;
        for(int i = 0;i<360;i+=3){
            for(int n = 0;n<180;n+=3){
                Face contactPoint = sendRay(n, i, light, visibleObjects);
                if(contactPoint!=null){
                    contactPoint.isLit(true);
                    test2++;
                }
                test ++;
            }
        }
        
        System.out.println("Generated "+test+" lighting rays.");
        System.out.println("Lit "+test2+" faces.");
    }
    /**
     * creates an Environment and creates points within it
     */
    public static void main(String args[]){
        Environment game = new Environment();
        game.createTestPoints();
        game.light = new LightSource(3, 3, 15, game.objects);
        game.getLighting();
        
        game.generateRays();
    }
}
