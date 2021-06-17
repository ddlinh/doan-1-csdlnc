package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;

import Controller.APIHoaDon;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class DSHoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMHD;
	private JTextField txtMKH;
	private JTextField txtTien;
	private JTextField searchMaKH;
	private JTable table;
	private JScrollPane sp_table;
	private JDateChooser searchNgayBD;
	private JDateChooser txtNgayKT;
	private JTextField txtNgay;
	private JTextField ErrorMHD;
	private JTextField ErrorMKH;
	private JTextField ErrorTG;
	private JTextField ErrorTongTien;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DSHoaDon frame = new DSHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean check_form() {
		int count = 0;
		if(txtMKH.getText().length() <= 0) {
			ErrorMKH.setText("Ma KH rong");
		}
		else {
			if(!check_nb(txtMKH.getText())) {
				ErrorMKH.setText("Ma KH khong dung");
			}
			else {
				ErrorMKH.setText("");
				count += 1;
			}
		}
		
		if(txtTien.getText().length() <= 0) {
			ErrorTongTien.setText("Tong tien rong");
		}
		else {
			if(!check_nb(txtTien.getText())) {
				ErrorTongTien.setText("Chua ki tu");
			}
			else {
				ErrorTongTien.setText("");
				count += 1;
			}
		}
		
		if(txtNgay.getText().length() <= 0) {
			ErrorTG.setText("Ngay rong");
		}
		else {
			if(!check_date(txtNgay.getText())) {
				ErrorTG.setText("Sai (YYYY-MM-DD)");
			}
			else {
				ErrorTG.setText("");
				count += 1;
			}
		}
		
		if(count != 3) {
			return false;
		}
		return true;
		
		
		
	}
	
	public boolean check_nb(String nb) {
		
		try {
			Integer.parseInt(nb);
		}catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean check_date(String d) {
		
		String[] tmp = d.split("-");
		if(tmp.length != 3) {
			return false;
		}
		else {
			for(int i = 0; i < tmp.length; i++) {
				try{
	                Integer.parseInt(tmp[i]);
	            } catch (Exception e) {
	            	return false;
	            }
			}
		}
		
		return true;
	}
	
	public void LoadData() {
		table.removeAll();
		
		boolean check = false;
		
		int MaHD  = -1;
		if(searchMaKH.getText().length() > 0) {
			MaHD = Integer.parseInt(searchMaKH.getText());
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String NgayBD = "";
		String NgayKT = "";
		if(searchNgayBD.getDate() != null && txtNgayKT.getDate() != null) {
			NgayBD = dateFormat.format(searchNgayBD.getDate());
			NgayKT = dateFormat.format(txtNgayKT.getDate());
		}
		
		APIHoaDon query = new APIHoaDon();
		if(MaHD > 0 && NgayBD.length() > 0 && NgayKT.length() > 0) {
			query.TimKiemTheoMaKH_Ngay(MaHD, NgayBD, NgayKT);
			check = true;
		}
		else {
			if(MaHD > 0) {
				query.TimKiemTheoMaKH(MaHD);
				check = true;
			}
			else {
				if(NgayBD.length() > 0 && NgayKT.length() > 0) {
					query.TimKiemTheoNgay(NgayBD, NgayKT);
					check = true;
				}
				else {
					query.GetAll();
				}
			}
		}
		
		
		
		
		String[] title = {"MaHD","MaKH","NgayLap","TongTien"};
		
		if(check) {
			String[][] data = new String[query.list_result.size()][4];
			for(int i = 0; i < query.list_result.size(); i++) {
				data[i][0] = ""+query.list_result.get(i).MaHD;
				data[i][1] = ""+query.list_result.get(i).MaKH;
				data[i][2] = ""+query.list_result.get(i).NgayLap;
				data[i][3] = ""+query.list_result.get(i).TongTien;
				
				
			}
			
			
			
			
			DefaultTableModel model = new DefaultTableModel(data, title);
			table.setModel(model);
		}
		else {
			String[][] data = new String[20][4];
			for(int i = 0; i < 20; i++) {
				data[i][0] = ""+query.list_hd.get(i).MaHD;
				data[i][1] = ""+query.list_hd.get(i).MaKH;
				data[i][2] = ""+query.list_hd.get(i).NgayLap;
				data[i][3] = ""+query.list_hd.get(i).TongTien;
				
				
			}
			
			
			
			DefaultTableModel model = new DefaultTableModel(data, title);
			table.setModel(model);
		}
		
		
		
		
		searchMaKH.setText("");
		searchNgayBD.setDate(null);
		txtNgayKT.setDate(null);
		
		
	}

	/**
	 * Create the frame.
	 */
	public DSHoaDon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 829);
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh s\u00E1ch h\u00F3a \u0111\u01A1n");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(260, 10, 263, 39);
		contentPane.add(lblNewLabel);
		
		// Ma hoa don
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E3 h\u00F3a \u0111\u01A1n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(100, 59, 95, 39);
		contentPane.add(lblNewLabel_1);
		
		txtMHD = new JTextField();
		txtMHD.setBounds(205, 70, 96, 19);
		contentPane.add(txtMHD);
		txtMHD.setColumns(10);
		
		// Ma khach hang
		
		JLabel lblNewLabel_1_1 = new JLabel("M\u00E3 kh\u00E1ch h\u00E0ng");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(100, 122, 95, 39);
		contentPane.add(lblNewLabel_1_1);
		
		txtMKH = new JTextField();
		txtMKH.setColumns(10);
		txtMKH.setBounds(205, 133, 96, 19);
		contentPane.add(txtMKH);
		
		// Thoi gian
		
		JLabel lblNewLabel_1_2 = new JLabel("Th\u1EDDi gian");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(437, 59, 95, 39);
		contentPane.add(lblNewLabel_1_2);
		
		// Tong tien
		
		JLabel lblNewLabel_1_1_1 = new JLabel("T\u1ED5ng ti\u1EC1n");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(437, 122, 95, 39);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtTien = new JTextField();
		txtTien.setColumns(10);
		txtTien.setBounds(542, 133, 96, 19);
		contentPane.add(txtTien);
		
		JButton btnNewButton = new JButton("Th\u00EAm h\u00F3a \u0111\u01A1n");
		
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(137, 197, 116, 30);
		contentPane.add(btnNewButton);
		
		JButton btnChnhSa = new JButton("Ch\u1EC9nh s\u1EEDa");
		
		btnChnhSa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChnhSa.setForeground(Color.DARK_GRAY);
		btnChnhSa.setBackground(new Color(245, 222, 179));
		btnChnhSa.setBounds(313, 197, 116, 30);
		contentPane.add(btnChnhSa);
		
		JButton btnXa = new JButton("X\u00F3a");
		btnXa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXa.setForeground(Color.DARK_GRAY);
		btnXa.setBackground(new Color(250, 128, 114));
		btnXa.setBounds(496, 197, 116, 30);
		contentPane.add(btnXa);
		
		JButton btnLu = new JButton("L\u01B0u");
	
		btnLu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLu.setForeground(Color.DARK_GRAY);
		btnLu.setBackground(new Color(65, 105, 225));
		btnLu.setBounds(403, 237, 116, 30);
		contentPane.add(btnLu);
		
		JButton btnHyB = new JButton("H\u1EE7y b\u1ECF");
		
		btnHyB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHyB.setForeground(Color.DARK_GRAY);
		btnHyB.setBackground(new Color(255, 255, 0));
		btnHyB.setBounds(237, 237, 116, 30);
		contentPane.add(btnHyB);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("M\u00E3 kh\u00E1ch h\u00E0ng");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_2.setBounds(159, 277, 95, 39);
		contentPane.add(lblNewLabel_1_1_2);
		
		searchMaKH = new JTextField();
		searchMaKH.setColumns(10);
		searchMaKH.setBounds(257, 288, 96, 19);
		contentPane.add(searchMaKH);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("T\u1EEB ng\u00E0y");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(403, 277, 95, 39);
		contentPane.add(lblNewLabel_1_2_1);
		
		searchNgayBD = new JDateChooser();
		searchNgayBD.setBounds(496, 288, 96, 19);
		contentPane.add(searchNgayBD);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("T\u1EDBi ng\u00E0y");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_2.setBounds(403, 320, 95, 39);
		contentPane.add(lblNewLabel_1_2_2);
		
		txtNgayKT = new JDateChooser();
		txtNgayKT.setBounds(496, 331, 96, 19);
		contentPane.add(txtNgayKT);
		
		JButton btnHinTh = new JButton("T\u00ECm ki\u1EBFm");
		
		btnHinTh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHinTh.setForeground(Color.DARK_GRAY);
		btnHinTh.setBackground(new Color(0, 255, 127));
		btnHinTh.setBounds(611, 299, 116, 40);
		contentPane.add(btnHinTh);
		
		JButton btnThngKDoanh = new JButton("Th\u1ED1ng k\u00EA doanh thu");
		btnThngKDoanh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThngKDoanh.setForeground(new Color(250, 240, 230));
		btnThngKDoanh.setBackground(new Color(112, 128, 144));
		btnThngKDoanh.setBounds(554, 414, 148, 30);
		contentPane.add(btnThngKDoanh);
		
		
		// Load data
		
		
		String[] title = {"MaHD","MaKH","NgayLap","TongTien"};
		
		
		table = new JTable();
		APIHoaDon query = new APIHoaDon();
		query.GetAll();
		
		System.out.println("So luong:" + query.list_hd.size());
		
		
		
		
		String[][] data = new String[20][4];
		for(int i = 0; i < 20; i++) {
			data[i][0] = ""+query.list_hd.get(i).MaHD;
			data[i][1] = ""+query.list_hd.get(i).MaKH;
			data[i][2] = ""+query.list_hd.get(i).NgayLap;
			data[i][3] = ""+query.list_hd.get(i).TongTien;
			
			
		}
		
		
		
		DefaultTableModel model = new DefaultTableModel(data, title);
		table.setModel(model);
		table.setBounds(89, 454, 612, 295);
		sp_table = new JScrollPane(table);
		
		sp_table.setBounds(89, 454, 612, 295);
		contentPane.add(sp_table);
		
		txtNgay = new JTextField();
		txtNgay.setColumns(10);
		txtNgay.setBounds(542, 70, 96, 19);
		contentPane.add(txtNgay);
		
		
		// Event
		
		btnHinTh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData();
			}
		});
		
		
		//
		txtMHD.setEnabled(false);
		txtMKH.setEnabled(false);
		txtNgay.setEnabled(false);
		txtTien.setEnabled(false);
		
		ErrorMHD = new JTextField();
		ErrorMHD.setBackground(UIManager.getColor("Button.background"));
		ErrorMHD.setBorder(null);
		ErrorMHD.setBounds(205, 91, 96, 19);
		contentPane.add(ErrorMHD);
		ErrorMHD.setColumns(10);
		
		ErrorMKH = new JTextField();
		ErrorMKH.setEnabled(false);
		ErrorMKH.setColumns(10);
		ErrorMKH.setBorder(null);
		ErrorMKH.setBackground(SystemColor.menu);
		ErrorMKH.setBounds(205, 153, 96, 19);
		contentPane.add(ErrorMKH);
		
		ErrorTG = new JTextField();
		ErrorTG.setEnabled(false);
		ErrorTG.setColumns(10);
		ErrorTG.setBorder(null);
		ErrorTG.setBackground(SystemColor.menu);
		ErrorTG.setBounds(542, 91, 96, 19);
		contentPane.add(ErrorTG);
		
		ErrorTongTien = new JTextField();
		ErrorTongTien.setEnabled(false);
		ErrorTongTien.setColumns(10);
		ErrorTongTien.setBorder(null);
		ErrorTongTien.setBackground(SystemColor.menu);
		ErrorTongTien.setBounds(542, 153, 96, 19);
		contentPane.add(ErrorTongTien);
		
		// Chinh sua
		btnChnhSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtMKH.setEnabled(true);
				txtNgay.setEnabled(true);
				txtTien.setEnabled(true);
			}
		});
		
		
		// Huy bo
		btnHyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtMKH.setEnabled(false);
				txtNgay.setEnabled(false);
				txtTien.setEnabled(false);
				
				txtMHD.setText("");
				txtMKH.setText("");				
				txtNgay.setText("");
				txtTien.setText("");
				
			}
		});
		
		
		// Luu
		
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check_form()) {
					APIHoaDon query = new APIHoaDon();
					int MaHD = Integer.parseInt(txtMHD.getText());
					int MaKH = Integer.parseInt(txtMKH.getText());
					int TongTien = Integer.parseInt(txtTien.getText());
					String Ngay = txtNgay.getText();
					query.Edit(MaHD, MaKH, Ngay, TongTien);
				}
			}
		});
		
		
		// Lay data tu bang
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String maHD = table.getModel().getValueAt(row, 0).toString();
				String maKH = table.getModel().getValueAt(row, 1).toString();
				String ngaylap = table.getModel().getValueAt(row, 2).toString();
				String tongtien = table.getModel().getValueAt(row, 3).toString();
				txtMHD.setText(maHD);
				txtMKH.setText(maKH);				
				txtNgay.setText(ngaylap);
				txtTien.setText(tongtien);
				
			}
		});
		
		// Them hoa don
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DSHoaDon.this.dispose();
				ThemHoaDon hd = new ThemHoaDon();
				hd.setVisible(true);
			}
		});
	}
}
