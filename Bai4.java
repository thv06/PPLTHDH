package InheritanceC3;


// class NhanVien {
class NhanVienBai4 {
    private String maNV;
    private String tenNV;
    private double heSoLuong;
    public static final double LUONG_CO_BAN = 1150;

    // Constructor mac dinh
    public NhanVienBai4() {
        this.maNV = "NV001";
        this.tenNV = "Sinh Vien";
        this.heSoLuong = 2.34;
    }

    // Constructor co tham so
    public NhanVienBai4(String maNV, String tenNV, double heSoLuong) {
        if (maNV != null && maNV.length() >= 2 && maNV.substring(0, 2).equalsIgnoreCase("NV")) {
            this.maNV = maNV;
        } else {
            this.maNV = "NV001";
        }
        this.tenNV = tenNV;
        this.heSoLuong = heSoLuong;
    }

    // Phuong thuc tinh thu nhap
    public double thuNhap() {
        return heSoLuong * LUONG_CO_BAN;
    }

    // Phuong thuc xuat thong tin
    public void xuat() {
        System.out.println("Ma NV: " + maNV + 
                           ", Ten NV: " + tenNV + 
                           ", He so: " + heSoLuong + 
                           ", Thu nhap: " + thuNhap());
    }

    // Getter - Setter
    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }
}

// Lop can bo lanh dao
class CBLanhDao extends NhanVienBai4 {
    private String chucVu;
    private int thamNienQL;

    // Constructor khong tham so
    public CBLanhDao() {
        super("NV009", "Dieu Hien", 4.67);
        this.chucVu = "Giam doc";
        this.thamNienQL = 10;
    }

    // Constructor co tham so
    public CBLanhDao(String maNV, String tenNV, double heSoLuong, String chucVu, int thamNienQL) {
        super(maNV, tenNV, heSoLuong);
        this.chucVu = chucVu;
        this.thamNienQL = thamNienQL;
    }

    // Tinh thu nhap cho can bo lanh dao
    @Override
    public double thuNhap() {
        double heSoLanhDao;
        switch (chucVu.toLowerCase()) {
            case "giam doc":
                heSoLanhDao = 7.0;
                break;
            case "truong phong":
                heSoLanhDao = 6.0;
                break;
            case "pho phong":
                heSoLanhDao = 4.5;
                break;
            default:
                heSoLanhDao = 1.0;
                break;
        }
        double phuCap = 1500 * heSoLanhDao;
        // Thêm phụ cấp theo thâm niên quản lý để sử dụng thamNienQL
        phuCap += thamNienQL * 100; // mỗi năm thâm niên cộng 100 đơn vị (tùy chỉnh nếu cần)
        return super.thuNhap() + phuCap;
    }

    // Phuong thuc xuat thong tin
    @Override
    public void xuat() {
        System.out.println("Ma NV: " + getMaNV() +
                           ", Ten NV: " + getTenNV() +
                           ", He so: " + getHeSoLuong() +
                           ", Thu nhap: " + thuNhap() +
                           ", Chuc vu: " + chucVu);
    }
}

// Lop kiem thu
public class Bai4 {
    public static void main(String[] args) {
        // cập nhật sử dụng lớp NhanVienBai4
        NhanVienBai4 nv1 = new NhanVienBai4("NV002", "Nguyen Van A", 3.0);
        nv1.xuat();

        CBLanhDao ld1 = new CBLanhDao();
        ld1.xuat();

        CBLanhDao ld2 = new CBLanhDao("NV010", "Tran B", 5.0, "Truong phong", 7);
        ld2.xuat();
    }
}
