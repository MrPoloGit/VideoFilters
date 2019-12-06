import processing.core.PApplet;

import javax.swing.*;

public class Hue implements PixelFilter {
    String tred, tgreen, tblue, th;
    short r,g,b;
    int maxdistance;

    public Hue(){
        tred = JOptionPane.showInputDialog("Choose a target for red");
        r = Short.parseShort(tred);
        tgreen = JOptionPane.showInputDialog("Choose a target for green");
        g = Short.parseShort(tgreen);
        tblue = JOptionPane.showInputDialog("Choose a target for blue");
        b = Short.parseShort(tblue);
        th = JOptionPane.showInputDialog("Choose a thresold");
        maxdistance = Integer.parseInt(th);
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                if(calcDistance(red[r][c], green[r][c], blue[r][c]) > maxdistance) {
                    red[r][c] = 0;
                    green[r][c] = 0;
                    blue[r][c] = 0;
                }
            }
        }

        img.setColorChannels(red, green, blue);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }

    public double calcDistance(short rp, short gp, short bp){
        int dr = (rp - r);
        int dg = (gp - g);
        int db = (bp - b);
        double distance = Math.sqrt(dr*dr+dg*dg+db*db);
        return distance;
    }
}
