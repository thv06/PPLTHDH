import java.util.Scanner;

public class BTVN_I {

	public static void main(String[] args) {
		//nhap gia tri
		int x, dem = 0;
		Scanner nhap_x=new Scanner (System.in);
		System.out.print("nhap so x:");
		x = nhap_x.nextInt();
		//uoc so
		for (int i = 1; i < x; i++ ) {
			if( x % i == 0) {
				dem = dem + i;
			}	
		}
		//kiem tra 
		if (dem == x) {
			System.out.print("x la so hoan thien");
		}else {
			System.out.print("x la so khong hoan thien");
		}
	}

}
