import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// =================================================================
// 1. LỚP TRỪU TƯỢNG: NhanVien
// =================================================================
abstract class NhanVien {
    
    protected String ma;
    protected String ten;
    protected String gioiTinh;
    protected int namVaoLam; 
    protected int soNgayNghi; 
    protected double hsLuong; 
    
    protected static final double LUONG_CO_BAN = 1150.0;
    
    public NhanVien(String ma, String ten, String gioiTinh, int namVaoLam, int soNgayNghi, double hsLuong) {
        this.ma = ma;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.namVaoLam = namVaoLam;
        this.soNgayNghi = soNgayNghi;
        this.hsLuong = hsLuong;
    }
    
    // Getters
    public String getMa() { return ma; }
    public String getTen() { return ten; }
    public String getGioiTinh() { return gioiTinh; }
    public int getNamVaoLam() { return namVaoLam; }
    public int getSoNgayNghi() { return soNgayNghi; }
    public double getHsLuong() { return hsLuong; }

    // Phương thức trừu tượng
    public abstract char XepLoai();
    public abstract double TinhLuong();
    
    // Logic chung: Tinh phu cap tham nien
    public double PhuCapThamNien() {
        int namHienTai = LocalDate.now().getYear();
        int soNamCongTac = namHienTai - namVaoLam; 
        
        if (soNamCongTac >= 5) {
            return soNamCongTac * (LUONG_CO_BAN / 100.0);
        }
        return 0.0; 
    }
    
    // Logic chung: Tinh tong thu nhap
    public double ThuNhap() {
        char xepLoai = XepLoai();
        double hsThiDua = 0.0;
        
        if (xepLoai == 'A') hsThiDua = 1.0;
        else if (xepLoai == 'B') hsThiDua = 0.75;
        else if (xepLoai == 'C') hsThiDua = 0.5;
        else if (xepLoai == 'D') hsThiDua = 0.0;
        
        return hsThiDua * TinhLuong() + PhuCapThamNien();
    }
}

// =================================================================
// 2. LỚP CON: NhanVienSX (Nhân Viên Sản Xuất)
// =================================================================
class NhanVienSX extends NhanVien {
    
    public static final double hsPhuCapNangNhoc = 0.1; 
    
    public NhanVienSX(String ma, String ten, String gioiTinh, int namVaoLam, int soNgayNghi, double hsLuong) {
        super(ma, ten, gioiTinh, namVaoLam, soNgayNghi, hsLuong);
    }
    
    @Override
    public char XepLoai() {
        if (soNgayNghi <= 1) return 'A';
        if (soNgayNghi <= 3) return 'B';
        if (soNgayNghi <= 5) return 'C';
        return 'D'; 
    }
    
    @Override
    public double TinhLuong() {
        return hsLuong * LUONG_CO_BAN * (1.0 + hsPhuCapNangNhoc);
    }
}

// =================================================================
// 3. LỚP CON: NhanVienKD (Nhân Viên Kinh Doanh)
// =================================================================
class NhanVienKD extends NhanVien {
    
    private double doanhSoBanHang; 
    private static final double DOANH_SO_TOI_THIEU = 2.0;

    public NhanVienKD(String ma, String ten, String gioiTinh, int namVaoLam, int soNgayNghi, double hsLuong, double doanhSoBanHang) {
        super(ma, ten, gioiTinh, namVaoLam, soNgayNghi, hsLuong);
        this.doanhSoBanHang = doanhSoBanHang;
    }
    
    // Sửa lỗi: Thêm Getter cho doanhSoBanHang
    public double getDoanhSoBanHang() {
        return doanhSoBanHang;
    }
    
    @Override
    public char XepLoai() {
        if (doanhSoBanHang >= DOANH_SO_TOI_THIEU) return 'A';
        if (doanhSoBanHang >= 0.5 * DOANH_SO_TOI_THIEU) return 'B';
        return 'D';
    }
    
    @Override
    public double TinhLuong() {
        double hoaHong = 0.0;
        
        if (doanhSoBanHang > DOANH_SO_TOI_THIEU) {
            hoaHong = (doanhSoBanHang - DOANH_SO_TOI_THIEU) * 0.15; // 15%
        }
        
        return hsLuong * LUONG_CO_BAN + hoaHong;
    }
}

// =================================================================
// 4. LỚP CON: CanBoQL (Cán Bộ Quản Lý)
// =================================================================
class CanBoQL extends NhanVien {
    
    private double hsPhuCapChucVu; 
    private static final double PHU_CAP_CO_BAN_QL = 1100.0;
    
    public CanBoQL(String ma, String ten, String gioiTinh, int namVaoLam, int soNgayNghi, double hsLuong, double hsPhuCapChucVu) {
        super(ma, ten, gioiTinh, namVaoLam, soNgayNghi, hsLuong);
        this.hsPhuCapChucVu = hsPhuCapChucVu;
    }
    
    // Sửa lỗi: Thêm Getter cho hsPhuCapChucVu
    public double getHsPhuCapChucVu() {
        return hsPhuCapChucVu;
    }

    @Override
    public char XepLoai() {
        return 'A';
    }
    
    @Override
    public double TinhLuong() {
        return hsLuong * LUONG_CO_BAN + hsPhuCapChucVu * PHU_CAP_CO_BAN_QL;
    }
}


