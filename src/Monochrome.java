import processing.core.PApplet;
import javax.swing.*;

public class Monochrome implements PixelFilter{
    int th;

    public Monochrome(){
        String num = JOptionPane.showInputDialog("Choose a threshold to split by");
        th = Integer.parseInt(num);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] Mpixels = img.getBWPixelGrid();

        for (int r = 0; r < Mpixels.length; r++) {
            for (int c = 0; c < Mpixels[r].length; c++) {
                int tmp = Mpixels[r][c];
                if(tmp > th) {
                    Mpixels[r][c] = 255;
                } else {
                    Mpixels[r][c] = 0;
                }
            }
        }

        img.setPixels(Mpixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
