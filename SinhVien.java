package InheritanceC3;

import java.util.Scanner;

class NguoiABC {
    protected String hoTen;
    protected String ngaySinh;
    protected String gioiTinh;

    // Shared Scanner để tránh Resource leak (không đóng System.in)
    protected static final Scanner SC = new Scanner(System.in);

    // Constructor mac dinh
    public NguoiABC() {
        this.hoTen = "Nguyen Van A";
        this.ngaySinh = "01/01/2000";
        this.gioiTinh = "nam";
    }

    // Constructor co tham so
    public NguoiABC(String hoTen, String ngaySinh, String gioiTinh) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        if (gioiTinh.equalsIgnoreCase("nam") || gioiTinh.equalsIgnoreCase("nu")) {
            this.gioiTinh = gioiTinh.toLowerCase();
        } else {
            this.gioiTinh = "nam";
        }
    }

    // Phuong thuc xuat
    public void xuat() {
        System.out.println("Ho ten: " + hoTen + ", Ngay sinh: " + ngaySinh + ", Gioi tinh: " + gioiTinh);
    }
}

class SinhVienABC extends NguoiABC {
    private String maSV;
    private String heDaoTao;
    private int tongSoTinChi;

    // Constructor khong tham so
    public SinhVienABC() {
        super();
        this.maSV = "SV001";
        this.heDaoTao = "dai hoc";
        this.tongSoTinChi = 150;
    }

    // Constructor co tham so
    public SinhVienABC(String hoTen, String ngaySinh, String gioiTinh, String maSV, String heDaoTao) {
        super(hoTen, ngaySinh, gioiTinh);
        this.maSV = maSV;
        setHeDaoTao(heDaoTao);
    }

    // Ham kiem tra va gan he dao tao
    public void setHeDaoTao(String heDaoTao) {
        heDaoTao = heDaoTao.toLowerCase();
        switch (heDaoTao) {
            case "dai hoc":
                this.heDaoTao = "dai hoc";
                this.tongSoTinChi = 150;
                break;
            case "cao dang":
                this.heDaoTao = "cao dang";
                this.tongSoTinChi = 100;
                break;
            case "cao dang nghe":
                this.heDaoTao = "cao dang nghe";
                this.tongSoTinChi = 130;
                break;
            default:
                this.heDaoTao = "dai hoc";
                this.tongSoTinChi = 150;
        }
    }

    // Tinh tong hoc phi
    public double tinhTongHocPhi() {
        double hocPhiTinChi;
        switch (heDaoTao) {
            case "dai hoc":
                hocPhiTinChi = 200000;
                break;
            case "cao dang":
                hocPhiTinChi = 150000;
                break;
            case "cao dang nghe":
                hocPhiTinChi = 120000;
                break;
            default:
                hocPhiTinChi = 200000;
        }
        return tongSoTinChi * hocPhiTinChi;
    }

    // Nhap thong tin sinh vien
    public void nhap() {
        System.out.print("Nhap ho ten: ");
        hoTen = SC.nextLine();
        System.out.print("Nhap ngay sinh: ");
        ngaySinh = SC.nextLine();
        System.out.print("Nhap gioi tinh (nam/nu): ");
        gioiTinh = SC.nextLine();
        System.out.print("Nhap ma sinh vien: ");
        maSV = SC.nextLine();
        System.out.print("Nhap he dao tao (dai hoc / cao dang / cao dang nghe): ");
        heDaoTao = SC.nextLine();
        setHeDaoTao(heDaoTao);
    }

    // Xuat thong tin sinh vien
    public void xuat() {
        super.xuat();
        System.out.println("Ma SV: " + maSV +
                           ", He dao tao: " + heDaoTao +
                           ", Tong so tin chi: " + tongSoTinChi +
                           ", Tong hoc phi: " + tinhTongHocPhi());
    }
}

public class SinhVien {
    public static void main(String[] args) {
        System.out.println("=== Sinh vien mac dinh ===");
        SinhVienABC sv1 = new SinhVienABC();
        sv1.xuat();

        System.out.println("\n=== Sinh vien co tham so ===");
        SinhVienABC sv2 = new SinhVienABC("Tran Thi B", "05/09/2003", "nu", "SV005", "cao dang");
        sv2.xuat();

        System.out.println("\n=== Nhap thong tin sinh vien ===");
        SinhVienABC sv3 = new SinhVienABC();
        sv3.nhap();
        sv3.xuat();
    }
}