import processing.core.PApplet;

import javax.swing.*;

public class Lighten implements PixelFilter {
    int per;


    public Lighten(){
        String num = JOptionPane.showInputDialog("Choose a percentage of lightness");
        per = Integer.parseInt(num);
    }

    @java.lang.Override
    public DImage processImage(DImage img) {
        short[] Lpixels = img.getBWPixelArray();

        for (int i = 0; i < Lpixels.length; i++) {
            short newvalue = (short)(Lpixels[i] + (255 - Lpixels[i])*(per/100.0));
            Lpixels[i] = newvalue;
        }
        img.setPixels(Lpixels);
        return img;
    }

    @java.lang.Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
