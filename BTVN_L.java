
public class BTVN_L {

	public static void main(String[] args) {
		long [] L = {12, 35, 68, 70, 90, 25, 46, 15};
		
		int n = L.length;
		for (int i=1; i< n-1 ; i++) {
			for (int j =0; j < n - i - 1; j++) {
				if (L[j] > L[j+1]) {
					long temp = L[j];
					L[j] = L[j+1];
					L[j + 1] = temp;
				}
			  }
			}
		System.out.print("Sap xep mang tang dan: ");
		for (int i=0; i<n; i++) {
			System.out.print(L[i]+" ");
		}
		System.out.println();
		
		
		for (int i=1; i< n-1; i++) {
			for (int j=0; j < n-i-1; j++) {
				if (L[j] < L[j+1]) {
					long temp = L[i];
					L[j] = L[j+1];
					L[j + 1] = temp;
				}
			}
			}
		System.out.print("Sap xep mang giam dan: ");
		for (int i=0; i<n; i++) {
			System.out.print(L[i]+" ");
		}
		
			
		}
	}

