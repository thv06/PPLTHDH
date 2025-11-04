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
import java.util.stream.Collectors;

// -----------------------------------------------------------------
// LOP 1: NhanVien (Dai dien cho mot nhan vien)
// -----------------------------------------------------------------
class NhanVien {
    private String maNV;
    private String tenNV;
    private String phongBan;
    private String chucVu; // "Lanh dao" hoac "Nhan vien"
    private double heSoLuong;
    private int soNgayLam;
    
    // Diem hien hien tai (Ap dung cho tat ca nhan vien)
    private static final double DIEM_HIEN_TAI = 1150;
    
    // Luong co ban do nha nuoc quy dinh
    private static final double LUONG_CO_BAN = 1100;

    public NhanVien(String maNV, String tenNV, String phongBan, String chucVu, double heSoLuong, int soNgayLam) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
        this.heSoLuong = heSoLuong;
        this.soNgayLam = soNgayLam;
    }

    // Getters
    public String getMaNV() { return maNV; }
    public String getTenNV() { return tenNV; }
    public String getPhongBan() { return phongBan; }
    public String getChucVu() { return chucVu; }
    public double getHeSoLuong() { return heSoLuong; }
    public int getSoNgayLam() { return soNgayLam; }

    // Setters (De thuc hien chuc nang nhap tu ban phim)
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    // Tinh he so thi đua
    public double tinhHeSoThiDua() {
        if (chucVu.equalsIgnoreCase("Lanh dao")) {
            return 1.0;
        } else if (chucVu.equalsIgnoreCase("Nhan vien")) {
            if (soNgayLam >= 22) {
                return 1.0;
            } else if (soNgayLam > 20) { // Tức là 21 ngày
                return 0.8;
            } else { // Còn lại (<= 20)
                return 0.6;
            }
        }
        return 0.0; // Truong hop chuc vu khong hop le
    }
    
    // Tinh phu cap chuc vu
    public double tinhPhuCap() {
        if (chucVu.equalsIgnoreCase("Lanh dao")) {
            return 2000.0;
        }
        return 0.0; // Nhan vien khong co phu cap chuc vu
    }
    
    // Tinh luong hang thang
    // Luong = he so luong * (He so thi dua * Diem hien tai + Luong co ban) + Phu cap
    public double tinhLuong() {
        double HS_TD = tinhHeSoThiDua();
        double PC = tinhPhuCap();
        
        return heSoLuong * (HS_TD * DIEM_HIEN_TAI + LUONG_CO_BAN) + PC;
    }
    
    // Xuat thong tin nhan vien
    public void xuatThongTin() {
        System.out.printf("%-8s | %-18s | %-12s | %-12s | %-8.2f | %-10d | %-10.2f\n",
            maNV, tenNV, phongBan, chucVu, heSoLuong, soNgayLam, tinhLuong());
    }
}

// -----------------------------------------------------------------
// LOP 2: QuanLyNhanVien (Quan ly danh sach nhan vien)
// -----------------------------------------------------------------
public class Bai4 {
    private List<NhanVien> danhSachNV;
    
    // Dinh nghia ten file XML
    private static final String XML_FILE_NAME = "NhanVien.xml";

    public Bai4() {
        this.danhSachNV = new ArrayList<>();
    }

