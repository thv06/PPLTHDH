import java.util.Arrays;
import java.util.List;

// =================================================================
// A. LOP NGUOI (Lớp cha)
// =================================================================
class Nguoi {
    // ... (Giữ nguyên code lớp Nguoi) ...
    protected String hoTen;
    protected String ngaySinh;
    protected String gioiTinh; 
    private static final String GIOI_TINH_MAC_DINH = "nam";

    public Nguoi() {
        this.hoTen = "Chua co ten";
        this.ngaySinh = "01/01/2000";
        this.gioiTinh = GIOI_TINH_MAC_DINH;
    }

    public Nguoi(String hoTen, String ngaySinh, String gioiTinh) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = xacDinhGioiTinh(gioiTinh);
    }
    
    private String xacDinhGioiTinh(String inputGioiTinh) {
        if (inputGioiTinh != null && 
            (inputGioiTinh.equalsIgnoreCase("nam") || inputGioiTinh.equalsIgnoreCase("nu"))) {
            return inputGioiTinh.toLowerCase();
        }
        return GIOI_TINH_MAC_DINH;
    }

    public void xuatMotNguoi() {
        System.out.printf("Ho ten: %s | Ngay sinh: %s | Gioi tinh: %s\n", 
                          hoTen, ngaySinh, gioiTinh);
    }
}

// =================================================================
// B. LOP GIANG VIEN (Lớp con)
// =================================================================
class GiangVien extends Nguoi {
    
    private String maSo;
    private double luongCoBan;
    private double heSo;
    private String chucVu;
    
    private static final List<Double> HE_SO_HOP_LE = Arrays.asList(2.34, 2.67, 3.0, 3.3);
    private static final double HE_SO_MAC_DINH = 2.34;
    
    // SỬA LỖI: Thay đổi phạm vi truy cập từ private sang public
    public static final String CHUC_VU_TRUONG_KHOA = "truong khoa";
    public static final String CHUC_VU_PHO_KHOA = "pho khoa";
    private static final String CHUC_VU_GIANG_VIEN = "giang vien"; // Cái này không dùng trong main nên giữ nguyên
    
    public GiangVien(String hoTen, String ngaySinh, String gioiTinh, 
                     String maSo, double luongCoBan, double heSo, String chucVu) {
        
        super(hoTen, ngaySinh, gioiTinh); 
        
        this.maSo = maSo;
        this.luongCoBan = luongCoBan;
        
        // Thiet lap He so
        if (HE_SO_HOP_LE.contains(heSo)) {
            this.heSo = heSo;
        } else {
            this.heSo = HE_SO_MAC_DINH;
        }
        
        // Thiet lap Chuc vu
        if (chucVu != null) {
            String cv = chucVu.toLowerCase();
            if (cv.equals(CHUC_VU_TRUONG_KHOA) || cv.equals(CHUC_VU_PHO_KHOA)) {
                this.chucVu = cv;
            } else {
                this.chucVu = CHUC_VU_GIANG_VIEN;
            }
        } else {
             this.chucVu = CHUC_VU_GIANG_VIEN;
        }
    }

    private double tinhPhuCapChucVu() {
        if (chucVu.equals(CHUC_VU_TRUONG_KHOA)) {
            return 5 * luongCoBan;
        } else if (chucVu.equals(CHUC_VU_PHO_KHOA)) {
            return 4 * luongCoBan;
        } else {
            return 0.0;
        }
    }

    public double tinhLuong() {
        double phuCap = tinhPhuCapChucVu();
        return luongCoBan * heSo + phuCap;
    }

    public void xuatGiangVien() {
        System.out.println("--- THONG TIN GIANG VIEN ---");
        super.xuatMotNguoi();
        System.out.printf("Ma so: %s | Luong CB: %.2f | He so: %.2f | Chuc vu: %s\n", 
                          maSo, luongCoBan, heSo, chucVu);
        System.out.printf("Phu cap CV: %.2f | Tong Luong: %.2f\n", 
                          tinhPhuCapChucVu(), tinhLuong());
    }
}

// Lớp chứa hàm main để chạy và kiểm tra
public class Bai2 {
    public static void main(String[] args) {
        
        // ... (Giữ nguyên kiểm tra lớp Nguoi) ...
        
        // 1. KIEM TRA LOP NGUOI
        System.out.println("====== KIEM TRA LOP NGUOI ======");
        Nguoi n1 = new Nguoi();
        System.out.print("Nguoi 1 (Mac dinh): ");
        n1.xuatMotNguoi();
        Nguoi n2 = new Nguoi("Nguyen Van A", "10/05/1985", "NU");
        System.out.print("Nguoi 2 (Hop le): ");
        n2.xuatMotNguoi();
        Nguoi n3 = new Nguoi("Tran Thi B", "01/01/1990", "khong xac dinh");
        System.out.print("Nguoi 3 (Khong hop le): ");
        n3.xuatMotNguoi();
        
        System.out.println("\n");
        
        // 2. KIEM TRA LOP GIANG VIEN
        System.out.println("====== KIEM TRA LOP GIANG VIEN ======");
        
        // Lỗi đã được sửa bằng cách truy cập các hằng số qua tên lớp GiangVien.
        
        // GV 1: Truong Khoa - He so hop le (3.3)
        // Lưu ý: Bây giờ cần truy cập hằng số thông qua tên lớp: GiangVien.CHUC_VU_TRUONG_KHOA
        GiangVien gv1 = new GiangVien("Le Thi Kim", "05/03/1970", "nu", 
                                       "GV001", 1000.0, 3.3, GiangVien.CHUC_VU_TRUONG_KHOA);
        gv1.xuatGiangVien();
        
        // GV 2: Pho Khoa - He so khong hop le (4.0) -> mac dinh 2.34
        GiangVien gv2 = new GiangVien("Pham Van C", "20/12/1975", "nam", 
                                       "GV002", 1200.0, 4.0, GiangVien.CHUC_VU_PHO_KHOA);
        gv2.xuatGiangVien();
        
        // GV 3: Giang Vien - He so hop le (2.67) - Khong co PCCV
        GiangVien gv3 = new GiangVien("Hoang Van D", "15/07/1980", "nam", 
                                       "GV003", 1500.0, 2.67, "tham gia");
        gv3.xuatGiangVien();
    }
}