import processing.core.PApplet;

public class IdentifyObject implements PixelFilter {

    private int threshold = 200;
    private int rth = 160;
    private int gth = 130;
    private int bth = 255;

    private double ratio = 0.3;

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        short[][] BWpixels = img.getBWPixelGrid();


        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[0].length; c++) {

//                int rg = red[r][c]/;
//                int gb = green[r][c]*green[r][c];
//                int blu = blue[r][c]*blue[r][c];
//
//                int re = red[r][c]*red[r][c];
//                int gre = green[r][c]*green[r][c];
//                int blu = blue[r][c]*blue[r][c];
//                if(red[r][c] > redth || green[r][c] > greenth || blue[r][c] > blueth){
//                    BWpixels[r][c] = 255;
//                }else{
//                    BWpixels[r][c] = 0;
//                }
            }
        }
        img.setPixels(BWpixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }


}
