package baekjoon.p09345;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] S = new int[N]; for (int i = 0; i < N; i++) S[i] = i;
			RMQ q = new RMQ(S);
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int Q = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				if (Q == 0) {
					int a = q.query(A, A)[0]; int b = q.query(B, B)[0];
					q.update(A, b); q.update(B, a);
				} else {
					int[] ret = q.query(A, B);
					ans.append(ret[0] == B && ret[1] == A ? "YES" : "NO").append("\n");
				}
			}
		}
		System.out.print(ans);
	}
	
}
// ���� �ִ� �ּ� ����
class RMQ {
	int n;
	int[][] range;
	
	public RMQ(int[] array) {
		n = array.length;
		range = new int[4 * n][2];
		init(array, 1, 0, n - 1);
	}
	
	// node�� ǥ���ϴ� [left, right]������ �ʱ�ȭ�ϰ� �� ������ �� ��ȯ
	private int[] init(int[] array, int node, int left, int right) {
		if (left == right) {
			range[node][0] = range[node][1] = array[left];
			return range[node];
		}
		int mid = (left + right) / 2;
		// ���ȣ���� ���� �ʱ�ȭ
		int[] l = init(array, 2 * node, left, mid);
		int[] r = init(array, 2 * node + 1, mid + 1, right);
		range[node][0] = Math.max(l[0], r[0]);
		range[node][1] = Math.min(l[1], r[1]);
		return range[node];
	}
	
	// [left, right]������ ���� ����
	int[] query(int left, int right) { return query(left, right, 1, 0, n - 1); }
	
	// node[nodeLeft, nodeRight]������ [left, right]������ �������� ���� ��ȯ
	private int[] query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// �������� ���� ��� �ٷ� ��ȯ
		if (right < nodeLeft || nodeRight < left) return new int[] { -1, 100000 };
		// ���ԵǴ� ���
		if (left <= nodeLeft && nodeRight <= right) return range[node];
		int mid = (nodeLeft + nodeRight) / 2;
		int[] l = query(left, right, 2 * node, nodeLeft, mid);
		int[] r = query(left, right, 2 * node + 1, mid + 1, nodeRight);
		return new int[] { Math.max(l[0], r[0]), Math.min(l[1], r[1]) };
	}
	
	public int[] update(int index, int newValue) { return update(index, newValue, 1, 0, n - 1); }
	
	private int[] update(int index, int newValue, int node, int left, int right) {
		if (index < left || right < index) return range[node];
		if (left == right) {
			range[node][0] = range[node][1] = newValue;
			return range[node];
		}
		int mid = (left + right) / 2;
		int[] l = update(index, newValue, 2 * node, left, mid);
		int[] r = update(index, newValue, 2 * node + 1, mid + 1, right);
		range[node][0] = Math.max(l[0], r[0]);
		range[node][1] = Math.min(l[1], r[1]);
		return range[node];
	}
	
}
