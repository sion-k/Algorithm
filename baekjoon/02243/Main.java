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

//구간 합 쿼리
class RSQ {
	int n; // 배열의 크기
	int[] rangeSum; // 구간 노드

	public RSQ(int[] array) {
		n = array.length; rangeSum = new int[4 * n];
		init(array, 1, 0, n - 1);
	}

	// node가 array[left, right]를 표현할 때,
	// node를 루트로 하는 서브트리를 초기화 하고 이 구간의 합 반환
	int init(int[] array, int node, int left, int right) {
		// 리프 노드에 도달한 경우 바로 초기화한다
		if (left == right) return rangeSum[node] = array[left];
		// 이 외에 경우 모두 재귀 호출을 통해 현재 노드를 초기화한다
		int mid = (left + right) / 2;
		return rangeSum[node] = init(array, 2 * node, left, mid) + init(array, 2 * node + 1, mid + 1, right);
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// [left, right]와 node가 표현하는 구간의 교집합이 공집합인 경우
		if (right < nodeLeft || nodeRight < left) return 0;
		// node가 표현하는 구간이 완전히 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) return rangeSum[node];
		// 이 외에 경우 모두 재귀 호출을 통해 답을 만든다
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2 * node, nodeLeft, mid) + query(left, right, 2 * node + 1, mid + 1, nodeRight);
	}

	// 외부 호출 인터페이스
	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	// 구간의 index의 값을 newValue로 바꾼 후 구간 노드의 값을 반환
	private int update(int index, int newValue, int node, int left, int right) {
		// 바꾸려는 index의 값과 상관없는 구간이면 무신
		if (index < left || right < index) return rangeSum[node];
		// 리프 노드에 도달한 경우 새로 초기화한다
		if (left == right) return rangeSum[node] = newValue;
		// 이 외에 경우 모두 재귀 호출을 통해 현재 노드를 초기화한다
		int mid = (left + right) / 2;
		return rangeSum[node] = update(index, newValue, 2 * node, left, mid) + update(index, newValue, 2 * node + 1, mid + 1, right);
	}

	// 외부 호출 인터페이스
	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}

	// node가 나타내는 [left, right]에 대해서 k번째 값을 반환(k >= 1)
	private int kth(int k, int node, int left, int right) {
		// 리프 노드에 도달한 경우 그 값이 k번째 값임
		if (left == right) return left;
		int mid = (left + right) / 2;
		int leftSum = rangeSum[2 * node];
		if (k <= leftSum) return kth(k, 2 * node, left, mid);
		else return kth(k - leftSum, 2 * node + 1, mid + 1, right);
	}
	
	// 외부 호출 인터페이스
	int kth(int k) {
		return kth(k, 1, 0, n - 1);
	}
	
}
