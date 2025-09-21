package HDT;

import java.util.Scanner;

class Time {
    private int gio;
    private int phut;
    private int giay;

    // ===== 3 Hàm khởi tạo =====
    public Time() {
        this.gio = 0;
        this.phut = 0;
        this.giay = 0;
    }

    public Time(int gio, int phut, int giay) {
        setTime(gio, phut, giay);
    }

    public Time(Time t) {
        this.gio = t.gio;
        this.phut = t.phut;
        this.giay = t.giay;
    }

    // ===== Setter kiểm tra giá trị =====
    public void setTime(int gio, int phut, int giay) {
        this.gio = (gio >= 0 && gio <= 23) ? gio : 0;
        this.phut = (phut >= 0 && phut <= 59) ? phut : 0;
        this.giay = (giay >= 0 && giay <= 59) ? giay : 0;
    }

    // ===== Kiểm tra giờ có hợp lệ không =====
    public boolean hopLe() {
        return (gio >= 0 && gio <= 23) &&
               (phut >= 0 && phut <= 59) &&
               (giay >= 0 && giay <= 59);
    }

    // ===== Nhập thời gian dạng 24h =====
    public void nhap24h() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập giờ (0-23): ");
        int h = sc.nextInt();
        System.out.print("Nhập phút (0-59): ");
        int m = sc.nextInt();
        System.out.print("Nhập giây (0-59): ");
        int s = sc.nextInt();
        setTime(h, m, s);
    }

    // ===== Xuất thời gian dạng 24h =====
    public void xuat24h() {
        System.out.printf("%02d:%02d:%02d\n", gio, phut, giay);
    }

    // ===== Xuất thời gian dạng 12h (AM/PM) =====
    public void xuat12h() {
        String kieu = (gio < 12) ? "AM" : "PM";
        int h = gio % 12;
        if (h == 0) h = 12; 
        System.out.printf("%02d:%02d:%02d %s\n", h, phut, giay, kieu);
    }

    // ===== Hàm tăng giờ =====
    // Tăng theo số giây (24h)
    public void tangGio(int soGiay) {
        int totalSeconds = gio * 3600 + phut * 60 + giay + soGiay;
        totalSeconds = ((totalSeconds % 86400) + 86400) % 86400; // Giới hạn trong 24h
        gio = totalSeconds / 3600;
        phut = (totalSeconds % 3600) / 60;
        giay = totalSeconds % 60;
    }

    // Tăng theo số giây, xuất dạng 12h/24h
    public void tangGio(int soGiay, String kieuGio) {
        tangGio(soGiay);
        if (kieuGio.equalsIgnoreCase("24h")) {
            xuat24h();
        } else if (kieuGio.equalsIgnoreCase("12h")) {
            xuat12h();
        } else {
            System.out.println("Kiểu giờ không hợp lệ (chỉ nhận 12h hoặc 24h).");
        }
    }

    // ===== Hàm giảm giờ =====
    public void giamGio(int soGiay) {
        tangGio(-soGiay); // Dùng lại logic của tăng giờ
    }

    public void giamGio(int soGiay, String kieuGio) {
        giamGio(soGiay);
        if (kieuGio.equalsIgnoreCase("24h")) {
            xuat24h();
        } else if (kieuGio.equalsIgnoreCase("12h")) {
            xuat12h();
        } else {
        	System.out.println("Kiểu giờ không hợp lệ (chỉ nhận 12h hoặc 24h).");
        }
    }


// ===== Lớp Main để kiểm tra =====
    public static void main(String[] args) {
        Time t1 = new Time(23, 59, 50);

        System.out.println("Thời gian ban đầu (24h): ");
        t1.xuat24h();

        System.out.println("Thời gian ban đầu (12h): ");
        t1.xuat12h();

        System.out.println("\nTăng 20 giây (24h): ");
        t1.tangGio(20);
        t1.xuat24h();

        System.out.println("\nTăng 3600 giây (1 giờ) theo kiểu 12h: ");
        t1.tangGio(3600, "12h");

        System.out.println("\nGiảm 120 giây (2 phút) theo kiểu 24h: ");
        t1.giamGio(120, "24h");
    }
}
       