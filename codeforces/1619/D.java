import java.io.*;
import java.util.*;

public class D {
	static int M, N;
	static int[][] S;
	static int[] R;

	static boolean f(int x) {
		for (int i = 0; i < M; i++) {
			int cnt = 0; int min = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if (S[i][j] >= x) {
					cnt++;
					min = Math.min(min, S[i][j]);
				}
			}
			if (cnt >= 2) {
				for (int j = 0; j < N; j++) {
					min = Math.min(min, R[j]);
				}
				if (min >= x) return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			S = new int[M][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			R = new int[N];
			for (int j = 0; j < N; j++) {
				int max = 1;
				for (int i = 0; i < M; i++) {
					max = Math.max(max, S[i][j]);
				}
				R[j] = max;
			}
			int lo = 1; int hi = 1000000001;
			while (lo + 1 < hi) {
				int mid = (lo + hi) / 2;
				if (f(mid)) lo = mid;
				else hi = mid;
			}
			bw.append(lo);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}