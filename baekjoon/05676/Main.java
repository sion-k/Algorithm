import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final char[] ans = {'-', '0', '+'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String read = "";
		while ((read = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(read, " ");
			int N = Integer.parseInt(st.nextToken());
			int[] S = new int[N];
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int c = Integer.parseInt(st.nextToken());
				c = c > 0 ? 1 : (c == 0 ? 0 : -1);
				S[i] = c;
			}
			RPQ q = new RPQ(S);
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				char c = st.nextToken().charAt(0);
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (c == 'C') {
					b = b > 0 ? 1 : (b == 0 ? 0 : -1);
					q.update(a - 1, b);
				} else {
					bw.write(ans[q.query(a - 1, b - 1) + 1]);
				}
			}
			bw.newLine();
		}
		bw.close();
	}

}

// Range Product Query
class RPQ {
	int n; int[] rangeProduct;

	public RPQ(int[] array) {
		n = array.length; rangeProduct = new int[4 * n];
		init(array, 1, 0, n - 1);
	}

	private int init(int[] array, int node, int left, int right) {
		// 기저 사례 : 리프 노드
		if (left == right) {return rangeProduct[node] = array[left];}
		int mid = (left + right) / 2;
		int leftProduct = init(array, 2 * node, left, mid);
		int rightProduct = init(array, 2 * node + 1, mid + 1, right);
		return rangeProduct[node] = leftProduct * rightProduct;
	}

	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		// 교집합이 공집합인 경우
		if (right < nodeLeft || nodeRight < left) {return 1;}
		// 구간 노드가 포함되는 경우
		if (left <= nodeLeft && nodeRight <= right) {return rangeProduct[node];}
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2 * node, nodeLeft, mid) *
			   query(left, right, 2 * node + 1, mid + 1, nodeRight);
	}

	int query(int left, int right) {
		return query(left, right, 1, 0, n - 1);
	}

	private int update(int index, int newValue, int node, int left, int right) {
		if (index < left || right < index) {return rangeProduct[node];}
		if (left == right) {return rangeProduct[node] = newValue;}
		int mid = (left + right) / 2;
		int leftProduct = update(index, newValue, 2 * node, left, mid);
		int rightProduct = update(index, newValue, 2 * node + 1, mid + 1, right);
		return rangeProduct[node] = leftProduct * rightProduct;
	}

	int update(int index, int newValue) {
		return update(index, newValue, 1, 0, n - 1);
	}

}
