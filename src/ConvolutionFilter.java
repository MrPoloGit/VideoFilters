import javafx.scene.effect.GaussianBlur;
import processing.core.PApplet;

import javax.swing.*;

public class ConvolutionFilter implements PixelFilter {
    private short[][] Kernal;
    private int Weight;

    public ConvolutionFilter(){
        Kernal = HorizontalLines();
        Weight = getKernalWeight(Kernal);
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] newimage = new short[pixels.length][pixels[0].length];

        for (int sr = 0; sr < pixels.length-Kernal.length; sr++) {
            for (int sc = 0; sc < pixels[sr].length-Kernal[0].length; sc++) {
                int output = sobelEdgeKernalUse(pixels,sr,sc);
                output = output/(1);
                if(output < 0){
                    output = 0;
                } else if(output > 255){
                    output = 255;
                }
                newimage[sr][sc] = (short)output;
            }
        }

        img.setPixels(newimage);
        return img;
    }

    public int kernalUse(short[][] pixels, int sr, int sc, short[][] kernal){
        int output = 0;
        for (int r = 0; r < kernal.length; r++) {
            for (int c = 0; c < kernal.length; c++) {
                int kernalVal = kernal[r][c];
                int pixelVal = pixels[r+sr][c+sc];

                output += kernalVal*pixelVal;
            }
        }
        return output;
    }

    public int sobelEdgeKernalUse(short[][] pixels, int sr, int sc){
        int output = 0;
        int x = 0;
        int y = 0;
        short[][] Xkernal = getXkernal();
        short[][] Ykernal = getYkernal();
        for (int r = 0; r < Xkernal.length; r++) {
            for (int c = 0; c < Xkernal.length; c++) {
                int XKernalVal = Xkernal[r][c];
                int YKernalVal = Ykernal[r][c];
                int pixelVal = pixels[r+sr][c+sc];

                x += XKernalVal*pixelVal;
                y += YKernalVal*pixelVal;
                output += Math.sqrt(x*x + y*y);
            }
        }
        return output;
    }

    private short[][] getXkernal() {
        short[][] arr = {{-1,-0,1},{-2,0,2},{-1,0,1}};
        return arr;
    }

    private short[][] getYkernal() {
        short[][] arr = {{1,2,1},{0,0,0},{-1,-2,-1}};
        return arr;
    }


    public int getKernalWeight(short[][] kernal){
        int weight = 0;
        for (int i = 0; i < kernal.length; i++) {
            for (int j = 0; j < kernal[i].length; j++) {
                weight += kernal[i][j];
            }
        }
        if(weight <= 0) weight = 1;
        return weight;
    }

    public short[][] standardNbyN(int n){
        short[][] arr = new short[n][n];
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                arr[r][c] = 1;
            }
        }
        return arr;
    }

    public short[][] GaussianBlurKernal3by3(){
        short[][] arr = {{1,2,3},{2,4,2},{1,2,1}};
        return arr;
    }

    public short[][] GaussianBlur7by7(){
        short[][] arr = {{0,0,0,5,0,0,0},{0,5,18,32,18,5,0},{0,18,64,100,64,18,0},{5,32,100,100,100,32,5},{0,18,64,100,64,18,0},{0,5,18,32,18,5,0},{0,0,0,5,0,0,0}};
        return arr;
    }

    public short[][] Sharpen3by3(){
        short[][] arr = {{0,-1,0},{-1,5,-1},{0,-1,0}};
        return arr;
    }

    public short[][] HorizontalLines(){
        short[][] arr = {{-1,-1,-1},{2,2,2},{-1,-1,-1}};
        return arr;
    }

    public short[][] VerticalLine(){
        short[][] arr = {{-1,2,-1},{-1,2,-1},{-1,2,-1}};
        return arr;
    }

    public short[][] Line45(){
        short[][] arr = {{2,-1,-1},{-1,2,-1},{-1,-1,2}};
        return arr;
    }

    public short[][] Line135(){
        short[][] arr = {{-1,-1,2},{-1,2,-1},{2,-1,-1}};
        return arr;
    }

    public short[][] PrewittEdgeDetection(){
        short[][] arr = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
        return arr;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
