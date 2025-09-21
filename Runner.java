package HDT;

import java.util.Scanner;

class Runner {
    private String maSo;
    private String hoTen;
    private String soBaoDanh;
    private int gioBD, phutBD, giayBD; // Thời gian bắt đầu
    private int gioKT, phutKT, giayKT; // Thời gian kết thúc

    // Thành tích chuẩn (3 giờ)
    private final int CHUAN_GIO = 3, CHUAN_PHUT = 0, CHUAN_GIAY = 0;

    // ====== Constructor ======
    public Runner() {}

    public Runner(String maSo, String hoTen, String soBaoDanh,
                  int gioBD, int phutBD, int giayBD,
                  int gioKT, int phutKT, int giayKT) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.soBaoDanh = soBaoDanh;
        this.gioBD = gioBD; this.phutBD = phutBD; this.giayBD = giayBD;
        this.gioKT = gioKT; this.phutKT = phutKT; this.giayKT = giayKT;
    }

    // ====== Hàm nhập thông tin ======
    public void nhap(Scanner sc) {
        System.out.print("Nhập mã số: ");
        maSo = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        hoTen = sc.nextLine();
        System.out.print("Nhập số báo danh: ");
        soBaoDanh = sc.nextLine();
        System.out.print("Nhập giờ bắt đầu (h m s): ");
        gioBD = sc.nextInt(); phutBD = sc.nextInt(); giayBD = sc.nextInt();
        System.out.print("Nhập giờ kết thúc (h m s): ");
        gioKT = sc.nextInt(); phutKT = sc.nextInt(); giayKT = sc.nextInt();
        sc.nextLine(); // bỏ dòng thừa
    }

    // ====== Xuất thông tin ======
    public void xuat() {
        System.out.println("----- Thông tin vận động viên -----");
        System.out.println("Mã số: " + maSo);
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Số báo danh: " + soBaoDanh);
        System.out.printf("Bắt đầu: %02d:%02d:%02d\n", gioBD, phutBD, giayBD);
        System.out.printf("Kết thúc: %02d:%02d:%02d\n", gioKT, phutKT, giayKT);
        System.out.println("Thành tích: " + tinhThanhTich());
        System.out.println("Đạt chuẩn: " + (datChuan() ? "Có" : "Không"));
    }

    // ====== Hàm tính thành tích ======
    public String tinhThanhTich() {
        int start = gioBD * 3600 + phutBD * 60 + giayBD;
        int end   = gioKT * 3600 + phutKT * 60 + giayKT;
        int diff = end - start;

        if (diff < 0) return "Thời gian không hợp lệ!";

        int h = diff / 3600;
        int m = (diff % 3600) / 60;
        int s = diff % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    // ====== Hàm kiểm tra đạt chuẩn ======
    public boolean datChuan() {
        int start = gioBD * 3600 + phutBD * 60 + giayBD;
        int end   = gioKT * 3600 + phutKT * 60 + giayKT;
        int diff = end - start;

        int chuan = CHUAN_GIO * 3600 + CHUAN_PHUT * 60 + CHUAN_GIAY;
        return diff > 0 && diff <= chuan;
    }

    // ====== Kiểm tra giờ hợp lệ (0–24h) ======
    public boolean hopLe() {
    	return checkTime(gioBD, phutBD, giayBD) && checkTime(gioKT, phutKT, giayKT);
    }

    private boolean checkTime(int h, int m, int s) {
        return (h >= 0 && h < 24 && m >= 0 && m < 60 && s >= 0 && s < 60);
    }

// ================== MAIN ==================

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Runner r = new Runner();
        r.nhap(sc);

        if (!r.hopLe()) {
            System.out.println(">> Giờ nhập không hợp lệ!");
        } else {
            r.xuat();
        }
    }
}
    