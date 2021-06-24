import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] A;
	static final int MOD = 1000;

	// A^B를 반환
	static int[][] pow(long B) {
		if (B == 1) return A;
		if (B % 2 == 1) return mul(A, pow(B - 1));
		int[][] d = pow(B / 2);
		return mul(d, d);
	}

	// 행렬 A B의 곱을 반환
	static int[][] mul(int[][] A, int[][] B) {
		int[][] ret = new int[A.length][B[0].length];
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A.length; j++)
				for (int k = 0; k < A[0].length; k++)
					ret[i][j] = (ret[i][j] + A[i][k] * B[k][j]) % MOD;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] ret = pow(B);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) sb.append(ret[i][j] % MOD).append(" ");
			System.out.println(sb.toString().trim());
			sb = new StringBuilder();
		}

	}

}
