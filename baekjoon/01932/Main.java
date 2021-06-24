import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] T;
	static int[][] cache;

	static int triMax(int y, int x) {
		if (y == N - 1) {return T[y][x];}
		if (cache[y][x] != -1) {return cache[y][x];}
		return cache[y][x] = T[y][x] + Math.max(triMax(y + 1, x), triMax(y + 1, x + 1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		T = new int[N][N];
		cache = new int[N][N];
		for (int[] c : cache) {Arrays.fill(c, -1);}

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < i + 1; j++) {
				T[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		System.out.println(triMax(0, 0));
	}
}
