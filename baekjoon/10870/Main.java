import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] cache;

	static int dp(int n) {
		if (n < 2) {return n;}
		if (cache[n] != 0) {return cache[n];}
		return cache[n] = dp(n - 1) + dp(n - 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cache = new int[N + 1];
		System.out.println(dp(N));
	}

}
