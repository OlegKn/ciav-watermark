/*************************************************************************
 *  Compilation:  javac GrayScale.java
 *  Execution:    java GrayScale r1 g1 b1 r2 g2 b2
 *
 *  Library for dealing with monochrome luminance. 
 *  Uses the NTSC formula  Y = .299*r + .587*g + .114*b.
 *
 *  % java GrayScale 0 0 0 0 0 255
 *
 *************************************************************************/

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

public class GrayScale {

	static int[] Rgb;
	
	int h;
	int w;
	
    public static double lum(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return .299*r + .587*g + .114*b;
    }
 
    // test client
    public static void main(String[] args) {
       
    	DwtConv imag1= new DwtConv();
    	BufferedImage imac = new BufferedImage(64, 64, 1);
    	
    	imac = imag1.readImage("C:/baboon.jpg");
    	GrayScale gra =new GrayScale();
		gra.grayScale(imac);
		
		imag1.writeImage(imac, "C:/noua.jpg", "jpg");
    }

	public int[][] grayScale(BufferedImage imac) {
		
		h=imac.getHeight();
		w=imac.getWidth();
		Rgb = new int[w*h];
		
		int k=0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Rgb[k] = imac.getRGB(j, i);
				k++;
				}
			}
    	
		for (int m = 0; m < h*w; m++) {
			int  red = (Rgb[m] & 0x00ff0000) >> 16;
			int  green = (Rgb[m] & 0x0000ff00) >> 8;
			int  blue = Rgb[m] & 0x000000ff;
			Rgb[m]=(int) (Math.round(lum(new Color(red,green,blue))));
		}
		
		
		int[][] ImRgb=new int[h][w];
		
		int h=0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				ImRgb[i][j]=Rgb[h];
				h++;
				System.out.print(ImRgb[i][j]+" ");
			}
			System.out.println();
		}
		return ImRgb;
	}
}
