import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// -----------------------------------------------------------------
// LOP 1: Diem (Dai dien cho mot diem trong khong gian Oxy)
// -----------------------------------------------------------------
class Diem {
    private double x;
    private double y;

    public Diem(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    
    // Tinh khoang cach tu diem den goc toa do O(0,0)
    public double tinhKhoangCachToiGoc() {
        // Khoang cach: sqrt(x^2 + y^2)
        return Math.sqrt(x * x + y * y);
    }
    
    // Hien thi diem
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f) - Khoang cach toi O: %.4f", x, y, tinhKhoangCachToiGoc());
    }
}

// -----------------------------------------------------------------
// LOP 2: QuanLyDiem (Quan ly danh sach cac diem va thuc hien cac yeu cau)
// -----------------------------------------------------------------
public class Bai3 {
    private List<Diem> danhSachDiem;

    public Bai3() {
        this.danhSachDiem = new ArrayList<>();
    }

    // Phuong thuc ho tro lay gia tri tu the XML
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    
    // ---------------------- CAC CHUC NANG CHINH ----------------------

    // 1. Xay dung phuong thuc nhap n diem tu ban phim
    public void nhapNDiemTuBanPhim() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhap so luong diem (n) can them: ");
            int n;
            try {
                n = Integer.parseInt(scanner.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.println("Nhap so luong khong hop le.");
                return;
            }

            for (int i = 0; i < n; i++) {
                try {
                    System.out.println("Nhap toa do diem thu " + (i + 1) + ":");
                    System.out.print("X = ");
                    double x = Double.parseDouble(scanner.nextLine());
                    System.out.print("Y = ");
                    double y = Double.parseDouble(scanner.nextLine());
                    danhSachDiem.add(new Diem(x, y));
                } catch (NumberFormatException e) {
                    System.out.println("Loi: Toa do khong hop le. Bo qua diem nay.");
                }
            }
        } // Scanner sẽ tự động được đóng ở đây
    }
    
    // 2. Xay dung phuong thuc nhap diem tu file xml
    public void nhapDiemTuFileXML(String fileName) {
        try {
            File xmlFile = new File(fileName);
            if (!xmlFile.exists()) {
                System.out.println("Loi: Khong tim thay file XML: " + fileName);
                return;
            }
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList diemList = doc.getElementsByTagName("Diem");
            int count = 0;

            for (int i = 0; i < diemList.getLength(); i++) {
                Node diemNode = diemList.item(i);

                if (diemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element diemElement = (Element) diemNode;

                    double x = Double.parseDouble(getTagValue("X", diemElement));
                    double y = Double.parseDouble(getTagValue("Y", diemElement));

                    danhSachDiem.add(new Diem(x, y));
                    count++;
                }
            }
            System.out.println("Da nhap thanh cong " + count + " diem tu file XML.");

        } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
            System.out.println("Loi khi doc file XML. Kiem tra dinh dang du lieu hoac duong dan file.");
            e.printStackTrace();
        }
    }

    // 3. Xay dung phuong thuc sap xep cac diem
    public void sapXepDiem() {
        Collections.sort(danhSachDiem, new Comparator<Diem>() {
            @Override
            public int compare(Diem d1, Diem d2) {
                // Tieu chi 1: Khoang cach toi goc toa do GIAM DAN
                int distanceCompare = Double.compare(d2.tinhKhoangCachToiGoc(), d1.tinhKhoangCachToiGoc());
                
                if (distanceCompare != 0) {
                    return distanceCompare;
                } else {
                    // Tieu chi 2: Neu khoang cach bang nhau, diem co hoanh do (X) lon hon duoc uu tien (GIAM DAN)
                    return Double.compare(d2.getX(), d1.getX());
                }
            }
        });
        System.out.println("Danh sach da duoc sap xep theo khoang cach toi goc toa do (Giam dan), sau do la hoanh do (Giam dan).");
    }

    // 4. Lay ra tat ca cac diem nam trong duong tron tam O ban kinh 1
    public List<Diem> layDiemTrongDuongTronDonVi() {
        List<Diem> result = new ArrayList<>();
        // Dieu kien: Khoang cach toi O phai <= ban kinh (1)
        for (Diem d : danhSachDiem) {
            if (d.tinhKhoangCachToiGoc() <= 1.0) {
                result.add(d);
            }
        }
        return result;
    }

    // 5. Lay ra tat ca cac diem nam trong goc phan tu thu nhat (Goc I)
    public List<Diem> layDiemTrongGocPhanTuThuNhat() {
        List<Diem> result = new ArrayList<>();
        // Dieu kien: X > 0 va Y > 0 (Khong bao gom cac truc toa do)
        for (Diem d : danhSachDiem) {
            if (d.getX() > 0 && d.getY() > 0) {
                result.add(d);
            }
        }
        return result;
    }

    // 6. Xoa khoi danh sach cac diem co hoanh do lon hon 5 nhung nho hon 8 (5 < X < 8)
    public void xoaDiemTheoHoanhDo() {
        // Su dung removeIf (Java 8+) de xoa hieu qua hon trong ArrayList
        boolean changed = danhSachDiem.removeIf(d -> d.getX() > 5.0 && d.getX() < 8.0);
        
        if (changed) {
            System.out.println("Da xoa cac diem co hoanh do (X) nam trong khoang (5, 8).");
        } else {
            System.out.println("Khong co diem nao thoa man dieu kien xoa.");
        }
    }

    // 7. Dem xem danh sach co bao nhieu diem co hoanh do la so duong (X > 0)
    public long demHoanhDoDuong() {
        return danhSachDiem.stream()
                           .filter(d -> d.getX() > 0)
                           .count();
    }
    
    // Phuong thuc hien thi danh sach diem
    public void hienThiDanhSach() {
        if (danhSachDiem.isEmpty()) {
            System.out.println("Danh sach diem hien tai trong.");
            return;
        }
        System.out.println("\n--- DANH SACH DIEM HIEN CO (" + danhSachDiem.size() + " diem) ---");
        for (Diem d : danhSachDiem) {
            System.out.println(d);
        }
    }

    // ---------------------- MAIN METHOD DE TEST ----------------------
    public static void main(String[] args) {
        Bai3 quanLy = new Bai3();
        
        // 1. Nhap du lieu tu file XML
        System.out.println("===== 1. NHAP DU LIEU TU FILE XML =====");
        // NOTE: Dam bao file Diem.xml ton tai cung thu muc project
        quanLy.nhapDiemTuFileXML("Diem.xml"); 
        quanLy.hienThiDanhSach();

        // 2. Sap xep cac diem
        System.out.println("\n===== 2. SAP XEP DIEM =====");
        quanLy.sapXepDiem();
        quanLy.hienThiDanhSach();

        // 3. Lay diem nam trong duong tron tam O ban kinh 1
        System.out.println("\n===== 3. LOC DIEM TRONG DUONG TRON TAM O BAN KINH 1 =====");
        List<Diem> diemTrongTron = quanLy.layDiemTrongDuongTronDonVi();
        if (diemTrongTron.isEmpty()) {
             System.out.println("Khong co diem nao nam trong duong tron don vi.");
        } else {
            diemTrongTron.forEach(System.out::println);
        }

        // 4. Lay diem nam trong goc phan tu thu nhat (Goc I)
        System.out.println("\n===== 4. LOC DIEM TRONG GOC PHAN TU THU NHAT =====");
        List<Diem> diemGocI = quanLy.layDiemTrongGocPhanTuThuNhat();
        if (diemGocI.isEmpty()) {
            System.out.println("Khong co diem nao nam trong goc phan tu thu nhat.");
        } else {
            diemGocI.forEach(System.out::println);
        }

        // 5. Xoa khoi danh sach cac diem co hoanh do (5 < X < 8)
        System.out.println("\n===== 5. XOA DIEM CO HOANH DO (5 < X < 8) =====");
        quanLy.xoaDiemTheoHoanhDo();
        quanLy.hienThiDanhSach();

        // 6. Dem hoanh do duong
        System.out.println("\n===== 6. DEM HOANH DO DUONG (X > 0) =====");
        long count = quanLy.demHoanhDoDuong();
        System.out.println("So luong diem co hoanh do duong la: " + count);
        
        // NOTE: Co the uncomment doan code duoi de test chuc nang nhap tu ban phim
        // quanLy.nhapNDiemTuBanPhim();
        // quanLy.hienThiDanhSach();
    }
}