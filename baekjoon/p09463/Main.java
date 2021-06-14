package baekjoon.p09463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N + 1];
			int[] P = new int[N + 1];
			FenwickTree t = new FenwickTree(N);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) P[Integer.parseInt(st.nextToken())] = i;
			long sum = 0;
			for (int i = N; i >= 1; i--) {
				sum += t.sum(P[S[i]] - 1);
				t.add(P[S[i]], 1);
			}
			bw.append(sum).append("\n");
		}
		System.out.print(bw);
	}
	
}

class FenwickTree {
	long[] tree;
	
	FenwickTree(int n) { tree = new long[n + 1]; }
	
	void add(int pos, int val) {
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
