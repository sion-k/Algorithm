package baekjoon.p01039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[][] booked = new boolean[1000001][K + 1];
		booked[start][0] = true;
		int[] dist = new int[1000001];
		dist[start] = 0;

		while (!q.isEmpty()) {
			int here = q.poll();
			int M = (here + "").length();
			for (int i = 0; i < M; i++) {
				for (int j = i + 1; j < M; j++) {
					int there = move(here, i, j);
					if (there != -1 && !booked[there][dist[here]]) {
						booked[there][dist[here]] = true;
						dist[there] = dist[here] + 1;
						if (dist[there] == K) {
							max = Math.max(max, there);
						} else {
							q.offer(there);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			BFS(N);
			System.out.println(max);
		}
	}

}