import java.util.*;

abstract class NhanVien {
    protected String maNV;
    protected String tenNV;
    protected int namSinh;
    protected String gioiTinh;
    protected double heSoLuong;
    protected int namLamViec;
    protected char xepLoai; // A,B,C,D
    protected double luongCoBan = 1800000; // co the thay doi neu can

    public NhanVien(String maNV, String tenNV, int namSinh, String gioiTinh, double heSoLuong, int namLamViec) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.heSoLuong = heSoLuong;
        this.namLamViec = namLamViec;
        this.xepLoai = 'D';
    }

    public abstract void tinhXepLoai();
    public abstract double tinhLuong();

    protected double phuCapThamNien() {
        if (namLamViec >= 5) {
            return (namLamViec * luongCoBan) / 100.0;
        }
        return 0;
    }

    public String getMaNV() { return maNV; }
    public String getTenNV() { return tenNV; }
    public char getXepLoai() { return xepLoai; }

    public boolean coNangLuc() {
        return xepLoai == 'A';
    }

    public boolean nangLucTot() {
        return xepLoai == 'A' || xepLoai == 'B';
    }

    @Override
    public String toString() {
        return String.format("%s | %s | Xep loai: %c | Luong: %.0f", maNV, tenNV, xepLoai, tinhLuong());
    }
}

// ================== NHAN VIEN SAN XUAT ==================
class NVSanXuat extends NhanVien {
    private int soNgayNghi;
    private static final double PHU_CAP_NANG_NHOC = 0.1; // 10%

    public NVSanXuat(String maNV, String tenNV, int namSinh, String gioiTinh, double heSoLuong, int namLamViec, int soNgayNghi) {
        super(maNV, tenNV, namSinh, gioiTinh, heSoLuong, namLamViec);
        this.soNgayNghi = soNgayNghi;
    }

    @Override
    public void tinhXepLoai() {
        if (soNgayNghi == 0) xepLoai = 'A';
        else if (soNgayNghi <= 3) xepLoai = 'B';
        else if (soNgayNghi <= 5) xepLoai = 'C';
        else xepLoai = 'D';
    }

    @Override
    public double tinhLuong() {
        tinhXepLoai();
        double luongCoBanTheoHeSo = heSoLuong * luongCoBan * (1 + PHU_CAP_NANG_NHOC);
        double phuCap = phuCapThamNien();
        double thuNhap = 0;
        switch (xepLoai) {
            case 'A': thuNhap = luongCoBanTheoHeSo + phuCap; break;
            case 'B': thuNhap = luongCoBanTheoHeSo * 0.75 + phuCap; break;
            case 'C': thuNhap = luongCoBanTheoHeSo * 0.5 + phuCap; break;
            case 'D': thuNhap = phuCap; break;
        }
        return thuNhap;
    }
}

// ================== NHAN VIEN KINH DOANH ==================
class NVKinhDoanh extends NhanVien {
    private double doanhSo;
    private double doanhSoToiThieu;

    public NVKinhDoanh(String maNV, String tenNV, int namSinh, String gioiTinh, double heSoLuong, int namLamViec,
                       double doanhSo, double doanhSoToiThieu) {
        super(maNV, tenNV, namSinh, gioiTinh, heSoLuong, namLamViec);
        this.doanhSo = doanhSo;
        this.doanhSoToiThieu = doanhSoToiThieu;
    }

    @Override
    public void tinhXepLoai() {
        if (doanhSo >= 2 * doanhSoToiThieu) xepLoai = 'A';
        else if (doanhSo >= doanhSoToiThieu) xepLoai = 'B';
        else if (doanhSo >= 0.5 * doanhSoToiThieu) xepLoai = 'C';
        else xepLoai = 'D';
    }

    @Override
    public double tinhLuong() {
        tinhXepLoai();
        double hoaHong = 0.15 * doanhSoToiThieu;
        double phuCap = phuCapThamNien();
        double luongCoBanTheoHeSo = heSoLuong * luongCoBan;
        double thuNhap = 0;
        switch (xepLoai) {
            case 'A': thuNhap = luongCoBanTheoHeSo + phuCap + hoaHong; break;
            case 'B': thuNhap = luongCoBanTheoHeSo * 0.75 + phuCap + hoaHong; break;
            case 'C': thuNhap = luongCoBanTheoHeSo * 0.5 + phuCap + hoaHong; break;
            case 'D': thuNhap = phuCap + hoaHong; break;
        }
        return thuNhap;
    }
}

