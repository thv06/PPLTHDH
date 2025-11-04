public class SinhVien {
    // Thuộc tính của thí sinh
    private String soBaoDanh;
    private String hoTen;
    private int namSinh;
    private double diemToan;
    private double diemVan;
    private double diemNgoaiNgu;

    // Thuộc tính điểm chuẩn (dùng chung cho tất cả thí sinh)
    private static double diemChuan = 25;

    // ===== a) 3 phương thức khởi tạo =====
    // 1. Khởi tạo mặc định
    public SinhVien() {
        this.soBaoDanh = "";
        this.hoTen = "";
        this.namSinh = 0;
        this.diemToan = 0;
        this.diemVan = 0;
        this.diemNgoaiNgu = 0;
    }

    // 2. Khởi tạo với SBD, họ tên và năm sinh
    public SinhVien(String soBaoDanh, String hoTen, int namSinh) {
        this.soBaoDanh = soBaoDanh;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    // 3. Khởi tạo đầy đủ thông tin
    public SinhVien(String soBaoDanh, String hoTen, int namSinh,
                    double diemToan, double diemVan, double diemNgoaiNgu) {
        this.soBaoDanh = soBaoDanh;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.diemToan = diemToan;
        this.diemVan = diemVan;
        this.diemNgoaiNgu = diemNgoaiNgu;
    }

    // ===== b) Property tính tổng điểm =====
    public double getTongDiem() {
        // Tổng điểm = Toán + Văn + Ngoại ngữ * 2
        return diemToan + diemVan + (diemNgoaiNgu * 2);
    }

    // ===== c) Property kết quả =====
    public String getKetQua() {
        // Nếu tổng điểm >= điểm chuẩn => Đậu, ngược lại => Rớt
        return getTongDiem() >= diemChuan ? "Dau" : "Rot";
    }

    // ===== d) Phương thức xuất thông tin thí sinh =====
    public void xuat() {
        System.out.println("=== Thong tin thi sinh ===");
        System.out.println("So bao danh: " + soBaoDanh);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Nam sinh: " + namSinh);
        System.out.println("Diem Toan: " + diemToan);
        System.out.println("Diem Van: " + diemVan);
        System.out.println("Diem Ngoai ngu: " + diemNgoaiNgu);
        System.out.println("Tong diem: " + getTongDiem());
        System.out.println("Ket qua: " + getKetQua());
        System.out.println("===========================\n");
    }

    // Getter và Setter cho các thuộc tính
    public String getSoBaoDanh() {
        return soBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        this.soBaoDanh = soBaoDanh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public double getDiemToan() {
        return diemToan;
    }

    public void setDiemToan(double diemToan) {
        this.diemToan = diemToan;
    }

    public double getDiemVan() {
        return diemVan;
    }

    public void setDiemVan(double diemVan) {
        this.diemVan = diemVan;
    }

    public double getDiemNgoaiNgu() {
        return diemNgoaiNgu;
    }

    public void setDiemNgoaiNgu(double diemNgoaiNgu) {
        this.diemNgoaiNgu = diemNgoaiNgu;
    }

    // Getter và Setter cho diemChuan
    public static void setDiemChuan(double diem) {
        diemChuan = diem;
    }

    public static double getDiemChuan() {
        return diemChuan;
    }

    // Phương thức main để kiểm thử
    public static void main(String[] args) {
        // Tạo 2 thí sinh để minh họa
        SinhVien sv1 = new SinhVien("001", "Tran A", 2006, 8.5, 7.5, 9.0);
        SinhVien sv2 = new SinhVien("002", "Nguyen B", 2006, 6.0, 7.0, 5.5);

        // Xuất thông tin
        sv1.xuat();
        sv2.xuat();
    }
}
