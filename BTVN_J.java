
public class BTVN_J {

	public static void main(String[] args) {
		long sum = 0;
		
		long [] j = {12, 35, 68, 70, 90};
		int n = j.length;
		
		for (int i = 0; i < n ; i++) {
			long dem = 0;
			long temp = j[i];
			while(temp>0) {
				dem = dem + temp % 10;
				temp = temp / 10;
			}
			if (dem % 2 == 0) {
				sum += j[i];
			}
		}
		System.out.print("tong cac phan tu co tong la chan: " + sum);

	}

}
