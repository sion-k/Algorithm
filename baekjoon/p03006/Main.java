package baekjoon.p03006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N + 1]; int[] P = new int[N + 1]; // x가 몇번째 위치에 있는지 저장
		FenwickTree t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(br.readLine());
			P[S[i]] = i;
			t.add(i, 1);
		}
		int lo = 1; int hi = N;
		for (int i = 1; i <= N; i++) {
			if (i % 2 == 1) {
				ans.append(t.sum(P[lo] - 1));
				t.add(P[lo], -1);
				lo++;
			} else {
				ans.append(t.sum(N) - t.sum(P[hi]));
				t.add(P[hi], -1);
				hi--;
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}
	
}

class FenwickTree {
	int[] tree;
	
	public FenwickTree(int n) { tree = new int[n + 1]; }
	
	void add(int pos, int val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}
	
	// pos번째 위치까지 원소가 몇개 있는지 반환
	int sum(int pos) {
		int ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}
	
}
