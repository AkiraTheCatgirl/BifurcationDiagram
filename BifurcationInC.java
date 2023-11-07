//imports (don't do anything to these, it WILL break)
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math.*;

public class BifurcationInC {
    //Things you can change to get new cool graphs=============================================================================================================================================

    //colors
    private static final Color background = new Color(10, 5, 0);
    private static final Color color = new Color(0, 150, 255);
    //bounds
    private static final double r1 = -2;
    private static final double r2 = 2;
    private static final double i1 = -2;
    private static final double i2 = 2;

    private static final int width = 300;
    private static final int height = 300; //this gets a 1:1 ratio: (int) Math.floor((x2-x1)/(r2-r1)*width);

    //how many times a point will be iterated to test for its divergence
    private static final int iterations = 6000;

    //the maximum a point can bounce around before returning to where it was to be included
    private static final int maxPeriodicity = 100;

    //the error in a point we allow (can be big, not really a problem---unless it is, it depends on the mapping)
    private static final double divergenceMargin;

    //the function we'll be finding the bifurcation diagram for
    private static ComplexNumber f(ComplexNumber z) {
        return z.multiply(z).sum(1);
    }






    
    //probably best not to mess with anything hereinafter===========================================================================================================================================
    private static ArrayList<ComplexNumber> orbit;
    
    private static boolean divergenceTest(ComplexNumber z) {
                //offset so the point settles
                for (int i = 0; i < iterations; i++) {
                    z = z.multiply(z);
                }
                //checks if it repeats ever
                orbit = new ArrayList<ComplexNumber>();
                for (int i = 0; i < maxPeriodicity; i++) {
                    z = f(z);
                    for (ComplexNumber s : orbit) {
                        if (s.isNear(z, divergenceMargin)) {
                            return false;
                        }
                    }
                    orbit.add(z);
                }
                return true;
    }

    public static BufferedImage generateGraph(double r1, double r2, double c1, double c2, int w, int h) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // Set the background color
        g.setColor(background);
        g.fillRect(0, 0, w, h);

        //setting up z
        ComplexNumber z = new ComplexNumber(0, 0);

        // Add drawing logic based on the function and window dimensions
        for (double r = r1; r <= r2; r += (r2-r1)/w) { // Increment r with a small step for precision
            
            

            for (double c = c1; c <= c2; c+= (c2-c1)/h) {
                //color pixel based on divergence TODO
            }
        }

        return image;
    }

    private static int map(double value, double fromLow, double fromHigh, int toLow, int toHigh) {
        return (int) ((value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow);
    }

    public static void main(String[] args) {

        BufferedImage graphImage = generateGraph(r1, r2, i1, i2, width, height);

        //chatgpt working its chatgpt magic
        File tempFile;
        try {
            tempFile = File.createTempFile("graph", ".png");
            ImageIO.write(graphImage, "png", tempFile);

            // Open the image using the default image viewer
            Desktop desktop = Desktop.getDesktop();
            desktop.open(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}