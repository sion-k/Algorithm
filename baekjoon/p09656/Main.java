package baekjoon.p09656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N; static int[] cache;

	// i[1, N]번째에서 턴을 시작할 때 이길 수 있는지 여부
	static int dp(int i) {
		if (i == N) {return 0;}
		if (cache[i] != -1) {return cache[i];}
		if (dp(i + 1) == 0 || (i + 3 <= N && dp(i + 3) == 0)) {
			return cache[i] = 1;
		}
		return cache[i] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N + 1]; Arrays.fill(cache, -1);
		System.out.println(dp(1) == 1 ? "SK" : "CY");
	}

}