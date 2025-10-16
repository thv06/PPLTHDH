package InheritanceC3;

import java.util.*;

// class NhanVien {    <-- đổi tên lớp này thành NhanVienBase để tránh trùng tên với public class ở dưới
class NhanVienBase {
    protected String maNV;
    protected String tenNV;
    protected double heSoLuong;
    protected String chucVu;
    protected int soNgayNghi;
    protected int namVaoLam;

    // shared Scanner để tránh cảnh báo resource leak (không đóng System.in)
    protected static final Scanner SC = new Scanner(System.in);

    // Constructor mac dinh
    public NhanVienBase() {
        this.maNV = "000";
        this.tenNV = "Chua ro";
        this.heSoLuong = 1.0;
        this.chucVu = "Nhan vien";
        this.soNgayNghi = 1;
        this.namVaoLam = Calendar.getInstance().get(Calendar.YEAR);
    }

    // Constructor day du tham so
    public NhanVienBase(String maNV, String tenNV, double heSoLuong, String chucVu, int soNgayNghi, int namVaoLam) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.heSoLuong = heSoLuong;
        this.chucVu = chucVu;
        this.soNgayNghi = soNgayNghi;
        this.namVaoLam = namVaoLam;
    }

    // Phuong thuc tinh luong co ban
    public double tinhLuong() {
        double luongCoBan = heSoLuong * 1800000; // vi du
        return luongCoBan;
    }

    public void nhap() {
        System.out.print("Nhap ma nhan vien: ");
        maNV = SC.nextLine();
        System.out.print("Nhap ten nhan vien: ");
        tenNV = SC.nextLine();
        System.out.print("Nhap he so luong: ");
        heSoLuong = SC.nextDouble();
        SC.nextLine(); // bo dong
        System.out.print("Nhap chuc vu: ");
        chucVu = SC.nextLine();
        System.out.print("Nhap so ngay nghi: ");
        soNgayNghi = SC.nextInt();
        System.out.print("Nhap nam vao lam: ");
        namVaoLam = SC.nextInt();
    }

    public void xuat() {
        System.out.println("Ma NV: " + maNV + " | Ten: " + tenNV + " | He so: " + heSoLuong +
                " | Chuc vu: " + chucVu + " | Ngay nghi: " + soNgayNghi + " | Nam vao lam: " + namVaoLam);
    }
}

// Lop CanBo ke thua tu NhanVien
// class CanBo extends NhanVien {    <-- cập nhật để kế thừa NhanVienBase
class CanBo extends NhanVienBase {
    private String phongBan;
    private double heSoPhuCap;

    // Constructor mac dinh
    public CanBo() {
        super("CB001", "Mac dinh", 2.5, "Truong phong", 1, Calendar.getInstance().get(Calendar.YEAR));
        this.phongBan = "Hanh chinh";
        this.heSoPhuCap = 5.0;
    }

    // Constructor day du tham so
    public CanBo(String ma, String ten, double heSoLuong, String chucVu, int soNgayNghi,
                 int namVaoLam, String phongBan, double heSoPhuCap) {
        super(ma, ten, heSoLuong, chucVu, soNgayNghi, namVaoLam);
        this.phongBan = phongBan;
        this.heSoPhuCap = heSoPhuCap;
    }

    // Tinh phu cap
    public double tinhPhuCap() {
        return heSoPhuCap * heSoLuong * 1800000;
    }

    // Ghi de phuong thuc tinh luong
    @Override
    public double tinhLuong() {
        return super.tinhLuong() + tinhPhuCap();
    }

    // Nhap thong tin can bo
    @Override
    public void nhap() {
        super.nhap();
        System.out.print("Nhap phong ban: ");
        phongBan = SC.nextLine();
        System.out.print("Nhap he so phu cap lanh dao: ");
        heSoPhuCap = SC.nextDouble();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Phong ban: " + phongBan + " | He so phu cap: " + heSoPhuCap +
                " | Tong luong: " + tinhLuong());
    }
}

// Lop main de test
class NhanVienMain {
    public static void main(String[] args) {
        System.out.println("== Tao can bo mac dinh ==");
        CanBo cb1 = new CanBo();
        cb1.xuat();

        System.out.println("\n== Tao can bo nhap tu ban phim ==");
        CanBo cb2 = new CanBo();
        cb2.nhap();
        cb2.xuat();
    }
}

