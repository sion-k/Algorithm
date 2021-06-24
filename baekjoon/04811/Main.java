import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static long[][] cache;

	static long dp(int w, int h) {
		if (w == 0) {return 1;}
		if (cache[w][h] != 0) {return cache[w][h];}
		cache[w][h] = dp(w - 1, h + 1);
		if (h != 0) {cache[w][h] += dp(w, h - 1);}
		return cache[w][h];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = 1;
		cache = new long[31][31];
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			bw.write(String.valueOf(dp(N, 0)));
			bw.newLine();
		}
		bw.close();
	}

}
