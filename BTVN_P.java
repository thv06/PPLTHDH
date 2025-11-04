
public class BTVN_P {

	public static void main(String[] args) {
		long [] P = {12, 35, 68, 70, 90, 25, 46, 28, 15};
		int n = P.length;
		float dem = 0;
		float tong = 0;
		for (int i=0; i<n ; i++) {
			dem++;
			tong = tong + P[i];
		}System.out.print("trung binh cong: " + (tong / dem));

	}

}
