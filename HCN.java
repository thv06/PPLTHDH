package HDT;

import java.util.Scanner;

public class HCN {
    private double chieuDai;
    private double chieuRong;

    public HCN(double chieuDai, double chieuRong) {
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }

    public double tinhChuVi() {
        return 2 * (chieuDai + chieuRong);
    }

    public double tinhDienTich() {
        return chieuDai * chieuRong;
    }

    public void hienThi() {
        System.out.println("Chiều dài: " + chieuDai + ", Chiều rộng: " + chieuRong);
        System.out.println("Chu vi HCN = " + tinhChuVi());
        System.out.println("Diện tích HCN = " + tinhDienTich());
    }

    // Hàm main để chạy HCN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== HÌNH CHỮ NHẬT ===");
        System.out.print("Nhập chiều dài: ");
        double dai = sc.nextDouble();
        System.out.print("Nhập chiều rộng: ");
        double rong = sc.nextDouble();

        HCN hcn = new HCN(dai, rong);
        hcn.hienThi();

        sc.close();
    }
}
