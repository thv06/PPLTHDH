package HDT;
import java.util.Scanner;

class NuocGiaiKhat {
    private String tenHang;
    private String donViTinh;
    private int soLuong;
    private float donGia;
    private static float thueVAT; // Thuế VAT dùng chung cho tất cả các loại nước giải khát

    // Các giá trị hợp lệ của đơn vị tính
    private static final String[] DVT_HOP_LE = {"két", "thùng", "chai", "lon"};

    // Hàm khởi tạo
    public NuocGiaiKhat(String tenHang, String donViTinh, int soLuong, float donGia, float vat) {
        this.tenHang = tenHang;
        this.soLuong = (soLuong > 0) ? soLuong : 1;
        this.donGia = (donGia > 0) ? donGia : 1;
        setDonViTinh(donViTinh); 
        thueVAT = vat;
    }

    // Hàm hủy (Java có garbage collector, chỉ viết phương thức finalize để minh họa)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Đối tượng nước giải khát đã được hủy!");
    }

    // Setter cho đơn vị tính (nếu không hợp lệ thì gán là "két")
    public void setDonViTinh(String dvt) {
        boolean hopLe = false;
        for (String x : DVT_HOP_LE) {
            if (x.equalsIgnoreCase(dvt)) {
                hopLe = true;
                break;
            }
        }
        this.donViTinh = hopLe ? dvt.toLowerCase() : "két";
    }

    // Tính thành tiền
    public float tinhThanhTien() {
        float thanhTien = 0;
        switch (donViTinh) {
            case "két":
            case "thùng":
                thanhTien = soLuong * donGia * thueVAT;
                break;
            case "chai":
                thanhTien = soLuong * (donGia / 20) * thueVAT;
                break;
            case "lon":
                thanhTien = soLuong * (donGia / 24) * thueVAT;
                break;
            default:
                thanhTien = soLuong * donGia * thueVAT;
        }
        return thanhTien;
    }

    // Nhập thông tin
    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên hàng: ");
        tenHang = sc.nextLine();
        System.out.print("Nhập đơn vị tính (két/thùng/chai/lon): ");
        setDonViTinh(sc.nextLine());
        System.out.print("Nhập số lượng: ");
        soLuong = sc.nextInt();
        System.out.print("Nhập đơn giá: ");
        donGia = sc.nextFloat();
    }

    // Xuất thông tin
    public void xuatThongTin() {
        System.out.println("Tên hàng: " + tenHang);
        System.out.println("Đơn vị tính: " + donViTinh);
        System.out.println("Số lượng: " + soLuong);
        System.out.println("Đơn giá: " + donGia);
        System.out.println("Thuế VAT: " + thueVAT);
        System.out.println("Thành tiền: " + tinhThanhTien());
    }

    public static void main(String[] args) {
        NuocGiaiKhat nuoc1 = new NuocGiaiKhat("Coca Cola", "chai", 50, 100000, 1.1f);
        nuoc1.xuatThongTin();

        System.out.println("\n--- Nhập thông tin nước giải khát khác ---");
        NuocGiaiKhat nuoc2 = new NuocGiaiKhat("", "", 0, 0, 1.1f);
        nuoc2.nhapThongTin();
        nuoc2.xuatThongTin();
    }
}
