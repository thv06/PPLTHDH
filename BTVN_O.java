
public class BTVN_O {

	public static void main(String[] args) {
long [] O = {12, 35, 68, 70, 90, 25, 46, 15};
		
		int n = O.length;
		for (int i=1; i< n-1 ; i++) {
			for (int j =0; j < n - i - 1; j++) {
				if (O[j] > O[j+1]) {
					long temp = O[j];
					O[j] = O[j+1];
					O[j + 1] = temp;
				}
			  }
			}
			System.out.println("MAX: " + O[n-1]);
			System.out.println("MIN: " + O[0]);
	}

}
