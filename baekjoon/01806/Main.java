import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] S;
	
	static final int INF = 100000;
	
	// S[head, tail]의 부분합 중에서 K를 넘는 가장 작은 tail을 찾는다
	static int bin(int head) {
		int lo = head - 1; int hi = N + 1;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (S[mid] - S[head - 1] >= K) hi = mid;
			else lo = mid;
		}
		return hi;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		S = new int[N + 1];
		for (int i = 1; i <= N; i++)
			S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
		int min = INF;
		for (int head = 1; head <= N; head++) {
			int tail = bin(head);
			if (tail != N + 1) min = Math.min(min, tail - head + 1);
		}
		System.out.println(min == INF ? 0 : min);
	}
	
}
