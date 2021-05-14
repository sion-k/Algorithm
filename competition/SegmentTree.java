package competition;

public class SegmentTree {

}
//���� �ּ� ����
class RMQ {
	int n; // �迭�� ũ��
	int[] rangeMin; // ���� ���
	static final int INF = 1000000001;

	public RMQ(int[] array) {
		n = array.length; rangeMin = new int[4 * n];
		init(array, 0, n - 1, 1);
	}

	// node�� array[left, right]�� ǥ���� ��,
	// node�� ��Ʈ�� �ϴ� ����Ʈ���� �ʱ�ȭ �ϰ� �� ������ �ּڰ� ��ȯ
	int init(int[] array, int node, int left, int right) {
		// ���� ��忡 ������ ��� �ٷ� �ʱ�ȭ�Ѵ�
		if (left == right) {return rangeMin[node] = array[left];}
		// �� �ܿ� ��� ��� ��� ȣ���� ���� ���� ��带 �ʱ�ȭ�Ѵ�
		int mid = (left + right) / 2;
		return rangeMin[node] =
		Math.min(init(array, 2 * node, left, mid),
			     init(array, 2 * node + 1, mid + 1, right));
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// [left, right]�� node�� ǥ���ϴ� ������ �������� �������� ���
		if (right < nodeLeft || nodeRight < left) {return INF;}
		// node�� ǥ���ϴ� ������ ������ ���ԵǴ� ���
		if (left <= nodeLeft && nodeRight <= right) {return rangeMin[node];}
		// �� �ܿ� ��� ��� ��� ȣ���� ���� ���� �����
		int mid = (nodeLeft + nodeRight) / 2;
		return Math.min(query(left, right, 2 * node, nodeLeft, mid),
				        query(left, right, 2 * node + 1, mid + 1, nodeRight));
	}

	// �ܺ� ȣ�� �������̽�
	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	// ������ index�� ���� newValue�� �ٲ� �� ���� ����� ���� ��ȯ
	private int update(int index, int newValue, int node, int left, int right) {
		// �ٲٷ��� index�� ���� ������� �����̸� ����
		if (index < left || right < index) {return rangeMin[node];}
		// ���� ��忡 ������ ��� ���� �ʱ�ȭ�Ѵ�
		if (left == right) {return rangeMin[node] = newValue;}
		// �� �ܿ� ��� ��� ��� ȣ���� ���� ���� ��带 �ʱ�ȭ�Ѵ�
		int mid = (left + right) / 2;
		return rangeMin[node] =
		Math.min(update(index, newValue, 2 * node, left, mid),
				 update(index, newValue, 2 * node + 1, mid + 1, right));
	}

	// �ܺ� ȣ�� �������̽�
	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}

}

// ���� Ʈ��
class FenwickTree {
	long[] tree;
	// 1-based
	public FenwickTree(int n) {tree = new long[n + 1];}

	void add(int pos, int val) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos += (pos & -pos);
		}
	}

	long sum(int pos) {
		long ret = 0;
		while (pos > 0) {
			ret += tree[pos];
			pos -= (pos & -pos);
		}
		return ret;
	}

}