package baekjoon.p01395;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		RSQ q = new RSQ(N);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int O = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken()) - 1;
			int T = Integer.parseInt(st.nextToken()) - 1;
			if (O == 0) q.update(S, T);
			else bw.append(q.query(S, T)).append("\n");
		}
		System.out.print(bw);
	}
	
}
class RSQ {
	int n; int[] range; boolean[] lazy;
	
	RSQ(int length) {
		this.n = length;
		range = new int[4 * n]; lazy = new boolean[4 * n];
	}
	
	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}
	
	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return 0;
		if (left <= nodeLeft && nodeRight <= right) return range[node];
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2 * node, nodeLeft, mid) + query(left, right, 2 * node + 1, mid + 1, nodeRight);
	}
	
	void update(int left, int right) {
		update(left, right, 1, 0, n - 1);
	}
	
	private void update(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return;
		if (left <= nodeLeft && nodeRight <= right) {
			lazy[node] ^= true;
			propagate(node, nodeLeft, nodeRight);
			return;
		}
		int mid = (nodeLeft + nodeRight) / 2;
		update(left, right, 2 * node, nodeLeft, mid);
		update(left, right, 2 * node + 1, mid + 1, nodeRight);
		range[node] = range[2 * node] + range[2 * node + 1];
	}
	
	private void propagate(int node, int nodeLeft, int nodeRight) {
		if (lazy[node]) {
			if (nodeLeft != nodeRight) {
				lazy[2 * node] ^= true;
				lazy[2 * node + 1] ^= true;
			}
			range[node] = nodeRight - nodeLeft + 1 - range[node];
			lazy[node] = false;
		}
	}
	
}
