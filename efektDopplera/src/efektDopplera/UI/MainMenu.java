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

import java.awt.Component;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.SwingUtilities;

public class MainMenu extends JMenuBar {
    private static final long serialVersionUID = 1L;
    ResourceBundle messages;
    JMenu file, chart, language, help;
    JMenuItem plItem, enItem, description, authors, save, close, drawChart;
	
	public MainMenu(Locale locale) {
		messages = ResourceBundle.getBundle("messages_pl", locale);
		
		file = new JMenu(messages.getString("menuFile"));
		chart = new JMenu(messages.getString("menuChart"));
		language = new JMenu(messages.getString("menuLanguage"));
        file.setName("File");
        chart.setName("Chart");
        language.setName("Language");
		
		plItem = new JMenuItem(messages.getString("menuLanguagePolish"));
		enItem = new JMenuItem(messages.getString("menuLanguageEnglish"));
		language.add(plItem);
		language.add(enItem);
        plItem.setName("LanguagePolish");
        enItem.setName("LanguageEnglish");
		
		ChangeIcon("img/flagapl.jpeg", language);
		
		plItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ChangeIcon("img/flagapl.jpeg", language);
		    }
		});
		
		enItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ChangeIcon("img/falgawb.jpeg", language);
		    }
		});
		
		help = new JMenu(messages.getString("menuHelp"));
		description = new JMenuItem(messages.getString("menuHelpDescription"));
		authors = new JMenuItem(messages.getString("menuHelpAuthors"));
		//JLabel helpLabel = new JLabel(helpText);
		//help.add(helpLabel);
		description.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        			JOptionPane.showMessageDialog(null, messages.getString("menuHelpDescriptionDialog"));
        	}
        	});
		authors.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        			JOptionPane.showMessageDialog(null, messages.getString("menuHelpAuthorsDialog"));
        	}
        	});
        help.add(description);
        help.add(authors);
        help.setName("Help");
        description.setName("HelpDescription");
        authors.setName("HelpAuthors");
		
		save = new JMenuItem(messages.getString("menuFileSave"));
		close = new JMenuItem(messages.getString("menuFileClose"));
		file.add(save);
		file.add(close);
        save.setName("FileSave");
        close.setName("FileClose");
		
		drawChart = new JMenuItem(messages.getString("menuChartDraw"));
		chart.add(drawChart);
        drawChart.setName("ChartDraw");
		
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
	
    public void updateMenu() {

        for (Component component : this.getComponents()) {
            //System.out.println(component.getName());
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                //System.out.println(menu.getName());
                menu.setText(messages.getString("menu" + menu.getName()));
                for (Component menuItemComponent : menu.getMenuComponents()) {
                    if (menuItemComponent instanceof JMenuItem) {
                        JMenuItem menuItem = (JMenuItem) menuItemComponent;
                        menuItem.setText(messages.getString("menu" + menuItem.getName()));
                    }
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

}
