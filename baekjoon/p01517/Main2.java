package baekjoon.p01517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[N];
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		FenwickTree t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) t.add(i, 1);
		// map[x] = x가 S의 몇번째에 있는지 반환 (1-based), x가 여러개일 수 있으므로 작은 것 부터 
		Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			PriorityQueue<Integer> pq = map.get(S[i]);
			if (pq == null) map.put(S[i], pq = new PriorityQueue<>());
			pq.offer(i + 1);
		}
		Arrays.sort(S);
		long ret = 0;
		for (int i = 0; i < N; i++) {
			PriorityQueue<Integer> pq = map.get(S[i]);
			while (!pq.isEmpty()) {
				int x = pq.poll();
				ret += t.sum(x) - 1;
				t.add(x, -1);
			}
		}
		System.out.println(ret);
	}
	
}

class FenwickTree {
	int[] tree;
	
	FenwickTree(int n) { tree = new int[n + 1]; }
	
	void add(int pos, int val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	int sum(int pos) {
		int ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}
	
}
