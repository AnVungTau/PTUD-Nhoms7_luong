package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import entity.HopDong;
import util.QuanLyMaHopDong;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import dao.HopDong_DAO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;
import javax.swing.ImageIcon;
import java.util.Locale;
/**
 * @author Trần Vũ Minh Nhật & Nguyễn Hồng Quân
 */
public class HopDong_Panel extends  JPanel implements ActionListener, PropertyChangeListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaHD;
	private JTextField txtTenHD;
	private JTextField txtTimKiem;
	private JTable tblHopDong;
	private JTextField txtTenKH;
	private JButton btnLamMoi;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnTim;
	private JSpinner spnTienCoc;
	private JSpinner spnTongTien;
	private JDateChooser dateChooserBatDau;
	private JDateChooser dateChooserKetThuc;
	private JComboBox<String> cmbLoc;
	private JLabel lblGhiChu;
	private JTextField txtGhiChu;
	private JLabel lblTBNgayBD;
	private JLabel lblTBNgayKT;
	private HopDong_DAO hopDong_dao = new HopDong_DAO();
	private DefaultTableModel model;
	private URL urlTim = HopDong_Panel.class.getResource("/img/Ampeross-Qetto-2-Search.24.png");
	private URL urlThem = HopDong_Panel.class.getResource("/img/Custom-Icon-Design-Flatastic-1-Add-1.24.png");
	private URL urlLamMoi = HopDong_Panel.class.getResource("/img/Hopstarter-Button-Button-Reload.24.png");
	private URL urlLuu = HopDong_Panel.class.getResource("/img/Oxygen-Icons.org-Oxygen-Actions-document-save.24.png");
	private URL urlTBFail = HopDong_Panel.class.getResource("/img/Saki-NuoveXT-Actions-button-cancel.16.png");
	private URL urlTBSuccess = HopDong_Panel.class.getResource("/img/Custom-Icon-Design-Pretty-Office-8-Accept.16.png");

	public HopDong_Panel() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setPreferredSize(new Dimension(10, 250));
		pnlTop.setBackground(new Color(255, 255, 255));
		add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN HỢP ĐỒNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblNewLabel.setBounds(10, 11, 1280, 25);
		pnlTop.add(lblNewLabel);
		
		JLabel lblMaHopDong = new JLabel("Mã hợp đồng");
		lblMaHopDong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHopDong.setPreferredSize(new Dimension(64, 14));
		lblMaHopDong.setMinimumSize(new Dimension(64, 14));
		lblMaHopDong.setMaximumSize(new Dimension(64, 14));
		lblMaHopDong.setBounds(166, 65, 82, 25);
		pnlTop.add(lblMaHopDong);
		
		JLabel lblTenHopDong = new JLabel("Tên hợp đồng");
		lblTenHopDong.setPreferredSize(new Dimension(64, 14));
		lblTenHopDong.setMinimumSize(new Dimension(64, 14));
		lblTenHopDong.setMaximumSize(new Dimension(64, 14));
		lblTenHopDong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenHopDong.setBounds(166, 100, 90, 25);
		pnlTop.add(lblTenHopDong);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setPreferredSize(new Dimension(64, 14));
		lblTenKhachHang.setMinimumSize(new Dimension(64, 14));
		lblTenKhachHang.setMaximumSize(new Dimension(64, 14));
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenKhachHang.setBounds(166, 135, 110, 25);
		pnlTop.add(lblTenKhachHang);
		
		txtMaHD = new JTextField();
		txtMaHD.setText("");
		txtMaHD.setEnabled(false);
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(276, 65, 150, 25);
		pnlTop.add(txtMaHD);
		
		txtTenHD = new JTextField();
		txtTenHD.setColumns(10);
		txtTenHD.setBounds(276, 100, 150, 25);
		pnlTop.add(txtTenHD);
		
		spnTongTien = new JSpinner();
		spnTongTien.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(5000000)));
		spnTongTien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spnTongTien.setBounds(276, 170, 150, 25);
		customJSpiner(spnTongTien);
		pnlTop.add(spnTongTien);
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setIcon(new ImageIcon(urlThem));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThem.setBounds(611, 205, 120, 30);
		pnlTop.add(btnThem);

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setBackground(new Color(255, 255, 255));
		btnLuu.setIcon(new ImageIcon(urlLuu));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLuu.setBounds(741, 205, 120, 30);
		pnlTop.add(btnLuu);
		
		JLabel lblTongTien = new JLabel("Tổng tiền (VND)");
		lblTongTien.setPreferredSize(new Dimension(64, 14));
		lblTongTien.setMinimumSize(new Dimension(64, 14));
		lblTongTien.setMaximumSize(new Dimension(64, 14));
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongTien.setBounds(166, 170, 110, 25);
		pnlTop.add(lblTongTien);
		
		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(276, 135, 150, 25);
		pnlTop.add(txtTenKH);
		
		JLabel lblSoTienCoc = new JLabel("Số tiền cọc");
		lblSoTienCoc.setPreferredSize(new Dimension(64, 14));
		lblSoTienCoc.setMinimumSize(new Dimension(64, 14));
		lblSoTienCoc.setMaximumSize(new Dimension(64, 14));
		lblSoTienCoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoTienCoc.setBounds(536, 65, 82, 25);
		pnlTop.add(lblSoTienCoc);
		
		spnTienCoc = new JSpinner();
		spnTienCoc.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(5000000)));
		spnTienCoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spnTienCoc.setBounds(642, 65, 150, 25);
		customJSpiner(spnTienCoc);
		pnlTop.add(spnTienCoc);
		
		JPanel pnlDanhSachHopDong = new JPanel();
		pnlDanhSachHopDong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch hợp đồng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDanhSachHopDong.setBackground(Color.WHITE);
		add(pnlDanhSachHopDong);
		pnlDanhSachHopDong.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTim = new JPanel();
		pnlTim.setLayout(null);
		pnlTim.setSize(new Dimension(0, 100));
		pnlTim.setPreferredSize(new Dimension(10, 35));
		pnlTim.setBackground(Color.WHITE);
		pnlDanhSachHopDong.add(pnlTim, BorderLayout.NORTH);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(341, 5, 226, 25);
		pnlTim.add(txtTimKiem);
		
		cmbLoc = new JComboBox<String>();
		cmbLoc.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Mã hợp đồng"}));
		cmbLoc.setBounds(80, 5, 116, 25);
		pnlTim.add(cmbLoc);
		
		JLabel lblLoc = new JLabel("Lọc");
		lblLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoc.setBounds(10, 5, 60, 25);
		pnlTim.add(lblLoc);
		
		btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBorderPainted(false);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTim.setIcon(new ImageIcon(urlTim));
		btnTim.setBounds(570, 5, 25, 25);
		pnlTim.add(btnTim); 
		btnTim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        timKiemHopDong();
		    }
		});

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		pnlDanhSachHopDong.add(scrollPane, BorderLayout.CENTER);
		
		String header[] = { "Mã HĐ", "Tên HĐ", "Khách hàng", "Tiền cọc", "Tổng tiền", "Ngày BĐ", "Ngày KT", "Ghi chú" };
		model = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblHopDong = new JTable(model);
		tblHopDong.setSelectionForeground(new Color(255, 255, 255));
		tblHopDong.setSelectionBackground(new Color(0, 128, 255));
		tblHopDong.setRowHeight(20);
		tblHopDong.setFillsViewportHeight(true);
		tblHopDong.setBackground(Color.WHITE);
		scrollPane.setViewportView(tblHopDong);
		
		dateChooserBatDau = new JDateChooser();
		dateChooserBatDau.setLocale(new Locale("vi", "VN"));
		dateChooserBatDau.setDateFormatString("dd/MM/yyyy");
		dateChooserBatDau.setDate(new Date());
		dateChooserBatDau.setBounds(642, 100, 150, 25);
		pnlTop.add(dateChooserBatDau);
		
		
		dateChooserKetThuc = new JDateChooser();
		dateChooserKetThuc.setLocale(new Locale("vi", "VN"));
		dateChooserKetThuc.setDateFormatString("dd/MM/yyyy");
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		dateChooserKetThuc.setDate(calendar.getTime());
		dateChooserKetThuc.setBounds(642, 135, 150, 25);
		pnlTop.add(dateChooserKetThuc);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayKetThuc.setBounds(536, 135, 142, 25);
		pnlTop.add(lblNgayKetThuc);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayBatDau.setBounds(536, 100, 142, 25);
		pnlTop.add(lblNgayBatDau);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 255));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLamMoi.setIcon(new ImageIcon(urlLamMoi));
		btnLamMoi.setBounds(481, 205, 120, 30);
		pnlTop.add(btnLamMoi);
		
		lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setPreferredSize(new Dimension(64, 14));
		lblGhiChu.setMinimumSize(new Dimension(64, 14));
		lblGhiChu.setMaximumSize(new Dimension(64, 14));
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGhiChu.setBounds(908, 65, 82, 25);
		pnlTop.add(lblGhiChu);
		
		txtGhiChu = new JTextField();
		txtGhiChu.setColumns(10);
		txtGhiChu.setBounds(982, 65, 150, 70);
		pnlTop.add(txtGhiChu);
		
		JLabel lblTBTenHD = new JLabel("");
		lblTBTenHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblTBTenHD.setIcon(new ImageIcon(urlTBFail));
		lblTBTenHD.setBackground(Color.WHITE);
		lblTBTenHD.setBounds(436, 100, 25, 25);
		pnlTop.add(lblTBTenHD);
		
		JLabel lblTBTenKH = new JLabel("");
		lblTBTenKH.setIcon(new ImageIcon(urlTBFail));
		lblTBTenKH.setHorizontalAlignment(SwingConstants.CENTER);
		lblTBTenKH.setBackground(Color.WHITE);
		lblTBTenKH.setBounds(436, 135, 25, 25);
		pnlTop.add(lblTBTenKH);
		
		lblTBNgayBD = new JLabel("");
		lblTBNgayBD.setIcon(new ImageIcon(urlTBFail));
		lblTBNgayBD.setHorizontalAlignment(SwingConstants.CENTER);
		lblTBNgayBD.setBackground(Color.WHITE);
		lblTBNgayBD.setBounds(802, 100, 25, 25);
		pnlTop.add(lblTBNgayBD);
		
		lblTBNgayKT = new JLabel("");
		lblTBNgayKT.setIcon(new ImageIcon(urlTBFail));
		lblTBNgayKT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTBNgayKT.setBackground(Color.WHITE);
		lblTBNgayKT.setBounds(802, 135, 25, 25);
		pnlTop.add(lblTBNgayKT);
		
		txtTenHD.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				btnThem.setEnabled(validData());
				btnLuu.setEnabled(validDataSave());
				if (valiDataTenHD()) {
					lblTBTenHD.setIcon(new ImageIcon(urlTBSuccess));
				} else {
					lblTBTenHD.setIcon(new ImageIcon(urlTBFail));
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				btnThem.setEnabled(validData());
				btnLuu.setEnabled(validDataSave());
				if (valiDataTenHD()) {
					lblTBTenHD.setIcon(new ImageIcon(urlTBSuccess));
				} else {
					lblTBTenHD.setIcon(new ImageIcon(urlTBFail));
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		txtTenKH.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				btnThem.setEnabled(validData());
				btnLuu.setEnabled(validDataSave());
				if (valiDataTenKH()) {
					lblTBTenKH.setIcon(new ImageIcon(urlTBSuccess));
				} else {
					lblTBTenKH.setIcon(new ImageIcon(urlTBFail));
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				btnThem.setEnabled(validData());
				btnLuu.setEnabled(validDataSave());
				if (valiDataTenKH()) {
					lblTBTenKH.setIcon(new ImageIcon(urlTBSuccess));
				} else {
					lblTBTenKH.setIcon(new ImageIcon(urlTBFail));
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		LoadDanhSachHopDong(hopDong_dao.getAllHopDong());
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this); 
		dateChooserBatDau.addPropertyChangeListener(this);
		dateChooserKetThuc.addPropertyChangeListener(this);
		tblHopDong.addMouseListener(this);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		tblHopDong.setRowSorter(sorter);
	} 
	
	private void customJSpiner(JSpinner X) {
		JComponent editor = X.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
			DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
			formatter.setOverwriteMode(true);
			textField.setHorizontalAlignment(JTextField.CENTER);
		}
	}
	
	private boolean valiDataTenHD() {
		String tenHD = txtTenHD.getText().trim();
		if (tenHD.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private boolean valiDataTenKH() {
		String tenKH = txtTenKH.getText().trim();
		if (tenKH.isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean validDataNgayBD() {
		if (dateChooserBatDau.getDate() == null) {
			return false;
		}
		Date ngayBD = (Date) dateChooserBatDau.getDate();
		Calendar currentDate = Calendar.getInstance();
		// Tạo một Calendar từ ngày bắt đầu
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(ngayBD);

		// Kiểm tra xem ngày bắt đầu có phải là ngày hiện tại không
	    boolean isCurrentDate = currentDate.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR) &&
	                            currentDate.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH) &&
	                            currentDate.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH);

	    return isCurrentDate;
	}
	
	private boolean validDataNgayKT() {
		if (dateChooserKetThuc.getDate() == null) {
			return false;
		}
		Date ngayKT = (Date) dateChooserKetThuc.getDate();
		Calendar currentDate = Calendar.getInstance();
		// Tạo một Calendar từ ngày bắt đầu
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(ngayKT);

		// Kiểm tra xem ngày kết thúc có sau ngày hiện tại không
	    boolean isAfterCurrentDate = endCalendar.after(currentDate);

	    return isAfterCurrentDate;
	}
	
	private boolean validData() {
		if (valiDataTenHD() && valiDataTenKH() && validDataNgayBD() && validDataNgayKT()) {
			return true;
		}
		return false;
	}
	
	private boolean validDataSave() {
		if (valiDataTenHD() && valiDataTenKH()) {
			return true;
		}
		return false;
	}
	
	private void LoadDanhSachHopDong(ArrayList<HopDong> dsHD) {
	    clearTable();   
	    
	    DecimalFormat format = new DecimalFormat("#,##0 VND");
	    for (HopDong hd : dsHD) {
	        model.addRow(new Object[] {
	                hd.getMaHD(),
	                hd,
	                hd.getTenKH(),
	                format.format(hd.getSoTienCoc()),
	                format.format(hd.getTongTienHD()),
	                hd.getNgayBatDau(),
	                hd.getNgayKetThuc(),
	                hd.getGhiChu()
	        });
	    }
	}
	
	private void fillForm() {
		int r = tblHopDong.getSelectedRow();
		if(r < 0) {
			return;
		}
		HopDong hd = (HopDong) tblHopDong.getValueAt(r, 1);
		if(hd != null) {
			txtMaHD.setText(hd.getMaHD());
			txtTenHD.setText(hd.getTenHD());
			txtTenKH.setText(hd.getTenKH());
			spnTienCoc.setValue(hd.getSoTienCoc());
			spnTongTien.setValue(hd.getTongTienHD());
			dateChooserBatDau.setDate(hd.getNgayBatDau());
			dateChooserKetThuc.setDate(hd.getNgayKetThuc());
			txtGhiChu.setText(hd.getGhiChu());
		}
	}

 
	public void themHD() {
		QuanLyMaHopDong qlmaHD = new QuanLyMaHopDong();
	    String maHD = txtMaHD.getText().trim();
	    String tenHD = txtTenHD.getText().trim();
	    String tenKH = txtTenKH.getText().trim();
	    double soTienCoc = Double.parseDouble(spnTienCoc.getValue().toString());
	    double tongTienHD = Double.parseDouble(spnTongTien.getValue().toString());
	    Date ngayBatDau = dateChooserBatDau.getDate();
	    Date ngayKetThuc = dateChooserKetThuc.getDate();
	    String ghiChu = txtGhiChu.getText().trim();  

	    if ( tenHD.isEmpty() || tenKH.isEmpty() || ngayBatDau == null || ngayKetThuc == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
	        return;
	    }

	    HopDong hopDong = new HopDong(maHD, tenHD, tenKH, soTienCoc, tongTienHD, ngayBatDau, ngayKetThuc, ghiChu);
	    hopDong.setMaHD(qlmaHD.generateMaHopDong());

	    boolean themThanhCong = hopDong_dao.themHopDong(hopDong);

	    if (themThanhCong) {
	        JOptionPane.showMessageDialog(this, "Thêm hợp đồng thành công.");
	        LoadDanhSachHopDong(hopDong_dao.getAllHopDong());
	    } else {
	        JOptionPane.showMessageDialog(this, "Thêm hợp đồng thất bại.");
	    }
	}

	public void suaHD() {
		if(JOptionPane.showConfirmDialog(this, "Bạn muốn lưu thay đổi", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			int r = tblHopDong.getSelectedRow();
			if(r < 0) {
				JOptionPane.showMessageDialog(this, "Chọn dòng muốn sửa");
				return;
			}
		    String maHD = txtMaHD.getText().trim();
		    String tenHD = txtTenHD.getText().trim();
		    String tenKH = txtTenKH.getText().trim();
		    String ghiChu = txtGhiChu.getText().trim();

		    double soTienCoc = Double.parseDouble(spnTienCoc.getValue().toString());
		    double tongTien = Double.parseDouble(spnTongTien.getValue().toString());

		    Date ngayBatDau = dateChooserBatDau.getDate();
		    Date ngayKetThuc = dateChooserKetThuc.getDate();

		    if (ngayBatDau == null || ngayKetThuc == null || ngayBatDau.after(ngayKetThuc)) {
		        JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc không hợp lệ.");
		        return;
		    }

		    HopDong hopDong = new HopDong(maHD, tenHD, tenKH, soTienCoc, tongTien, ngayBatDau, ngayKetThuc, ghiChu);

		    boolean capNhatThanhCong = hopDong_dao.suaHD(hopDong);

		    if (capNhatThanhCong) {
		        JOptionPane.showMessageDialog(this, "Cập nhật thông tin hợp đồng thành công.");
		        LoadDanhSachHopDong(hopDong_dao.getAllHopDong());
		    } else {
		        JOptionPane.showMessageDialog(this, "Cập nhật thông tin hợp đồng không thành công.");
		    }
		}
	}

	public void timKiemHopDong() {
	    String tuKhoa = txtTimKiem.getText().trim();
	    int luaChonLoc = cmbLoc.getSelectedIndex();
	    ArrayList<HopDong> ketQuaTimKiem = new ArrayList<>();

	    switch (luaChonLoc) {
	        case 0: // Tất cả
	            ketQuaTimKiem =	hopDong_dao.getAllHopDong();
	            break;
	        case 1: // Mã hợp đồng
	            ketQuaTimKiem = hopDong_dao.getHopDongByMa(tuKhoa);
	            break;
	        case 2: // Tên khách hàng
	            ketQuaTimKiem = hopDong_dao.getHopDongByTenKhachHang(tuKhoa);
	            break;
	        case 3: // Tổng tiền
	            try {
	                double tongTien = Double.parseDouble(tuKhoa);
	                ketQuaTimKiem = hopDong_dao.getHopDongByTongTien(tongTien);
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "Nhập số vào trường Tổng tiền để tìm kiếm.");
	                return;
	            }
	            break;
	        default:
	            break;
	    }

	    LoadDanhSachHopDong(ketQuaTimKiem);
	}

	private void clearTable() {
		int r = tblHopDong.getRowCount();
		while (r > 0) {
			model.removeRow(r - 1);
			r--;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			txtMaHD.setText("");
			txtTenHD.setText("");
			txtTenKH.setText("");
			txtGhiChu.setText("");
			spnTienCoc.setValue(0);
			spnTongTien.setValue(0);
			LoadDanhSachHopDong(hopDong_dao.getAllHopDong());
		}
	
		if (o.equals(btnThem)) {
			themHD();
		}
	
		if (o.equals(btnLuu)) {
			suaHD();
		} 
	}  

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object o = evt.getSource();
		if (o.equals(dateChooserBatDau)) {
			btnThem.setEnabled(validData());
			if (validDataNgayBD()) {
				lblTBNgayBD.setIcon(new ImageIcon(urlTBSuccess));
			} else {
				lblTBNgayBD.setIcon(new ImageIcon(urlTBFail));
			}
		}
		
		if (o.equals(dateChooserKetThuc)) {
			btnThem.setEnabled(validData());
			if (validDataNgayKT()) {
				lblTBNgayKT.setIcon(new ImageIcon(urlTBSuccess));
			} else {
				lblTBNgayKT.setIcon(new ImageIcon(urlTBFail));
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		fillForm();
		btnLuu.setEnabled(validDataSave());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


