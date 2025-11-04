import java.util.Scanner;

public class BTVN_G {

	public static void main(String[] args) {
		double sum=0,s=0, n;
		Scanner nhap_n =new Scanner (System.in);
		System.out.print("nhap so nguyen duong n:");
		n=nhap_n.nextInt();
		
		for (int i = 0; i <= n; i++) {
			sum = sum + n - i ;
		}System.out.print("tong cua s1:" + sum);
		
		for (int i = 0; i < n; i++) {
			s = s + 1 / (n - i) ;
		}System.out.print("\ntong cua s2:" + s);
		


	}

}
