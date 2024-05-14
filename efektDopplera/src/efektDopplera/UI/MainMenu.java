package efektDopplera.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class MainMenu extends JMenuBar {

	
	public MainMenu() {
		JMenu file = new JMenu("Plik");
		JMenu chart = new JMenu("Wykres");
		JMenu language = new JMenu("Język");
		
		JMenuItem plItem = new JMenuItem("polski");
		JMenuItem wbItem = new JMenuItem("angielski");
		language.add(plItem);
		language.add(wbItem);
		
		ChangeIcon("img/flagapl.jpeg", language);
		
		plItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ChangeIcon("img/flagapl.jpeg", language);
		    }
		});
		
		wbItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ChangeIcon("img/falgawb.jpeg", language);
		    }
		});
		
		JMenu help = new JMenu("Pomoc");
		JMenuItem description = new JMenuItem("Działanie programu");
		//JLabel helpLabel = new JLabel(helpText);
		//help.add(helpLabel);
		description.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        			JOptionPane.showMessageDialog(null, "Krótki opis programu :)");
        	}
        	});
        help.add(description);
		
		JMenuItem save = new JMenuItem("Zapisz do pliku");
		JMenuItem close = new JMenuItem("Zamknij");
		file.add(save);
		file.add(close);
		
		JMenuItem drawChart = new JMenuItem("Rysuj wykres");
		chart.add(drawChart);
		
		this.add(file);
		this.add(chart);
		this.add(language);
		this.add(help);
		
        //help.addActionListener(new ActionListener() {
        //	@Override
        //	public void actionPerformed(ActionEvent e) {
        //			JOptionPane.showMessageDialog(null, helpText);
	     //   	}
	     //   });
        
        close.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        		
        	});
    
        
	}
	
	public void ChangeIcon(String imagename, JMenuItem button) {
        ImageIcon languageIcon = new ImageIcon(imagename);
        languageIcon.setImage(languageIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(languageIcon);
        button.revalidate();
        button.repaint();
	}

}
