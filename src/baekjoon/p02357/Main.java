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
	int n; // �迭�� ����;
	int[] rangeMin; int[] rangeMax; //�� ������ �ּ�, �ִ�ġ

	static final int INF = 1000000001;

	public RMQ(int[] array) {
		n = array.length;
		rangeMin = new int[4 * n]; rangeMax = new int[4 * n];
		init(array, 0, n - 1, 1);
	}

	// node ��尡 array[left, right]�� �迭�� ǥ���� ��
	// node�� ��Ʈ�� �ϴ� ����Ʈ���� �ʱ�ȭ �� ��, �� ������ �ּ�ġ, �ִ�ġ ��ȯ
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
		// �� ������ �������� �������� ���
		if (right < nodeLeft || nodeRight < left) {
			return new int[] { -INF, INF };
		}
		// node�� ������ array[left, right]�� ������ ���ԵǴ� ���
		if (left <= nodeLeft && nodeRight <= right) {
			return new int[] { rangeMin[node], rangeMax[node] };
		}
		// �� �ܿ� ��� ���� node�� �ڽĿ� ���� ��� ȣ��
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