import processing.core.PApplet;

import javax.swing.*;

public class Gamma implements PixelFilter {

    private double gamma,k;
    private short[] arr = new short[256];

    public Gamma(){
        String splits = JOptionPane.showInputDialog("What gamma value do you want?");
        gamma = Double.parseDouble(splits);
        k = 255/Math.pow(255,gamma);
        for (int i = 0; i < 256; i++) {
            arr[i] = (short)(k*Math.pow(i,gamma));
        }
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();

        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[r].length; c++) {
                int val = pixels[r][c];
                pixels[r][c] = arr[val];
            }
        }
        img.setPixels(pixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
