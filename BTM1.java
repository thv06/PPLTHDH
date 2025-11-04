import java.util.Scanner;  
public class BTM1 {

	public static void main(String[] args) {
		//khai bao bien
		float r, cv, dt;
		//nhap du lieu input
		Scanner scan =new Scanner(System.in);
		System.out.print("nhap ban kinh:");
		
		r = scan.nextFloat();
		
		cv=(float)Math.PI * r *2;
		dt=(float)Math.PI* r*r;
		System.out.println("chu vi la " + cv);
		System.out.println("dien tich la " + dt);
	}

}
