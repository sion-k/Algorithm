package baekjoon.p12899;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		FenwickTree t = new FenwickTree();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			if (T == 1) {
				t.add(X, 1);
			} else {
				int kth = t.kth(X);
				t.add(kth, -1);
				ans.append(kth).append("\n");
			}
		}
		System.out.print(ans);
	}
	
}

class FenwickTree {
	int[] tree = new int[2000001];
	
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
		int lo = 0; int hi = 2000000;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (mid != 0 && sum(mid) >= k) hi = mid;
			else lo = mid;
		}
		return hi;
	}
	
}
