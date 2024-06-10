package efektDopplera.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable {

	int width, height;
	
	final public String sourcePath = "img/speaker_icon.png";
	final ImageIcon sourceIcon = new ImageIcon(sourcePath);
	final public Image source = sourceIcon.getImage();
	
	final public String observerPath = "img/microphone_icon.png";
	final ImageIcon observerIcon = new ImageIcon(observerPath);
	//final public Image observer = observerIcon.getImage();
	
	int imgWidth, imgHeight;
	
	//float observerFrequency;
	
	WaveSource waveSource;
	Observer observer; 
	
	//lista narysowanych okregow

	ArrayList<Wave> waves = new ArrayList<Wave>();
	
	ArrayList<Float> freqSrcList = new ArrayList<Float>();
	ArrayList<Float> freqRecList = new ArrayList<Float>();
	ArrayList<Float> xPosList = new ArrayList<Float>();
	int yPosToFile = -1;
	ArrayList<Float> velList = new ArrayList<Float>();
	
	
	
	
	MainWindow f; //Przyjmuje ten parametr, zeby watek sie mial kiedy skonczyc i do odmierzania czasu dla fali 
	
	public MainPanel(WaveSource waveSource) {
		this.setBackground(new Color(200, 210, 240));
		//narazie na sztywno, potencjalnie brane z aktualnych rozmiarów okna
		
		this.width = 900;
		this.height = 600;
		
		this.imgWidth = 150;
		this.imgHeight = 150;
		
		
		this.waveSource = waveSource;
		//this.observer = new Observer((this.width - this.imgWidth)/2, this.height - 160, this.imgWidth , this.imgHeight - 10);
		this.observer = new Observer((this.width - this.imgWidth)/2, this.height - 160, 80, 130);
		
		this.setPreferredSize(new Dimension(this.width, this.height)); 
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		synchronized(waves) {
		this.waveSource.draw(g);
		this.observer.draw(g);
		this.drawWaves(g);
		//g.drawImage(source, 10, this.height - 160, this.imgWidth, this.imgHeight, this);
		//g.drawImage(observer, (this.width - this.imgWidth)/2, this.height - 160, this.imgWidth , this.imgHeight - 10, this);
		this.drawArrow(g);
		}
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
	
	
	public void setFrame(MainWindow f) {
		this.f =f;
	}
	
	private void drawWaves(Graphics g) {
		Wave wave;
		Color tmp = g.getColor();
		
		for (int i = 0; i < waves.size(); i++) {
			//System.out.println(waves.get(i));
			
//			if (i%2 == 0) {
//				g.setColor(this.getBackground());
//			}
//			else {
//				g.setColor(Color.RED);
//			}
			wave = (Wave) waves.get(i);
			g.setColor(wave.getColor());
			wave.draw(g);
		}
		g.setColor(tmp);
	}
	
	public void run() {
		System.out.println("rozpoczecie watku");
		
		//czyszczenie dotychczasowego zapisu list
		clearLists();
		this.yPosToFile = waveSource.getY();
		
		while(f.getControlPanel().isItRunning()) { 
			synchronized(waves) {
				
				//dodawanie w kazdej iteracji danych do list do zapisu
				freqSrcList.add((float) this.waveSource.getFreq());
				velList.add(this.waveSource.getVelocity());
				xPosList.add(this.waveSource.getX());
				freqRecList.add(this.observer.getFreq());

				waveSource.updatePosition();
				for(Wave w: this.waves) {
					w.updateWavePosition(f.getTimeDelayMilis());
				}
				//TODOWrzucic w inny watek lub jakos w ten sposob
				if(waves.size() > 0) {
					if((int)waves.get(0).getWidth() >= 2 *this.getWidth()) {
						waves.remove(0);
					}
				}
				
				//Stworzenie nowej fali rozchodzacej sie 
				//TODO przemyslec ideę tworzenia fali w danej chwili czasu
				if (this.waveSource.getFreq() != 0 ) {
					Wave circle = new Wave();
					if(circle.getIndex()%2 == 0) {
						//circle.setColor(this.getBackground());
						circle.setColor(Color.BLUE);
					}
					else {
						circle.setColor(Color.RED);
					}
					circle.setX((int)this.waveSource.getX());
					circle.setY(this.waveSource.getY());
					circle.setSourceFreq(this.waveSource.getFreq());
					waves.add(circle);
					
				}
			}
			
			try {
				Thread.sleep(f.getTimeDelayMilis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			f.getControlPanel().xCords.setText(String.valueOf((int)this.waveSource.getX()));
			//f.getControlPanel().yCords.setText(String.valueOf(this.waveSource.getY()));
			//yCords.setText(String.valueOf(this.waveSource.getY()));
			this.repaint();
		}
		System.out.println("Zakmnieto okno");
	}
	
	public void calculateObserverFreq() {
		float positionCoefficient = (float) ( (this.waveSource.getX() - this.observer.getX())  / Math.pow(Math.pow(this.waveSource.getX() - this.observer.getX(), 2) + Math.pow(this.waveSource.getY() - this.observer.getY(), 2), 0.5));
		this.observer.setFreq(waveSource.getFreq() * 340 / (340 + waveSource.getVelocity() * positionCoefficient)); //Czy to abs faktycznie tu powinno byc?
		
	}
	
	public float getObserverFreq() {
		return this.observer.getFreq();
	}
	
	public void clearWaveArray() {
		this.waves.clear();
	}

	public void createWaves(double n) {
		ArrayList<Wave> tmpList = new ArrayList<Wave>();
		//System.out.println(n);
		//System.out.println(Math.ceil(n));
		
//		int nInv2 = (int)(1/(n+1));

		if(n%2!=0) {
			n++;
		}
		int nInv = (int)(1/n);
		for(int i =1; i < Math.ceil(n+1) ; i++) {
			Wave circle = new Wave();
			if(circle.getIndex()%2 == 0) {
				//circle.setColor(this.getBackground());
				circle.setColor(Color.BLUE);
			}
			else {
				circle.setColor(Color.RED);
			}
			circle.setX((int)this.waveSource.getX());
			circle.setY(this.waveSource.getY());
			circle.setSourceFreq(this.waveSource.getFreq());
			
			try {
				Thread.sleep(f.getTimeDelayMilis()*nInv);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tmpList.add(circle);
			
			waves.add(circle);

			for(Wave wave: tmpList) {
				wave.updateWavePosition(f.getTimeDelaySecs()*nInv);
			}
			
		}
	}
	
	public void clearLists() {
		freqSrcList.removeAll(freqSrcList);
		freqRecList.removeAll(freqRecList);
		velList.removeAll(velList);
		xPosList.removeAll(xPosList);
	}
	
	public float getList(int list, int index) {
		switch(list) 
		{
			case -1:
			{
				return freqSrcList.size();
			}
			case 0:
			{
				return freqSrcList.get(index);
			}
			case 1:
			{
				return freqRecList.get(index);
			}
			case 2:
			{
				return xPosList.get(index);
			}
			case 3:
			{
				return velList.get(index);
			}
		}
		return (Float) null;

		
	}
}
