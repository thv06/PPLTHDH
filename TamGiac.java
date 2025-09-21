package HDT;

import java.util.Scanner;

public class TamGiac {
    private double a, b, c;

    public TamGiac(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double tinhChuVi() {
        return a + b + c;
    }

    public double tinhDienTich() {
        double p = tinhChuVi() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public void hienThi() {
        System.out.println("Cạnh a = " + a + ", b = " + b + ", c = " + c);
        System.out.println("Chu vi tam giác = " + tinhChuVi());
        System.out.println("Diện tích tam giác = " + tinhDienTich());
    }

    // Hàm main để chạy TamGiac
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TAM GIÁC ===");
        System.out.print("Nhập cạnh a: ");
        double a = sc.nextDouble();
        System.out.print("Nhập cạnh b: ");
        double b = sc.nextDouble();
        System.out.print("Nhập cạnh c: ");
        double c = sc.nextDouble();

        if (a + b > c && a + c > b && b + c > a) {
            TamGiac tg = new TamGiac(a, b, c);
            tg.hienThi();
        } else {
            System.out.println("Ba cạnh không lập thành tam giác!");
        }

        sc.close();
    }
}
