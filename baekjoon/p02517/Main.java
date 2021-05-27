package baekjoon.p02517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N]; ArrayList<Integer> sorted = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(br.readLine());
			sorted.add(S[i]);
		}
		Collections.sort(sorted);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < sorted.size(); i++)
			map.put(sorted.get(i), i + 1);
		FenwickTree t = new FenwickTree(N);
		for (int i = 0; i < N; i++) {
			int x = map.get(S[i]);
			t.add(x, 1);
			bw.append(i + 1 - t.sum(x) + 1).append("\n");
		}
		System.out.print(bw);
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
