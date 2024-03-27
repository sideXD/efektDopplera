package efektDopplera.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ControlPanel extends JPanel implements ActionListener{
	static final int VMINOR_SPACING = 1;
	static final int VMAJOR_SPACING = 5;
	static final int FMINOR_SPACING = 5;
	static final int FMAJOR_SPACING = 10;
	static final int INIT_Val = 0;
	
	final int WIDTH = 300;
	final int SCALE = 3; //okresla odstepy miedzy komponentami w panelach z suwakami
	final Color COLOR = new Color(200, 200,250);
	
	JSlider vSource, vObserver, fSource;
	JTextField fObserver;
	JButton pauseButton;
	
	public ControlPanel() {
//ustawienia panelu kontrolnego
		this.setLayout(new GridLayout(5,1));
		
//predkosc zrodla
		JPanel sourcePanel = new JPanel();
		sourcePanel.setLayout(new GridLayout(SCALE,1));
		sourcePanel.setPreferredSize(new Dimension(this.WIDTH, 100));
		sourcePanel.setBackground(this.COLOR);
		
		JLabel vSourceLabel = new JLabel ("Prędkość źródła [m/s]: ", SwingConstants.CENTER);
		Font sourceFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		vSourceLabel.setFont(sourceFont);
		
		vSource = new JSlider(-10, 10, INIT_Val); //predkosc w m/s
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
		
		JLabel positionLabel = new JLabel("Początkowa pozycja źródła", SwingConstants.CENTER);
		Font postionFont = new Font("Arial", Font.ROMAN_BASELINE, 16);
		positionLabel.setFont(postionFont);
		
		//panel  z samymi wartosciami
			JPanel positionValPanel = new JPanel();
			positionValPanel.setLayout(new GridLayout(1,4));
			positionValPanel.setBackground(this.COLOR);
			
			Font coordinatesFont = new Font("Arial", Font.ROMAN_BASELINE, 20);
			
			JLabel xCordsLabel = new JLabel("x", SwingConstants.CENTER);
			xCordsLabel.setFont(coordinatesFont);
			
			JTextField xCords = new JTextField();
			xCords.setSize(new Dimension(100, xCords.getWidth()));
			
			JLabel yCordsLabel = new JLabel("y", SwingConstants.CENTER);
			yCordsLabel.setFont(coordinatesFont);
		
			JTextField yCords = new JTextField();
			yCords.setSize(new Dimension(100, yCords.getWidth()));
			
			positionValPanel.add(xCordsLabel);
			positionValPanel.add(xCords);
			positionValPanel.add(yCordsLabel);
			positionValPanel.add(yCords);
			
			
		positionPanel.add(positionLabel);
		positionPanel.add(positionValPanel);
		
		
		
		
//czestotliwosc obserwwatora
		JPanel freqPanel = new JPanel();
		freqPanel.setLayout(new GridLayout(SCALE,1));
		freqPanel.setBackground(this.COLOR);
		
		JLabel fLabel = new JLabel ("Częstotliwość źródła [Hz]: ", SwingConstants.CENTER);
		Font freqFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		fLabel.setFont(freqFont);
		
		fSource = new JSlider(0, 100, INIT_Val); //czestotliwosc w Hz
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
		JLabel fObserverLabel = new JLabel("Częstotliwość obserwowana: ", SwingConstants.CENTER);
		Font fObserverLabelFont = new Font ("Arrial", Font.ROMAN_BASELINE, 16);
		fObserverLabel.setFont(fObserverLabelFont);
		
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
	
	public void actionPerformed(ActionEvent e) {
		if(pauseButton.getText() == "Start") {
			pauseButton.setText("Stop");
			pauseButton.setBackground(Color.RED);
		}
		else {
			pauseButton.setText("Start");
			pauseButton.setBackground(Color.GREEN);
		}
	}

}
