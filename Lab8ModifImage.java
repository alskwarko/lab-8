import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lab8ModifImage {
	public Lab8ModifImage() {
		try {
			File ouptut;
			
			ouptut = new File("res/filtrRoberts1.jpg");
			ImageIO.write(filtrRobertsaPionowym("res/2.jpg"), "jpg", ouptut);
			ouptut = new File("res/filtrRoberts2.jpg");
			ImageIO.write(filtrRobertsaPoziomym("res/2.jpg"), "jpg", ouptut);
			
			ouptut = new File("res/filtrPrewitta1.jpg");
			ImageIO.write(filtrPrewittaPionowym("res/2.jpg"), "jpg", ouptut);
			ouptut = new File("res/filtrPrewitta2.jpg");
			ImageIO.write(filtrPrewittaPoziomym("res/2.jpg"), "jpg", ouptut);
			
			ouptut = new File("res/filtrSobela1.jpg");
			ImageIO.write(filtrSobelaPionowym("res/2.jpg"), "jpg", ouptut);
			ouptut = new File("res/filtrSobela2.jpg");
			ImageIO.write(filtrSobelaPoziomym("res/2.jpg"), "jpg", ouptut);
			
			
			ouptut = new File("res/filtrLaplaca.jpg");
			ImageIO.write(filtrLaplaca("res/2.jpg"), "jpg", ouptut);
			
			ouptut = new File("res/filtrMin.jpg");
			ImageIO.write(filtrMin("res/2.jpg"), "jpg", ouptut);
			
			ouptut = new File("res/filtrMax.jpg");
			ImageIO.write(filtrMax("res/2.jpg"), "jpg", ouptut);
			
			ouptut = new File("res/filtrMedian.jpg");
			ImageIO.write(filtrMedianowy("res/2.jpg"), "jpg", ouptut);
			
		} catch (Exception e) {}
	}
	
	//  filtr Robertsa1 pionowym
	public BufferedImage filtrRobertsaPionowym(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
		
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
		
		int[][] maska=new int[3][3];
		maska [0][0] = 0;
		maska [0][1] = 0;
		maska [0][2] = 0;
		maska [1][0] = 0;
		maska [1][1] = 1;
		maska [1][2] = -1;
		maska [2][0] = 0;
		maska [2][1] = 0;
		maska [2][2] = 0;

		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;

				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));

						int red = (int)(c.getRed());

						int green = (int)(c.getGreen());

						int blue = (int)(c.getBlue());

						pomoc_r += red*maska[k+1][l+1];

						pomoc_g += green*maska[k+1][l+1];

						pomoc_b += blue*maska[k+1][l+1];

					}
				}

				pomoc_r = (int) Math.abs(pomoc_r);

				pomoc_g = (int) Math.abs(pomoc_g);

				pomoc_b = (int) Math.abs(pomoc_b);
				
				pomoc_r = sprawdzenie(pomoc_r);
				pomoc_g = sprawdzenie(pomoc_g);
				pomoc_b = sprawdzenie(pomoc_b);
				
				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
		
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}
	
	//  filtr Robertsa2 poziomym
	public BufferedImage filtrRobertsaPoziomym(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
			
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
			
		int[][] maska=new int[3][3];
		maska [0][0] = 0;
		maska [0][1] = 0;
		maska [0][2] = 0;
			maska [1][0] = 0;
			maska [1][1] = 1;
			maska [1][2] = 0;
			maska [2][0] = 0;
			maska [2][1] = -1;
			maska [2][2] = 0;

			int pomoc_r, pomoc_g, pomoc_b;

			for(int i=1; i<height-1; i++){
				for(int j=1; j<width-1; j++){
					pomoc_r = 0;
					pomoc_g = 0;
					pomoc_b = 0;

					for(int k=-1; k<=1; k++){
						for(int l=-1; l<=1; l++){
							Color c = new Color(img.getRGB(j+k, i+l));

							int red = (int)(c.getRed());

							int green = (int)(c.getGreen());

							int blue = (int)(c.getBlue());

							pomoc_r += red*maska[k+1][l+1];

							pomoc_g += green*maska[k+1][l+1];

							pomoc_b += blue*maska[k+1][l+1];

						}
					}

					pomoc_r = (int) Math.abs(pomoc_r);

					pomoc_g = (int) Math.abs(pomoc_g);

					pomoc_b = (int) Math.abs(pomoc_b);
					
					pomoc_r = sprawdzenie(pomoc_r);
					pomoc_g = sprawdzenie(pomoc_g);
					pomoc_b = sprawdzenie(pomoc_b);
					
					R[j][i] = pomoc_r;
					G[j][i] = pomoc_g;
					B[j][i] = pomoc_b;
				}
			}
			for(int f=0; f<height; f++){
				 for(int t=0; t<width; t++){
					Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
					img.setRGB(t,f,newColor.getRGB());
				 }
			}
			return img;
		}

	//  filtr Prewitta1 pionowym
	public BufferedImage filtrPrewittaPionowym(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
			
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
				
		int[][] maska=new int[3][3];
		maska [0][0] = 1;
		maska [0][1] = 1;
		maska [0][2] = 1;
		maska [1][0] = 0;
		maska [1][1] = 0;
		maska [1][2] = 0;
		maska [2][0] = -1;
		maska [2][1] = -1;
		maska [2][2] = -1;

		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;

				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));

						int red = (int)(c.getRed());
						int green = (int)(c.getGreen());
						int blue = (int)(c.getBlue());

						pomoc_r += red*maska[k+1][l+1];

						pomoc_g += green*maska[k+1][l+1];

						pomoc_b += blue*maska[k+1][l+1];

					}
				}

				pomoc_r = (int) Math.abs(pomoc_r);

				pomoc_g = (int) Math.abs(pomoc_g);

				pomoc_b = (int) Math.abs(pomoc_b);
					
				pomoc_r = sprawdzenie(pomoc_r);
				pomoc_g = sprawdzenie(pomoc_g);
				pomoc_b = sprawdzenie(pomoc_b);
					
				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
		
			}
		}
			
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}
		
	//  filtr Prewitta2 poziomym
	public BufferedImage filtrPrewittaPoziomym(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
				
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
				
		int[][] maska=new int[3][3];
		maska [0][0] = 1;
		maska [0][1] = 0;
		maska [0][2] = -1;
		maska [1][0] = 1;
		maska [1][1] = 0;
		maska [1][2] = -1;
		maska [2][0] = 1;
		maska [2][1] = 0;
		maska [2][2] = -1;
		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;

				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));
						int red = (int)(c.getRed());
						int green = (int)(c.getGreen());
						int blue = (int)(c.getBlue());

						pomoc_r += red*maska[k+1][l+1];
						pomoc_g += green*maska[k+1][l+1];
						pomoc_b += blue*maska[k+1][l+1];

					}
				}

				pomoc_r = (int) Math.abs(pomoc_r);
				pomoc_g = (int) Math.abs(pomoc_g);
				pomoc_b = (int) Math.abs(pomoc_b);
						
				pomoc_r = sprawdzenie(pomoc_r);
				pomoc_g = sprawdzenie(pomoc_g);
				pomoc_b = sprawdzenie(pomoc_b);
					
				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
			
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}

	//  filtr Sobela1 pionowym
	public BufferedImage filtrSobelaPionowym(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
			
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
						
		int[][] maska=new int[3][3];
		maska [0][0] = 1;
		maska [0][1] = 2;
		maska [0][2] = 1;
		maska [1][0] = 0;
		maska [1][1] = 0;
		maska [1][2] = 0;
		maska [2][0] = -1;
		maska [2][1] = -2;
		maska [2][2] = -1;

		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;

				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));
						int red = (int)(c.getRed());
						int green = (int)(c.getGreen());
						int blue = (int)(c.getBlue());

						pomoc_r += red*maska[k+1][l+1];

						pomoc_g += green*maska[k+1][l+1];

						pomoc_b += blue*maska[k+1][l+1];

					}
				}

				pomoc_r = (int) Math.abs(pomoc_r);

				pomoc_g = (int) Math.abs(pomoc_g);

				pomoc_b = (int) Math.abs(pomoc_b);
							
				pomoc_r = sprawdzenie(pomoc_r);
				pomoc_g = sprawdzenie(pomoc_g);
				pomoc_b = sprawdzenie(pomoc_b);
							
				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
		
			}
		}
					
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}
				
	//  filtr Sobela2 poziomym
	public BufferedImage filtrSobelaPoziomym(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
						
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
						
		int[][] maska=new int[3][3];
		maska [0][0] = 1;
		maska [0][1] = 0;
		maska [0][2] = -1;
		maska [1][0] = 2;
		maska [1][1] = 0;
		maska [1][2] = -2;
		maska [2][0] = 1;
		maska [2][1] = 0;
		maska [2][2] = -1;

		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;

				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));
						int red = (int)(c.getRed());
						int green = (int)(c.getGreen());
						int blue = (int)(c.getBlue());

						pomoc_r += red*maska[k+1][l+1];
						pomoc_g += green*maska[k+1][l+1];
						pomoc_b += blue*maska[k+1][l+1];

					}
				}

				pomoc_r = (int) Math.abs(pomoc_r);
				pomoc_g = (int) Math.abs(pomoc_g);
				pomoc_b = (int) Math.abs(pomoc_b);
								
				pomoc_r = sprawdzenie(pomoc_r);
				pomoc_g = sprawdzenie(pomoc_g);
				pomoc_b = sprawdzenie(pomoc_b);
								
				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
							
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}
								
		
	//  filtr  Laplaca
	public BufferedImage filtrLaplaca(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
						
		int[][] maska=new int[3][3];
		maska [0][0] = 0;
		maska [0][1] = -1;
		maska [0][2] = 0;
		maska [1][0] = -1;
		maska [1][1] = 4;
		maska [1][2] = -1;
		maska [2][0] = 0;
		maska [2][1] = -1;
		maska [2][2] = 0;

		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;

				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));

						int red = (int)(c.getRed());

						int green = (int)(c.getGreen());

						int blue = (int)(c.getBlue());

						pomoc_r += red*maska[k+1][l+1];

						pomoc_g += green*maska[k+1][l+1];

						pomoc_b += blue*maska[k+1][l+1];

					}
				}
				pomoc_r = (int) Math.abs(pomoc_r);

				pomoc_g = (int) Math.abs(pomoc_g);

				pomoc_b = (int) Math.abs(pomoc_b);

				/*pomoc_r = sprawdzenie(pomoc_r);
				pomoc_g = sprawdzenie(pomoc_g);
				pomoc_b = sprawdzenie(pomoc_b);*/
								
				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
					
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}
	
