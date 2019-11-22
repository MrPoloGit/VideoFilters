import processing.core.PApplet;

public class DarkenFilter implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[] bwpixels = img.getBWPixelArray();
        for (int i = 0; i < bwpixels.length; i++) {
            bwpixels[i] = (short) (bwpixels[i]/2);
        }
        img.setPixels(bwpixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}

