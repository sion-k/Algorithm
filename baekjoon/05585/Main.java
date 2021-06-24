import java.util.*;

public class Main {
	private static int[] unit = { 500, 100, 50, 10, 5, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		sc.close();
		k = 1000 - k;
		int min = 0;
		for (int u = 0; u < 6; u++) {
			if (k == 0)
				break;
			int q = 0;
			if ((q = k / unit[u]) > 0) {
				k -= unit[u] * q;
				min += q;
			}
		}
		System.out.println(min);
	}
}
