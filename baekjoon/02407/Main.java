import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		BigInteger[][] cache = new BigInteger[2][N + 1];
		Arrays.fill(cache[0], BigInteger.ONE);
		Arrays.fill(cache[1], BigInteger.ONE);
		for (int n = 2; n <= N; n++) {
			for (int r = 1; r < n; r++) {
				cache[1 - (n % 2)][r] = cache[n % 2][r - 1].add(cache[n % 2][r]);
			}
		}
		System.out.println(cache[1 - (N % 2)][R]);

	}

}
