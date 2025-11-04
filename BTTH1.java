import java.util.Scanner;
public class BTTH1 {

	public static void main(String[] args) {
//cau a
	System.out.println("CAU a:");
	//khai bao bien
	float r, d, cv, dt;
	//nhap du lieu input
	Scanner dai =new Scanner(System.in);
	System.out.print("nhap dai:");
	d = dai.nextFloat();
	
	Scanner rong =new Scanner(System.in);
	System.out.print("nhap rong:");
	r = rong.nextFloat();
	
	//Tinh chu vi
	cv = (float)(d + r)*2;
	System.out.println("chu vi la " + cv);
	//tinh dien tich
	dt = (float)(d*r);
	System.out.println("dien tich la " + dt);
//=====================================================
//cau b
	System.out.println("CAU b:");
	//khai bao bien
	float a, b, c;
	//nhap du lieu input
	Scanner nhap_a = new Scanner(System.in);
	System.out.print("nhap a: ");
	a = nhap_a.nextFloat();
	
	Scanner nhap_b = new Scanner(System.in);
	System.out.print("nhap b: ");
	b = nhap_b.nextFloat();
	
	Scanner nhap_c = new Scanner(System.in);
	System.out.print("nhap c: ");
	c = nhap_c.nextFloat();
	
	if (a>b && a>c) {
		System.out.println("So lon nhat:" +a);
		if (b<c) System.out.println("So be nhat:" +b);
		if (b>c) System.out.println("So be nhat:" +c);
	}if (b>a && b>c) {
		System.out.println("So lon nhat:" +b);
		if (a<c) System.out.println("So be nhat:" +a);
		if (a>c) System.out.println("So be nhat:" +c);
	}if (c>b && c>a) {
		System.out.println("So lon nhat:" +c);
		if (b<a) System.out.println("So be nhat:" +b);
		if (b>a) System.out.println("So be nhat:" +a);
	}
//=====================================================
    //cau c
		System.out.println("CAU c:");
		//khai bao bien
		int n;
		//nhap du lieu input
		Scanner nhap_n = new Scanner(System.in);
		System.out.print("nhap n: ");
		n = nhap_n.nextInt();
		
		for (int i=1; i <= n; i++) {
			if (n%i == 0) System.out.println("uoc cua n: " +i);
		}
//=====================================================
	    //cau d
			System.out.println("CAU d:");
			//khai bao bien
			int x, dem=0;
			//nhap du lieu input
			Scanner nhap_x=new Scanner (System.in);
			System.out.print("nhap so nguyen duong x:");
			x = nhap_x.nextInt();
			//tinh toan
			while(x>0) {
				dem++;
				x = x/10;
			}
			//xuat ket qua 
			System.out.println("so chu so la: " +dem);

	}

}
