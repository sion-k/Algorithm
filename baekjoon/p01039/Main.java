package baekjoon.p01039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int max = -1;
	static final int[] POW = {1, 10, 100, 1000, 10000, 100000, 1000000};

	// here의 i번과 j번째 숫자의 위치를 바꿔서 반환
	// (0 <= i < j < here.length())
	// index : 543210
	//         163750
	// 바꾼 수가 0으로 시작하면 -1을 반환
	static int move(int here, int i, int j) {
		int M = (here + "").length(); int[] digit = new int[7];
		for (int p = 0; here != 0; here /= 10, p++) {digit[p] = here % 10;}
		if (digit[i] == 0 && j == M - 1) {return -1;}
		int temp = digit[i];
		digit[i] = digit[j];
		digit[j] = temp;
		int sum = 0;
		for (int p = 0; p < 7; p++) {sum += (digit[p] * POW[p]);}
		return sum;
	}

	static void BFS(int start) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, start});
		int[][] dist = new int[K + 1][1000001];
		for (int i = 0; i < K + 1; i++) {Arrays.fill(dist[i], -1);}
		dist[0][start] = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int n = here[1]; int k = here[0];
			int M = (n + "").length();
			for (int i = 0; i < M; i++) {
				for (int j = i + 1; j < M; j++) {
					int there = move(n, i, j);
					if (there != -1 && dist[k + 1][there] == -1) {
						dist[k + 1][there] = dist[k][n] + 1;
						if ((k + 1) == K) {
							max = Math.max(max, there);
						} else {
							q.offer(new int[] {k + 1, there});
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		BFS(N);
		System.out.println(max);
	}

}