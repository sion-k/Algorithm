package baekjoon.p01168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		bw.append("<");
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		FenwickTree t = new FenwickTree(N);
		for (int i = 1; i <= N; i++) t.add(i, 1);
		int cnt = 0;
		int here = K;
		while (cnt < N) {
			int kth = t.kth(here);
			t.add(kth, -1);
			cnt++;
			bw.append(kth);
			if (cnt != N) bw.append(", ");
			here += (K - 1);
			int left = N - cnt;
			if (cnt != N && here > left) {
				int mod = here % left;
				here = mod == 0 ? left : mod;				
			}
		}
		System.out.println(bw.append(">"));
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
