package efektDopplera.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Observer {

	private float freq;
	private int posX;
	final private int posY;
	private BufferedImage img;
	
	private int imgWidth = 0;
	private int imgHeight = 0;
	
	public Observer(int posX, int posY, int imgWidth, int imgHeight) {
		this.posX = posX;
		this.posY = posY;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		freq = 0;
		
		try {
			img = ImageIO.read(new File("img/microphone_icon.png"));
		} catch (IOException e) {
			String errorMess = "Problem z wczytanie grafiki obserwatora";
			JOptionPane.showMessageDialog(null, errorMess, "Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	public void setFreq(float freq) {
		this.freq = freq;
	}
	public float getFreq() {
		return this.freq;
	}
	public void draw(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, posX, posY, imgWidth, imgHeight, null);
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	

}