//  filtr  min
	public BufferedImage filtrMin(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
						
		int[] maskaR=new int[9];
		int[] maskaG=new int[9];
		int[] maskaB=new int[9];
	
		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;
				
				int m=0;
						
				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));
						
						int red = (int)(c.getRed());

						int green = (int)(c.getGreen());

						int blue = (int)(c.getBlue());
						
						maskaR[m] = red;
						maskaG[m] = green;
						maskaB[m] = blue;
						m = m + 1;

					}
				}
				pomoc_r = min(maskaR);

				pomoc_g = min(maskaG);

				pomoc_b = min(maskaB);

				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
					
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}
	
//  filtr  max
	public BufferedImage filtrMax(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
						
		int[] maskaR=new int[9];
		int[] maskaG=new int[9];
		int[] maskaB=new int[9];
	
		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;
				
				int m=0;
						
				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));
						
						int red = (int)(c.getRed());

						int green = (int)(c.getGreen());

						int blue = (int)(c.getBlue());
						
						maskaR[m] = red;
						maskaG[m] = green;
						maskaB[m] = blue;
						m = m + 1;

					}
				}
				pomoc_r = max(maskaR);

				pomoc_g = max(maskaG);

				pomoc_b = max(maskaB);

				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
					
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}	
	
//  filtr  medianowy
	public BufferedImage filtrMedianowy(String strImg) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
					
		int[][] R = new int[width][height];
		int[][] G = new int[width][height];
		int[][] B = new int[width][height];
						
		int[] maskaR=new int[9];
		int[] maskaG=new int[9];
		int[] maskaB=new int[9];
	
		int pomoc_r, pomoc_g, pomoc_b;

		for(int i=1; i<height-1; i++){
			for(int j=1; j<width-1; j++){
				pomoc_r = 0;
				pomoc_g = 0;
				pomoc_b = 0;
				
				int m=0;
						
				for(int k=-1; k<=1; k++){
					for(int l=-1; l<=1; l++){
						Color c = new Color(img.getRGB(j+k, i+l));
						
						int red = (int)(c.getRed());

						int green = (int)(c.getGreen());

						int blue = (int)(c.getBlue());
						
						maskaR[m] = red;
						maskaG[m] = green;
						maskaB[m] = blue;
						m = m + 1;

					}
				}
				pomoc_r = median(maskaR);

				pomoc_g = median(maskaG);

				pomoc_b = median(maskaB);

				R[j][i] = pomoc_r;
				G[j][i] = pomoc_g;
				B[j][i] = pomoc_b;
					
			}
		}
		for(int f=0; f<height; f++){
			 for(int t=0; t<width; t++){
				Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
				img.setRGB(t,f,newColor.getRGB());
			 }
		}
		return img;
	}		
	
	int sprawdzenie(int x)
    {
        if (x > 255)
            return 255;
        else if (x < 0)
            return 0;
        else
        	return x;
    }
	
	//znalezienie maksymalnego
	public static int max(int[] array) {

        int maximum = array[0];

        for (int i = 0; i < array.length; i++)
            if (maximum < array[i]) maximum = array[i];
        
        return maximum;
    }
	
	// znalezienie minimalnego
	public static int min(int[] array) {

        int minimum = array[0];

        for (int i = 0; i < array.length; i++) {
             if (minimum > array[i]) minimum = array[i];
        }
        return minimum;
    }

	
	// znalezienie medianowy 
	public static int median(int[] array) {
		int min_val, tmp_val;
		
		for (int i = 0; i < array.length; i++) {
			min_val = array[i];
			for (int j = i+1; j < array.length; j++) {
				if (min_val > array[j]) {
					min_val = array[j];
					tmp_val = array[i];
					array[i] = array[j];
					array[j] = tmp_val;
				}
			}
		}
		
        return array[array.length / 2];
    }	
	public static void main(String[] args) {
		Lab8ModifImage obj = new Lab8ModifImage();
		
		// Definiowanie okna do wyswietlania obrazow
		int WIDTH = 1200, HEIGHT = 600;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 2 - WIDTH / 2;
		int y = screenSize.height / 2 - HEIGHT / 2;
		
		JFrame f=new JFrame("Image Processing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(x, y,WIDTH, HEIGHT);
		  
		Image image = new ImageIcon("res/2.jpg").getImage();
		Image image1 = new ImageIcon("res/filtrRoberts1.jpg").getImage();
		Image image2 = new ImageIcon("res/filtrRoberts2.jpg").getImage();
		Image image3 = new ImageIcon("res/filtrPrewitta1.jpg").getImage();
		Image image4 = new ImageIcon("res/filtrPrewitta2.jpg").getImage();
		Image image5 = new ImageIcon("res/filtrSobela1.jpg").getImage();
		Image image6 = new ImageIcon("res/filtrSobela2.jpg").getImage();
		Image image7 = new ImageIcon("res/filtrLaplaca.jpg").getImage();
		Image image8 = new ImageIcon("res/filtrMin.jpg").getImage();
		Image image9 = new ImageIcon("res/filtrMax.jpg").getImage();
		Image image10 = new ImageIcon("res/filtrMedian.jpg").getImage();
		  
		JLabel label1 = new JLabel(new ImageIcon(image));
	    label1.setBounds(0,0, 500,500);  
	    JLabel label2 = new JLabel(new ImageIcon(image1));
	    label2.setBounds(0,0, 500,500);
	    JLabel label3 = new JLabel(new ImageIcon(image2));
	    label3.setBounds(0,0, 500,500);
	    JLabel label4 = new JLabel(new ImageIcon(image3));
	    label4.setBounds(0,0, 500,500);  
	    JLabel label5 = new JLabel(new ImageIcon(image4));
	    label5.setBounds(0,0, 500,500);
	    JLabel label6 = new JLabel(new ImageIcon(image5));
	    label6.setBounds(0,0, 500,500);
	    JLabel label7 = new JLabel(new ImageIcon(image6));
	    label7.setBounds(0,0, 500,500);
	    JLabel label8 = new JLabel(new ImageIcon(image7));
	    label8.setBounds(0,0, 500,500);
	    JLabel label9 = new JLabel(new ImageIcon(image8));
	    label9.setBounds(0,0, 500,500);
	    JLabel label10 = new JLabel(new ImageIcon(image9));
	    label10.setBounds(0,0, 500,500);
	    JLabel label11 = new JLabel(new ImageIcon(image10));
	    label11.setBounds(0,0, 500,500);
	      
		JPanel panel1=new JPanel();  
	    panel1.setBounds(20,20,500,500); 
     
	    panel1.add(label1);
	  	      
	    JPanel panel2=new JPanel();  
	    panel2.setBounds(680,10,500,500);
	      
	    // Definiowanie przycisku1 do zmiany obrazow
		JButton b1=new JButton("filtr Robertsa 1");  
		b1.setBounds(525,15,150,50); 
		b1.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e){ 
				panel2.removeAll();
				panel2.add(label2); 
				f.repaint();
			} 
			
		 });

		  // Definiowanie przycisku2 do zmiany obrazow
		  JButton b2=new JButton("filtr Robertsa 2");  
		  b2.setBounds(525,70,150,50); 
		  b2.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label3); 
		          f.repaint();  
			  } 
		  });
		  
		// Definiowanie przycisku3 do zmiany obrazow
		  JButton b3=new JButton("filtr Prewitta 1");  
		  b3.setBounds(525,125,150,50); 
		  b3.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label4); 
		          f.repaint();  
			  } 
		  });
		 
		// Definiowanie przycisku4 do zmiany obrazow
		  JButton b4=new JButton("filtr Prewitta 2");  
		  b4.setBounds(525,180,150,50); 
		  b4.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label5); 
		          f.repaint();  
			  } 
		  });

		// Definiowanie przycisku5 do zmiany obrazow
		  JButton b5=new JButton("filtr Sobela 1");  
		  b5.setBounds(525,235,150,50); 
		  b5.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label6); 
		          f.repaint();  
			  } 
		  });
		  
		// Definiowanie przycisku6 do zmiany obrazow
		  JButton b6=new JButton("filtr Sobela 2");  
		  b6.setBounds(525,290,150,50); 
		  b6.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label7); 
		          f.repaint();  
			  } 
		  });
		  
		// Definiowanie przycisku7 do zmiany obrazow
		  JButton b7=new JButton("filtr Laplaca");  
		  b7.setBounds(525,345,150,50); 
		  b7.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label8); 
		          f.repaint();  
			  } 
		  });
		  
		// Definiowanie przycisku8 do zmiany obrazow
		  JButton b8=new JButton("filtr Min");  
		  b8.setBounds(525,400,150,50); 
		  b8.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label9); 
		          f.repaint();  
			  } 
		  });
		  
		// Definiowanie przycisku9 do zmiany obrazow
		  JButton b9=new JButton("filtr Max");  
		  b9.setBounds(525,455,150,50); 
		  b9.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label10); 
		          f.repaint();  
			  } 
		  });
		  
		// Definiowanie przycisku10 do zmiany obrazow
		  JButton b10=new JButton("filtr Medianowy");  
		  b10.setBounds(525,510,150,50); 
		  b10.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label11); 
		          f.repaint();  
			  } 
		  });
		  
		  f.add(panel1);
		  f.add(panel2);
		  f.add(b1);
		  f.add(b2);
		  f.add(b3);
		  f.add(b4);
		  f.add(b5);
		  f.add(b6);
		  f.add(b7);
		  f.add(b8);
		  f.add(b9);
		  f.add(b10);
	      f.setLayout(null);  
		  f.setVisible(true);

	}

}
