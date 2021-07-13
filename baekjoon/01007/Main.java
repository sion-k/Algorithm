import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Pair[] P;
	static boolean[] picked;
	
	// P[here, N)에서 k개를 고르는 경우 모두 시도
	static double bfc(int here, int k) {
		if (here == N) {
			if (k != 0) return Double.MAX_VALUE;
			long y = 0; long x = 0;
			for (int i = 0; i < N; i++) {
				if (picked[i]) { y -= P[i].y; x -= P[i].x; }
				else { y += P[i].y; x += P[i].x; }
			}
			return Math.sqrt(y * y + x * x);
		}
		double min = bfc(here + 1, k);
		picked[here] = true;
		min = Math.min(min, bfc(here + 1, k - 1));
		picked[here] = false;
		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			N = Integer.parseInt(br.readLine());
			P = new Pair[N];
			picked = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				P[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			bw.append(bfc(0, N / 2)).append("\n");
		}
		System.out.print(bw);
	}

}
class Pair { 
	int y, x;
	Pair(int y, int x)  { this.y = y; this.x = x; }
}
