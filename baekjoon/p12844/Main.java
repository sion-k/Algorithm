import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		RSQ q = new RSQ(S);
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				q.update(b, c, Integer.parseInt(st.nextToken()), 1, 0, N - 1);
			} else {
				bw.append(q.query(b, c, 1, 0, N - 1)).append("\n");
			}
		}
		System.out.print(bw);
	}
	
}

class RSQ {
	int n; int[] range; int[] lazy;
	
	RSQ(int[] array) {
		n = array.length;
		range = new int[4 * n]; lazy = new int[4 * n];
		init(array, 1, 0, n - 1);
	}
	
	int init(int[] array, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) return range[node] = array[nodeLeft];
		int mid = (nodeLeft + nodeRight) / 2;
		return range[node] = init(array, 2 * node, nodeLeft, mid) ^ init(array, 2 * node + 1, mid + 1, nodeRight);
	}
	
	int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return 0;
		if (left <= nodeLeft && nodeRight <= right) return range[node];
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2 * node, nodeLeft, mid) ^ query(left, right, 2 * node + 1, mid + 1, nodeRight);
	}
	
	void update(int left, int right, int k, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return;
		if (left <= nodeLeft && nodeRight <= right) { 
			lazy[node] ^= k;
			propagate(node, nodeLeft, nodeRight);
			return;
		}
		int mid = (nodeLeft + nodeRight) / 2;
		update(left, right, k, 2 * node, nodeLeft, mid);
		update(left, right, k, 2 * node + 1 , mid + 1, nodeRight);
		range[node] = range[2 * node] ^ range[2 * node + 1];
	}
	
	void propagate(int node, int nodeLeft, int nodeRight) {
		if (lazy[node] != 0) {
			if (nodeLeft != nodeRight) {
				lazy[2 * node] ^= lazy[node];
				lazy[2 * node + 1] ^= lazy[node];
			}
			range[node] ^= lazy[node] * ((nodeRight - nodeLeft + 1) % 2);
			lazy[node] = 0;
		}
	}
	
}
