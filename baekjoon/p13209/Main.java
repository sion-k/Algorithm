package baekjoon.p13209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] S; static long M;
	static ArrayList<ArrayList<Integer>> adj;
	static int cnt;
	
	// here 루트로 트리에 한 컴포넌트의 정점의 가중치의 합이 M을 넘지 않게
	// 최적의 방법으로 나누고 here이 속한 컴포넌트의 가중치의 합을 반환
	static long postorder(int here, int parent) {
		long sum = S[here];
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int there : adj.get(here)) {
			if (there == parent) continue;
			long t = postorder(there, here);
			sum += t; pq.offer(t);
		}
		// 어차피 끊어내야 한다면 큰거부터 끊어낸다
		while (!pq.isEmpty() && sum > M) { sum -= pq.poll(); cnt++; }
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			S = new int[N + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(N);
			for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj.get(u).add(v); adj.get(v).add(u);
			}
			// f(x) = 한 컴포넌트의 가중치의 합이 x가 넘지 않게,
			// 최적의 방법으로 잘랐을 때 자르는 횟수가 K를 넘지 않게 자를 수 있는지 여부
			// f(lo) = false f(hi) = true인 hi를 반환
			long lo = 0; long hi = 100000000000000L;
			int max = 0;
			for (int i = 1; i <= N; i++) max = Math.max(max, S[i]);
			while (lo + 1 < hi) {
				long mid = (lo + hi) / 2;
				M = mid; cnt = 0;
				postorder(1, 1);
				if (max <= mid && cnt <= K) hi = mid;
				else lo = mid;
			}
			ans.append(hi).append("\n");
		}
		System.out.print(ans);
	}
	
}