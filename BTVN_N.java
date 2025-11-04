
public class BTVN_N {

	public static void main(String[] args) {
		long [] N = {12, 35, 68, 70, 90, 25, 46, 28, 15};
		int n = N.length;
		int dem = 0;
		int count = 0;
		for(int i = 0; i < n; i++) {
			if (N[i] % 2 == 0) {
				dem++;
			}else {	
				count ++;	
			}	
		}
		System.out.println("tong chan: " +dem);
		System.out.println("tong le: " +count);

	}

}
