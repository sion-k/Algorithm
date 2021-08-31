import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] S;
	
	static final int INF = 37;
	
	static int bfc(int x) {
		if (x == M) {
			int cnt = 0;
			ArrayList<Integer> R = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) sum += S[i][j];
				if (sum == 0) cnt += INF;
				if (sum < 0) {
					reverseRow(i);
					R.add(i);
					cnt++;
				}
			}
			for (int j = 0; j < M; j++) {
				int sum = 0;
				for (int i = 0; i < N; i++) sum += S[i][j];
				if (sum <= 0) cnt += INF;
			}
			for (int r : R) reverseRow(r);
			return cnt;
		}
		int min = bfc(x + 1);
		reverseCol(x);
		min = Math.min(min, 1 + bfc(x + 1));
		reverseCol(x);
		return min;
	}
	
	static void reverseRow(int i) {
		for (int j = 0; j < M; j++)
			S[i][j] *= -1;
	}
	
	static void reverseCol(int j) {
		for (int i = 0; i < N; i++)
			S[i][j] *= -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		if (N < M) { 
			int[][] R = new int[M][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					R[M - j - 1][i] = S[i][j];
			S = R;
			int temp = N; N = M; M = temp;
		}
		int ret = bfc(0);
		System.out.println(ret >= INF ? -1 : ret);
	}

}
