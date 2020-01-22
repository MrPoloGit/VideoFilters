import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColorThresold implements PixelFilter, Clickable {
    private ArrayList<RGB> targetValues;
    private int thDiff = 20;


    public ColorThresold(){
        targetValues = new ArrayList<>();
    }


    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        short[][] BWpixels = img.getBWPixelGrid();


        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[0].length; c++) {
                if(compareVals(red[r][c], green[r][c], blue[r][c])){
                    BWpixels[r][c] = 255;
                }else{
                    BWpixels[r][c] = 0;
                }
            }
        }
        img.setPixels(BWpixels);
        return img;
    }

    public boolean compareVals(int r, int g, int b){
        for (RGB p: targetValues) {
            if(Math.abs(p.getRed()-r) < 20 && Math.abs(p.getGreen()-g) < 20 && Math.abs(p.getBlue()-b) < 20){
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        RGB p = new RGB(red[mouseY][mouseX], green[mouseY][mouseX], blue[mouseY][mouseX]);
        targetValues.add(p);
    }

    @Override
    public void keyPressed(char key) {
        if(key == '+') thDiff += 5;
        if(key == '-') thDiff -= 5;
    }
}
