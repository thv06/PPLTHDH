package InheritanceC3;

import java.util.Calendar;
import java.util.Scanner;

// class NhanVienABC {
class NhanVienABCBase {
    private String maNV;
    private String tenNV;
    private int namVaoLam;
    private double heSoLuong;
    private int soNgayNghi;
    public static final double LUONG_CO_BAN = 1150;

    // Shared Scanner để tránh Resource leak (không đóng System.in)
    private static final Scanner SC = new Scanner(System.in);

    // Constructor khong tham so
    public NhanVienABCBase() {
        this.maNV = "NV001";
        this.tenNV = "Sinh Vien";
        this.namVaoLam = Calendar.getInstance().get(Calendar.YEAR);
        this.heSoLuong = 2.34;
        this.soNgayNghi = 0;
    }

    // Constructor co tham so
    public NhanVienABCBase(String maNV, String tenNV, double heSoLuong) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.heSoLuong = heSoLuong;
        this.namVaoLam = Calendar.getInstance().get(Calendar.YEAR);
        this.soNgayNghi = 0;
    }

    // Constructor day du
    public NhanVienABCBase(String maNV, String tenNV, int namVaoLam, double heSoLuong, int soNgayNghi) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.namVaoLam = namVaoLam;
        this.heSoLuong = heSoLuong;
        this.soNgayNghi = soNgayNghi;
    }

    // Tinh phu cap tham nien
    public double phuCapThamNien() {
        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
        int soNamLamViec = namHienTai - namVaoLam;
        if (soNamLamViec >= 5) {
            return soNamLamViec * LUONG_CO_BAN / 100;
        } else {
            return 0;
        }
    }

    // Xet thi dua
    public String xepLoaiThiDua() {
        if (soNgayNghi <= 1)
            return "A";
        else if (soNgayNghi <= 3)
            return "B";
        else
            return "C";
    }

    // Tinh luong
    public double tinhLuong() {
        String loai = xepLoaiThiDua();
        double heSoThiDua;
        if (loai.equals("A"))
            heSoThiDua = 1.0;
        else if (loai.equals("B"))
            heSoThiDua = 0.75;
        else
            heSoThiDua = 0.5;
        return LUONG_CO_BAN * heSoLuong * heSoThiDua + phuCapThamNien();
    }

    // Nhap thong tin
    public void nhap() {
        System.out.print("Nhap ma nhan vien: ");
        maNV = SC.nextLine();
        System.out.print("Nhap ten nhan vien: ");
        tenNV = SC.nextLine();
        System.out.print("Nhap nam vao lam: ");
        namVaoLam = SC.nextInt();
        System.out.print("Nhap he so luong: ");
        heSoLuong = SC.nextDouble();
        System.out.print("Nhap so ngay nghi: ");
        soNgayNghi = SC.nextInt();
        SC.nextLine(); // consume endline if needed
    }

    // Xuat thong tin
    public void xuat() {
        System.out.println("Ma NV: " + maNV +
                           ", Ten NV: " + tenNV +
                           ", Nam vao lam: " + namVaoLam +
                           ", He so luong: " + heSoLuong +
                           ", So ngay nghi: " + soNgayNghi +
                           ", Xep loai: " + xepLoaiThiDua() +
                           ", Phu cap: " + phuCapThamNien() +
                           ", Luong: " + tinhLuong());
    }
}

// Lop kiem thu
public class NhanVienABC {
    public static void main(String[] args) {
        NhanVienABCBase nv1 = new NhanVienABCBase();
        nv1.xuat();

        NhanVienABCBase nv2 = new NhanVienABCBase("NV005", "Tran Van B", 2015, 3.5, 2);
        nv2.xuat();

        NhanVienABCBase nv3 = new NhanVienABCBase();
        nv3.nhap();
        nv3.xuat();
    }
}
