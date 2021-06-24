package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.APIHoaDon;
import Controller.APISanPham;
import Model.ChiTietHoaDon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemCTHD extends JFrame {

	private JPanel contentPane;
	private JTable table_sp;
	private JTextField txtTenSP;
	private JTextField txtFrom;
	private JTextField txtTo;
	private JTextField txtTrang;
	private JTextField txtSoLuong;
	private JTextField ErrorSLSP;
	private JTextField txtTenSp;
	private JTextField txtGiaTien;
	private JTextField txtMa;
	
	
	public int SoTrang;
	public int TrangHienTai;
	public APISanPham query = new APISanPham();
	public boolean isSearch = false;
	
	public int SLTon;
	
	
	public boolean check_nb(String nb) {
		
		try {
			Integer.parseInt(nb);
		}catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	
	public void LoadData() {
		table_sp.removeAll();
		
		boolean check = false;
		
		int from = -1;
		int to = -1;
		if(txtFrom.getText().length() > 0 && check_nb(txtFrom.getText())) {
			from = Integer.parseInt(txtFrom.getText());
		}
		if(txtTo.getText().length() > 0 && check_nb(txtTo.getText())) {
			to = Integer.parseInt(txtTo.getText());
		}
		
		
		
		if(txtTenSP.getText().length() > 0 && from >= 0 && to >= 0 && from <= to) {
			query.TimKiemTheoGiaVaTen(txtTenSP.getText(), from, to);
			isSearch = true;
			check = true;
		}
		else {
			if(txtTenSP.getText().length() > 0) {
				query.TimKiemTheoTen(txtTenSP.getText());
				check = true;
				isSearch = true;
			}
			else {
				if(from >= 0 && to >= 0 && from <= to) {
					query.TimKiemTheoGia(from, to);
					check = true;
					isSearch = true;
				}
				else {
					isSearch = false;
					query.GetAll();
				}
			}
		}
		
		
		
		
		String[] title = {"MaSP","TenSP","SoLuongTon","MoTa","Gia"};
		
		if(check) {
			this.SoTrang = query.list_result.size() / 20;
			this.TrangHienTai = 1;
			if(this.SoTrang >= 1) {
				String[][] data = new String[20][5];
				for(int i = 0; i < 20; i++) {
					data[i][0] = ""+query.list_result.get(i).MaSP;
					data[i][1] = ""+query.list_result.get(i).TenSP;
					data[i][2] = ""+query.list_result.get(i).SoLuongTon;
					data[i][3] = ""+query.list_result.get(i).MoTa;
					data[i][4] = ""+query.list_result.get(i).Gia;
					
					
				}
				
				DefaultTableModel model = new DefaultTableModel(data, title);
				table_sp.setModel(model);
			}
			else {
				String[][] data = new String[query.list_result.size()][5];
				for(int i = 0; i < query.list_result.size(); i++) {
					data[i][0] = ""+query.list_result.get(i).MaSP;
					data[i][1] = ""+query.list_result.get(i).TenSP;
					data[i][2] = ""+query.list_result.get(i).SoLuongTon;
					data[i][3] = ""+query.list_result.get(i).MoTa;
					data[i][4] = ""+query.list_result.get(i).Gia;
					
					
				}
				
				DefaultTableModel model = new DefaultTableModel(data, title);
				table_sp.setModel(model);
			}
		}
		else {
			this.SoTrang = query.list_sp.size() / 20;
			this.TrangHienTai = 1;
			String[][] data = new String[20][5];
			for(int i = 0; i < 20; i++) {
				data[i][0] = ""+query.list_sp.get(i).MaSP;
				data[i][1] = ""+query.list_sp.get(i).TenSP;
				data[i][2] = ""+query.list_sp.get(i).SoLuongTon;
				data[i][3] = ""+query.list_sp.get(i).MoTa;
				data[i][4] = ""+query.list_sp.get(i).Gia;
				
				
			}
			
			
			
			DefaultTableModel model = new DefaultTableModel(data, title);
			table_sp.setModel(model);
		}
		
		
		
		txtTrang.setText("1");
		txtTenSP.setText("");
		txtFrom.setText("");
		txtTo.setText("");
		
	}
	
	
	
	public void Load_Trang(int SoTrang) {
		table_sp.removeAll();
		String[] title = {"MaSP","TenSP","SoLuongTon","MoTa","Gia"};
		if(this.isSearch) {
			String[][] data = null;
			this.TrangHienTai = SoTrang;
			txtTrang.setText("" + this.TrangHienTai);
			if(this.TrangHienTai < this.SoTrang) {
				data = new String[20][5];
				System.out.println(this.TrangHienTai*20 + 20);
				int k = 0;
				for(int i = (this.TrangHienTai-1)*20; i < (this.TrangHienTai-1)*20 + 20; i++) {
					data[k][0] = ""+query.list_result.get(i).MaSP;
					data[k][1] = ""+query.list_result.get(i).TenSP;
					data[k][2] = ""+query.list_result.get(i).SoLuongTon;
					data[k][3] = ""+query.list_result.get(i).MoTa;
					data[k][4] = ""+query.list_result.get(i).Gia;
					k += 1;
				}
			}
			else {
				data = new String[query.list_result.size()-this.TrangHienTai*20][5];
				int k = 0;
				for(int i = (this.TrangHienTai)*20; i < query.list_result.size(); i++) {
					data[k][0] = ""+query.list_result.get(i).MaSP;
					data[k][1] = ""+query.list_result.get(i).TenSP;
					data[k][2] = ""+query.list_result.get(i).SoLuongTon;
					data[k][3] = ""+query.list_result.get(i).MoTa;
					data[k][4] = ""+query.list_result.get(i).Gia;
					k += 1;
					
				}
			}
			DefaultTableModel model = new DefaultTableModel(data, title);
			table_sp.setModel(model);
		}
		else {
			String[][] data = null;
			this.TrangHienTai = SoTrang;
			txtTrang.setText("" + this.TrangHienTai);
			if(this.TrangHienTai < this.SoTrang) {
				data = new String[20][5];
				int k = 0;
				for(int i = (this.TrangHienTai-1)*20; i < (this.TrangHienTai-1)*20 + 20; i++) {
					
					data[k][0] = ""+query.list_sp.get(i).MaSP;
					data[k][1] = ""+query.list_sp.get(i).TenSP;
					data[k][2] = ""+query.list_sp.get(i).SoLuongTon;
					data[k][3] = ""+query.list_sp.get(i).MoTa;
					data[k][4] = ""+query.list_sp.get(i).Gia;
					k += 1;
					
				}
				
			}
			else {
				data = new String[query.list_sp.size()-this.TrangHienTai*20][5];
				int k = 0;
				for(int i = (this.TrangHienTai)*20; i < query.list_sp.size(); i++) {
					data[k][0] = ""+query.list_sp.get(i).MaSP;
					data[k][1] = ""+query.list_sp.get(i).TenSP;
					data[k][2] = ""+query.list_sp.get(i).SoLuongTon;
					data[k][3] = ""+query.list_sp.get(i).MoTa;
					data[k][4] = ""+query.list_sp.get(i).Gia;
					k += 1;
				}
			}
			DefaultTableModel model = new DefaultTableModel(data, title);
			table_sp.setModel(model);
		}
	}
	
	public boolean check_form() {
		if(txtSoLuong.getText().length() <= 0) {
			ErrorSLSP.setText("So luong san pham rong");
			return false;
		}
		else {
			int slSP = Integer.parseInt(txtSoLuong.getText());
			if(slSP > this.SLTon) {
				ErrorSLSP.setText("So luong lon hon so luong ton kho");
				return false;
			}
			else {
				ErrorSLSP.setText("");
			}
		}
		
		
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemCTHD frame = new ThemCTHD();
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
	public ThemCTHD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DANH S\u00C1CH S\u1EA2N PH\u1EA8M");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(273, 21, 250, 28);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		table_sp = new JTable();
		
		String[] title = {"MaSP","TenSP","SoLuongTon","MoTa","Gia"};
		
		query.GetAll();
		
		
		
		
		
		String[][] data = new String[20][5];
		this.TrangHienTai = 1;
		this.SoTrang = query.list_sp.size()/20;
		for(int i = 0; i < 20; i++) {
			data[i][0] = ""+query.list_sp.get(i).MaSP;
			data[i][1] = ""+query.list_sp.get(i).TenSP;
			data[i][2] = ""+query.list_sp.get(i).SoLuongTon;
			data[i][3] = ""+query.list_sp.get(i).MoTa;
			data[i][4] = ""+query.list_sp.get(i).Gia;
			
		}
		
		
		DefaultTableModel model = new DefaultTableModel(data, title);
		table_sp.setModel(model);
		
		table_sp.setBounds(10, 176, 769, 308);
		
		
		
		JScrollPane scr_table = new JScrollPane(table_sp);
		
		scr_table.setBounds(10, 176, 769, 308);
		contentPane.add(scr_table);
		
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(398, 540, 105, 35);
		contentPane.add(btnNewButton);
		
		JButton btnHy = new JButton("H\u1EE7y");
		
		btnHy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHy.setForeground(Color.BLACK);
		btnHy.setBackground(new Color(250, 128, 114));
		btnHy.setBounds(252, 540, 105, 35);
		contentPane.add(btnHy);
		
		JButton btnNewButton_1 = new JButton("Tho\u00E1t");
		
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(250, 235, 215));
		btnNewButton_1.setBounds(664, 569, 115, 28);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m");
		lblNewLabel_1.setBounds(10, 150, 84, 13);
		contentPane.add(lblNewLabel_1);
		
		txtTenSP = new JTextField();
		txtTenSP.setBounds(102, 147, 96, 19);
		contentPane.add(txtTenSP);
		txtTenSP.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Gi\u00E1 th\u1EA5p nh\u1EA5t");
		lblNewLabel_2.setBounds(224, 150, 80, 13);
		contentPane.add(lblNewLabel_2);
		
		txtFrom = new JTextField();
		txtFrom.setBounds(314, 147, 96, 19);
		contentPane.add(txtFrom);
		txtFrom.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Gi\u00E1 cao nh\u1EA5t");
		lblNewLabel_2_1.setBounds(420, 150, 84, 13);
		contentPane.add(lblNewLabel_2_1);
		
		txtTo = new JTextField();
		txtTo.setColumns(10);
		txtTo.setBounds(509, 147, 96, 19);
		contentPane.add(txtTo);
		
		JButton btnNewButton_2 = new JButton("T\u00ECm ki\u1EBFm");
		
		btnNewButton_2.setBackground(new Color(175, 238, 238));
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(615, 146, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("<");
		
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBounds(9, 488, 43, 21);
		contentPane.add(btnNewButton_3);
		
		txtTrang = new JTextField();
		txtTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrang.setBounds(53, 489, 54, 19);
		contentPane.add(txtTrang);
		txtTrang.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Ti\u1EBFn");
		
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setBounds(113, 488, 71, 21);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3_1 = new JButton(">");
		
		btnNewButton_3_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3_1.setBounds(188, 488, 43, 21);
		contentPane.add(btnNewButton_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng s\u1EA3n ph\u1EA9m");
		lblNewLabel_3.setBounds(10, 72, 115, 13);
		contentPane.add(lblNewLabel_3);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(135, 69, 96, 19);
		contentPane.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		ErrorSLSP = new JTextField();
		ErrorSLSP.setEnabled(false);
		ErrorSLSP.setBackground(UIManager.getColor("Button.background"));
		ErrorSLSP.setBorder(null);
		ErrorSLSP.setBounds(135, 94, 188, 19);
		contentPane.add(ErrorSLSP);
		ErrorSLSP.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m");
		lblNewLabel_4.setBounds(263, 72, 84, 13);
		contentPane.add(lblNewLabel_4);
		
		txtTenSp = new JTextField();
		txtTenSp.setEnabled(false);
		txtTenSp.setColumns(10);
		txtTenSp.setBounds(357, 69, 96, 19);
		contentPane.add(txtTenSp);
		
		JLabel lblNewLabel_4_1 = new JLabel("Gi\u00E1 ti\u1EC1n");
		lblNewLabel_4_1.setBounds(463, 72, 60, 13);
		contentPane.add(lblNewLabel_4_1);
		
		txtGiaTien = new JTextField();
		txtGiaTien.setEnabled(false);
		txtGiaTien.setColumns(10);
		txtGiaTien.setBounds(523, 69, 96, 19);
		contentPane.add(txtGiaTien);
		
		JLabel lblNewLabel_4_2 = new JLabel("M\u00E3");
		lblNewLabel_4_2.setBounds(626, 72, 43, 13);
		contentPane.add(lblNewLabel_4_2);
		
		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setColumns(10);
		txtMa.setBounds(664, 69, 96, 19);
		contentPane.add(txtMa);
		
		
		txtTrang.setText("1");
		txtSoLuong.setText("1");
		
		// Load data len input
		
		table_sp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_sp.getSelectedRow();
				String tensp = table_sp.getModel().getValueAt(row, 1).toString();
				String giatien = table_sp.getModel().getValueAt(row, 4).toString();
				String ma = table_sp.getModel().getValueAt(row, 0).toString();
				txtTenSp.setText(tensp);
				txtGiaTien.setText(giatien);
				txtMa.setText(ma);
				String soluongton = table_sp.getModel().getValueAt(row, 2).toString();
				SLTon = Integer.parseInt(soluongton);
			}
		});
		
		
		// Tim kiem
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData();
			}
		});
		
		
		// Huy
		
		btnHy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenSp.setText("");
				txtGiaTien.setText("");
				txtMa.setText("");
				txtSoLuong.setText("");
			}
			
		});
		
		
		// Lui 
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTrang.getText().length() > 0 && check_nb(txtTrang.getText())) {
					int soTrang = Integer.parseInt(txtTrang.getText());
					soTrang -= 1;
					if(soTrang >= 1) {
						Load_Trang(soTrang);
					}
				}
			}
		});
		
		// Tien toi so trang
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTrang.getText().length() > 0 && check_nb(txtTrang.getText())) {
					int soTrang = Integer.parseInt(txtTrang.getText());
					if(soTrang <= SoTrang && soTrang >= 1) {
						Load_Trang(soTrang);
					}
					else {
						JOptionPane.showMessageDialog(ThemCTHD.this,
							    "So trang toi thieu la 1 va toi da la: " + SoTrang,
							    "Thong bao",
							    JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		
		// Tien
		
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTrang.getText().length() > 0 && check_nb(txtTrang.getText())) {
					
					int soTrang = Integer.parseInt(txtTrang.getText());
					soTrang += 1;
					if(soTrang < SoTrang) {
						
						Load_Trang(soTrang);
					}
				}
			}
		});
		
		// Them
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check_form()) {
					int SL = Integer.parseInt(txtSoLuong.getText());
					int GiaTien = Integer.parseInt(txtGiaTien.getText());
					int GiaGiam = (GiaTien*SL*20)/(100);
					int maSp = Integer.parseInt(txtMa.getText());
					int ThanhTien = (GiaTien*SL) - GiaGiam;
					ChiTietHoaDon cthd = new ChiTietHoaDon(maSp,SL,GiaTien,GiaGiam,ThanhTien);
					ThemHoaDon.list_cthd.add(cthd);
					JOptionPane.showMessageDialog(ThemCTHD.this,
						    "Them san pham thanh cong",
						    "Thong bao",
						    JOptionPane.PLAIN_MESSAGE);
					txtSoLuong.setText("1");
					System.out.println(GiaGiam);
				}
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemCTHD.this.dispose();
			}
		});
		
	}
}
