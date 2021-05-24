package baekjoon.p16975;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
		FenwickTree t = new FenwickTree(N);
		long last = 0;
		for (int i = 1; i <= N; i++) {
			t.add(i, S[i] - last);
			last = S[i];
		}
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int q = Integer.parseInt(st.nextToken());
			if (q == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				t.add(a, k); t.add(b + 1, -k);
			} else {
				ans.append(t.sum(Integer.parseInt(st.nextToken()))).append("\n");
			}
		}
		System.out.print(ans);
	}
	
}

class FenwickTree {
	long[] tree;
	FenwickTree(int n) { tree = new long[n + 1]; }
	
	void add(int pos, long val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	long sum(int pos) {
		long ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}
	
}
