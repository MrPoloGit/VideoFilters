import processing.core.PApplet;

import javax.swing.*;

public class Polychrome implements PixelFilter{
    private int numsplits;

    public Polychrome(){
        String splits = JOptionPane.showInputDialog("How many colors would you like?");
        numsplits = Integer.parseInt(splits);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] BWpixels = img.getBWPixelGrid();
        short[][] newPixels = new short[BWpixels.length][BWpixels[0].length];

        int[] splitloc = new int[numsplits];
        for (int i = 0; i < splitloc.length; i++) {
            int splitPoint = (255/(numsplits-1))*i;
            splitloc[i] = splitPoint;
        }

        for (int r = 0; r < BWpixels.length; r++) {
            for (int c = 0; c < BWpixels[r].length; c++) {
                for (int th = 0; th < splitloc.length-1; th++) {
                    if(BWpixels[r][c] > splitloc[th] && BWpixels[r][c] < splitloc[th+1]){
                        newPixels[r][c] = (short)(splitloc[th]);
                    }
                }
            }
        }
        img.setPixels(newPixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
