import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

class GiaoVien {
    private String tenGV;
    private int soNhom;

    // Constructor
    public GiaoVien(String tenGV, int soNhom) {
        this.tenGV = tenGV;
        this.soNhom = soNhom;
    }

    public String getTenGV() {
        return tenGV;
    }

    public int getSoNhom() {
        return soNhom;
    }

    @Override
    public String toString() {
        return tenGV + " - So nhom: " + soNhom;
    }
}

public class DanhSachGiaoVien {
    private List<GiaoVien> danhSach = new ArrayList<>();

    // Doc du lieu tu file XML
    public void docFileXML(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("giaovien");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String ten = e.getElementsByTagName("ten").item(0).getTextContent();
                    int soNhom = Integer.parseInt(e.getElementsByTagName("sonhom").item(0).getTextContent());
                    danhSach.add(new GiaoVien(ten, soNhom));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tinh tong so nhom
    public int tongSoNhom() {
        int tong = 0;
        for (GiaoVien gv : danhSach) {
            tong += gv.getSoNhom();
        }
        return tong;
    }

    // Sap xep theo ten tang dan
    public void sapXepTheoTen() {
        danhSach.sort(Comparator.comparing(GiaoVien::getTenGV));
    }

    // Sap xep theo so nhom giam dan
    public void sapXepTheoSoNhomGiam() {
        danhSach.sort((a, b) -> b.getSoNhom() - a.getSoNhom());
    }

    // Loc giao vien co so nhom > 1
    public List<GiaoVien> locGVSoNhomLonHon1() {
        List<GiaoVien> kq = new ArrayList<>();
        for (GiaoVien gv : danhSach) {
            if (gv.getSoNhom() > 1) kq.add(gv);
        }
        return kq;
    }

    // Xuat danh sach ra man hinh
    public void xuatDanhSach() {
        for (GiaoVien gv : danhSach) {
            System.out.println(gv);
        }
    }

    // Ham main de chay
    public static void main(String[] args) {
        DanhSachGiaoVien ds = new DanhSachGiaoVien();
        ds.docFileXML("giaovien.xml"); // duong dan file XML

        System.out.println("== Danh sach giao vien ==");
        ds.xuatDanhSach();

        System.out.println("\nTong so nhom: " + ds.tongSoNhom());

        System.out.println("\n== Sap xep theo ten ==");
        ds.sapXepTheoTen();
        ds.xuatDanhSach();

        System.out.println("\n== Sap xep theo so nhom giam dan ==");
        ds.sapXepTheoSoNhomGiam();
        ds.xuatDanhSach();

        System.out.println("\n== Giao vien co so nhom > 1 ==");
        for (GiaoVien gv : ds.locGVSoNhomLonHon1()) {
            System.out.println(gv);
        }
    }
}
