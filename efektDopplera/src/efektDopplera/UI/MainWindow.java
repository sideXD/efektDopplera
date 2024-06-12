package efektDopplera.UI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindow extends JFrame implements ComponentListener{

	private static final long serialVersionUID = 1L;
	ControlPanel controlPanel;
	MainMenu mainMenu;
	MainPanel mainPanel;
	WaveSource waveSource;
	ChartToPDF chartToPDF;
	
	final private int timeDelay = 50; //w milisekundach !!!
	
	public MainWindow () throws HeadlessException{
		
		this.setLayout(new BorderLayout());
		this.setSize(1200, 700);
		//this.setTitle(mainMenu.messages.getString("window.name"));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addComponentListener(this);
		
		
		//cotrolPanel przyjmuje mainPanel i waveSource w kkostruktorze jako argumenty
		waveSource = new WaveSource(); 
		waveSource.setFrame(this);
		
		mainPanel = new MainPanel(waveSource);
		mainPanel.setFrame(this);
		this.add(mainPanel, BorderLayout.LINE_START);
		
		mainMenu = new MainMenu(Locale.getDefault());
		mainMenu.setFrame(this);
		this.add(mainMenu, BorderLayout.PAGE_START);
		
		controlPanel = new ControlPanel(Locale.getDefault(), waveSource, mainPanel, mainMenu);
		controlPanel.setFrame(this);
		this.add(controlPanel, BorderLayout.LINE_END);
		
		rename();
		
		mainMenu.plItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	mainMenu.ChangeIcon("img/flagapl.jpeg", mainMenu.language);
		    	mainMenu.messages = ResourceBundle.getBundle("messages_pl", Locale.getDefault());
		    	//System.out.println(mainMenu.messages.getString("menu.file"));
		    	rename();
		    	controlPanel.updateControlPanel(mainMenu);
		    	mainMenu.updateMenu();
		    }
		});
		
		mainMenu.enItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	mainMenu.ChangeIcon("img/falgawb.jpeg", mainMenu.language);
		    	mainMenu.messages = ResourceBundle.getBundle("messages_en", Locale.getDefault());
		    	//System.out.println(mainMenu.messages.getString("menu.file"));
		    	rename();
		    	controlPanel.updateControlPanel(mainMenu);
		    	mainMenu.updateMenu();
		    }
		});
		
		
		mainMenu.speakerItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	mainPanel.sourcePath="img/speaker_icon.png";
		    	waveSource.setImage("img/speaker_icon.png");
		    }
		});
		
		mainMenu.dinoItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	mainPanel.sourcePath="img/dino_icon.png";
		    	waveSource.setImage("img/dino_icon.png");
		    }
		});
		
		mainMenu.ambulanceItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	mainPanel.sourcePath="img/ambulance_icon.png";
		    	waveSource.setImage("img/ambulance_icon.png");
		    }
		});
		
		
		mainMenu.drawChart.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	chartToPDF = new ChartToPDF(controlPanel, mainMenu, mainPanel);
		    }
		});
		
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
	
	public void rename() {
		this.setTitle(mainMenu.messages.getString("windowName"));
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
