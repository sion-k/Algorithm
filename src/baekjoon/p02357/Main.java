package baekjoon.p02357;

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
		int[] S = new int[N];
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(br.readLine());
		RMQ r = new RMQ(S);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int[] ret = r.query(left, right);
			bw.write(String.valueOf(ret[0]));
			bw.write(" ");
			bw.write(String.valueOf(ret[1]));
			bw.newLine();
		}
		bw.close();
	}

}

class RMQ {
	int n; // 배열의 길이;
	int[] rangeMin; int[] rangeMax; //각 구간의 최소, 최대치

	static final int INF = 1000000001;

	public RMQ(int[] array) {
		n = array.length;
		rangeMin = new int[4 * n]; rangeMax = new int[4 * n];
		init(array, 0, n - 1, 1);
	}

	// node 노드가 array[left, right]를 배열을 표현할 때
	// node를 루트로 하는 서브트리를 초기화 한 뒤, 이 구간의 최소치, 최대치 반환
	int[] init(int[] array, int left, int right, int node) {
		if (left == right) {
			int ret = rangeMin[node] = rangeMax[node] = array[left];
			return new int[] { ret, ret };
		}
		int mid = (left + right) / 2;
		int[] L = init(array, left, mid, 2 * node);
		int[] R = init(array, mid + 1, right, 2 * node + 1);
		int min = rangeMin[node] = Math.min(L[0], R[0]);
		int max = rangeMax[node] = Math.max(L[1], R[1]);
		return new int[] { min, max };
	}

	private int[] query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// 두 구간의 교집합이 공집합인 경우
		if (right < nodeLeft || nodeRight < left) {
			return new int[] { -INF, INF };
		}
		// node의 범위가 array[left, right]에 완전히 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) {
			return new int[] { rangeMin[node], rangeMax[node] };
		}
		// 이 외에 모든 경우는 node의 자식에 대해 재귀 호출
		int mid = (nodeLeft + nodeRight) / 2;
		int[] L = query(left, right, 2 * node, nodeLeft, mid);
		int[] R = query(left, right, 2 * node + 1, mid + 1, nodeRight);
		int min = Math.min(L[0], R[1]);
		int max = Math.max(L[1], R[1]);
		return new int[] { min, max };
	}

	int[] query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

}