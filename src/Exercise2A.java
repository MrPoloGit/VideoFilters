import processing.core.PApplet;

public class Exercise2A implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        int w = original.getWidth()/3;

        for (int i = 0; i < 2; i++) {
            window.stroke(0,0,0);
            window.line(w,0,w,original.getHeight());
            w += w;
        }
        int h = original.getHeight()/3;
        for (int i = 0; i < 2; i++) {
            window.stroke(0,0,0);
            window.line(0,h,original.getWidth(),h);
            h += h;
        }
    }
}
