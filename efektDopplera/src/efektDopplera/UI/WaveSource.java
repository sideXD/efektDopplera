package efektDopplera.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class WaveSource {

	private float posX;
	private int posY;
	
	public float vx;

	private int freq;
	private BufferedImage img;
	
	private int imgWidth, imgHeight; 
	
	private MainWindow f;
	
	public WaveSource() {
		this.posX = 0;
		this.posY = 0;
		
		//przykladowe wartosci - do dyskusji
		this.imgWidth = 100;
		this.imgHeight = 100;
		//Wczytanie defaultowej ikony zrodla
		readImage("img/speaker_icon.png");
		
	}
	public void readImage(String filePath) {
		try {
			img = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			String errorMess = "Problem z wczytanie grafiki źródła fali";
			JOptionPane.showMessageDialog(null, errorMess, "Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void setImage() {
		//TODO gdy beda gotowe dodatkowe zrdola dziwieku
	}
	
	public void draw(Graphics g) {
		
		g = (Graphics2D) g;
		g.drawImage(this.img, (int)this.posX,this.posY, this.imgWidth,this.imgHeight, null);
	}
	
	public void updatePosition() {
		this.posX += vx * this.f.getTimeDelaySecs();
	}
	
	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
		
	}
	
	public void setVelocity(int vx) {
		this.vx = vx;
	}
	
	public float getVelocity() {
		return this.vx;
	}
	
	public float getX() {
		return this.posX;
	}
	public int getY() {
		return this.posY;
	}
	
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public int getFreq() {
		return this.freq;
	}
	public void setFrame(MainWindow f) {
		this.f =f;
	}
	
}
