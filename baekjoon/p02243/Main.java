package baekjoon.p02243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		RSQ q = new RSQ(new int[1000001]);
		for (int i = 0; i < N; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (A == 1) {
				int kth = q.kth(B);
				ans.append(kth).append("\n");
				int prev = q.query(kth, kth);
				q.update(kth, prev - 1);
			} else {
				int C = Integer.parseInt(st.nextToken());
				q.update(B, q.query(B, B) + C);
			}
		}
		System.out.print(ans);
	}
	
}

//���� �� ����
class RSQ {
	int n; // �迭�� ũ��
	int[] rangeSum; // ���� ���

	public RSQ(int[] array) {
		n = array.length; rangeSum = new int[4 * n];
		init(array, 1, 0, n - 1);
	}

	// node�� array[left, right]�� ǥ���� ��,
	// node�� ��Ʈ�� �ϴ� ����Ʈ���� �ʱ�ȭ �ϰ� �� ������ �� ��ȯ
	int init(int[] array, int node, int left, int right) {
		// ���� ��忡 ������ ��� �ٷ� �ʱ�ȭ�Ѵ�
		if (left == right) return rangeSum[node] = array[left];
		// �� �ܿ� ��� ��� ��� ȣ���� ���� ���� ��带 �ʱ�ȭ�Ѵ�
		int mid = (left + right) / 2;
		return rangeSum[node] = init(array, 2 * node, left, mid) + init(array, 2 * node + 1, mid + 1, right);
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// [left, right]�� node�� ǥ���ϴ� ������ �������� �������� ���
		if (right < nodeLeft || nodeRight < left) return 0;
		// node�� ǥ���ϴ� ������ ������ ���ԵǴ� ���
		if (left <= nodeLeft && nodeRight <= right) return rangeSum[node];
		// �� �ܿ� ��� ��� ��� ȣ���� ���� ���� �����
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2 * node, nodeLeft, mid) + query(left, right, 2 * node + 1, mid + 1, nodeRight);
	}

	// �ܺ� ȣ�� �������̽�
	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	// ������ index�� ���� newValue�� �ٲ� �� ���� ����� ���� ��ȯ
	private int update(int index, int newValue, int node, int left, int right) {
		// �ٲٷ��� index�� ���� ������� �����̸� ����
		if (index < left || right < index) return rangeSum[node];
		// ���� ��忡 ������ ��� ���� �ʱ�ȭ�Ѵ�
		if (left == right) return rangeSum[node] = newValue;
		// �� �ܿ� ��� ��� ��� ȣ���� ���� ���� ��带 �ʱ�ȭ�Ѵ�
		int mid = (left + right) / 2;
		return rangeSum[node] = update(index, newValue, 2 * node, left, mid) + update(index, newValue, 2 * node + 1, mid + 1, right);
	}

	// �ܺ� ȣ�� �������̽�
	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}

	// node�� ��Ÿ���� [left, right]�� ���ؼ� k��° ���� ��ȯ(k >= 1)
	private int kth(int k, int node, int left, int right) {
		// ���� ��忡 ������ ��� �� ���� k��° ����
		if (left == right) return left;
		int mid = (left + right) / 2;
		int leftSum = rangeSum[2 * node];
		if (k <= leftSum) return kth(k, 2 * node, left, mid);
		else return kth(k - leftSum, 2 * node + 1, mid + 1, right);
	}
	
	// �ܺ� ȣ�� �������̽�
	int kth(int k) {
		return kth(k, 1, 0, n - 1);
	}
	
}