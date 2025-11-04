import java.util.Scanner;

public class BTVN_H {

	public static void main(String[] args) {
		int a, b, c;
		double x1, x2, x;
		//nhap he so a, b, c
		
		Scanner nhap_a=new Scanner (System.in);
		System.out.print("nhap so a:");
		a = nhap_a.nextInt();
		
		Scanner nhap_b=new Scanner (System.in);
		System.out.print("nhap so b:");
		b = nhap_b.nextInt();
		
		Scanner nhap_c=new Scanner (System.in);
		System.out.print("nhap so c:");
		c = nhap_c.nextInt();
		
		// tinh delta
		int delta = b*b - 4*a*c;
		
		//phuong trinh co hai nghiem
		if(delta > 0) {
			System.out.println("phuong trinh co 2 nghiem:");
			x1 = (-b + Math.sqrt(delta)) / (2*a);
			x2 = (-b - Math.sqrt(delta)) / (2*a);
			System.out.println("x1:" +x1 );
			System.out.println("x2:" +x2 );
		}
		//phuong trinh co nghiem kep
		else if(delta == 0) {
			System.out.println("phuong trinh co nghiem kep:");
			x = -b / (2*a);
			
		}
		//phuong trinh vo nghiem
		else {
			System.out.println("phuong trinh vo nghiem");
		}

	}

}
