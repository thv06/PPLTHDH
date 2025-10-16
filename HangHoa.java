package InheritanceC3;

import java.util.Scanner;

// ------------------- LOP HANG HOA -------------------
class HangHoa {
    protected String maHang;
    protected String tenHang;

    // Phuong thuc khoi tao mac dinh
    public HangHoa() {
        this.maHang = "";
        this.tenHang = "";
    }

    // Phuong thuc khoi tao co tham so
    public HangHoa(String maHang, String tenHang) {
        if (kiemTraMa(maHang))
            this.maHang = maHang;
        else
            this.maHang = "HH001";
        this.tenHang = tenHang;
    }

    // Ham kiem tra ma hang hop le
    private boolean kiemTraMa(String ma) {
        if (ma.length() == 5 && ma.substring(0, 2).equals("HH")) {
            String so = ma.substring(2);
            return so.matches("\\d{3}");
        }
        return false;
    }

    // Xuat thong tin hang hoa
    public void xuat() {
        System.out.printf("Ma hang: %s | Ten hang: %s\n", maHang, tenHang);
    }
}

// ------------------- LOP NUOC GIAI KHAT -------------------
class NuocGiaiKhat extends HangHoa {
    private String donViTinh;
    private int soLuong;
    private double donGia;
    private static double tyLeChietKhau = 0.9; // cong ty quy dinh

    // Phuong thuc khoi tao mac dinh
    public NuocGiaiKhat() {
        super();
        this.donViTinh = "ket";
        this.soLuong = 0;
        this.donGia = 0;
    }

    // Phuong thuc khoi tao co tham so
    public NuocGiaiKhat(String maHang, String tenHang, String donViTinh, int soLuong, double donGia) {
        super(maHang, tenHang);
        if (donViTinh.equals("ket") || donViTinh.equals("thung") ||
            donViTinh.equals("chai") || donViTinh.equals("lon"))
            this.donViTinh = donViTinh;
        else
            this.donViTinh = "ket";
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Tinh tong tien
    public double tongTien() {
        double thanhTien;
        if (donViTinh.equals("ket") || donViTinh.equals("thung"))
            thanhTien = soLuong * donGia;
        else if (donViTinh.equals("chai"))
            thanhTien = soLuong * donGia / 20;
        else if (donViTinh.equals("lon"))
            thanhTien = soLuong * donGia / 24;
        else
            thanhTien = 0;
        return thanhTien * tyLeChietKhau;
    }

    // Xuat thong tin
    @Override
    public void xuat() {
        System.out.printf("Ma hang: %s | Ten: %s | DVT: %s | So luong: %d | Don gia: %.2f | Tong tien: %.2f\n",
                maHang, tenHang, donViTinh, soLuong, donGia, tongTien());
    }

    // Nhap thong tin
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma hang: ");
        String ma = sc.nextLine();
        if (ma != null && ma.length() == 5 && ma.substring(0, 2).equals("HH") && ma.substring(2).matches("\\d{3}"))
            this.maHang = ma;
        else
            this.maHang = "HH001";

        System.out.print("Nhap ten hang: ");
        this.tenHang = sc.nextLine();

        System.out.print("Nhap don vi tinh (ket/thung/chai/lon): ");
        this.donViTinh = sc.nextLine();
        if (!(donViTinh.equals("ket") || donViTinh.equals("thung") || donViTinh.equals("chai") || donViTinh.equals("lon")))
            this.donViTinh = "ket";

        // Read soLuong safely
        while (true) {
            System.out.print("Nhap so luong: ");
            String line = sc.nextLine();
            if (line == null) { this.soLuong = 0; break; }
            line = line.trim();
            if (line.isEmpty()) continue;
            try {
                this.soLuong = Integer.parseInt(line);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("So luong khong hop le. Vui long nhap so nguyen.");
            }
        }

        // Read donGia safely
        while (true) {
            System.out.print("Nhap don gia: ");
            String line = sc.nextLine();
            if (line == null) { this.donGia = 0.0; break; }
            line = line.trim();
            if (line.isEmpty()) continue;
            try {
                this.donGia = Double.parseDouble(line);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Don gia khong hop le. Vui long nhap so.");
            }
        }
    }
}

// ------------------- Runner for HangHoa examples -------------------
class HangHoaMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== NHAP THONG TIN NUOC GIAI KHAT ===");
        NuocGiaiKhat n1 = new NuocGiaiKhat();
        n1.nhap(sc);

        System.out.println("\n=== THONG TIN NUOC GIAI KHAT ===");
        n1.xuat();

        sc.close();
    }
}
