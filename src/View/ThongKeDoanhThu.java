package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeries;  

public class ThongKeDoanhThu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeDoanhThu frame = new ThongKeDoanhThu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public ThongKeDoanhThu() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Th\u1ED1ng k\u00EA doanh thu theo th\u00E1ng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 224));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("TH\u1ED0NG K\u00CA DOANH THU THEO TH\u00C1NG");
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 34));
		titleLabel.setBounds(110, 35, 600, 50);
		contentPane.add(titleLabel);
		
		JLabel monthFromLabel = new JLabel("T\u1EEA TH\u00C1NG:");
		monthFromLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		monthFromLabel.setBounds(40, 110, 100, 30);
		contentPane.add(monthFromLabel);
		
		String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		JComboBox monthFromComboBox = new JComboBox(months);
		monthFromComboBox.setForeground(new Color(0, 0, 0));
		monthFromComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		monthFromComboBox.setBackground(new Color(230, 230, 250));
		monthFromComboBox.setBounds(150, 110, 130, 30);
		
		
		JLabel yearFromLabel = new JLabel("N\u0102M:");
		yearFromLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		yearFromLabel.setBounds(360, 110, 50, 30);
				
		Statement stm = null;
		ArrayList<String> years = new ArrayList<String>();
		if(ConnectionDB.connect()) {
			String sql = "SELECT DISTINCT(YEAR(NgayLap)) AS YEAR FROM HoaDon";
			try {
				stm = ConnectionDB.conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet rs = null;
			try {
				rs = stm.executeQuery(sql);
				while (rs.next()) {
					String year = Integer.toString(rs.getInt("YEAR"));
					years.add(year);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] years_array = new String[years.size()];
		for(int i = 0; i < years.size(); i++)
			years_array[i] = years.get(i);
		
		JComboBox yearFromComboBox = new JComboBox(years_array);
		yearFromComboBox.setForeground(Color.BLACK);
		yearFromComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		yearFromComboBox.setBackground(new Color(230, 230, 250));
		yearFromComboBox.setBounds(419, 110, 130, 30);
		
		contentPane.add(yearFromComboBox);
		contentPane.add(yearFromLabel);
		contentPane.add(monthFromComboBox);
		contentPane.add(yearFromComboBox);
		
		JLabel monthToLabel = new JLabel("T\u1EEA TH\u00C1NG:");
		monthToLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		monthToLabel.setBounds(40, 162, 100, 30);
		contentPane.add(monthToLabel);
		
		JComboBox monthToComboBox = new JComboBox(months);
		monthToComboBox.setForeground(Color.BLACK);
		monthToComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		monthToComboBox.setBackground(new Color(230, 230, 250));
		monthToComboBox.setBounds(150, 162, 130, 30);
		contentPane.add(monthToComboBox);
		
		JLabel yearToLabel = new JLabel("N\u0102M:");
		yearToLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		yearToLabel.setBounds(360, 162, 50, 30);
		contentPane.add(yearToLabel);
		
		JComboBox yearToComboBox = new JComboBox(years_array);
		yearToComboBox.setForeground(Color.BLACK);
		yearToComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		yearToComboBox.setBackground(new Color(230, 230, 250));
		yearToComboBox.setBounds(419, 162, 130, 30);
		contentPane.add(yearToComboBox);
		
		JPanel statisticPanel = new JPanel();
		statisticPanel.setLocation(5, 205);
		statisticPanel.setPreferredSize(new Dimension(1180, 750));
		statisticPanel.setLayout(new GridLayout(2, 0));
		
		final JScrollPane statisticScroll = new JScrollPane(statisticPanel);
		statisticScroll.setLocation(5, 205);
		statisticScroll.setSize(1180, 495);
		statisticScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(statisticScroll);
		
		JButton viewButton = new JButton("Xem");
		viewButton.setBackground(new Color(152, 251, 152));
		viewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		viewButton.setBounds(630, 125, 100, 40);
		viewButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year_from = yearFromComboBox.getSelectedItem().toString();
				int year_from_int = Integer.parseInt(year_from);
				String year_to = yearToComboBox.getSelectedItem().toString();
				int year_to_int = Integer.parseInt(year_to);
				
				String month_from = monthFromComboBox.getSelectedItem().toString();
				int month_from_int = Integer.parseInt(month_from);
				String month_to = monthToComboBox.getSelectedItem().toString();
				int month_to_int = Integer.parseInt(month_to);
				
				if (year_from_int > year_to_int) {
					JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn năm bắt đầu trước năm kết thúc");
				} else if (year_from_int == year_to_int && month_from_int > month_to_int) {
						JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn tháng bắt đầu trước tháng kết thúc");
				}
				else {
					ArrayList<String> time_list = new ArrayList<String>();
					ArrayList<Long> money_list = new ArrayList<Long>();
					
					String sql_doanh_thu = "";
					Statement stm_doanh_thu = null;
					
					String start_date = month_from + "/1/" + year_from;
					
					if(month_to_int == 12) {
						year_to_int += 1;
						month_to_int = 1;
						year_to = Integer.toString(year_to_int);
						month_to = Integer.toString(month_to_int);
					} else {
						month_to_int += 1;
						month_to = Integer.toString(month_to_int);
					}
					
					String end_date = month_to + "/1/" + year_to;
					sql_doanh_thu = "SELECT YEAR(NgayLap) AS YEAR, MONTH(NgayLap) AS MONTH, COUNT(TongTien) AS DOANH_THU FROM HoaDon WHERE NgayLap >= '" + start_date + "' AND NgayLap < '" + end_date + "' GROUP BY YEAR(NgayLap), MONTH(NgayLap) ORDER BY YEAR(NgayLap), MONTH(NgayLap)";	
					ResultSet rs = null;
						try {
							stm_doanh_thu = ConnectionDB.conn.createStatement();
							rs = stm_doanh_thu.executeQuery(sql_doanh_thu);
							while (rs.next()) {
								int year = rs.getInt("YEAR");
								int month = rs.getInt("MONTH");
								long doanh_thu = rs.getLong("DOANH_THU");
								System.out.println(year + "\t" + month + "\t" + doanh_thu);
								time_list.add(month + "/" + year);
								money_list.add(doanh_thu);
							}
						} catch (SQLException error) {
							// TODO Auto-generated catch block
							error.printStackTrace();
						}
					
					statisticPanel.removeAll();
					statisticPanel.revalidate();
					statisticPanel.repaint();
					JFreeChart chart = ChartFactory.createLineChart(
							"Doanh thu theo tháng giai đoạn từ " + month_from + "/" + year_from + " đến " + month_to + "/" + year_to, 
							"Thời gian (tháng/năm)",
							"Doanh thu (VND)", 
							createDataset_DoanhThu(time_list, money_list),
							PlotOrientation.VERTICAL,
							true, true, false);
																
					ChartPanel CP = new ChartPanel(chart);
					statisticPanel.add(CP, BorderLayout.NORTH);
					statisticPanel.validate();
					
					JPanel piePanel = new JPanel();
					piePanel.setPreferredSize(new Dimension(1180, 750));
					piePanel.setLayout(new GridLayout(0, 2));
					statisticPanel.add(piePanel);
					
					ArrayList<String> product_best_seller = new ArrayList<String>();
					ArrayList<Integer> numbers = new ArrayList<Integer>();
					
					try {
						stm_doanh_thu = ConnectionDB.conn.createStatement();
						sql_doanh_thu = "SELECT TOP 3 TenSP AS SanPham, SUM(SoLuong) AS SoLuongBan FROM (CT_HoaDon C JOIN SanPham S ON C.MaSP = S.MaSP) JOIN HoaDon H On C.MaHD = H.MaHD WHERE H.NgayLap >= '"+ start_date + "' AND H.NgayLap < '" + end_date + "' GROUP BY C.MaSP, S.TenSP ORDER BY SUM(SoLuong) DESC";
						rs = stm_doanh_thu.executeQuery(sql_doanh_thu);
						while (rs.next()) {
							String product = rs.getString("SanPham");
							int number = rs.getInt("SoLuongBan");
							product_best_seller.add(product);
							numbers.add(number);
						}
					} catch (SQLException error) {
						// TODO Auto-generated catch block
						error.printStackTrace();
					}
					
					piePanel.removeAll();
					piePanel.revalidate();
					piePanel.repaint();
					
					JFreeChart bestSellers = ChartFactory.createPieChart(      
					         "TOP 3 SẢN PHẨM BÁN CHẠY NHẤT",   
					         createDataset(product_best_seller, numbers),    
					         true, 
					         true, 
					         false);
					
					ChartPanel bestSellerChart = new ChartPanel(bestSellers);
					piePanel.add(bestSellerChart, BorderLayout.NORTH);
					piePanel.validate();
					
					ArrayList<String> product_best_money = new ArrayList<String>();
					ArrayList<Long> best_money = new ArrayList<Long>();
					
					try {
						stm_doanh_thu = ConnectionDB.conn.createStatement();
						sql_doanh_thu = "SELECT TOP 3 TenSP AS SanPham, SUM(ThanhTien) AS Doanh_Thu FROM (CT_HoaDon C JOIN SanPham S ON C.MaSP = S.MaSP) JOIN HoaDon H On C.MaHD = H.MaHD WHERE H.NgayLap >= '"+ start_date + "' AND H.NgayLap < '" + end_date + "' GROUP BY C.MaSP, S.TenSP ORDER BY SUM(ThanhTien) DESC";
						rs = stm_doanh_thu.executeQuery(sql_doanh_thu);
						while (rs.next()) {
							String product = rs.getString("SanPham");
							long number = rs.getLong("Doanh_Thu");
							product_best_money.add(product);
							best_money.add(number);
						}
					} catch (SQLException error) {
						// TODO Auto-generated catch block
						error.printStackTrace();
					}
					
					JFreeChart bestMoney = ChartFactory.createPieChart(      
					         "TOP 3 SẢN PHẨM CÓ DOANH THU CAO NHẤT",   
					         createDataset_2(product_best_money, best_money),    
					         true, 
					         true, 
					         false);
					
					ChartPanel bestMoneyChart = new ChartPanel(bestMoney);
					piePanel.add(bestMoneyChart, BorderLayout.NORTH);
					piePanel.validate();
				}
				}

				
		});
		
		contentPane.add(viewButton);
					
		}
		
	   private static PieDataset createDataset(ArrayList<String> products, ArrayList<Integer> numbers) {
		      DefaultPieDataset dataset = new DefaultPieDataset();
		      
		      int n = products.size();
		      for(int i = 0; i < n; i++)
		    	  dataset.setValue(products.get(i), numbers.get(i));
		      
		      return dataset;         
		   }
	   
	   private static PieDataset createDataset_2(ArrayList<String> products, ArrayList<Long> numbers) {
		      DefaultPieDataset dataset = new DefaultPieDataset();
		      
		      int n = products.size();
		      for(int i = 0; i < n; i++)
		    	  dataset.setValue(products.get(i), numbers.get(i));
		      
		      return dataset;         
		   }

	
		private DefaultCategoryDataset createDataset_DoanhThu(ArrayList<String> time_list, ArrayList<Long> money_list) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		    int n = time_list.size();
		    for(int i = 0; i < n; i++) {
		    	dataset.addValue(money_list.get(i), "", time_list.get(i));
		    }
		    return dataset;
		}
}
