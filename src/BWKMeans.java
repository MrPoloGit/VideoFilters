import processing.core.PApplet;

import java.util.ArrayList;

public class BWKMeans implements PixelFilter{
    private ArrayList<BWCluster> clusters;
    private int k;

    public BWKMeans(int k){
        clusters = new ArrayList<>();
        this.k = k;
        for (int i = 0; i < k; i++) {
            BWCluster c = new BWCluster((int)(Math.random()*640), (int)(Math.random()*480));
            clusters.add(c);
        }
    }

    public ArrayList<BWCluster> getClusters(){
        return clusters;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] pixel = img.getBWPixelGrid();
        for (int i = 0; i < 10; i++) {
            for (int r = 0; r < pixel.length; r++) {
                for (int c = 0; c < pixel[0].length; c++) {
                    if(pixel[r][c] == 255){
                        placeCenter(r,c);
                    }
                }
            }
        }
        for (int i = 0; i < clusters.size(); i++) {
            clusters.get(i).calcNewCenter();
        }
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(0);
        for (int i = 0; i < clusters.size(); i++) {
            window.ellipse(clusters.get(i).getCenter().getX(), clusters.get(i).getCenter().getX(), 10, 10);
            clusters.get(i).clear();
        }
    }

    public void placeCenter(int x, int y){
        int mini = 0;
        int mx = clusters.get(0).getCenter().getX()-x;
        int my = clusters.get(0).getCenter().getY()-y;
        int mindist = mx*mx+my*my;
        int dist = 0;
        for (int i = 1; i < clusters.size(); i++) {
            dist = calcDistance(x,y,clusters.get(i).getCenter());
            if(dist < mindist){
                mini = i;
                mx = clusters.get(mini).getCenter().getX();
                my = clusters.get(mini).getCenter().getY();
                mindist = mx*mx+my*my;
            }
        }
        clusters.get(mini).add(x,y);
    }

    public int calcDistance(int x, int y, Point center){
        int dx = x - center.getX();
        int dy = y - center.getY();

        int dist = (dx*dx + dy*dy);
        return dist;
    }






}
