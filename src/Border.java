import processing.core.PApplet;

import javax.swing.*;

public class Border implements PixelFilter {
    int w;

    public Lighten(){
        String num = JOptionPane.showInputDialog("Choose a width of lightness");
        w = Integer.parseInt(num);
    }

    @java.lang.Override
    public DImage processImage(DImage img) {
        short[][] bw = img.getBWPixelGrid();
        short[][] border = new short[bw.length + 2*w][bw[0].length + 2*w];

        for (int rw = 0; rw < border.length; rw++) {
            for (int cw = 0; cw < border[cw].length ; cw++) {
                border[rw][cw] = (short)(w);
            }
        }

        for (int r = 0; r < bw.length; r++) {
            for (int c = 0; c < bw[r].length; c++) {
                border[r+w][c+w] = bw[r][c];
            }
        }
        img.setPixels(border);
        return img;
    }

    @java.lang.Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
