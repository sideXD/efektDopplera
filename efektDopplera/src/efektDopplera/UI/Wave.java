package efektDopplera.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Wave {
	double posX, posY;
	double width, height;
	double freq; //kazda fala ma swoja czestotliwosc
	
	MainWindow f;
	
	final int velocity = 340; //w metra na sekunde okolo 340 m/s 
	
	static int wavesNum = 0; //zlicznik indexow fal
	int index;
	Color color;
	
	public Wave() {
		this.width = 100;
		this.height = this.width;
		wavesNum+=1;
		index = wavesNum;
		
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
		g.drawOval((int)this.posX, (int)this.posY, (int)this.width, (int)this.height);
		
		//g.setColor(tmp);
	}
	
	public void setSourceFreq(double freq) {
		this.freq = freq;
		this.posX += this.posX;
		this.posY += this.posY;
	}
	
	public void updateWavePosition(double timeDelay) { //czas podany w sekundach
		//int currentWidth;
		//double timeConst = 1 / (this.freq * timeDelay);
		//oblicza aktualna pozycje 
		//currentWidth = (int) ( this.width + this.velocity * timeConst );  //poprawne?
		//System.out.println(timeConst * this.velocity);
		this.width += this.velocity * timeDelay;
		this.height += this.velocity * timeDelay;
		this.posX -= this.velocity * timeDelay * 0.5;
		this.posY -= this.velocity * timeDelay * 0.5;
	}
	public void shiftPositionTo(int x, int y) {
		this.posX = x;;
		this.posY = y;
	}
	public double getWidth() {
		return this.width;
	}
	public int getIndex() {
		return this.index;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return this.color;
	}

}
