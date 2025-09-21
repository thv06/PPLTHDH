package HDT;

import java.util.Scanner;

public class NhanVien {
    private String maSo;
    private String hoTen;
    private int soNgayCong;
    private char xepLoai;
    private static final int LUONG_NGAY = 200000;

    // Getter & Setter
    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        if (soNgayCong > 0) {
            this.soNgayCong = soNgayCong;
            this.xepLoai = tinhXepLoai();
        } else {
            System.out.println("❌ Số ngày công phải > 0!");
        }
    }

    public char getXepLoai() {
        return xepLoai;
    }

    // Tính xếp loại
    private char tinhXepLoai() {
        if (soNgayCong > 26) return 'A';
        else if (soNgayCong >= 22) return 'B';
        else return 'C';
    }

    // Tính lương
    public int tinhLuong() {
        return soNgayCong * LUONG_NGAY;
    }

    // Tính thưởng
    public double tinhThuong() {
        double luong = tinhLuong();
        switch (xepLoai) {
            case 'A': return luong * 0.05;
            case 'B': return luong * 0.02;
            default:  return 0;
        }
    }

    // Nhập thông tin nhân viên
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã số: ");
        maSo = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        hoTen = sc.nextLine();

        int ngayCong;
        do {
            System.out.print("Nhập số ngày công (>0): ");
            ngayCong = sc.nextInt();
        } while (ngayCong <= 0);
        setSoNgayCong(ngayCong);
    }

    // Xuất thông tin nhân viên
    public void xuat() {
        System.out.println("\n--- Thông tin nhân viên ---");
        System.out.println("Mã số: " + maSo);
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Số ngày công: " + soNgayCong);
        System.out.println("Xếp loại: " + xepLoai);
        System.out.println("Lương: " + tinhLuong());
        System.out.println("Thưởng: " + tinhThuong());
        System.out.println("Tổng thu nhập: " + (tinhLuong() + tinhThuong()));
    }

    // Main test
    public static void main(String[] args) {
        NhanVien nv = new NhanVien();
        nv.nhap();
        nv.xuat();
    }
}
