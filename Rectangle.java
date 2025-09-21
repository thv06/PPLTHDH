package HDT;

import java.util.Scanner;

class Rectangle {
    private int length;
    private int width;

    // Constructor không tham số
    public Rectangle() {
        this.length = 0;
        this.width = 0;
    }

    // Constructor có tham số
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    // Tính chu vi
    public int getPerimeter() {
        return 2 * (length + width);
    }

    // Tính diện tích
    public int getArea() {
        return length * width;
    }

    // Tính đường chéo
    public double getDiagonal() {
        return Math.sqrt(length * length + width * width);
    }

    // Xuất thông tin
    public void display() {
        System.out.println("Chiều dài: " + length);
        System.out.println("Chiều rộng: " + width);
        System.out.println("Chu vi: " + getPerimeter());
        System.out.println("Diện tích: " + getArea());
        System.out.println("Đường chéo: " + getDiagonal());
    }

    // Nhập thông tin
    public void input(Scanner sc) {
        System.out.print("Nhập chiều dài: ");
        length = sc.nextInt();
        System.out.print("Nhập chiều rộng: ");
        width = sc.nextInt();
    }

    // Hàm thay đổi kích thước overload
    public void changeSize(int tx, int ty) {
        length += tx;
        width += ty;
    }

    // Hàm thay đổi kích thước theo kiểu
    public void changeSize(int kieu) {
        if (kieu == 1) { // giảm
            length--;
            width--;
        } else if (kieu == 0) { // tăng
            length++;
            width++;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Rectangle hcn = new Rectangle();
        hcn.input(sc);

        System.out.println("\n--- Thông tin ban đầu ---");
        hcn.display();

        // Thử changeSize overload
        hcn.changeSize(2, 3);
        System.out.println("\n--- Sau khi changeSize(tx, ty) ---");
        hcn.display();

        // Thử changeSize theo kiểu
        hcn.changeSize(0); // tăng
        System.out.println("\n--- Sau khi changeSize(kieu=0 - tăng) ---");
        hcn.display();

        hcn.changeSize(1); // giảm
        System.out.println("\n--- Sau khi changeSize(kieu=1 - giảm) ---");
        hcn.display();

        sc.close();
    }
}