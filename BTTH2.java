import java.util.Scanner;  
import java.util.ArrayList;  
import java.util.Random;  

public class BTTH2 {

	public static void main(String[] args) {
//==========================================
		//cau a:
		System.out.println("CAU A");

		long [] a1 = {1, 2, 5, 9, 10};
		System.out.println("So phan tu cua mang " + a1.length);
		for (int i=0; i < a1.length; i++) {
			System.out.print( a1[i] + " ");
			}
//==========================================
		//cau b:
		System.out.println("\nCAU B");
		
		ArrayList<Integer> a2 = new ArrayList<>();
		for(int z = 0; z <= 15; z++) {
			Random rd = new Random();
			Integer x = new Integer(rd.nextInt(100));
			a2.add(x);
			System.out.print(a2.get(z) +" ");
		} 
//==========================================
		//cau c
		System.out.println("\nCAU C");
		
		System.out.print("so phan tu duoc them la: ");
		Scanner nhap_c = new Scanner(System.in); 
		int n = nhap_c.nextInt();
		int [] c = new int [n];
		for (int y = 0; y < c.length; y++) {
			System.out.print("c [" + y + "] ="  );
			c[y] = nhap_c.nextInt();
		}
		
		for (int y = 0; y < c.length; y++) {
			System.out.print(c[y] +  " ");
		}
		
		//-----------------------------------------------
		System.out.println("\nmang a sau khi duoc them: ");
		
		System.out.println("phan tu cua mang a1");
		for (int i=0; i < a1.length; i++) {
			System.out.print( a1[i] + " ");
			}
		for (int y = 0; y < c.length; y++) {
			System.out.print(c[y] +  " ");
		}
		//-----------------------------------------------
		System.out.println("\nphan tu cua mang a2");
		for(int z = 0; z <= 15; z++) {
			System.out.print(a2.get(z) +" ");
		} 
		for (int y = 0; y < c.length; y++) {
			System.out.print(c[y] +  " ");
		}
//==========================================
		//cau d
		System.out.print("\nCAU D");
		//xoa cac so thuoc boi cua 5
		//------------------------------------------------
		System.out.print("\nmang a1 sau khi xoa boi 5: ");
		for (int i=0; i < a1.length; i++) {
			if (a1[i] % 5 != 0) 
				System.out.print( a1[i] + " ");
			}
		//------------------------------------------------
		System.out.println("\nmang a2 sau khi xoa boi 5: ");
		for(int z = 0; z < a2.size(); z++) {
			if (a2.get(z) % 5 != 0) 
			System.out.print(a2.get(z) +" ");
		}
//==========================================
		//cau e
		System.out.println("\nCAU E");
		//------------------------------------------------
		System.out.print("so phan tu x la: ");
		Scanner nhap_x = new Scanner(System.in);
		int x = nhap_x.nextInt();
		//tim x
		boolean found = false;
		System.out.print("\nmang a1 co x hay khong: ");
		for (int i=0; i < a1.length; i++) {
			if (a1[i] == x ) {
				found = true;
				break;
			}
		}
		if (found) System.out.print("co");
		else System.out.print("khong");
		
		
		System.out.print("\nmang a2 co x hay khong: ");
		for(int z = 0; z < a2.size(); z++) {
			if (a2.get(z) == x ) {
				found = true;
				break;	
			}
		}
		if (found) System.out.print("co");
		else System.out.print("khong");
		
		
//==========================================
		//cau f
		System.out.println("\nCAU F");
		//------------------------------------------------
		System.out.print("cac phan co chu so hang chuc la 1 trong a1:");
		for (int i=0; i < a1.length; i++) {
			if (a1[i] > 9 && a1[i] < 20 ) System.out.print( a1[i] + " ");
			}
		
		System.out.println("\ncac phan co chu so hang chuc la 1 trong a2:");
		for(int z = 0; z < a2.size(); z++) {
			if (a2.get(z) > 9 && a2.get(z) < 20 )
			System.out.print(a2.get(z) +" ");
		}
		
	}		
}
