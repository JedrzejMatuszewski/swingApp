package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a horizontal bar
 * chart.
 *
 */
public class Chart extends JPanel {

	Float[][] data;
	Float[] data3;
	JFreeChart chart;
	DefaultCategoryDataset dataset;
	ChartPanel chartPanel;
	NumberAxis rangeAxis;

	public Chart() {

		data = new Float[][] { { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f },
				{ 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f }, { 0f },
				{ 0f } };

		dataset = new DefaultCategoryDataset();

		setValue();

		chart = createChart(dataset);


		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(460, 225));
		Border outer = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		Border inner = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createCompoundBorder(outer, inner));

		setLayout(new BorderLayout());
		add(chartPanel, BorderLayout.CENTER);

	}

	public CategoryDataset createDataset() {
		return DatasetUtilities.createCategoryDataset("Series ", "", data);
	}

	public JFreeChart createChart(CategoryDataset dataset) {

		chart = ChartFactory.createBarChart("", // chart title
				"", // domain axis label
				"", // range axis label
				dataset, // data
				PlotOrientation.HORIZONTAL, // orientation
				false, // include legend
				true, false);
		

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);


		rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setRange(0.0, 1);
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		return chart;

	}

	public void setData(float[] data2) {
		int len = data2.length;

		for (int i = 0; i < len; i++) {
			this.data[i][0] = data2[i];
		}

		setValue();
		setRange();

	}

	public void setValue() {
		for (int i = 0; i < 25; i++) {
			String id = String.valueOf(i + 1);
			dataset.setValue(data[i][0], id, "1-25");
		}
	}
	
	public float getMaxvalue()
	{
		float max = 0;
		float temp;
		for (int i = 0; i < 25; i++) {
			temp = data[i][0];
			if(temp>max)
				max=temp;
		}
		return max + 0.5f;
	}
	
	public void setRange()
	{
		rangeAxis.setRange(0.0, getMaxvalue());
	}
}
