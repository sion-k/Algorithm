package baekjoon.p01849;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		FenwickTree t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) t.add(i, 1);
		int[] S = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int k = t.kth(Integer.parseInt(br.readLine()) + 1);
			S[k] = i; t.add(k, -1);
		}
		for (int i = 1; i <= N; i++) bw.append(S[i]).append("\n");
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
	
	int kth(int k) {
		int lo = 0; int hi = tree.length - 1;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (mid != 0 && sum(mid) >= k) hi = mid;
			else lo = mid;
		}
		return hi;
	}
	
}
