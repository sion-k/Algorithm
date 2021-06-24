import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static final int MOD = 1000000009;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] cache = new int[1000001];
		cache[1] = 1; cache[2] = 2; cache[3] = 4;
		for (int i = 4; i <= 1000000; i++) {
			cache[i] = (cache[i] + cache[i - 1]) % MOD;
			cache[i] = (cache[i] + cache[i - 2]) % MOD;
			cache[i] = (cache[i] + cache[i - 3]) % MOD;
		}
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(cache[N]));
			bw.newLine();
		}
		bw.close();
	}

}