// =================================================================
// 5. LỚP CHÍNH: QLNhanVienToanTap (Lớp Quản Lý và Hàm main)
// =================================================================
public class QLNhanVienToanTap {
    
    private List<NhanVien> danhSachNV;

    public QLNhanVienToanTap() {
        this.danhSachNV = new ArrayList<>();
    }
    
    public void themNhanVien(NhanVien nv) {
        this.danhSachNV.add(nv);
    }

    public double tinhTongLuongCongTy() {
        return danhSachNV.stream()
            .mapToDouble(NhanVien::ThuNhap) 
            .sum();
    }
    
    public void xuatTatCaNhanVien() {
        if (danhSachNV.isEmpty()) {
            System.out.println("Danh sach nhan vien trong.");
            return;
        }
        
        // Hien thi nam hien tai de thong tin ve phu cap tham nien co y nghia
        System.out.println("\n--- DANH SACH NHAN VIEN CONG TY ABC (Nam " + LocalDate.now().getYear() + ") ---");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ma NV | Ten NV        | Gioi Tinh  | Loai NV  | HS Luong | Chi Tiet(Ngày/DS/PC) | Nam VL | PC Th.Nien | Xep Loai | Tinh Luong | Thu Nhap");
        System.out.println("------|---------------|------------|----------|----------|----------------------|--------|------------|----------|------------|---------");
        
        for (NhanVien nv : danhSachNV) {
            String loaiNV = "";
            String chiTiet = "";
            double luongTinh = nv.TinhLuong();
            
            if (nv instanceof NhanVienSX) {
                loaiNV = "SX";
                chiTiet = String.format("%-20d", nv.getSoNgayNghi());
            } else if (nv instanceof NhanVienKD) {
                loaiNV = "KD";
                NhanVienKD nvkd = (NhanVienKD) nv;
                // Sửa lỗi: Gọi phương thức Getter
                chiTiet = String.format("%.2f (DS)", nvkd.getDoanhSoBanHang());
            } else if (nv instanceof CanBoQL) {
                loaiNV = "QL";
                CanBoQL cbql = (CanBoQL) nv;
                // Sửa lỗi: Gọi phương thức Getter
                chiTiet = String.format("HS PC: %.2f", cbql.getHsPhuCapChucVu());
            }
            
            System.out.printf("%-5s | %-13s | %-10s | %-8s | %-8.2f | %-20s | %-6d | %-10.2f | %-8c | %-10.2f | %-8.2f\n",
                nv.getMa(), nv.getTen(), nv.getGioiTinh(), loaiNV, nv.getHsLuong(), 
                chiTiet, nv.getNamVaoLam(), nv.PhuCapThamNien(), nv.XepLoai(), luongTinh, nv.ThuNhap());
        }
    }
    
    // CHUONG TRINH CHINH DE TEST
    public static void main(String[] args) {
        QLNhanVienToanTap congTy = new QLNhanVienToanTap();
        int namHienTai = LocalDate.now().getYear();

        // Thêm dữ liệu mẫu (Sử dụng namHienTai để tính PC Tham niên chính xác)
        
        // NVSanXuat (namVaoLam, soNgayNghi, hsLuong)
        congTy.themNhanVien(new NhanVienSX("NV001", "A Van San", "Nam", namHienTai - 6, 1, 4.0)); 
        congTy.themNhanVien(new NhanVienSX("NV002", "B Thi San", "Nu", namHienTai - 4, 4, 3.5)); 
        congTy.themNhanVien(new NhanVienSX("NV003", "C Thi San", "Nu", namHienTai - 5, 3, 3.0)); 

        // NVKinhDoanh (namVaoLam, hsLuong, doanhSo)
        congTy.themNhanVien(new NhanVienKD("NV004", "D Van Doanh", "Nam", namHienTai - 10, 0, 5.0, 2.5)); 
        congTy.themNhanVien(new NhanVienKD("NV005", "E Thi Doanh", "Nu", namHienTai - 2, 0, 4.5, 1.5)); 
        congTy.themNhanVien(new NhanVienKD("NV006", "F Thi Doanh", "Nu", namHienTai - 6, 0, 4.0, 0.5)); 
        
        // CanBoQL (namVaoLam, hsLuong, hsPhuCapChucVu)
        congTy.themNhanVien(new CanBoQL("NV007", "G Van QL", "Nam", namHienTai - 7, 0, 6.0, 1.5)); 
        congTy.themNhanVien(new CanBoQL("NV008", "H Thi QL", "Nu", namHienTai - 3, 0, 5.5, 1.2)); 


        System.out.println("===============================================================");
        System.out.println("CHUONG TRINH QUAN LY NHAN VIEN CONG TY ABC");
        System.out.printf("Gia tri Luong co ban (tieu chuan): %.2f\n", NhanVien.LUONG_CO_BAN);
        System.out.println("===============================================================");
        
        // Xuat tat ca nhan vien
        congTy.xuatTatCaNhanVien();

        // Tinh tong luong cong ty phai tra
        System.out.println("\n===============================================================");
        double tongLuong = congTy.tinhTongLuongCongTy();
        System.out.printf("TONG THU NHAP CONG TY PHAI CHI TRA: %.2f\n", tongLuong);
        System.out.println("===============================================================");
    }
}