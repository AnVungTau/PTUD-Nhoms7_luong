package entity;

import java.util.Date;
import java.util.Objects;

public class LuongCongNhan {
	private String maLuong;
	private CongNhan cn;
	private NhanVien nv;
	private Date ngayCham;
	private int tongLuong;
	private String ghiChu;
	public String getMaLuong() {
		return maLuong;
	}
	public void setMaLuong(String maLuong) {
		this.maLuong = maLuong;
	}
	public CongNhan getCn() {
		return cn;
	}
	public void setCn(CongNhan cn) {
		this.cn = cn;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	public Date getNgayCham() {
		return ngayCham;
	}
	public void setNgayCham(Date ngayCham) {
		this.ngayCham = ngayCham;
	}
	public int getTongLuong() {
		return tongLuong;
	}
	public void setTongLuong(int tongLuong) {
		this.tongLuong = tongLuong;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLuong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuongCongNhan other = (LuongCongNhan) obj;
		return Objects.equals(maLuong, other.maLuong);
	}
	@Override
	public String toString() {
		return "LuongCongNhan [maLuong=" + maLuong + ", cn=" + cn + ", nv=" + nv + ", ngayCham=" + ngayCham
				+ ", tongLuong=" + tongLuong + ", ghiChu=" + ghiChu + "]";
	}
	public LuongCongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LuongCongNhan(String maLuong, CongNhan cn, NhanVien nv, Date ngayCham, int tongLuong, String ghiChu) {
		super();
		this.maLuong = maLuong;
		this.cn = cn;
		this.nv = nv;
		this.ngayCham = ngayCham;
		this.tongLuong = tongLuong;
		this.ghiChu = ghiChu;
	}
	
}