    // Phuong thuc ho tro lay gia tri tu the XML
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    
    // 1. Chuc nang nhap danh sach tu file XML
    public void nhapDSSVTruyFileXML() {
        try {
            File xmlFile = new File(XML_FILE_NAME);
            if (!xmlFile.exists()) {
                System.out.println("Loi: Khong tim thay file XML: " + XML_FILE_NAME);
                return;
            }
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nvList = doc.getElementsByTagName("NhanVien");
            int count = 0;

            for (int i = 0; i < nvList.getLength(); i++) {
                Node nvNode = nvList.item(i);

                if (nvNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element nvElement = (Element) nvNode;

                    String maNV = getTagValue("MaNV", nvElement);
                    String tenNV = getTagValue("TenNV", nvElement);
                    String phongBan = getTagValue("PhongBan", nvElement);
                    String chucVu = getTagValue("ChucVu", nvElement);
                    double heSoLuong = Double.parseDouble(getTagValue("HeSoLuong", nvElement));
                    int soNgayLam = Integer.parseInt(getTagValue("SoNgayLam", nvElement));

                    NhanVien nv = new NhanVien(maNV, tenNV, phongBan, chucVu, heSoLuong, soNgayLam);
                    danhSachNV.add(nv);
                    count++;
                }
            }
            System.out.println("Da nhap thanh cong " + count + " nhan vien tu file XML.");

        } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
            System.out.println("Loi khi doc file XML. Kiem tra dinh dang du lieu hoac duong dan file.");
            e.printStackTrace();
        }
    }
    
    // 2. Xuat thong tin tat ca cac nhan vien
    public void xuatTatCaNhanVien() {
        if (danhSachNV.isEmpty()) {
            System.out.println("Danh sach nhan vien trong.");
            return;
        }
        System.out.println("\n--- DANH SACH TAT CA NHAN VIEN ---");
        System.out.println("Ma NV    | Ten NV           | Phong Ban  | Chuc Vu    | HS Luong | Ngay Lam | Luong");
        System.out.println("---------|------------------|------------|------------|----------|----------|------------");
        for (NhanVien nv : danhSachNV) {
            nv.xuatThongTin();
        }
    }
    
    // 3. Xuat thong tin nhan vien tung nhom he so thi đua
    public void xuatNhomTheoHeSoThiDua() {
        if (danhSachNV.isEmpty()) return;
        
        // Nhom theo HeSoThiDua
        var nhom = danhSachNV.stream()
            .collect(Collectors.groupingBy(NhanVien::tinhHeSoThiDua));
        
        System.out.println("\n--- THONG TIN NHAN VIEN THEO NHOM HE SO THI DUA ---");
        
        // Sap xep nhom theo HeSoThiDua giam dan
        nhom.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .forEach(heSo -> {
                System.out.printf("\n>>> NHOM CO HE SO THI DUA = %.1f <<<\n", heSo);
                System.out.println("Ma NV    | Ten NV           | Phong Ban  | Chuc Vu    | HS Luong | Ngay Lam | Luong");
                System.out.println("---------|------------------|------------|------------|----------|----------|------------");
                nhom.get(heSo).forEach(NhanVien::xuatThongTin);
            });
    }
    
    // 4. Lay ra danh sach nhan vien theo phong (sap xep theo phong truoc)
    public List<NhanVien> layDSTheoPhong(String tenPhong) {
        return danhSachNV.stream()
            .filter(nv -> nv.getPhongBan().equalsIgnoreCase(tenPhong))
            .collect(Collectors.toList());
    }
    
    // 5. Lay ra danh sach nhan vien co chuc vu "Lanh dao"
    public List<NhanVien> layDSLanhDao() {
        return danhSachNV.stream()
            .filter(nv -> nv.getChucVu().equalsIgnoreCase("Lanh dao"))
            .collect(Collectors.toList());
    }
    
    // 6. Tinh tong luong cong ty phai tra
    public double tinhTongLuong() {
        return danhSachNV.stream()
            .mapToDouble(NhanVien::tinhLuong)
            .sum();
    }
    
    // 7. Loai bo cac nhan vien co so ngay lam viec nho hon 10
    public void loaiBoNhanVienNgayLamNhoHon10() {
        int soLuongTruoc = danhSachNV.size();
        danhSachNV.removeIf(nv -> nv.getSoNgayLam() < 10);
        int soLuongSau = danhSachNV.size();
        System.out.printf("Da loai bo %d nhan vien co so ngay lam viec < 10.\n", soLuongTruoc - soLuongSau);
    }
    
    // 8. Lay ra danh sach nhan vien khong phai lanh dao co so ngay lam viec lon hon 22
    public List<NhanVien> layDSNhanVienChamChi() {
        return danhSachNV.stream()
            .filter(nv -> !nv.getChucVu().equalsIgnoreCase("Lanh dao")) // Khong phai lanh dao
            .filter(nv -> nv.getSoNgayLam() > 22) // So ngay lam viec > 22
            .collect(Collectors.toList());
    }
    
    // 9. Lay ra danh sach nhan vien co he so luong >= 4.34 va o phong "Tai vu"
    public List<NhanVien> layDSNhanVienTieuChiCuoi() {
        return danhSachNV.stream()
            .filter(nv -> nv.getHeSoLuong() >= 4.34)
            .filter(nv -> nv.getPhongBan().equalsIgnoreCase("Tai vu"))
            .collect(Collectors.toList());
    }

    // ---------------------- MAIN METHOD DE TEST ----------------------
    public static void main(String[] args) {
        Bai4 ql = new Bai4();
        
        // 1. Nhap du lieu tu file XML
        System.out.println("===== 1. NHAP DU LIEU TU FILE XML =====");
        ql.nhapDSSVTruyFileXML(); 
        ql.xuatTatCaNhanVien();

        // 2. Xuat thong tin tung nhom he so thi đua
        ql.xuatNhomTheoHeSoThiDua();

        // 3. Tinh tong luong
        System.out.println("\n===== 3. TINH TONG LUONG CONG TY PHAI TRA =====");
        System.out.printf("Tong luong cong ty phai tra la: %.2f VND\n", ql.tinhTongLuong());
        
        // 4. Loai bo nhan vien ngay lam < 10 (Thao tac thay doi danh sach)
        System.out.println("\n===== 4. LOAI BO NHAN VIEN NGAY LAM < 10 =====");
        ql.loaiBoNhanVienNgayLamNhoHon10();
        ql.xuatTatCaNhanVien(); // Xuat danh sach sau khi loai bo

        // 5. Lay ra danh sach khong phai lanh dao, ngay lam > 22
        System.out.println("\n===== 5. DANH SACH NHAN VIEN (KHONG LD, NGAY LAM > 22) =====");
        List<NhanVien> dsChamChi = ql.layDSNhanVienChamChi();
        if (dsChamChi.isEmpty()) {
            System.out.println("Khong co nhan vien nao thoa man tieu chi.");
        } else {
            dsChamChi.forEach(NhanVien::xuatThongTin);
        }
        
        // 6. Lay ra danh sach co he so luong >= 4.34 va o phong "Tai vu"
        System.out.println("\n===== 6. DANH SACH NHAN VIEN (HSLuong >= 4.34 VA PHONG 'Tai vu') =====");
        List<NhanVien> dsTieuChiCuoi = ql.layDSNhanVienTieuChiCuoi();
        if (dsTieuChiCuoi.isEmpty()) {
            System.out.println("Khong co nhan vien nao thoa man tieu chi.");
        } else {
            dsTieuChiCuoi.forEach(NhanVien::xuatThongTin);
        }
    }
}