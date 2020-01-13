import processing.core.PApplet;

import javax.swing.*;

public class Exercise2B implements PixelFilter {
    int n;
    public Exercise2B(){
        String splits = JOptionPane.showInputDialog("What number of grids do you want?");
        n = Integer.parseInt(splits);
    }
    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        int w = original.getWidth()/n;
        int valx = w;
        for (int i = 0; i < n-1; i++) {
            window.stroke(0,0,0);
            window.line(valx,0,valx,original.getHeight());
            valx += w;
        }
        int h = original.getHeight()/n;
        int valy = h;
        for (int i = 0; i < n-1; i++) {
            window.stroke(0,0,0);
            window.line(0,valy,original.getWidth(),valy);
            valy += h;
        }
    }
}
