package baekjoon.p01275;

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
		int Q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[N];
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		RSQ r = new RSQ(S);
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			int start = Math.min(x, y);
			int end = Math.max(x, y);
			bw.write(String.valueOf(r.query(start, end)));
			r.update(a, b);
			bw.newLine();
		}
		bw.close();
	}
}

// Range Sum Query
class RSQ {
	int n; // �迭�� ����
	long[] rangeSum; // ���� ���

	public RSQ(int[] array) {
		n = array.length; rangeSum = new long[4 * n];
		init(array, 0, n - 1, 1);
	}

	// node�� array[left, right]�� ǥ���� ��,
	// node�� ��Ʈ�� �ϴ� ���׸�Ʈ Ʈ���� �ʱ�ȭ �ϰ� ���� �� ��ȯ
	private long init(int[] array, int left, int right, int node) {
		// ���� ��� : ���׸�Ʈ Ʈ���� ������ ������ ���
		if (left == right) {return rangeSum[node] = array[left];}
		int mid = (left + right) / 2;
		long leftSum = init(array, left, mid, 2 * node);
		long rightSum = init(array, mid + 1, right, 2 * node + 1);
		return rangeSum[node] = leftSum + rightSum;
	}

	// ������ �־��� ������ [left, right]�̰�, node�� ǥ���ϴ� ������ [nodeLeft, nodeRight]�϶�
	// �� ������ �����տ� ���ؼ� ������ �����Ѵ�.
	private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// �� ������ �������� �������� ��� ������� �� 0 ��ȯ
		if (right < nodeLeft || nodeRight < left) {return 0;}
		// �� ������ �������� [nodeLeft, nodeRight]�� ��� (��, �����ϴ� ���) ������ �ٷ� �� �Ҽ� �ִ�.
		if (left <= nodeLeft && nodeRight <= right) {return rangeSum[node];}
		// ������ ���� ���ȣ��� ���� ��ģ��
		int mid = (nodeLeft + nodeRight) / 2;
		long leftSum = query(left, right, 2 * node, nodeLeft, mid);
		long rightSum = query(left, right, 2 * node + 1, mid + 1 , nodeRight);
		return leftSum + rightSum;
	}

	long query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	// array[index] ��ġ�� ���� newValue�� �ٲٰ� ��尡 ǥ���ϴ� ������ �� ��ȯ
	private long update(int index, long newValue, int node, int nodeLeft, int nodeRight) {
		// ������� ������ ������ �� ��ȯ
		if (index < nodeLeft || nodeRight < index) {return rangeSum[node];}
		// ���� ��忡 ������ ��� index��ġ�� ������
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