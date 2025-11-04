

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Thiet ke lop tinh tien nuoc cho chung cu XYZ
 * Chay: javac ChungCuXYZ.java
 *       java ChungCuXYZ
 */

abstract class Household {
    protected String ownerName;
    protected String address;
    protected int oldIndex;
    protected int newIndex;
    protected static final int PRICE_PER_UNIT = 8000;

    public Household(String ownerName, String address, int oldIndex, int newIndex) {
        this.ownerName = ownerName;
        this.address = address;
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
    }

    /** Tra ve so khoi nuoc phai tra (da ap dung giam tru theo loai) */
    public int getConsumption() {
        int raw = newIndex - oldIndex;
        if (raw < 0) {
            return 0; // Dam bao khong co tieu thu am
        }
        int adjusted = Math.max(0, raw - getReduction());
        return adjusted;
    }

    /** Moi loai se dinh nghia luong giam (khoi) */
    protected abstract int getReduction();

    public long getBill() {
        return (long) getConsumption() * PRICE_PER_UNIT;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-20s | old=%3d new=%3d | consumption=%2d | bill=%,d VND",
                ownerName, address, oldIndex, newIndex, getConsumption(), getBill());
    }
}

/** Loai A: giam = 5 * so nhan khau */
class HouseholdA extends Household {
    private int numResidents;

    public HouseholdA(String ownerName, String address, int oldIndex, int newIndex, int numResidents) {
        super(ownerName, address, oldIndex, newIndex);
        this.numResidents = Math.max(0, numResidents);
    }

    @Override
    protected int getReduction() {
        return 5 * numResidents;
    }
}

/** Loai B: giam co dinh 20 khoi */
class HouseholdB extends Household {
    public HouseholdB(String ownerName, String address, int oldIndex, int newIndex) {
        super(ownerName, address, oldIndex, newIndex);
    }

    @Override
    protected int getReduction() {
        return 20;
    }
}

/** Loai C: giam = 10 * so nhan khau phuc vu */
class HouseholdC extends Household {
    private int numServing;

    public HouseholdC(String ownerName, String address, int oldIndex, int newIndex, int numServing) {
        super(ownerName, address, oldIndex, newIndex);
        this.numServing = Math.max(0, numServing);
    }

    @Override
    protected int getReduction() {
        return 10 * numServing;
    }
}

public class ChungCuXYZ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Household> list = new ArrayList<>();

        System.out.println("Chuong trinh tinh tien nuoc cho chung cu XYZ");
        System.out.print("Nhap so ho gia dinh can xu ly: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("So ho gia dinh phai lon hon 0. Thoat.");
                sc.close();
                return;
            }
        } catch (Exception e) {
            System.out.println("So khong hop le. Thoat.");
            sc.close();
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("\n--- Ho thu %d ---\n", i + 1);
            System.out.print("Loai ho (A/B/C): ");
            String type = sc.nextLine().trim().toUpperCase();
            System.out.print("Ten chu ho: ");
            String name = sc.nextLine().trim();
            System.out.print("Dia chi: ");
            String addr = sc.nextLine().trim();

            int oldIndex = readInt(sc, "Chi so cu: ");
            int newIndex = readInt(sc, "Chi so moi: ");
            if (newIndex < oldIndex) {
                System.out.printf("Chi so moi (%d) nho hon chi so cu (%d). Dat new = old.\n", newIndex, oldIndex);
                newIndex = oldIndex;
            }

            switch (type) {
                case "A":
                    int numRes = readInt(sc, "So nhan khau: ");
                    list.add(new HouseholdA(name, addr, oldIndex, newIndex, numRes));
                    break;
                case "B":
                    list.add(new HouseholdB(name, addr, oldIndex, newIndex));
                    break;
                case "C":
                    int numServ = readInt(sc, "So nhan khau phuc vu: ");
                    list.add(new HouseholdC(name, addr, oldIndex, newIndex, numServ));
                    break;
                default:
                    System.out.println("Loai khong hop le, mac dinh la B.");
                    list.add(new HouseholdB(name, addr, oldIndex, newIndex));
                    break;
            }
        }

        // In bao cao
        System.out.println("\n" + "=".repeat(80));
        System.out.println("--- BAO CAO TIEN NUOC CHUNG CU XYZ ---");
        System.out.println("=".repeat(80));
        long totalBill = 0;
        for (int i = 0; i < list.size(); i++) {
            Household h = list.get(i);
            System.out.printf("%2d. %s\n", i + 1, h.toString());
            totalBill += h.getBill();
        }
        System.out.println("=".repeat(80));
        System.out.printf("TONG TIEN PHAI THU CUA CHUNG CU: %,d VND\n", totalBill);
        System.out.println("=".repeat(80));

        sc.close();
    }

    private static int readInt(Scanner sc, String prompt) {
        int val = 0;
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                val = Integer.parseInt(line);
                if (val < 0) {
                    System.out.println("Gia tri phai >= 0, nhap lai.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Khong hop le, nhap lai so nguyen.");
            }
        }
        return val;
    }
}
