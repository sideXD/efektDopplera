package efektDopplera.UI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.io.File;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

import java.awt.image.BufferedImage;

public class ChartToPDF {

    JFreeChart chart1, chart2, chart3;
    XYSeries series1, series2, series3;
    
    MainMenu menu;
    MainPanel mainPanel;

    public ChartToPDF(ControlPanel controlPanel, MainMenu menu, MainPanel mainPanel) {
    	
    	this.menu = menu;
    	this.mainPanel = mainPanel;

        // tworzenie nowej klasy reprezentujacej dokument PDF
        PDFDocument pdfDoc = new PDFDocument();

        // opcjonalne ustawianie tytulu:
        pdfDoc.setTitle("DopplerPDF");

        // dodawanie nowej strony do klasy reprezentujacej PDF
        Page page1 = pdfDoc.createPage(new Rectangle(794, 1123));
        // 794px x 1123px odpowiada mniej wiecej A4 z rozdzielczoscia 96 dpi

        PDFGraphics2D g1 = page1.getGraphics2D();

        // Create dataset
        series1 = new XYSeries("fo(Vz)");
        int size = (int)mainPanel.getList(-1, -1);
        for (int i = 0; i < size; i++) {
        	float x = mainPanel.getList(2, i);
        	float fObs = mainPanel.getList(1, i);
            series1.add(x, fObs);
        }

        XYSeriesCollection dataset1 = new XYSeriesCollection(series1);

        // Create chart
        chart1 = createChart1(dataset1);
        JFreeChart jfreeChart1 = getChart1();
        BufferedImage chartImage1 = jfreeChart1.createBufferedImage(600, 400);


        // Narysowanie wykresu na drugiej stronie PDF
        g1.drawImage(chartImage1, 50, 50, null);

        Page page2 = pdfDoc.createPage(new Rectangle(794, 1123));

        PDFGraphics2D g2 = page2.getGraphics2D();

        // creaate dataset
        series2 = new XYSeries("fo(Vz)");
        double fz1 = controlPanel.fSource.getValue();
        for (int Vz1 = -50; Vz1 <= 50; Vz1++) {
            double fo1 = fz1 * (340.0 / (340.0 + Vz1));
            series2.add(Vz1, fo1);
        }

        XYSeriesCollection dataset2 = new XYSeriesCollection(series2);

        // Create chart
        chart2 = createChart2(dataset2);
        JFreeChart jfreeChart2 = getChart2();
        BufferedImage chartImage2 = jfreeChart2.createBufferedImage(600, 400);


        // Narysowanie wykresu na drugiej stronie PDF
        g2.drawImage(chartImage2, 50, 50, null);

        Page page3 = pdfDoc.createPage(new Rectangle(794, 1123));

        PDFGraphics2D g3 = page3.getGraphics2D();

        // Create dataset
        series3 = new XYSeries("fo(fz)");
        int Vz3 = controlPanel.vSource.getValue();
        for (int fz3 = 0; fz3 <= 500; fz3++) {
            double fo3 = fz3 * (340.0 / (340.0 + Vz3));
            series3.add(fz3, fo3);
        }

        XYSeriesCollection dataset3 = new XYSeriesCollection(series3);

        // Create chart
        chart3 = createChart3(dataset3);
        JFreeChart jfreeChart3 = getChart3();
        BufferedImage chartImage3 = jfreeChart3.createBufferedImage(600, 400);

        // Narysowanie wykresu na trzeciej stronie PDF
        g3.drawImage(chartImage3, 50, 50, null);

        // Tworzenie pustego pliku 
        File file = new File(menu.messages.getString("PDFfileName"));
        // zapis do pliku zawartosci dodanej do obiektu pdfDoc
        pdfDoc.writeToFile(file);

        //System.out.println("Dokument zapisano do pliku: " + file.getAbsolutePath());
        JOptionPane.showMessageDialog(null, menu.messages.getString("PDFfileSaveNotification")+ file.getAbsolutePath());
    }

    public JFreeChart createChart1(XYSeriesCollection dataset) {
        // Create the chart using the dataset
        return ChartFactory.createXYLineChart(
        	menu.messages.getString("chartTitle1"), // Title
            "x",     // X-axis label
            "fo",     // Y-axis label
            dataset   // Dataset
        );
    }

    public JFreeChart getChart1() {
        return chart1;
    }
    
    public JFreeChart createChart2(XYSeriesCollection dataset) {
        // Create the chart using the dataset
        return ChartFactory.createXYLineChart(
        	menu.messages.getString("chartTitle2"), // Title
            "Vz",     // X-axis label
            "fo",     // Y-axis label
            dataset   // Dataset
        );
    }

    public JFreeChart getChart2() {
        return chart2;
    }

    public JFreeChart createChart3(XYSeriesCollection dataset) {
        // Create the chart using the dataset
        return ChartFactory.createXYLineChart(
        	menu.messages.getString("chartTitle3"), // Title
            "fz",     // X-axis label
            "fo",     // Y-axis label
            dataset   // Dataset
        );
    }

    public JFreeChart getChart3() {
        return chart3;
    }
}