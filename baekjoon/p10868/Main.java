package baekjoon.p10868;

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
		int[] S = new int[N];
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(br.readLine());
		RMQ r = new RMQ(S);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			bw.write(String.valueOf(r.query(left, right)));
			bw.newLine();
		}
		bw.close();
	}

}

// 구간 최소 쿼리
class RMQ {
	int n; // 배열의 크기
	int[] rangeMin; // 구간 노드
	static final int INF = 1000000001;

	public RMQ(int[] array) {
		n = array.length; rangeMin = new int[4 * n];
		init(array, 0, n - 1, 1);
	}

	// node가 array[left, right]를 표현할 때,
	// node를 루트로 하는 서브트리를 초기화 하고 이 구간의 최솟값 반환
	int init(int[] array, int left, int right, int node) {
		if (left == right) {return rangeMin[node] = array[left];}
		int mid = (left + right) / 2;
		int leftMin = init(array, left, mid, 2 * node);
		int rightMin = init(array, mid + 1, right, 2 * node + 1);
		return rangeMin[node] = Math.min(leftMin, rightMin);
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// [left, right]와 node가 표현하는 구간의 교집합이 공집합인 경우
		if (right < nodeLeft || nodeRight < left) {return INF;}
		// node가 표현하는 구간이 완전히 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) {return rangeMin[node];}
		// 그 외에 경우 자식 노드에 대해 재귀 호출해서 답을 만든다
		int mid = (nodeLeft + nodeRight) / 2;
		int leftMin = query(left, right, 2 * node, nodeLeft, mid);
		int rightMin = query(left, right, 2 * node + 1, mid + 1, nodeRight);
		return Math.min(leftMin, rightMin);
	}

	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

}