import processing.core.PApplet;

public class Negative implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[] Npixels = img.getBWPixelArray();

        for (int i = 0; i < Npixels.length; i++) {
            short num = Npixels[i];
            Npixels[i] = (short)(255-num);
        }
        img.setPixels(Npixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
