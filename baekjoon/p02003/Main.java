package baekjoon.p02003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] S = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		int head = 0; int tail = 0;
		int sum = S[0];
		int cnt = 0;
		while (head < N) {
			while (tail + 1 < N && sum + S[tail + 1] <= M) {
				sum += S[tail + 1]; tail++;
			}
			if (sum == M) cnt++;
			sum -= S[head];
			head++;
		}
		System.out.println(cnt);
	}
	
}
