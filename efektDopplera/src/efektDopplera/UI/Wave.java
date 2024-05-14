package efektDopplera.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Wave {
	String test = "jakis test";
	int posX, posY;
	int width, height;
	double freq; //kazda fala ma swoja czestotliwosc
	
	MainWindow f;
	
	final int velocity = 340; //w metra na sekunde okolo 340 m/s 
	
	public Wave() {
		this.width = 100;
		this.height = this.width;
	}
	
	public void setX(int x) {
		this.posX = x;
	}
	public void setY(int y) {
		this.posY = y;
	}
	public void setFrame(MainWindow f) {
		this.f =f;
	}
	
	//public void update
	
	public void draw(Graphics g) {
		g = (Graphics2D) g;
		//Color tmp = g.getColor();
		//g.setColor(new Color(200,0,0));
		g.drawOval(this.posX, this.posY, this.width, this.height);
		//g.setColor(tmp);
	}
	
	public void setSourceFreq(double freq) {
		this.freq = freq;
	}
	
	public void updateWavePosition(double timeDelay) { //czas podany w sekundach
		int currentWidth;
		//double timeConst = 1 / (this.freq * timeDelay);
		//oblicza aktualna pozycje 
		//currentWidth = (int) ( this.width + this.velocity * timeConst );  //poprawne?
		//System.out.println(timeConst * this.velocity);
		this.width += this.velocity * timeDelay * this.freq;
		this.height += this.velocity * timeDelay * this.freq;
		this.posX -= this.velocity * timeDelay * 0.5;
		this.posY -= this.velocity * timeDelay * 0.5;
	}
	public void shiftPositionTo(int x, int y) {
		this.posX = x;;
		this.posY = y;
	}
	
}
