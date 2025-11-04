import java.util.ArrayList;
import java.util.List;

// =================================================================
// 1. INTERFACE: IPhi
// Định nghĩa phương thức tính phí kinh doanh (Chỉ áp dụng cho Khách sạn và Biệt thự)
// =================================================================
interface IPhi {
    double phiKinhDoanh();
}

// =================================================================
// 2. LỚP TRỪU TƯỢNG: BatDongSan (Lớp Cha)
// Chứa các thuộc tính chung và phương thức trừu tượng/logic chung
// =================================================================
abstract class BatDongSan {
    protected double ChieuRong;
    protected double ChieuDai;
    private String MaSo;

    // Constructor chung cho lớp cha
    public BatDongSan(String MaSo, double ChieuRong, double ChieuDai) {
        this.MaSo = MaSo;
        this.ChieuRong = ChieuRong;
        this.ChieuDai = ChieuDai;
    }

    // Abstract method: Bắt buộc các lớp con phải triển khai logic tính giá bán
    public abstract double giaBan();

    // Logic chung: Phương thức tính diện tích
    public double dienTich() {
        return ChieuDai * ChieuRong;
    }

    // Getter cho MaSo (cần thiết cho xuất thông tin)
    public String getMaSo() {
        return MaSo;
    }
    
    // Phương thức xuất thông tin chung (dùng cho tất cả các loại BĐS)
    public void xuatThongTin() {
        // Khởi tạo các giá trị chung
        double giaBan = this.giaBan();
        double phiKinhDoanh = 0.0;
        String loaiBDS = this.getClass().getSimpleName();
        
        // Tính phí kinh doanh nếu đối tượng implement IPhi
        if (this instanceof IPhi) {
            phiKinhDoanh = ((IPhi) this).phiKinhDoanh();
        }

        System.out.printf("Loai: %-10s | Ma so: %-5s | DT: %-8.2f | Gia ban: %-12.2f | Phi KD: %-10.2f | Tong tien: %.2f\n",
                loaiBDS, MaSo, dienTich(), giaBan, phiKinhDoanh, giaBan + phiKinhDoanh);
    }
}

// =================================================================
// 3. LỚP CON: DatTrong
// =================================================================
class DatTrong extends BatDongSan {
    
    public DatTrong(String MaSo, double ChieuRong, double ChieuDai) {
        super(MaSo, ChieuRong, ChieuDai);
    }

    @Override
    public double giaBan() {
        // Đất trống: giá bán = diện tích * 10.000
        return dienTich() * 10000;
    }
}

// =================================================================
// 4. LỚP CON: NhaO
// =================================================================
class NhaO extends BatDongSan {
    private int SoLau;

    public NhaO(String MaSo, double ChieuRong, double ChieuDai, int SoLau) {
        super(MaSo, ChieuRong, ChieuDai);
        this.SoLau = SoLau;
    }

    @Override
    public double giaBan() {
        // Nhà ở: giá bán = diện tích * 10.000 + số lầu * 100.000
        return dienTich() * 10000 + SoLau * 100000;
    }
}

// =================================================================
// 5. LỚP CON: KhachSan
// Kế thừa BatDongSan VÀ triển khai IPhi
// =================================================================
class KhachSan extends BatDongSan implements IPhi {
    private int SoSao;

    public KhachSan(String MaSo, double ChieuRong, double ChieuDai, int SoSao) {
        super(MaSo, ChieuRong, ChieuDai);
        this.SoSao = SoSao;
    }

    @Override
    public double phiKinhDoanh() {
        // Khách sạn: Phí KD = Chiều rộng * 5000 (Sửa lỗi theo yêu cầu của bạn, không phải 10000 + Sosao*5000)
        // Yêu cầu (46CB717C-296F-41B0-B438-DDAB1F9DDFC3.jpg): Phí KD = Chiều rộng * 5000
        return ChieuRong * 5000;
    }

    @Override
    public double giaBan() {
        // Khách sạn: giá bán = 100.000 + số sao * 50.000
        return 100000 + SoSao * 50000;
    }
}

// =================================================================
// 6. LỚP CON: BietThu
// Kế thừa BatDongSan VÀ triển khai IPhi
// =================================================================
class BietThu extends BatDongSan implements IPhi {
    
    public BietThu(String MaSo, double ChieuRong, double ChieuDai) {
        super(MaSo, ChieuRong, ChieuDai);
    }

    @Override
    public double phiKinhDoanh() {
        // Biệt thự: Phí KD = Diện tích * 1000
        return dienTich() * 1000;
    }

    @Override
    public double giaBan() {
        // Biệt thự: giá bán = diện tích * 400.000
        return dienTich() * 400000;
    }
}

// =================================================================
// 7. LỚP CHẠY CHÍNH (MAIN)
// =================================================================
public class QuanLyBatDongSan {

    public static void main(String[] args) {
        // Tạo danh sách chung để quản lý tất cả các loại BĐS (Đa hình)
        List<BatDongSan> danhSachBDS = new ArrayList<>();
        
        // Thêm các đối tượng (Diện tích đều là 10*20 = 200m2)
        danhSachBDS.add(new DatTrong("DT01", 10, 20));          // Đất trống
        danhSachBDS.add(new NhaO("NO02", 10, 20, 3));           // Nhà ở 3 lầu
        danhSachBDS.add(new KhachSan("KS03", 10, 20, 4));        // Khách sạn 4 sao (Chiều rộng 10)
        danhSachBDS.add(new BietThu("BT04", 10, 20));           // Biệt thự

        System.out.println("====== UNG DUNG QUAN LY BAT DONG SAN ======");
        System.out.println("Chi tiet: DT=Dien tich, Phi KD=Phi Kinh doanh");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        double tongGiaTri = 0.0;

        for (BatDongSan bds : danhSachBDS) {
            bds.xuatThongTin();
            
            // Tính tổng giá trị (Giá bán + Phí kinh doanh)
            double giaTri = bds.giaBan();
            if (bds instanceof IPhi) {
                giaTri += ((IPhi) bds).phiKinhDoanh();
            }
            tongGiaTri += giaTri;
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("TONG GIA TRI TAT CA CAC BAT DONG SAN: %.2f\n", tongGiaTri);
    }
}