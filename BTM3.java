import java.util.Scanner;  
import java.util.ArrayList;  
import java.util.Random;  

public class BTM3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//minh hoa arrylist
		ArrayList<Integer> ds = new ArrayList<>();
		for(int i = 0; i<10; i++) {
			Random rd = new Random();
			Integer x = new Integer(rd.nextInt(100));
			ds.add(x);
			System.out.print(ds.get(i) +" ");
		}
		//Tim so x trong arraaylist
		System.out.println("\nTrong ds co chua so 50 khong:" + ds.contains(50));
		//======================================================================
		// minh hoa mang 1 chieu
		int []mlc = new int [10];
		for (int i=0; i<10; i++) {
			Random rd = new Random();
			mlc[i] = rd.nextInt(100);
			System.out.print(mlc[i]+" ");
		}
		//tim so trong arrylist
		for (int i = 0; i<mlc.length;i++)
			if(mlc[i]==50)
				System.out.println("\nTrong ds co chua so 50: true");
		System.out.println("\nTrong ds co chua 50: false");

	}

}
