import java.io.*;

public class Main {
	private static int[] cache;
	private static int[] k;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] fl = null;
		String[] sl = null;

		try {
			fl = br.readLine().split(" ");
			sl = br.readLine().split(" ");
			br.close();
		} catch (IOException e) {
			System.out.println("error");
		}

		int n = Integer.parseInt(fl[0]);
		int m = Integer.parseInt(fl[1]);

		cache = new int[n + 1];
		for (int i = 0; i < cache.length; i++) {
			cache[i] = i;
		}

		k = new int[m];
		for (int i = 0; i < m; i++) {
			k[i] = Integer.parseInt(sl[i]);
		}

		int totalSum = n * (1 + n) / 2;
		for (int i = 2; i <= n; i++) {
			if (cache[i] == 0) {
				continue;
			}
			for (int j = 0; j < k.length; j++) {
				if (k[j] > i) {
					break;
				}
				if (i % k[j] == 0) {
					int term = 0;
					int fac = 1;
					while ((term = k[j] * fac) <= n) {
						cache[term] = 0;
						fac++;
					}
				}
			}
		}
		int sub = 0;
		for (int i = 0; i < cache.length; i++) {
			sub += cache[i];
		}
		System.out.println(totalSum - sub);
	}

}
