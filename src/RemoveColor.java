import processing.core.PApplet;

import javax.swing.*;

public class RemoveColor implements PixelFilter {
    String color;

    public RemoveColor(){
        color = JOptionPane.showInputDialog("Choose a color to remove");
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        short[][] empty = new short[img.getHeight()][img.getWidth()];
        if(color.equals("red")) img.setColorChannels(empty, green, blue);
        if(color.equals("green")) img.setColorChannels(red, empty, blue);
        if(color.equals("blue")) img.setColorChannels(red, green, empty);

        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }


}
