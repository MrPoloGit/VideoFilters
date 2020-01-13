import processing.core.PApplet;

public class Exercise4 implements PixelFilter {

    DImage previousImg;

    public Exercise4(){
        previousImg = null;
    }
    @Override
    public DImage processImage(DImage img) {


        if(previousImg == null){
            previousImg = new DImage(img);
            return img;
        }
        short[][] current = img.getBWPixelGrid();
        short[][] previous = previousImg.getBWPixelGrid();

        for (int r = 0; r < current.length; r++) {
            for (int c = 0; c < current[r].length; c++) {
                int val = Math.abs(current[r][c]-previous[r][c]);
                if(val > 5){
                    current[r][c] = 0;
                }else{
                    current[r][c] = 255;
                }
            }
        }
        previousImg = new DImage(img);
        img.setPixels(current);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
