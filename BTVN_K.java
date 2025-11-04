
public class BTVN_K {

	public static void main(String[] args) {
		long [] k = {12, 35, 68, 70, 90, 25, 46, 15};
		long n = k.length;
		
		for(int i=0; i<n; i++) {
			System.out.print(k[i] + " ");
			
			if ((i + 1) % 3 == 0) {
				System.out.println();
			}
		}

	}

}
