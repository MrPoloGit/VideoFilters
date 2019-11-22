import processing.core.PApplet;

public class Negative implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] Npixels = img.getBWPixelGrid();

        for (int r = 0; r < Npixels.length; r++) {
            for (int c = 0; c < Npixels[r].length; c++) {
                short num = Npixels[r][c];
                Npixels[r][c] = (short)(255-num);
            }
        }
        img.setPixels(Npixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
