import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] bit = {
		0b111_101_101_101_111,
		0b001_001_001_001_001,
		0b111_001_111_100_111,
		0b111_001_111_001_111,
		0b101_101_111_001_001,
		0b111_100_111_001_111,
		0b111_100_111_101_111,
		0b111_001_001_001_001,
		0b111_101_111_101_111,
		0b111_101_111_001_111
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] C = new char[5][4* N - 1];
		for (int i = 0; i < 5; i++)
			C[i] = br.readLine().toCharArray();
		int[] S = new int[N];
		for (int k = 0; k < 4 * N - 1; k += 4) {
			int x = 0;
			int t = 0;
			for (int i = 4; i >= 0; i--)
				for (int j = k + 2; j >= k; j--) {
					if (C[i][j] == '#') x |= (1 << t);
					t++;
				}
			S[k / 4] = x;
		}
		long sum = 0; long cnt = 1;
		for (int i = N - 1; i >= 0; i--) {
			long s = 0; long t = 0;
			for (int j = 0; j <= 9; j++)
				if ((bit[j] & S[i]) == S[i]) {
					s += j;
					t++;
				}
			if (t == 0) { sum = 0; break; }
			sum += (t - 1) * sum;
			sum += cnt * s * Math.pow(10, N - 1 - i);
			cnt *= t;
		}
		if (sum == 0) System.out.println(-1);
		else System.out.printf("%f\n", (double)sum / cnt);
	}

}
