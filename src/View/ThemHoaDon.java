package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Controller.APIHoaDon;
import DAO.ConnectionDB;
import Model.HoaDon;

import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemHoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtTongTien;
	private JDateChooser txtThoiGian;
	private JTextField ErrorMaKH;
	private JTextField ErrorTongTien;
	private JTextField ErrorThoiGian;
	
	
	
	
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
		
		if(txtTongTien.getText().length() < 0){
			ErrorTongTien.setText("Tong tien rong");
		}
		else {
			if(!check_nb(txtTongTien.getText())) {
				ErrorTongTien.setText("Tien phai la so");
			}
			else {
				ErrorTongTien.setText("");
				count += 1;
			}
		}
		
		if(txtThoiGian.getDate() == null) {
			ErrorThoiGian.setText("Ban chua chon thoi gian");
		}
		else {
			ErrorThoiGian.setText("");
			count += 1;
		}
		
		if(count < 3)
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 606);
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
		lblNewLabel_1.setBounds(86, 72, 169, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Th\u00F4ng tin h\u00F3a \u0111\u01A1n");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(86, 269, 169, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("H\u1ECD v\u00E0 t\u00EAn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(111, 121, 80, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(121, 159, 80, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(100, 196, 80, 13);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("D\u01B0\u01A1ng Quang Vinh");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_3.setBounds(201, 122, 128, 13);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("26 L\u00EA L\u1EE3i, qu\u1EADn 1, tp H\u1ED3 Ch\u00ED Minh");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_4.setBounds(201, 159, 201, 13);
		contentPane.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("0123456789");
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_5.setBounds(190, 196, 118, 13);
		contentPane.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("M\u00E3 kh\u00E1ch h\u00E0ng:");
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_6.setBounds(100, 335, 90, 13);
		contentPane.add(lblNewLabel_2_6);
		
		txtMaKH = new JTextField();
		txtMaKH.setBounds(201, 333, 111, 19);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		JLabel lblNewLabel_2_6_1 = new JLabel("T\u1ED5ng ti\u1EC1n:");
		lblNewLabel_2_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_6_1.setBounds(111, 388, 90, 13);
		contentPane.add(lblNewLabel_2_6_1);
		
		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(201, 386, 111, 19);
		contentPane.add(txtTongTien);
		
		JLabel lblNewLabel_2_6_2 = new JLabel("Th\u1EDDi gian:");
		lblNewLabel_2_6_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_6_2.setBounds(111, 445, 90, 13);
		contentPane.add(lblNewLabel_2_6_2);
		
		txtThoiGian = new JDateChooser();
		txtThoiGian.setBounds(204, 439, 108, 19);
		contentPane.add(txtThoiGian);
		
		ErrorMaKH = new JTextField();
		ErrorMaKH.setEnabled(false);
		ErrorMaKH.setColumns(10);
		ErrorMaKH.setBorder(null);
		ErrorMaKH.setBackground(new Color(255, 255, 224));
		ErrorMaKH.setBounds(201, 357, 107, 19);
		contentPane.add(ErrorMaKH);
		
		ErrorTongTien = new JTextField();
		ErrorTongTien.setEnabled(false);
		ErrorTongTien.setColumns(10);
		ErrorTongTien.setBorder(null);
		ErrorTongTien.setBackground(new Color(255, 255, 224));
		ErrorTongTien.setBounds(201, 410, 107, 19);
		contentPane.add(ErrorTongTien);
		
		ErrorThoiGian = new JTextField();
		ErrorThoiGian.setEnabled(false);
		ErrorThoiGian.setColumns(10);
		ErrorThoiGian.setBorder(null);
		ErrorThoiGian.setBackground(new Color(255, 255, 224));
		ErrorThoiGian.setBounds(201, 468, 107, 19);
		contentPane.add(ErrorThoiGian);
		
		JButton btnNewButton = new JButton("H\u1EE7y");
		
		btnNewButton.setBackground(new Color(255, 215, 0));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(264, 491, 108, 34);
		contentPane.add(btnNewButton);
		
		JButton btnThmMi = new JButton("Th\u00EAm m\u1EDBi");
		
		btnThmMi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThmMi.setBackground(new Color(135, 206, 250));
		btnThmMi.setBounds(411, 491, 107, 34);
		contentPane.add(btnThmMi);
		
		JButton btnQuayV = new JButton("Quay v\u1EC1");
		btnQuayV.setForeground(new Color(255, 255, 224));
		btnQuayV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuayV.setBackground(new Color(47, 79, 79));
		btnQuayV.setBounds(658, 525, 108, 34);
		contentPane.add(btnQuayV);
		
		JButton btnDanhSchHa = new JButton("Danh s\u00E1ch h\u00F3a \u0111\u01A1n");
		
		btnDanhSchHa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDanhSchHa.setForeground(new Color(255, 255, 224));
		btnDanhSchHa.setBackground(new Color(47, 79, 79));
		btnDanhSchHa.setBounds(597, 268, 169, 34);
		contentPane.add(btnDanhSchHa);
		
		
		
		
		//// Huy
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaKH.setText("");
				txtTongTien.setText("");
				txtThoiGian.setDate(null);
			}
		});
		
		
		// Them moi
		
		btnThmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check_form()) {
					int MaKH = Integer.parseInt(txtMaKH.getText());
					int TongTien = Integer.parseInt(txtTongTien.getText());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String Ngay = dateFormat.format(txtThoiGian.getDate());
					
					APIHoaDon query = new APIHoaDon();
					query.ThemHD(MaKH, Ngay, TongTien);
					
					JOptionPane.showMessageDialog(ThemHoaDon.this,
						    "Them moi du lieu thanh cong",
						    "Thong bao",
						    JOptionPane.PLAIN_MESSAGE);
					
					txtMaKH.setText("");
					txtTongTien.setText("");
					txtThoiGian.setDate(null);
					
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
	}
}
