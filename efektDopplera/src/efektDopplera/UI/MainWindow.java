package efektDopplera.UI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame implements ComponentListener{

	private static final long serialVersionUID = 1L;
	ControlPanel controlPanel;
	MainMenu mainMenu;
	MainPanel mainPanel;
	WaveSource waveSource;
	
	final private int timeDelay = 50; //w milisekundach !!!
	
	public MainWindow () throws HeadlessException{
		this.setLayout(new BorderLayout());
		this.setSize(1200, 700);
		this.setTitle("Program");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addComponentListener(this);
		
		
		//cotrolPanel przyjmuje mainPanel i waveSource w kkostruktorze jako argumenty
		waveSource = new WaveSource(); 
		waveSource.setFrame(this);
		
		mainPanel = new MainPanel(waveSource);
		mainPanel.setFrame(this);
		this.add(mainPanel, BorderLayout.LINE_START);
		
		controlPanel = new ControlPanel(waveSource, mainPanel);
		controlPanel.setFrame(this);
		this.add(controlPanel, BorderLayout.LINE_END);
		
		mainMenu = new MainMenu();
		this.add(mainMenu, BorderLayout.PAGE_START);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				controlPanel.runningOff();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public MainPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public ControlPanel getControlPanel() {
		return this.controlPanel;
	}

	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

				public void run() {
				MainWindow f = new MainWindow();
				
				f.setVisible(true);
		
				
			}
		});
	}
	
	public void componentResized(ComponentEvent e) {
		mainPanel.changeDimention(this.getWidth() - 300, this.getHeight() - 100);	
	}
	public void componentMoved(ComponentEvent e) {
	}
	
	public void componentShown(ComponentEvent e) {
		
	}
	public void componentHidden(ComponentEvent e) {
		
	}
	
	public int getTimeDelayMilis() {
		return this.timeDelay;
	}
	
	public double getTimeDelaySecs() {
		return this.timeDelay * 0.001;
	}


}
