package efektDopplera.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.InvalidAttributeValueException;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Component;
import java.awt.Container;

public class ControlPanel extends JPanel implements ActionListener, ChangeListener, Runnable{
	static final int VMINOR_SPACING = 5;
	static final int VMAJOR_SPACING = 10;
	static final int FMINOR_SPACING = 50;
	static final int FMAJOR_SPACING = 100;
	static final int INIT_Val = 0;
	
	final int WIDTH = 300;
	final int SCALE = 3; //okresla odstepy miedzy komponentami w panelach z suwakami
	final Color COLOR = new Color(200, 200,250);
	
	JSlider vSource, vObserver, fSource;
	JTextField xCords;
	JTextField yCords;
	JTextField fObserver;
	JButton pauseButton;
	
	boolean isRunning = false;
	
	
	MainWindow f;
	
	//przyjmoawne jako argument w konstruktorze - komunikacja
	WaveSource waveSource;
	MainPanel mainPanel;
	MainMenu menu;
	//
	
	//guziki do updateowania pozycji zrodla fali
	JButton okButton;
	
	JLabel vSourceLabel, positionLabel, xCordsLabel, yCordsLabel, fLabel, fObserverLabel;
	
	
	public ControlPanel(Locale locale, WaveSource waveSource, MainPanel mainPanel, MainMenu menu) {
//Przypisanie kontrtnych pol control panelu
		this.waveSource = waveSource;
		this.mainPanel = mainPanel;
		menu = new MainMenu(locale);
		
//ustawienia panelu kontrolnego
		this.setLayout(new GridLayout(5,1));
//predkosc zrodla
		
		JPanel sourcePanel = new JPanel();
		sourcePanel.setLayout(new GridLayout(SCALE,1));
		sourcePanel.setPreferredSize(new Dimension(this.WIDTH, 100));
		sourcePanel.setBackground(this.COLOR);
		
		vSourceLabel = new JLabel (menu.messages.getString("controlSourcevelocity"), SwingConstants.CENTER);
		Font sourceFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		vSourceLabel.setFont(sourceFont);
		vSourceLabel.setName("controlSourcevelocity");
		
		vSource = new JSlider(-50, 50, INIT_Val); //predkosc w m/s
		 
		vSource.addChangeListener(this);
		
		vSource.setMinorTickSpacing(VMINOR_SPACING);
		vSource.setMajorTickSpacing(VMAJOR_SPACING);
		vSource.setPaintLabels(true);
		vSource.setPaintTicks(true);
		
		sourcePanel.add(vSourceLabel, BorderLayout.NORTH);
		sourcePanel.add(vSource, BorderLayout.CENTER);
		
		
//predkosc obserwatora
		
		/*
		JPanel observerPanel = new JPanel();
		observerPanel.setLayout(new GridLayout(SCALE,1));
		observerPanel.setBackground(this.COLOR);
		
		JLabel vObserverLabel = new JLabel ("Prędkość obserwatora [m/s]: ", SwingConstants.CENTER);
		Font observerFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		vObserverLabel.setFont(observerFont);
		
		vObserver = new JSlider(-10, 10, INIT_Val); //prednosc w m/s
		vObserver.setMinorTickSpacing(VMINOR_SPACING);
		vObserver.setMajorTickSpacing(VMAJOR_SPACING);
		vObserver.setPaintLabels(true);
		vObserver.setPaintTicks(true);
		
		observerPanel.add(vObserverLabel, BorderLayout.PAGE_START);
		observerPanel.add(vObserver, BorderLayout.CENTER);
		*/
		
//pozycja zrodla
		
		JPanel positionPanel = new JPanel();
		positionPanel.setLayout(new GridLayout(SCALE,1));
		positionPanel.setBackground(this.COLOR);
		
		positionLabel = new JLabel(menu.messages.getString("controlSourceposition"), SwingConstants.CENTER);
		Font postionFont = new Font("Arial", Font.ROMAN_BASELINE, 16);
		positionLabel.setFont(postionFont);
		positionLabel.setName("controlSourceposition");
		
		//panel  z samymi wartosciami
			JPanel positionValPanel = new JPanel();
			positionValPanel.setLayout(new GridLayout(1,4));
			positionValPanel.setBackground(this.COLOR);
			
			Font coordinatesFont = new Font("Arial", Font.ROMAN_BASELINE, 20);
			
			xCordsLabel = new JLabel("x", SwingConstants.CENTER);
			xCordsLabel.setFont(coordinatesFont);
			/*
			NumberFormat numFormat = NumberFormat.getIntegerInstance();
			NumberFormatter numFormatter = new NumberFormatter(numFormat);
			JFormattedTextField xCords = new JFormattedTextField(numFormatter);
			
			xCords.setSize(new Dimension(100, xCords.getWidth()));
			*/
			this.xCords = new JTextField(String.valueOf(this.waveSource.getX()));
			xCords.setSize(new Dimension(100, xCords.getWidth()));
			
			
			yCordsLabel = new JLabel("y", SwingConstants.CENTER);
			yCordsLabel.setFont(coordinatesFont);
		
			this.yCords = new JTextField(String.valueOf(this.waveSource.getY()));
			yCords.setSize(new Dimension(100, yCords.getWidth()));

			positionValPanel.add(xCordsLabel);
			positionValPanel.add(xCords);
			positionValPanel.add(yCordsLabel);
			positionValPanel.add(yCords);
			

	//Panel z guzikiem zatwierdzajacym pozycje x, y
			JPanel guzikOkPanel = new JPanel();
			guzikOkPanel.setLayout(new BorderLayout());
			guzikOkPanel.setBackground(this.COLOR);
			this.okButton = new JButton();
			okButton.setText(menu.messages.getString("controlConfirm"));
			
			this.okButton.addActionListener(this);
			
			Font okFont = new Font("Arrial", Font.ROMAN_BASELINE, 16);
			okButton.setFont(okFont);
			okButton.setName("controlConfirm");
			
			okButton.setPreferredSize(new Dimension(this.WIDTH, 40));
			guzikOkPanel.add(okButton, BorderLayout.CENTER);
			
			
		positionPanel.add(positionLabel);
		positionPanel.add(positionValPanel);
		positionPanel.add(guzikOkPanel);
	
		
//czestotliwosc zrodla
		JPanel freqPanel = new JPanel();
		freqPanel.setLayout(new GridLayout(SCALE,1));
		freqPanel.setBackground(this.COLOR);
		
		fLabel = new JLabel (menu.messages.getString("controlSourcefrequency"), SwingConstants.CENTER);
		Font freqFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		fLabel.setFont(freqFont);
		fLabel.setName("controlSourcefrequency");
		
		fSource = new JSlider(0, 500, INIT_Val); //czestotliwosc w Hz
		
		fSource.addChangeListener(this);
		
		fSource.setMinorTickSpacing(FMINOR_SPACING);
		fSource.setMajorTickSpacing(FMAJOR_SPACING);
		fSource.setPaintLabels(true);
		fSource.setPaintTicks(true);
		
		freqPanel.add(fLabel, BorderLayout.PAGE_START);
		freqPanel.add(fSource, BorderLayout.CENTER);
		
//dane wyjsciowe
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2,1));
		textPanel.setBackground(this.COLOR);
		
		textPanel.setMinimumSize(new Dimension(100, 100));
		fObserverLabel = new JLabel(menu.messages.getString("controlObservedfrequency"), SwingConstants.CENTER);
		Font fObserverLabelFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		fObserverLabel.setFont(fObserverLabelFont);
		fObserverLabel.setName("controlObservedfrequency");
		
		fObserver = new JTextField("0 Hz");
		fObserver.setEditable(false);
		Font fObserverFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		fObserver.setFont(fObserverFont);
		
		textPanel.add(fObserverLabel);
		textPanel.add(fObserver);
		
