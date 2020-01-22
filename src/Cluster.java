import java.util.ArrayList;

public class Cluster {
    private RGB center;
    ArrayList<RGB> points;

    public Cluster(int red, int green, int blue){
        this.center = new RGB(red, green, blue);
        points = new ArrayList<>();
    }

    public void add(int red, int green ,int blue){
        RGB p = new RGB(red, green, blue);
        points.add(p);
    }

    public RGB getCenter(){
        return center;
    }

    public void setCenter(int r, int g, int b){
        RGB c = new RGB(r, g, b);
        center = c;
    }

    public void calcNewCenter(){
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int i = 0; i < points.size(); i++) {
            red += points.get(i).getRed();
            green += points.get(i).getGreen();
            blue += points.get(i).getBlue();
        }

        if(points.size() == 0) {
            setCenter((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        } else {
            setCenter(red/points.size(), green/points.size(), blue/points.size());
        }
        points.clear();
    }
}
