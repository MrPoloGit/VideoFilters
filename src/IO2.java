import processing.core.PApplet;
import java.util.ArrayList;
import javax.swing.*;

public class IO2 implements PixelFilter, Clickable {
    private ArrayList<RGB> targetValues;
    private int thDiff;
    private ArrayList<BWCluster> clusters;
    private int k;

    public IO2(){
        clusters = new ArrayList<>();
        targetValues = new ArrayList<>();
        thDiff = 20;
        k = Integer.parseInt(JOptionPane.showInputDialog("The number of objects to detect"));
        for (int i = 0; i < k; i++) {
            BWCluster c = new BWCluster((int)(Math.random()*640), (int)(Math.random()*480));
            clusters.add(c);
        }
    }

    @Override
    public DImage processImage(DImage img) {
        DImage CTimg = ColorThresold(img);
        DImage blur = Blur(CTimg);
        DImage thimg = Thresold(blur);
        DImage Kmeans = BWKmeans(thimg);
        return thimg;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(0);
        for (int i = 0; i < clusters.size(); i++) {
            window.ellipse(clusters.get(i).getCenter().getY(), clusters.get(i).getCenter().getX(), 10, 10);
            clusters.get(i).clear();
        }
    }

    public DImage ColorThresold(DImage img){
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

    public DImage Blur(DImage img){
        short[][] pixel = img.getBWPixelGrid();
            short[][] bpixel = new short[pixel.length][pixel[0].length];

            for (int r = 2; r < pixel.length-2; r++) {
                for (int c = 2; c < pixel[r].length-2; c++) {
                    bpixel[r][c] = bluring(r, c, pixel);
                }
            }
            img.setPixels(bpixel);
            return img;
    }

    public short bluring(int row, int col, short[][] pixel) {
        int sum = 0;
            for (int r = -2; r < 2; r++) {
                for (int c = -2; c < 2; c++) {
                    sum += pixel[r + row][c + col];
                }
            }
            return (short) (sum / 25);
    }

    public DImage Thresold(DImage img){
        short[][] pixel = img.getBWPixelGrid();

        for (int r = 0; r < pixel.length; r++) {
            for (int c = 0; c < pixel[r].length; c++) {
                if(pixel[r][c] > 10) pixel[r][c] = 255;
            }
        }
        img.setPixels(pixel);
        return img;
    }

    public DImage BWKmeans(DImage img){
        short[][] pixel = img.getBWPixelGrid();
        for (int i = 0; i < 10; i++) {
            for (int r = 0; r < pixel.length; r++) {
                for (int c = 0; c < pixel[0].length; c++) {
                    if(pixel[r][c] == 255){
                        placeCenter(r,c);
                    }
                }
            }
        }
        if(clusters != null){
            for (int i = 0; i < clusters.size(); i++) {
                clusters.get(i).calcNewCenter();
            }
        }
        return img;
    }

    public void placeCenter(int x, int y){
        int mini = 0;
        int mx = clusters.get(0).getCenter().getX()-x;
        int my = clusters.get(0).getCenter().getY()-y;
        int mindist = mx*mx+my*my;
        int dist = 0;
        for (int i = 1; i < clusters.size(); i++) {
            dist = calcDistance(x,y,clusters.get(i).getCenter());
            if(dist < mindist){
                mini = i;
                mx = clusters.get(mini).getCenter().getX();
                my = clusters.get(mini).getCenter().getY();
                mindist = mx*mx+my*my;
            }
        }
        clusters.get(mini).add(x,y);
    }

    public int calcDistance(int x, int y, Point center){
        int dx = x - center.getX();
        int dy = y - center.getY();

        int dist = (dx*dx + dy*dy);
        return dist;
    }
}
