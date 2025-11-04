
public class BTVN_M {

	public static void main(String[] args) {
		long [] M = {12, 35, 68, 70, 90, 25, 46, 28,15};
		
		int n = M.length;
		for (int i = 0; i<n-1 ;i++) {
			System.out.print(M[i] + " ");
			if (M[i] % 2 == 0 && M[i +1] % 2 == 0) {
				long a = M[i] + M[i+1];
				System.out.print(a + " ");
			}
		}
		System.out.print(M[n-1]);
	}

}
