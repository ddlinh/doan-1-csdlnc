package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Controller.APIHoaDon;
import DAO.ConnectionDB;
import Model.ChiTietHoaDon;
import Model.HoaDon;

import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ThemHoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaKH;
	private JDateChooser txtThoiGian;
	private JTextField ErrorMaKH;
	private JTextField ErrorThoiGian;
	
	public int IndexSP = -1;
	
	
	public static ArrayList<ChiTietHoaDon> list_cthd = new ArrayList<ChiTietHoaDon>();
	private JTable table_sp;
	
	/// Check du lieu kieu so
	
	public boolean check_nb(String nb) {
		
		try {
			Integer.parseInt(nb);
		}catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	
	/// Kiem tra ton tai cua ma KH
	public boolean check_maKH(int MaKH) {
		Statement stm = null;
		try {
			if(ConnectionDB.connect_() && MaKH > 0) {
				String sql = "SELECT * FROM KhachHang WHERE MaKH = " + MaKH;
				stm = ConnectionDB.conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					
					return true;
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void LoadSP() {
		System.out.println(list_cthd.size());
		if(list_cthd.size() > 0) {
			table_sp.removeAll();
			String[] title = {"MaSP","SoLuong","GiaBan","GiaGiam","ThanhTien"};
			String[][] data = new String[list_cthd.size()][5];
			for(int i = 0; i < list_cthd.size(); i++) {
				data[i][0] = list_cthd.get(i).MaSP + "";
				data[i][1] = list_cthd.get(i).SoLuong + "";
				data[i][2] = list_cthd.get(i).GiaBan + "";
				data[i][3] = list_cthd.get(i).GiaGiam + "";
				data[i][4] = list_cthd.get(i).ThanhTien + "";
			}
			
			DefaultTableModel model = new DefaultTableModel(data, title);
			table_sp.setModel(model);
		}
		
	}
	
	// Kiem tra form data
	
	public boolean check_form() {
		int count = 0;
		if(txtMaKH.getText().length() < 0) {
			ErrorMaKH.setText("Ma KH rong");
		}
		else {
			if(!check_nb(txtMaKH.getText())) {
				ErrorMaKH.setText("Ma KH phai la so");
			}
			else {
				int maKH = Integer.parseInt(txtMaKH.getText());
				if(!check_maKH(maKH)) {
					ErrorMaKH.setText("Ma KH khong ton tai");
				}
				else {
					ErrorMaKH.setText("");
					count += 1;
				}
			}
		}
		
		
		if(txtThoiGian.getDate() == null) {
			ErrorThoiGian.setText("Ban chua chon thoi gian");
		}
		else {
			ErrorThoiGian.setText("");
			count += 1;
		}
		
		if(count < 2)
		{
			return false;
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
					ThemHoaDon frame = new ThemHoaDon();
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
	public ThemHoaDon() {
		
		list_cthd.clear();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 683);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TH\u00CAM H\u00D3A \u0110\u01A0N");
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(264, 20, 254, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Th\u00F4ng tin kh\u00E1ch h\u00E0ng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(86, 57, 169, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Th\u00F4ng tin h\u00F3a \u0111\u01A1n");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(86, 181, 169, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("H\u1ECD v\u00E0 t\u00EAn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(100, 96, 80, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(110, 119, 80, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(100, 142, 80, 13);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("D\u01B0\u01A1ng Quang Vinh");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_3.setBounds(190, 96, 128, 13);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("26 L\u00EA L\u1EE3i, qu\u1EADn 1, tp H\u1ED3 Ch\u00ED Minh");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_4.setBounds(190, 119, 201, 13);
		contentPane.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("0123456789");
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_5.setBounds(200, 142, 80, 13);
		contentPane.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("M\u00E3 kh\u00E1ch h\u00E0ng:");
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_6.setBounds(86, 220, 90, 13);
		contentPane.add(lblNewLabel_2_6);
		
		txtMaKH = new JTextField();
		txtMaKH.setBounds(190, 220, 111, 19);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		JLabel lblNewLabel_2_6_2 = new JLabel("Th\u1EDDi gian:");
		lblNewLabel_2_6_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_6_2.setBounds(100, 268, 76, 13);
		contentPane.add(lblNewLabel_2_6_2);
		
		txtThoiGian = new JDateChooser();
		txtThoiGian.setBounds(190, 268, 108, 19);
		contentPane.add(txtThoiGian);
		
		ErrorMaKH = new JTextField();
		ErrorMaKH.setEnabled(false);
		ErrorMaKH.setColumns(10);
		ErrorMaKH.setBorder(null);
		ErrorMaKH.setBackground(new Color(255, 255, 224));
		ErrorMaKH.setBounds(190, 240, 107, 19);
		contentPane.add(ErrorMaKH);
		
		ErrorThoiGian = new JTextField();
		ErrorThoiGian.setEnabled(false);
		ErrorThoiGian.setColumns(10);
		ErrorThoiGian.setBorder(null);
		ErrorThoiGian.setBackground(new Color(255, 255, 224));
		ErrorThoiGian.setBounds(190, 297, 107, 19);
		contentPane.add(ErrorThoiGian);
		
		JButton btnNewButton = new JButton("H\u1EE7y");
		
		btnNewButton.setBackground(new Color(255, 215, 0));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(264, 573, 108, 34);
		contentPane.add(btnNewButton);
		
		JButton btnThmMi = new JButton("Th\u00EAm m\u1EDBi");
		
		btnThmMi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThmMi.setBackground(new Color(135, 206, 250));
		btnThmMi.setBounds(411, 573, 107, 34);
		contentPane.add(btnThmMi);
		
		JButton btnQuayV = new JButton("Quay v\u1EC1");
		btnQuayV.setForeground(new Color(255, 255, 224));
		btnQuayV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuayV.setBackground(new Color(47, 79, 79));
		btnQuayV.setBounds(658, 602, 108, 34);
		contentPane.add(btnQuayV);
		
		JButton btnDanhSchHa = new JButton("Danh s\u00E1ch h\u00F3a \u0111\u01A1n");
		
		btnDanhSchHa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDanhSchHa.setForeground(new Color(255, 255, 224));
		btnDanhSchHa.setBackground(new Color(47, 79, 79));
		btnDanhSchHa.setBounds(597, 210, 169, 34);
		contentPane.add(btnDanhSchHa);
		
		JButton btnNewButton_1 = new JButton("Th\u00EAm s\u1EA3n ph\u1EA9m");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemCTHD frame = new ThemCTHD();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(332, 217, 138, 21);
		contentPane.add(btnNewButton_1);
		
		table_sp = new JTable();
		table_sp.setBounds(39, 382, 700, 165);
		String[] title = {"MaSP","SoLuong","GiaBan","GiaGiam","ThanhTien"};
		DefaultTableModel model = new DefaultTableModel(null, title);
		table_sp.setModel(model);
		
		JScrollPane scr_table = new JScrollPane(table_sp);
		
		scr_table.setBounds(39, 382, 700, 165);
		contentPane.add(scr_table);
		
		JButton btnNewButton_1_1 = new JButton("S\u1EA3n ph\u1EA9m \u0111\u00E3 th\u00EAm");
		
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setBackground(new Color(176, 196, 222));
		btnNewButton_1_1.setBounds(597, 314, 138, 21);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("X\u00F3a s\u1EA3n ph\u1EA9m");
		
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(332, 265, 138, 21);
		contentPane.add(btnNewButton_2);
		
		
		
		
		//// Huy
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaKH.setText("");
				txtThoiGian.setDate(null);
			}
		});
		
		
		// Them moi
		
		btnThmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_cthd.size() > 0) {
					if(check_form()) {
						int MaKH = Integer.parseInt(txtMaKH.getText());
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String Ngay = dateFormat.format(txtThoiGian.getDate());
						
						APIHoaDon query = new APIHoaDon();
						query.ThemHD(MaKH, Ngay, list_cthd);
						
						JOptionPane.showMessageDialog(ThemHoaDon.this,
							    "Them moi du lieu thanh cong",
							    "Thong bao",
							    JOptionPane.PLAIN_MESSAGE);
						
						txtMaKH.setText("");
						txtThoiGian.setDate(null);
						
					}
				}
				else {
					JOptionPane.showMessageDialog(ThemHoaDon.this,
						    "Ban chua them san pham nao trong hoa don",
						    "Thong bao",
						    JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		
		
		// Quay ve
		
		btnDanhSchHa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemHoaDon.this.dispose();
				DSHoaDon ds = new DSHoaDon();
				ds.setVisible(true);
			}
		});
		
		// Load san pham da them
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadSP();
			}
		});
		
		// Lay index sp de xoa khoi hoa don
		table_sp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_sp.getSelectedRow();
				IndexSP = row;
				
			}
		});
		
		// Xoa san pham khoi hoa don
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IndexSP >= 0) {
					list_cthd.remove(IndexSP);
					IndexSP = -1;
					
					LoadSP();
				}
				else {
					JOptionPane.showMessageDialog(ThemHoaDon.this,
						    "Ban chua chon san pham de xoa",
						    "Thong bao",
						    JOptionPane.PLAIN_MESSAGE);
				}
			//	IndexSP = -1;
			}
		});
		
	}
}
