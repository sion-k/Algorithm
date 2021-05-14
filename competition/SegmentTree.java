package competition;

public class SegmentTree {

}
//구간 최소 쿼리
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
	int init(int[] array, int node, int left, int right) {
		// 리프 노드에 도달한 경우 바로 초기화한다
		if (left == right) {return rangeMin[node] = array[left];}
		// 이 외에 경우 모두 재귀 호출을 통해 현재 노드를 초기화한다
		int mid = (left + right) / 2;
		return rangeMin[node] =
		Math.min(init(array, 2 * node, left, mid),
			     init(array, 2 * node + 1, mid + 1, right));
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// [left, right]와 node가 표현하는 구간의 교집합이 공집합인 경우
		if (right < nodeLeft || nodeRight < left) {return INF;}
		// node가 표현하는 구간이 완전히 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) {return rangeMin[node];}
		// 이 외에 경우 모두 재귀 호출을 통해 답을 만든다
		int mid = (nodeLeft + nodeRight) / 2;
		return Math.min(query(left, right, 2 * node, nodeLeft, mid),
				        query(left, right, 2 * node + 1, mid + 1, nodeRight));
	}

	// 외부 호출 인터페이스
	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	// 구간의 index의 값을 newValue로 바꾼 후 구간 노드의 값을 반환
	private int update(int index, int newValue, int node, int left, int right) {
		// 바꾸려는 index의 값과 상관없는 구간이면 무신
		if (index < left || right < index) {return rangeMin[node];}
		// 리프 노드에 도달한 경우 새로 초기화한다
		if (left == right) {return rangeMin[node] = newValue;}
		// 이 외에 경우 모두 재귀 호출을 통해 현재 노드를 초기화한다
		int mid = (left + right) / 2;
		return rangeMin[node] =
		Math.min(update(index, newValue, 2 * node, left, mid),
				 update(index, newValue, 2 * node + 1, mid + 1, right));
	}

	// 외부 호출 인터페이스
	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}

}

// 펜윅 트리
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