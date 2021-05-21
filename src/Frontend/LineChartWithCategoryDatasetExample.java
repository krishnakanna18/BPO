package Frontend;
import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.*;



public class LineChartWithCategoryDatasetExample extends JFrame {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	enum Months{
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	};
	int year;
	int month;
	String[] months= {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
	public LineChartWithCategoryDatasetExample(int year,String month) {
        super("Debit and Credit Transactions vs Month");
        this.year=year;
//        for(int i=0; i<months.length; i++)
//        		if(month.equals(months[i]))
//        		{
//        			this.month=i+1;
//        			break;
//        		}
//        if(month==0) {
//        JPanel chartPanel = createChartPanel();
//        add(chartPanel, BorderLayout.CENTER);
//        setSize(640, 480);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        }
//        else {
//        	JPanel chartPanel = createChartPanelMonth();
//            add(chartPanel, BorderLayout.CENTER);
//            setSize(640, 480);
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setLocationRelativeTo(null);
//        }
    }
 
    private JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
    	 String chartTitle = "Totla Amount vs Month";
    	    String categoryAxisLabel = "Month";
    	    String valueAxisLabel = "Total Amount";
    	 
    	    CategoryDataset dataset = createDatasetMonth();
    	 
    	    JFreeChart chart = ChartFactory.createLineChart(chartTitle,
    	            categoryAxisLabel, valueAxisLabel, dataset);
    	 
    	    return new ChartPanel(chart);
    }
    
    private JPanel createChartPanelMonth() {
        // creates a line chart object
        // returns the chart panel
    	 String chartTitle = "Totla Amount vs Month";
    	    String categoryAxisLabel = "Month";
    	    String valueAxisLabel = "Total Amount";
    	 
    	    CategoryDataset dataset = createDatasetMonth();
    	 
    	    JFreeChart chart = ChartFactory.createLineChart(chartTitle,
    	            categoryAxisLabel, valueAxisLabel, dataset);
    	 
    	    return new ChartPanel(chart);
    }
 
    private CategoryDataset createDatasetMonth() {
        // creates chart dataset...
        // returns the dataset
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	    String series1 = "Debit";
    	    String series2 = "Credit";
    	    Admin ad=new Admin();
    	    ad.login("Mrudul", "rugged");
			
    	    Transactions t=new Transactions();
    	    Map<Integer,Double> debit=t.report("Debit", this.year,1); 
    	   for(int i=1; i<=31; i++)
    		   if(debit.containsKey(i)==true)
    			   	dataset.addValue(debit.get(i), series1, String.valueOf(i));
    		   else
    				dataset.addValue(0, series1, String.valueOf(i));
    	      
    	   Map<Integer,Double> credit=t.report("Credit", this.year,1); 
    	   for(int i=1; i<=31; i++)
    		   if(credit.containsKey(i)==true)
    			   	dataset.addValue(credit.get(i), series2, String.valueOf(i));
    		   else
    				dataset.addValue(0, series2, String.valueOf(i));
    	      
    	    
    	   
    	 
    	    

//    	 
    	    
    	    return dataset;
    }
 
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LineChartWithCategoryDatasetExample(2021,"JAN").setVisible(true);
            }
        });
    }
}
