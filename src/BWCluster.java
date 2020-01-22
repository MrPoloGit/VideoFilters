import java.util.ArrayList;

public class BWCluster {
    private Point center;
    ArrayList<Point> points;
    int x;
    int y;


    public BWCluster(int x,int y){
        this.x = x;
        this.y = y;
        this.center = new Point(x,y);
        points = new ArrayList<>();
    }

    public void add(int x, int y){
        Point p = new Point(x,y);
        points.add(p);
    }

    public Point getCenter(){
        return center;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setCenter(int x, int y){
        Point c = new Point(x,y);
        center = c;
    }

    public void calcNewCenter(){
        if(points != null && points.size() != 0){
            int x = 0;
            int y = 0;
            for (Point p : points) {
                if ( p != null) {
                    x += p.getX();
                    y += p.getY();
                }
            }
            setCenter(x/points.size(), y/points.size());

        } else {
            x = (int)(Math.random()*640);
            y = (int)(Math.random()*480);
            setCenter(x, y);

        }
        points.clear();
        System.out.println(x + ", " + y);
    }

    public void clear(){
        points.clear();
    }
}
