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

import javax.swing.JFrame;

public class MainWindow extends JFrame implements ComponentListener{

	private static final long serialVersionUID = 1L;
	ControlPanel controlPanel;
	MainMenu mainMenu;
	MainPanel mainPanel;
	
	public MainWindow () throws HeadlessException{
		this.setLayout(new BorderLayout());
		this.setSize(1200, 700);
		this.setTitle("Program");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addComponentListener(this);
		
		controlPanel = new ControlPanel();
		this.add(controlPanel, BorderLayout.LINE_END);
		
		mainMenu = new MainMenu();
		this.add(mainMenu, BorderLayout.PAGE_START);
		
		mainPanel = new MainPanel();
		this.add(mainPanel, BorderLayout.LINE_START);
		
	}

	public static void main (String[] args) {
		MainWindow f = new MainWindow();
		f.setVisible(true);
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


}
