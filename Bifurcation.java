    //imports (don't do anything to these, it WILL break)
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.lang.Math;

public class Bifurcation {
    //Things you can change to get new cool graphs=============================================================================================================================================

    //colors
    private static final Color background = new Color(10, 5, 0);
    private static final Color color = new Color(0, 150, 255);
    //bounds
    private static double r1 = 0.7;
    private static double r2 = 2;
    private static double x1 = -r2*Math.PI/2;
    private static double x2 = r2*Math.PI/2;


    private static int width = 3000;
    private static int height = 1500; //this gets a 1:1 ratio: (int) Math.floor((x2-x1)/(r2-r1)*width);

    //The bifurcation diagram is created by iterating a function on this over and over again, then plotting the points after offset
    private static final double x0 = 0.1*Math.PI;

    //how many iterations will not be recorded (offset), and how many will (iterations)
    private static int offset = 6000;
    private static int iterations = 1000;

    //the function we'll be finding the bifurcation diagram for
    private static Double f(Double r, Double x) {
        return r*Math.sin(Math.PI*x);
    }







    //probably best not to mess with anything hereinafter===========================================================================================================================================
    public static BufferedImage generateGraph(double x1, double x2, int w, int h) {


        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // Set the background color
        g.setColor(background);
        g.fillRect(0, 0, w, h);

        //setting up x
        double x;
        // Add drawing logic based on the function and window dimensions
        for (double r = r1; r <= r2; r += (r2-r1)/w) { // Increment x with a small step (0.01) for precision
            x = x0;

            for (double i = 0; i < offset; i++) {
                x = f(r, x);
                // System.out.println(x);
            }
            //draw the points
            for (double i = 0; i < iterations; i++) {

                x = f(r, x);

                if (inBounds(x)) {
                    int pixelX = map(r, r1, r2, 0, w - 1);
                    int pixelY = map(x, x1, x2, h - 1, 0);
                    image.setRGB(pixelX, pixelY, color.getRGB());
                }

            }
        }

        return image;
    }

    private static int map(double value, double fromLow, double fromHigh, int toLow, int toHigh) {
        return (int) ((value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow);
    }

    private static boolean inBounds(double y) {
        return y <= x2 && y >= x1;
    }

    public static void main(String[] args) {

        BufferedImage graphImage = generateGraph(x1, x2, width, height);

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
            System.out.println("to be clear, this is an error bc of file stuffs");
        }
    }
}
/*
 * 
 * This is the 42-cycle of the logistic function
    //bounds
    private static double x1 = 0;
    private static double x2 = 1;
    private static double r1 = 3.702526;
    private static double r2 = 3.702528;

    private static int width = 5000;
    private static int height = 15000; //this gets a 1:1 ratio: (int) Math.floor((x2-x1)/(r2-r1)*width);

    //The bifurcation diagram is created by iterating a function on this over and over again, then plotting the points after offset
    private static final double x0 = 0.1*Math.PI;

    //how many iterations will not be recorded (offset), and how many will (iterations)
    private static int offset = 6000;
    private static int iterations = width;

    //the function we'll be finding the bifurcation diagram for
    private static Double f(Double r, Double x) {
        return r*x*(1-x);
    }
 * 
 * 
 * 
 * 
 * 
 * this is the logistic map but with the negative side as well
//bounds
    private static double x1 = -.5;
    private static double x2 = 1.5;
    private static double r1 = -2;
    private static double r2 = 4;

    private static int width = 3000;
    private static int height = 1500; //this gets a 1:1 ratio: (int) Math.floor((x2-x1)/(r2-r1)*width);

    //The bifurcation diagram is created by iterating a function on this over and over again, then plotting the points after offset
    private static final double x0 = 0.1*Math.PI;

    //how many iterations will not be recorded (offset), and how many will (iterations)
    private static int offset = 6000;
    private static int iterations = 1000;

    //the function we'll be finding the bifurcation diagram for
    private static Double f(Double r, Double x) {
        return r*x*(1-x);
    }
 * 
 * 
 * 
 * Sine map
    //colors
    private static final Color background = new Color(10, 5, 0);
    private static final Color color = new Color(0, 150, 255);
    //bounds
    private static double r1 = 0.7;
    private static double r2 = 2;
    private static double x1 = -r2*Math.PI/2;
    private static double x2 = r2*Math.PI/2;


    private static int width = 3000;
    private static int height = 1500; //this gets a 1:1 ratio: (int) Math.floor((x2-x1)/(r2-r1)*width);

    //The bifurcation diagram is created by iterating a function on this over and over again, then plotting the points after offset
    private static final double x0 = 0.1*Math.PI;

    //how many iterations will not be recorded (offset), and how many will (iterations)
    private static int offset = 6000;
    private static int iterations = 1000;

    //the function we'll be finding the bifurcation diagram for
    private static Double f(Double r, Double x) {
        return r*Math.sin(Math.PI*x);
    }
 */