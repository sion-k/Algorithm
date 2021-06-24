import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] W;
	static int[][] cache;
	
	// 이전에 prev 선택을 했을 때 i번째부터 선택해서 얻을 수 있는 최대 값
	static int dp(int prev, int i) {
		if (i >= N) {return 0;}
		if (i == N - 1 && prev != 0) {return W[i];}
		if (cache[prev][i] != -1) {return cache[prev][i];}
		int ret = 0;
		switch (prev) {
		case 0:// OO
			ret = dp(1, i + 1);
			break;
		case 1:// OX
			ret = Math.max(W[i] + dp(2, i + 1), W[i + 1] + dp(2, i + 2));
			break;
		case 2:// XO
			ret = Math.max(W[i] + dp(1, i + 2), dp(1, i + 1));
			break;
		case 3:// XX
			ret = W[i] + dp(2, i + 1);
			break;
		}
		return cache[prev][i] = ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N];
		cache = new int[4][N];
		for (int i = 0; i < 4; i++) {
			Arrays.fill(cache[i], -1);
		}
		for (int i = 0; i < N; i++) {W[i] = Integer.parseInt(br.readLine());}
		int max = 0;
		if (N >= 3) {
			int[] c = new int[4];
			c[0] = dp(0, 2) + W[0] + W[1];
			c[1] = dp(1, 2) + W[0];
			c[2] = dp(2, 2) + W[1];
			c[3] = dp(3, 2);
			Arrays.sort(c);
			max = c[3];
		} else if (N == 2) {
			max = W[0] + W[1];
		} else if (N == 1) {
			max = W[0];
		}
		System.out.println(max);
		br.close();
	}

}
