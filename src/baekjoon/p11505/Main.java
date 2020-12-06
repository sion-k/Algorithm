package baekjoon.p11505;

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
		int[] S = new int[N];
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(br.readLine());
		RPQ s = new RPQ(S);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				s.update(b - 1, c);
			} else {
				bw.write(String.valueOf(s.query(b - 1, c - 1)));
				bw.newLine();
			}
		}
		bw.close();
	}

}

// Range Product Query
class RPQ {
	int n; // 배열의 길이
	int[] rangeProduct; // 구간 노드

	static final int MOD = 1000000007;

	public RPQ(int[] array) {
		n = array.length;
		rangeProduct = new int[4 * n];
		init(array, 1, 0, n - 1);
	}

	// node 노드가 표현하는 구간 [left, right]를 루트로 하는 서브트리를 초기화하고 곱 반환
	private int init(int[] array, int node, int left, int right) {
		// 기저 사례 : 리프 노드에 도달한 경우
		if (left == right) {return rangeProduct[node] = array[left];}
		// 이 외에 모두 재귀 호출로 답을 초기화
		int mid = (left + right) / 2;
		int leftProduct = init(array, 2 * node, left, mid);
		int rightProduct = init(array, 2 * node + 1, mid + 1, right);
		return rangeProduct[node] =
		(int)(((long)leftProduct * rightProduct) % MOD);
	}

	// array[left, right]와 [nodeLeft, nodeRight]의 교집합의 답 반환
	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// 교집합이 공집합인 경우
		if (right < nodeLeft || nodeRight < left) {return 1;}
		// 구간 노드가 완전히 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) {return rangeProduct[node];}
		// 이 외에 모두 재귀 호출로 답을 만듬
		int mid = (nodeLeft + nodeRight) / 2;
		int leftProduct = query(left, right, 2 * node, nodeLeft, mid);
		int rightProduct = query(left, right, 2 * node + 1, mid + 1, nodeRight);
		return (int)(((long)leftProduct * rightProduct) % MOD);
	}

	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	private int update(int index, int newValue, int node, int left, int right) {
		// 기저 사례 : 상관없는 구간이면 기존의 값 반환
		if (index < left || right < index) {return rangeProduct[node];}
		// 리프 노드에 도착한 경우 [index, index]위치의 구간 노드임
		if (left == right) {return rangeProduct[node] = newValue;}
		// 이 외에 모두 재귀 호출로 답을 갱신함
		int mid = (left + right) / 2;
		int leftProduct = update(index, newValue, 2 * node, left, mid);
		int rightProduct = update(index, newValue, 2 * node + 1, mid + 1, right);
		return rangeProduct[node] =
		(int)(((long)leftProduct * rightProduct) % MOD);
	}

	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}
}