//start/stop
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(this.COLOR);
		
		pauseButton = new JButton("Start");
		pauseButton.setBackground(Color.GREEN);
		pauseButton.setFocusable(true);
		pauseButton.setOpaque(true);
		
		pauseButton.addActionListener(this);
		
		Font buttonFont = new Font("Arial", Font.BOLD, 32);
		pauseButton.setFont(buttonFont);
		pauseButton.setPreferredSize(new Dimension(100, 30));
		pauseButton.setBounds((int) (this.getLocation().x + this.WIDTH* 0.15), (int) ((this.getLocation().y + 30) * 0.5), 200, 100);
		buttonPanel.add(pauseButton);

//dodanie poszczegolnych komponentow do panelu kontrolnego
		
		this.add(sourcePanel);
		//this.add(observerPanel);
		this.add(positionPanel);
		this.add(freqPanel);
		this.add(textPanel);
		this.add(buttonPanel);
	}
	
	public void updateControlPanel(MainMenu menu) {
	    for (Component componentPanel : this.getComponents()) {
	        if (componentPanel instanceof JPanel) {
	            JPanel panel = (JPanel) componentPanel;
	            for (Component component : panel.getComponents()) {
	    	        if (component instanceof JLabel) {
	    	            JLabel label = (JLabel) component;
	    	            //System.out.println("Aktualizacja etykiety: " + label.getName());
	    	            label.setText(menu.messages.getString(label.getName()));
	    	        }
	            }
	        }
	    }
	    okButton.setText(menu.messages.getString("controlConfirm"));
	    SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		if(e.getSource() == this.pauseButton) {
			if(pauseButton.getText() == "Start") {
				pauseButton.setText("Stop");
				this.isRunning = true;
				pauseButton.setBackground(Color.RED);
				System.out.println(this.isRunning);
				this.mainPanel.clearWaveArray();
				mainPanel.repaint();
				
				exec.execute(f.getMainPanel());
				exec.execute(f.getControlPanel());
				exec.shutdown();
			}
			else {
				pauseButton.setText("Start");
				pauseButton.setBackground(Color.GREEN);
				this.isRunning = false;
				System.out.println(this.isRunning);
			}
		}
		else if (e.getSource() == this.okButton) {
			try {
				int sourceXpos, sourceYpos;
				sourceXpos = Integer.valueOf(this.xCords.getText());
				sourceYpos = Integer.valueOf(this.yCords.getText());
				waveSource.setPosition(sourceXpos, sourceYpos);
				mainPanel.moveWaves();
				this.mainPanel.repaint();
			}
			catch(NumberFormatException ex) {
				this.xCords.setText(String.valueOf(waveSource.getX()));
				this.yCords.setText(String.valueOf(waveSource.getY()));
			}
		}
	}
	

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == this.vSource) {
			this.waveSource.setVelocity(this.vSource.getValue());
		}
		else if (e.getSource() == this.fSource) {
			this.waveSource.setFreq(this.fSource.getValue());
			//System.out.println(waveSource.getFreq());
		}
	}
	
	private void updateFreqDisplay() {
			this.mainPanel.calculateObserverFreq();
			float freq = this.mainPanel.getObserverFreq();
			//System.out.println(freq);
			this.fObserver.setText(String.valueOf(freq) + "Hz");
	}
	
	public void run() {
		while(isRunning) {
			this.updateFreqDisplay();
			//xCords.setText(String.valueOf(this.waveSource.getX()));
			//yCords.setText(String.valueOf(this.waveSource.getY()));
		}
	}
	public void setFrame(MainWindow f) {
		this.f =f;
	}
	
	public boolean isItRunning() {
		return this.isRunning;
	}
	
	public void runningOff() {
		this.isRunning = false;
	}

}
