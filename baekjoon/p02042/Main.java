package baekjoon.p02042;

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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] S = new long[N];
		for (int i = 0; i < N; i++)
			S[i] = Long.parseLong(br.readLine());
		RSQ r = new RSQ(S);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken()) - 1;
			if (a == 1) {
				r.update(b, c + 1);
			} else {
				bw.write(String.valueOf(r.query(b, (int)c)));
				bw.newLine();
			}
		}
		bw.close();
	}
}

// Range Sum Query
class RSQ {
	int n; // 배열의 길이
	long[] rangeSum; // 구간 노드

	public RSQ(long[] array) {
		n = array.length; rangeSum = new long[4 * n];
		init(array, 0, n - 1, 1);
	}

	// node가 array[left, right]를 표현할 때,
	// node를 루트로 하는 세그먼트 트리를 초기화 하고 구간 합 반환
	private long init(long[] array, int left, int right, int node) {
		// 기저 사례 : 세그먼트 트리의 리프에 도달한 경우
		if (left == right) {return rangeSum[node] = array[left];}
		int mid = (left + right) / 2;
		long leftSum = init(array, left, mid, 2 * node);
		long rightSum = init(array, mid + 1, right, 2 * node + 1);
		return rangeSum[node] = leftSum + rightSum;
	}

	// 쿼리로 주어진 구간이 [left, right]이고, node가 표현하는 구간이 [nodeLeft, nodeRight]일때
	// 두 구간의 교집합에 대해서 쿼리를 수행한다.
	private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// 두 구간의 교집합이 공집합인 경우 상관없는 값 0 반환
		if (right < nodeLeft || nodeRight < left) {return 0;}
		// 두 구간의 교집합이 [nodeLeft, nodeRight]인 경우 (즉, 포함하는 경우) 쿼리에 바로 답 할수 있다.
		if (left <= nodeLeft && nodeRight <= right) {return rangeSum[node];}
		// 나머지 경우는 재귀호출로 답을 합친다
		int mid = (nodeLeft + nodeRight) / 2;
		long leftSum = query(left, right, 2 * node, nodeLeft, mid);
		long rightSum = query(left, right, 2 * node + 1, mid + 1 , nodeRight);
		return leftSum + rightSum;
	}

	long query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	// array[index] 위치의 값을 newValue로 바꾸고 노드가 표현하는 구간의 합 반환
	private long update(int index, long newValue, int node, int nodeLeft, int nodeRight) {
		// 상관없는 범위면 기존의 답 반환
		if (index < nodeLeft || nodeRight < index) {return rangeSum[node];}
		// 리프 노드에 도달한 경우 index위치에 도달함
		if (nodeLeft == nodeRight) {return rangeSum[node] = newValue;}
		int mid = (nodeLeft + nodeRight) / 2;
		return rangeSum[node] =
		update(index, newValue, 2 * node,  nodeLeft, mid) +
		update(index, newValue, 2 * node + 1, mid + 1, nodeRight);
	}

	long update(int index, long newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}
}