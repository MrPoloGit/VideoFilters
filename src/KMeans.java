import processing.core.PApplet;

import javax.swing.*;
import java.util.ArrayList;

public class KMeans implements PixelFilter {
    private int k;
    private ArrayList<Cluster> clusters;

    public KMeans(){
        k = Integer.parseInt(JOptionPane.showInputDialog("How many colors?"));
        clusters = initClusters(k);
    }


    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        findOptimalColors(red, green, blue);
        reAssignPixels(red, green, blue, clusters);

        img.setColorChannels(red, green, blue);
        return img;
    }

    private void findOptimalColors(short[][] red, short[][] green, short[][] blue) {
        for (int n = 0; n < 10; n++) {
            for (int r = 0; r < red.length; r++) {
                for (int c = 0; c < red[r].length; c++) {
                    int re = red[r][c];
                    int gr = green[r][c];
                    int bl = blue[r][c];

                    storeInCluster(re,gr,bl);
                }
            }
            newCenters();
        }
    }

    private void newCenters(){
        for (int i = 0; i < clusters.size(); i++) {
            clusters.get(i).calcNewCenter();
        }
    }

    private void storeInCluster(int r, int g, int b){
        int mini = 0;
        double mindist = 255*255*255;

        for (int i = 0; i < clusters.size(); i++) {
            double dist = clusters.get(i).distance(r,g,b);
            if(dist < mindist) {
                mindist = dist;
                mini = i;
            }
        }

        clusters.get(mini).add(r,g,b);
    }

    private void reAssignPixels(short[][] red, short[][] green, short[][] blue, ArrayList<Cluster> clusters) {
        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[r].length; c++) {
                int re = red[r][c];
                int gr = green[r][c];
                int bl = blue[r][c];
                Point cen = findCenter(re,gr,bl);
                red[r][c] = (short)(cen.getRed());
                green[r][c] = (short)(cen.getGreen());
                blue[r][c] = (short)(cen.getBlue());
            }
        }
    }

    private Point findCenter(int r, int g, int b){
        int minc = 0;
        double mindist = 255*255*255;

        for (int i = 0; i < clusters.size(); i++) {
            int dist = calcDistance(r,g,b, clusters.get(i).getCenter());
            if(dist < mindist) {
                mindist = dist;
                minc = i;
            }
        }
        int cr = clusters.get(minc).getCenter().getRed();
        int cg = clusters.get(minc).getCenter().getGreen();
        int cb = clusters.get(minc).getCenter().getBlue();
        Point p = new Point(cr,cg,cb);
        return p;
    }

    public ArrayList<Cluster> initClusters(int k){
        ArrayList<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int red = (int)(Math.random()*256);
            int green = (int)(Math.random()*256);
            int blue = (int)(Math.random()*256);

            Cluster c = new Cluster(red, green, blue);
            clusters.add(c);
        }
        return clusters;
    }

    public int calcDistance(int red, int green, int blue, Point center){
        int dr = red - center.getRed();
        int dg = green - center.getGreen();
        int db = blue - center.getBlue();

        int dist = (dr*dr+dg*dg+db*db);
        return dist;
    }
    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        // Unneeded
    }


}
