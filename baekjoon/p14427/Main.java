package baekjoon.p14427;

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
		int[] S = new int[N + 1]; // 마지막 원소는 INF
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		S[N] = 1000000000;
		RMQ q = new RMQ(S);
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			if (c == 1) {
				int index = Integer.parseInt(st.nextToken()) - 1;
				int newValue = Integer.parseInt(st.nextToken());
				q.update(index, newValue);
			} else {
				bw.write(String.valueOf(q.query(0, N) + 1));
				bw.newLine();
			}
		}
		bw.close();
	}

}

// Range Minimum-index Query
class RMQ {
	int n; int[] rangeMin;
	int[] array;

	public RMQ(int[] array) {
		n = array.length - 1; this.array = array;
		rangeMin = new int[4 * n];
		init(1, 0, n - 1);
	}

	private int init(int node, int left, int right) {
		if (left == right) {return rangeMin[node] = left;}
		int mid = (left + right) / 2;
		int leftIndex = init(2 * node, left, mid);
		int rightIndex = init(2 * node + 1, mid + 1, right);
		if (array[leftIndex] <= array[rightIndex])
			return rangeMin[node] = leftIndex;
		else
			return rangeMin[node] = rightIndex;
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {return n;}
		if (left <= nodeLeft && nodeRight <= right) {return rangeMin[node];}
		int mid = (nodeLeft + nodeRight) / 2;
		int leftIndex = query(left, right, 2 * node, nodeLeft, mid);
		int rightIndex = query(left, right, 2 * node + 1, mid + 1, right);
		if (array[leftIndex] <= array[rightIndex])
			return leftIndex;
		else
			return rightIndex;
	}

	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	private int update(int index, int newValue, int node, int left, int right) {
		if (index < left || right < index) {return rangeMin[node];}
		if (left == right) {return rangeMin[node] = index;}
		int mid = (left + right) / 2;
		int leftIndex = update(index, newValue, 2 * node, left, mid);
		int rightIndex = update(index, newValue, 2 * node + 1, mid + 1, right);
		if (array[leftIndex] <= array[rightIndex])
			return rangeMin[node] = leftIndex;
		else
			return rangeMin[node] = rightIndex;
	}

	int update(int index, int newValue) {
		array[index] = newValue;
		return update(index, newValue, 1, 0, n - 1);
	}
}