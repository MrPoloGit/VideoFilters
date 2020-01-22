import processing.core.PApplet;

import java.util.ArrayList;

public class Exercise3 implements PixelFilter {

    ArrayList<Point> points;

    public Exercise3(){
       points = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(200,200);
            points.add(p);
        }
    }
    @Override
    public DImage processImage(DImage img) {
        return null;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(0,0,0);
        for(Point p: points){
            window.ellipse(p.getX(),p.getY(),10,10);
            p.takerandomStep();
        }

    }
}
