package baekjoon.p14438;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		RMQ q = new RMQ(S);
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (c == 1) {
				q.update(a - 1, b);
			} else {
				bw.write(String.valueOf(q.query(a - 1, b - 1)));
				bw.newLine();
			}
		}
		bw.close();
	}


}
// Range Minimum Query
class RMQ {
	int n; int[] rangeMin;
	static final int INF = 1000000000;

	public RMQ(int[] array) {
		n = array.length; rangeMin = new int[4 * n];
		init(array, 1, 0, n - 1);
	}

	private int init(int[] array, int node, int left, int right) {
		if (left == right) {return rangeMin[node] = array[left];}
		int mid = (left + right) / 2;
		return rangeMin[node] =
		Math.min(init(array, 2 * node, left, mid),
				 init(array, 2 * node + 1, mid + 1, right));
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {return INF;}
		if (left <= nodeLeft && nodeRight <= right) {return rangeMin[node];}
		int mid = (nodeLeft + nodeRight) / 2;
		return Math.min(query(left, right, 2 * node, nodeLeft, mid),
						query(left, right, 2 * node + 1, mid + 1, nodeRight));
	}

	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	private int update(int index, int newValue, int node, int left, int right) {
		if (index < left || right < index) {return rangeMin[node];}
		if (left == right) {return rangeMin[node] = newValue;}
		int mid = (left + right) / 2;
		return rangeMin[node] =
		Math.min(update(index, newValue, 2 * node, left, mid),
				 update(index, newValue, 2 * node + 1, mid + 1, right));
	}

	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}
}