// ================== CAN BO QUAN LY ==================
class NVQuanLy extends NhanVien {
    private double heSoChucVu;

    public NVQuanLy(String maNV, String tenNV, int namSinh, String gioiTinh, double heSoLuong, int namLamViec, double heSoChucVu) {
        super(maNV, tenNV, namSinh, gioiTinh, heSoLuong, namLamViec);
        this.heSoChucVu = heSoChucVu;
        this.xepLoai = 'A'; // Quan ly luon A
    }

    @Override
    public void tinhXepLoai() {
        // Quan ly luon A
    }

    @Override
    public double tinhLuong() {
        double phuCapThamNien = phuCapThamNien();
        double phuCapChucVu = heSoChucVu * 1100;
        return heSoLuong * luongCoBan + phuCapThamNien + phuCapChucVu;
    }
}

// ================== NHAN VIEN CONG TY BCD ==================
class NVBCD {
    private String maNV;
    private String tenNV;
    private double tongTienTaoRa;

    public NVBCD(String maNV, String tenNV, double tongTienTaoRa) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tongTienTaoRa = tongTienTaoRa;
    }

    public boolean laChiendich() {
        return tongTienTaoRa >= 20000000;
    }

    public boolean laNangLucTot() {
        return tongTienTaoRa >= 10000000;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | Tong tien: %.0f", maNV, tenNV, tongTienTaoRa);
    }
}

// ================== LOP CONG TY ==================
class CongTy {
    private String tenCongTy;
    private List<NhanVien> dsABC = new ArrayList<>();
    private List<NVBCD> dsBCD = new ArrayList<>();

    public CongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public void themNVABC(NhanVien nv) {
        dsABC.add(nv);
    }

    public void themNVBCD(NVBCD nv) {
        dsBCD.add(nv);
    }

    public void thongKe() {
        System.out.println("===== " + tenCongTy + " =====");
        System.out.println("So nhan vien ABC: " + dsABC.size());
        System.out.println("So nhan vien BCD: " + dsBCD.size());

        long coNangLucABC = dsABC.stream().filter(NhanVien::coNangLuc).count();
        long nangLucTotABC = dsABC.stream().filter(NhanVien::nangLucTot).count();
        long nangLucTotBCD = dsBCD.stream().filter(NVBCD::laNangLucTot).count();
        long chienDichBCD = dsBCD.stream().filter(NVBCD::laChiendich).count();

        System.out.println("ABC co nang luc: " + coNangLucABC);
        System.out.println("ABC nang luc tot: " + nangLucTotABC);
        System.out.println("BCD nang luc tot: " + nangLucTotBCD);
        System.out.println("BCD chien dich (nang luc cao): " + chienDichBCD);
    }

    public void inDanhSachABC() {
        System.out.println("\n--- Danh sach ABC ---");
        dsABC.forEach(nv -> System.out.println(nv));
    }

    public void inDanhSachBCD() {
        System.out.println("\n--- Danh sach BCD ---");
        dsBCD.forEach(nv -> System.out.println(nv));
    }
}

// ================== MAIN ==================
public class CongTyT {
    public static void main(String[] args) {
        CongTy ct = new CongTy("Tong Cong Ty T");

        // Them nhan vien ABC
        ct.themNVABC(new NVSanXuat("SX01", "Nguyen Van A", 1990, "Nam", 2.0, 6, 1));
        ct.themNVABC(new NVKinhDoanh("KD01", "Tran Thi B", 1992, "Nu", 2.5, 4, 6000000, 5000000));
        ct.themNVABC(new NVQuanLy("QL01", "Le Van C", 1985, "Nam", 3.0, 10, 1.2));

        // Them nhan vien BCD
        ct.themNVBCD(new NVBCD("BCD01", "Hoang Thi D", 12000000));
        ct.themNVBCD(new NVBCD("BCD02", "Pham Van E", 25000000));
        ct.themNVBCD(new NVBCD("BCD03", "Do Thi F", 8000000));

        // Thong ke
        ct.inDanhSachABC();
        ct.inDanhSachBCD();
        ct.thongKe();
    }
}
