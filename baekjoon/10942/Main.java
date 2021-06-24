import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] S;
	static int[][] cache;

	static int dp(int i, int j) {
		if (i >= j) {return 1;}
		if (cache[i][j] != -1) {return cache[i][j];}
		if ((S[i] == S[j]) && dp(i + 1, j - 1) == 1) {return cache[i][j] = 1;}
		return cache[i][j] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		S = new int[N + 1]; cache = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {Arrays.fill(cache[i], -1);}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(dp(start, end)));
			bw.newLine();
		}
		bw.close();
	}

}
