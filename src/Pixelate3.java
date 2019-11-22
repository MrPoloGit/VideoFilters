import processing.core.PApplet;

public class Pixelate3 implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        short[][] BWpixel = img.getBWPixelGrid();
        short[][] Pixelate = new short[BWpixel.length][BWpixel[0].length];

        for (int r = 1; r < BWpixel.length; r += 3) {
            for (int c = 1; c < BWpixel[r].length; c += 3) {
                short pix = BWpixel[r][c];
                Pixelate = pixelate(Pixelate, pix,r,c);
            }
        }
        img.setPixels(Pixelate);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }

    public short[][] pixelate(short[][] arr, short pix, int r, int c){
        for (int i = r-1; i < r+1; i++) {
            for (int j = c-1; j < c+1; j++) {
                arr[i][j] = pix;
            }
        }
        return arr;
    }
}
