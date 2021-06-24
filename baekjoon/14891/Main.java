import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] S = new int[4][8];
	static int[] left = new int[4];
	static int[] right = new int[4];
	static int[] top = new int[4];
	
	static {
		Arrays.fill(left, 6); Arrays.fill(right, 2);
	}
	
	// i번째 시계를 d방향으로 회전, p는 현재 톱니바퀴의 영향이 향하는 방향 (-1이면 왼쪽 1이면 오른쪽)
	static void wise(int i, int d, int p) {
		if ((p == 0 || p == -1) && 0 <= i - 1 && S[i - 1][right[i - 1]] != S[i][left[i]]) {
			wise(i - 1, -d, -1);			
		}
		if ((p == 0 || p == 1) && i + 1 < 4 && S[i + 1][left[i + 1]] != S[i][right[i]])
			wise(i + 1, -d, 1);
		left[i] = (left[i] - d + 8) % 8;
		right[i] = (right[i] - d + 8) % 8;
		top[i] = (top[i] - d + 8) % 8;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			String row = br.readLine();
			for (int j = 0; j < 8; j++)
				S[i][j] = row.charAt(j) - '0';
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());			
			wise(n - 1, d, 0);
		}
		int sum = 0;
		for (int i = 0; i < 4; i++) sum += (S[i][top[i]] == 1) ? (1 << i) : 0;
		System.out.println(sum);
	}
	
}
