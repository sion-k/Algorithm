import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] D;
	static int[][] cache;
	static final int INF = 200000;

	// 현재 일을 맡고 있는 사람의 목록 비트필드가 f일 때
	// [j, )의 일을 모두 처리하는데 필요한 최소 비용
	static int dp(int f, int j) {
		if (j >= N) return 0;
		if (cache[f][j] != 0) return cache[f][j];
		int min = INF;
		for (int i = 0; i < N; i++)
			// i번째 사람이 일을 담당하고 있지 않은 경우
			if ((f & (1 << i)) == 0)
				min = Math.min(min, D[i][j] + dp(f | (1 << i), j + 1));
		return cache[f][j] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		D = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				D[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[(int)Math.pow(2, N)][N];
		bw.write(String.valueOf(dp(0, 0)));
		bw.newLine();
		bw.close();
	}

}
