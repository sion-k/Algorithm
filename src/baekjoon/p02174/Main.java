package baekjoon.p02174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int[] turnLeft = {0, 3, 4, 2, 1};
	static final int[] turnRight = {0, 4, 3, 1, 2};

	static int N; static int M;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx=  {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] pos = new int[N][2];
	}

}