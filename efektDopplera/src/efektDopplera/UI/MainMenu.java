package efektDopplera.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainMenu extends JMenuBar {

	String helpText= "Krótka informacja o działaniu programu";
	
	public MainMenu() {
		JMenu file = new JMenu("Plik");
		JMenu soundSource = new JMenu("źródła dźwięku");
		JMenu language = new JMenu("Język");
		
		ImageIcon languageIcon = new ImageIcon("img/flagapl.jpeg");
		language.setIcon(languageIcon);
		languageIcon.setImage(languageIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		
		
		JMenu help = new JMenu("Pomoc");
		JLabel helpLabel = new JLabel(helpText);
		help.add(helpLabel);
		
		JMenuItem save = new JMenuItem("Zapisz do pliku");
		JMenuItem close = new JMenuItem("Zamknij");
		file.add(save);
		file.add(close);
		
		JMenuItem ambulance = new JMenuItem("Ambulans");
		JMenuItem scream = new JMenuItem("Krzyk człowieka");
		JMenuItem trumpet = new JMenuItem("Trąbka");
		JMenuItem dinosaur = new JMenuItem("Dinozaur");
		soundSource.add(ambulance);
		soundSource.add(scream);
		soundSource.add(trumpet);
		soundSource.add(dinosaur);
		
		this.add(file);
		this.add(soundSource);
		this.add(language);
		this.add(help);
		
        help.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        			JOptionPane.showMessageDialog(null, helpText);
	        	}
	        });
	}

}
