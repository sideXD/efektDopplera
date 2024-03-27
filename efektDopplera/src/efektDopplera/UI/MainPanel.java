package efektDopplera.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	int width, height;
	
	final public String sourcePath = "img/source.png";
	final ImageIcon sourceIcon = new ImageIcon(sourcePath);
	final public Image source = sourceIcon.getImage();
	
	final public String observerPath = "img/mic.png";
	final ImageIcon observerIcon = new ImageIcon(observerPath);
	final public Image observer = observerIcon.getImage();
	
	int imgWidth, imgHeight;
	
	public MainPanel() {
		this.setBackground(new Color(200, 210, 240));
		//narazie na sztywno, potencjalnie brane z aktualnych rozmiarów okna
		this.width = 900;
		this.height = 600;
		
		this.imgWidth = 150;
		this.imgHeight = 150;
		
		this.setPreferredSize(new Dimension(this.width, this.height)); 
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(source, 10, this.height - 160, this.imgWidth, this.imgHeight, this);
		g.drawImage(observer, 500, this.height - 160, this.imgWidth , this.imgHeight - 10, this);
		this.drawArrow(g);
		
	}
	
	void drawArrow(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        int xStart = 10;
        int yStart = this.height - 10;
        int xEnd = this.width - 30;
        int yEnd = this.height - 10;

        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(xStart, yStart, xEnd, yEnd);
        
        int []arrowXPoints = {this.width - 30, this.width - 5, this.width - 30};
        int []arrowYPoints = {this.height - 15, this.height-10, this.height - 5};
        
        
        g2d.drawPolygon(arrowXPoints, arrowYPoints, 3);
        
        Font axisFont = new Font("Arial", Font.BOLD, 25);
        g2d.setFont(axisFont);
        g2d.drawString("x", this.width - 48, this.height + 10);
        
	}

	void changeDimention(int width, int height) {
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.repaint();
	}
	

}
