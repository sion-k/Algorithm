import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] cache;

	static int dp(int i, int prev) {
		if (i >= N) {return 1;}
		if (cache[i][prev] != 0) {return cache[i][prev];}
		cache[i][prev] = (cache[i][prev] + dp(i + 1, 0)) % 9901;
		cache[i][prev] = (cache[i][prev] + dp(i + 1, 1)) % 9901;
		if (prev == 0) {
			cache[i][prev] = (cache[i][prev] + dp(i + 1, 1)) % 9901;
		}
		return cache[i][prev];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N][2];
		System.out.println(dp(0, 0));
	}

}
