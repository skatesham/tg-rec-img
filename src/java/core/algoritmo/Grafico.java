/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

import api.modelo.Resultado;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author shan
 */
public class Grafico {

    private JFreeChart lineChart;

    public Grafico(List<Resultado> resultados, Resultado resultado, char tipo) {

        String chartTitle = "Correlação em Váriação de Delta " + tipo;
        // DataSET
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = 0;
        if (tipo == 'X') {
            String line = "Delta Y = " + resultado.getY();
            for (Resultado r : resultados) {
                if (r.getY() == resultado.getY()) {
                    int res = (int) (r.getResultado() * 100);
                    dataset.addValue(res, line, count + "");
                    count++;
                }
            }
        } else if (tipo == 'Y') {
            String line = "Delta X = " + resultado.getX();
            for (Resultado r : resultados) {
                if (r.getX() == resultado.getX()) {
                    int res = (int) (r.getResultado() * 100);
                    dataset.addValue(res, line, count + "");
                    count++;
                }
            }
        }

        this.lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Delta " + tipo, "Correlação",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

    }
    
    public BufferedImage createImagem(int width, int height){
        return this.lineChart.createBufferedImage(width, height);
    }

    public void mostrarGrafico() {
        //FRAME
        String aplicationTitle = "Gráfico";
        LineChart_AWT chart = new LineChart_AWT(aplicationTitle, this.lineChart);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public void salvarImagem(String chartTitle) {
        int height = 600;
        int width = 1024;
        try {
            File file = new File(chartTitle + ".jpeg");
            ChartUtilities.saveChartAsJPEG(file, this.lineChart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
