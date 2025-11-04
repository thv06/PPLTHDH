import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DanhSachSinhVien {
    private ArrayList<SinhVien> ds = new ArrayList<>();

    // ===== a) Nhập danh sách thí sinh từ bàn phím =====
    public void nhapTuBanPhim() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong thi sinh: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap thong tin thi sinh thu " + (i + 1) + ":");
            System.out.print("So bao danh: ");
            String sbd = sc.nextLine();
            System.out.print("Ho ten: ");
            String ht = sc.nextLine();
            System.out.print("Nam sinh: ");
            int ns = Integer.parseInt(sc.nextLine());
            System.out.print("Diem Toan: ");
            double toan = Double.parseDouble(sc.nextLine());
            System.out.print("Diem Van: ");
            double van = Double.parseDouble(sc.nextLine());
            System.out.print("Diem Ngoai ngu: ");
            double nn = Double.parseDouble(sc.nextLine());
            ds.add(new SinhVien(sbd, ht, ns, toan, van, nn));
        }
        sc.close(); // Đóng Scanner để tránh resource leak
    }

    // ===== b) Nhập danh sách thí sinh từ file XML =====
    public void nhapTuFileXML(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("ThiSinh");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String sbd = e.getElementsByTagName("SBD").item(0).getTextContent();
                    String ht = e.getElementsByTagName("HT").item(0).getTextContent();
                    int ns = Integer.parseInt(e.getElementsByTagName("NamSinh").item(0).getTextContent());
                    double toan = Double.parseDouble(e.getElementsByTagName("Toan").item(0).getTextContent());
                    double van = Double.parseDouble(e.getElementsByTagName("Van").item(0).getTextContent());
                    double nn = Double.parseDouble(e.getElementsByTagName("NN").item(0).getTextContent());
                    ds.add(new SinhVien(sbd, ht, ns, toan, van, nn));
                }
            }
        } catch (Exception e) {
            System.out.println("Loi doc file XML: " + e.getMessage());
        }
    }

    // ===== c) In danh sách thí sinh =====
    public void inDanhSach() {
        for (SinhVien sv : ds) {
            sv.xuat();
        }
    }

    // ===== d) Sắp xếp theo tổng điểm giảm dần =====
    public void sapXepTheoTongDiemGiamDan() {
        ds.sort((a, b) -> Double.compare(b.getTongDiem(), a.getTongDiem()));
    }

    // ===== e) Sắp xếp theo tên, nếu trùng tên thì điểm Toán giảm dần =====
    public void sapXepTheoTenVaToan() {
        ds.sort((a, b) -> {
            int cmp = a.getHoTen().compareToIgnoreCase(b.getHoTen());
            if (cmp == 0) {
                return Double.compare(b.getDiemToan(), a.getDiemToan());
            }
            return cmp;
        });
    }

    // ===== f) Lấy danh sách thí sinh có kết quả “Đậu” =====
    public ArrayList<SinhVien> layDanhSachDau() {
        ArrayList<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : ds) {
            if (sv.getKetQua().equals("Dau")) {
                kq.add(sv);
            }
        }
        return kq;
    }

    // ===== g) Lấy danh sách sinh viên có năm sinh >1995 hoặc điểm Toán >=9 =====
    public ArrayList<SinhVien> locSinhVienDacBiet() {
        ArrayList<SinhVien> kq = new ArrayList<>();
        for (SinhVien sv : ds) {
            if (sv.getNamSinh() > 1995 || sv.getDiemToan() >= 9) {
                kq.add(sv);
            }
        }
        return kq;
    }

    // ===== h) Ghi danh sách thí sinh ra file XML =====
    public void ghiRaFileXML(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Tạo nút gốc DSSV
            Element root = doc.createElement("DSSV");
            doc.appendChild(root);

            // Duyệt danh sách sinh viên
            for (SinhVien sv : ds) {
                Element thiSinh = doc.createElement("ThiSinh");
                root.appendChild(thiSinh);

                Element sbd = doc.createElement("SBD");
                sbd.appendChild(doc.createTextNode(sv.getSoBaoDanh()));
                thiSinh.appendChild(sbd);

                Element ht = doc.createElement("HT");
                ht.appendChild(doc.createTextNode(sv.getHoTen()));
                thiSinh.appendChild(ht);

                Element ns = doc.createElement("NamSinh");
                ns.appendChild(doc.createTextNode(String.valueOf(sv.getNamSinh())));
                thiSinh.appendChild(ns);

                Element toan = doc.createElement("Toan");
                toan.appendChild(doc.createTextNode(String.valueOf(sv.getDiemToan())));
                thiSinh.appendChild(toan);

                Element van = doc.createElement("Van");
                van.appendChild(doc.createTextNode(String.valueOf(sv.getDiemVan())));
                thiSinh.appendChild(van);

                Element nn = doc.createElement("NN");
                nn.appendChild(doc.createTextNode(String.valueOf(sv.getDiemNgoaiNgu())));
                thiSinh.appendChild(nn);
            }

            // Ghi ra file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("Da ghi danh sach ra file: " + filePath);
        } catch (Exception e) {
            System.out.println("Loi ghi file XML: " + e.getMessage());
        }
    }

    // ===== Phương thức main để kiểm thử =====
    public static void main(String[] args) {
        DanhSachSinhVien ds = new DanhSachSinhVien();

        // Đọc từ file XML có sẵn (10 thí sinh)
        ds.nhapTuFileXML("sinhvien.xml");

        System.out.println("\n--- Danh sach sinh vien ---");
        ds.inDanhSach();

        System.out.println("\n--- Sap xep theo tong diem giam dan ---");
        ds.sapXepTheoTongDiemGiamDan();
        ds.inDanhSach();

        System.out.println("\n--- Sap xep theo ten (neu trung ten thi diem Toan giam dan) ---");
        ds.sapXepTheoTenVaToan();
        ds.inDanhSach();

        System.out.println("\n--- Danh sach thi sinh Dau ---");
        for (SinhVien sv : ds.layDanhSachDau()) {
            sv.xuat();
        }

        System.out.println("\n--- Danh sach sinh vien nam sinh >1995 hoac diem Toan >=9 ---");
        for (SinhVien sv : ds.locSinhVienDacBiet()) {
            sv.xuat();
        }

        // Ghi toàn bộ danh sách ra file mới
        ds.ghiRaFileXML("output.xml");
    }
}
