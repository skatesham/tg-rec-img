/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

/**
 *
 * @author shan
 */
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame {

    public LineChart_AWT(String applicationTitle, JFreeChart chart) {
        super(applicationTitle);

        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        chartPanel.setPreferredSize(new java.awt.Dimension(1024, 600));
        setContentPane(chartPanel);
    }
    
}
