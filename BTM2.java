import java.util.Scanner;  

public class BTM2 {

	public static void main(String[] args) {
		//khai bao bien
		int n, dem=0;
		//nhap du lieu input
		Scanner scan=new Scanner (System.in);
		System.out.print("nhap so nguyen duong n:");
		n=scan.nextInt();
		//tinh toan
		while(n>0) {
			dem++;
			n=n/10;
		}
		//xuat ket qua 
		System.out.println("so chu so la: " +dem);
	}

}
