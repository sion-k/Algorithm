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
		RSQ q = new RSQ(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a =  Integer.parseInt(st.nextToken()) - 1;
			int b =  Integer.parseInt(st.nextToken()) - 1;
			bw.append(q.query(a, b)).append("\n");
			q.update(a, b);
		}
		System.out.print(bw);
	}
	
}

class RSQ {
	int n; int[][] range; int[] lazy;
	
	RSQ(String S) {
		n = S.length();
		range = new int[4 * n][10]; lazy = new int[4 * n];
		init(S, 1, 0, n - 1);
	}
	
	private int[] init(String S, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			range[node][S.charAt(nodeLeft) - '0']++;
			return range[node];
		}
		int mid = (nodeLeft + nodeRight) / 2;
		int[] left = init(S, 2 * node, nodeLeft, mid);
		int[] right = init(S, 2 * node + 1, mid + 1, nodeRight);
		for (int i = 0; i <= 9; i++) range[node][i] = left[i] + right[i];
		return range[node];
	}
	
	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}
	
	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagate(node, nodeLeft, nodeRight);
		if (right < nodeLeft || nodeRight < left) return 0;
		if (left <= nodeLeft && nodeRight <= right) {
			int sum = 0;
			for (int i = 0; i <= 9; i++) sum += i * range[node][i];
			return sum;
		}
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
			lazy[node]++;
			propagate(node, nodeLeft, nodeRight);
			return;
		}
		int mid = (nodeLeft + nodeRight) / 2;
		update(left, right, 2 * node, nodeLeft, mid);
		update(left, right, 2 * node + 1, mid + 1, nodeRight);
		for (int i = 0; i <= 9; i++) range[node][i] = range[2 * node][i] + range[2 * node + 1][i];
	}
	
	private void propagate(int node, int nodeLeft, int nodeRight) {
		if (lazy[node] != 0) {
			if (nodeLeft != nodeRight) {
				lazy[2 * node] += lazy[node];
				lazy[2 * node + 1] += lazy[node];
			}
			int[] temp = new int[10];
			for (int i = 0; i <= 9; i++) temp[(i + lazy[node]) % 10] += range[node][i];
			range[node] = temp;
			lazy[node] = 0;
		}
	}
	
}
