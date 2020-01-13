import processing.core.PApplet;

public class Exercise1 implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(255,0,0);
        window.ellipse(original.getWidth(),original.getHeight(),10,10);

        window.fill(0,255,0);
        window.ellipse(0,0,10,10);

        window.stroke(0,0,255);
        window.line(0,0,original.getWidth(),original.getHeight());
    }


}
