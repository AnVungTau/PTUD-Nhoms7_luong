package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JYearChooser;
import dao.BangChamCongNhanVien_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import com.toedter.calendar.JMonthChooser;
import java.text.DecimalFormat;

import com.opencsv.CSVWriter;
import java.io.*;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * @author Nguyễn Tuấn Hùng
 */
public class LuongNhanVien_Panel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable tblLuongNV;
	private JButton btnTinhLuong;
	private JButton btnXuatFile;
	private DefaultTableModel modelBangLuong;
	private JTextField txtTim;
	private JButton btnTim;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private NhanVien_DAO nv_Dao = new NhanVien_DAO();
	private BangChamCongNhanVien_DAO ccnv_DAO = new BangChamCongNhanVien_DAO();
	private URL urlTim = LuongCongNhan_Panel.class.getResource("/img/Ampeross-Qetto-2-Search.24.png");
	private URL urlTinhLuong = LuongCongNhan_Panel.class.getResource("/img/calculator.png");
	private URL urlXuatFile = LuongCongNhan_Panel.class.getResource("/img/export.png");

	public LuongNhanVien_Panel() {
		setBackground(new Color(255, 255, 255));
		setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ti\u1EC1n l\u01B0\u01A1ng nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(255, 255, 255));
		pnlTop.setPreferredSize(new Dimension(10, 110));
		add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(null);

		JLabel lblChonThang = new JLabel("Chọn tháng");
		lblChonThang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChonThang.setBounds(61, 40, 76, 25);
		pnlTop.add(lblChonThang);

		btnXuatFile = new JButton("Xuất File");
		btnXuatFile.setIcon(new ImageIcon(urlXuatFile));
		btnXuatFile.setBackground(new Color(255, 255, 255));
		btnXuatFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXuatFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXuatFile.setBounds(800, 40, 120, 30);
		pnlTop.add(btnXuatFile);

		btnTinhLuong = new JButton("Tính lương");
		btnTinhLuong.setIcon(new ImageIcon(urlTinhLuong));
		btnTinhLuong.setBackground(new Color(255, 255, 255));
		btnTinhLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTinhLuong.setBounds(323, 40, 150, 30);
		pnlTop.add(btnTinhLuong);

		monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 12));
		monthChooser.setBounds(140, 40, 103, 25);
		setComboBoxLocale(monthChooser, new Locale("vi"));
		pnlTop.add(monthChooser);

		yearChooser = new JYearChooser();
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 12));
		yearChooser.setBounds(253, 40, 60, 25);
		pnlTop.add(yearChooser);
		
		JLabel lblBngLngNhn = new JLabel("BẢNG LƯƠNG NHÂN VIÊN");
		lblBngLngNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblBngLngNhn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBngLngNhn.setBackground(Color.WHITE);
		lblBngLngNhn.setBounds(10, 10, 1268, 22);
		pnlTop.add(lblBngLngNhn);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 255, 255));
		pnlCenter.setBorder(new TitledBorder(null, "Danh s\u00E1ch ti\u1EC1n l\u01B0\u01A1ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		String header[] = { "Mã NV", "Họ tên", "Tiền trợ cấp", "Nguyên ngày", "Nửa ngày", "Có phép",
				"Không phép", "Số giờ tăng ca", "Tổng lương"};
		modelBangLuong = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex == 2 || columnIndex == 8) {
					return Integer.class;
				}
				return super.getColumnClass(columnIndex);
			}	
		};

		JScrollPane scrollPane = new JScrollPane();
		pnlCenter.add(scrollPane);

		JPanel pnlTimNhanVien = new JPanel();
		pnlTimNhanVien.setLayout(null);
		pnlTimNhanVien.setSize(new Dimension(0, 100));
		pnlTimNhanVien.setPreferredSize(new Dimension(10, 35));
		pnlTimNhanVien.setBackground(Color.WHITE);
		pnlCenter.add(pnlTimNhanVien, BorderLayout.NORTH);

		txtTim = new JTextField();
		txtTim.setColumns(10);
		txtTim.setBounds(341, 5, 226, 25);
		pnlTimNhanVien.add(txtTim);

		JLabel lblTimKiem = new JLabel("Mã NV");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimKiem.setBounds(250, 5, 60, 25);
		pnlTimNhanVien.add(lblTimKiem);

		btnTim = new JButton("");
		btnTim.setBorderPainted(false);
		btnTim.setIcon(new ImageIcon(urlTim));
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setBounds(577, 5, 25, 25);
		pnlTimNhanVien.add(btnTim);

		tblLuongNV = new JTable(modelBangLuong);
		tblLuongNV.setRowHeight(20);
		tblLuongNV.setSelectionForeground(new Color(255, 255, 255));
		tblLuongNV.setSelectionBackground(new Color(0, 128, 255));
		tblLuongNV.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblLuongNV);
		TableRowSorter<TableModel> sorterSP = new TableRowSorter<TableModel>(modelBangLuong);
		tblLuongNV.setRowSorter(sorterSP);

		btnTim.addActionListener(this);
		btnTinhLuong.addActionListener(this);
		btnXuatFile.addActionListener(this);
	}

	private void clearTable() {
		int r = tblLuongNV.getRowCount();
		while (r > 0) {
			modelBangLuong.removeRow(r - 1);
			r--;
		}
	}

	public float tinhLuong(int a, int b, int c, float d, int e, float f) {
		return ((300000 * a + 150000 * b + 45000 * c + d - 100000 * e) * f);
	}

	private void LoadDanhSachNhanVien(ArrayList<NhanVien> dsNV) {
		clearTable();
		int thang = monthChooser.getMonth() + 1;
		int nam = yearChooser.getYear();
		DecimalFormat format = new DecimalFormat("#,##0 VND");
		for (NhanVien nv : dsNV) {
			int nguyenNgay = ccnv_DAO.soNgayLamNguyenNgay(nv, thang, nam);
			int nuaNgay = ccnv_DAO.soNgayLamNuaNgay(nv, thang, nam);
			int coPhep = ccnv_DAO.soNgayNghiCoPhep(nv, thang, nam);
			int koPhep = ccnv_DAO.soNgayNghiKhongPhep(nv, thang, nam);
			int soGioTangCa = ccnv_DAO.soGioTangCa(nv, thang, nam);
			float phuCap = nv.getPhuCap();
			float tongLuong = tinhLuong(nguyenNgay, nuaNgay, soGioTangCa, phuCap, koPhep, nv.getHeSoLuong());
			modelBangLuong.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), format.format(nv.getPhuCap()), nguyenNgay, nuaNgay,
					coPhep, koPhep, soGioTangCa, format.format(tongLuong)});
		}
	}

	public static void exportToCSV(JTable table, String filePath) {
		try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
				CSVWriter writer = new CSVWriter(osw)) {

			// BOM (Byte Order Mark) for UTF-8
			osw.write('\ufeff');

			// Xuất tiêu đề cột
			String[] header = new String[table.getColumnCount()];
			for (int col = 0; col < table.getColumnCount(); col++) {
				header[col] = table.getColumnName(col);
			}
			writer.writeNext(header);

			// Xuất dữ liệu từ JTable
			for (int row = 0; row < table.getRowCount(); row++) {
				String[] rowValues = new String[table.getColumnCount()];
				for (int col = 0; col < table.getColumnCount(); col++) {
					Object value = table.getValueAt(row, col);
					rowValues[col] = (value != null) ? value.toString() : "";
				}
				writer.writeNext(rowValues);
			}

			JOptionPane.showMessageDialog(null, "Xuất CSV thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất CSV.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void setComboBoxLocale(JComponent component, Locale locale) {
		component.setLocale(locale);
		component.getComponent(0).setLocale(locale);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			int thang = monthChooser.getMonth() + 1;
			int nam = yearChooser.getYear();
			String ma = txtTim.getText();
			LoadDanhSachNhanVien(nv_Dao.getDanhSachNhanVienTheoThangNamChamCongVaMa(thang, nam, ma));
		}
		if (o.equals(btnTinhLuong)) {
			int thang = monthChooser.getMonth() + 1;
			int nam = yearChooser.getYear();
			LoadDanhSachNhanVien(nv_Dao.getDanhSachNhanVienTheoThangNamChamCong(thang, nam));
		}
		if (o.equals(btnXuatFile)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn vị trí lưu file");
			int userSelection = fileChooser.showSaveDialog(null);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				exportToCSV(tblLuongNV, fileToSave.getAbsolutePath());
			}
		}
	}
}
