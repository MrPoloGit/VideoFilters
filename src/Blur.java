import processing.core.PApplet;

public class Blur implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] pixel = img.getBWPixelGrid();
        short[][] bpixel = new short[pixel.length][pixel[0].length];

        for (int r = 2; r < pixel.length-2; r++) {
            for (int c = 2; c < pixel[r].length-2; c++) {
                bpixel[r][c] = blur(r, c, pixel);
            }
        }
        img.setPixels(bpixel);
        return img;
    }

    private short blur(int row, int col, short[][] pixel){
        int sum = 0;
        for (int r = -2; r < 2; r++) {
            for (int c = -2; c < 2; c++) {
                sum += pixel[r+row][c+col];
            }
        }
        return (short)(sum/25);
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
