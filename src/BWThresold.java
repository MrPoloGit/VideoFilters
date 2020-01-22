import processing.core.PApplet;

public class BWThresold implements PixelFilter {

    short thresold = 10;
    @Override
    public DImage processImage(DImage img) {
        short[][] pixel = img.getBWPixelGrid();

        for (int r = 0; r < pixel.length; r++) {
            for (int c = 0; c < pixel[r].length; c++) {
                if(pixel[r][c] > 10) pixel[r][c] = 255;
            }
        }
        img.setPixels(pixel);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
