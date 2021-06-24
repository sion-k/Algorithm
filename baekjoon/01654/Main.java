import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K; static int[] S;

	static long binSearch() {
		long lo = 0;
		long hi = (long)Integer.MAX_VALUE + 1;
		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if (get(mid))
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}

	// 랜선의 길이를 l로 할 때, 그 길이로 N개 이상의 랜선을 만들 수 있는지 여부
	static boolean get(long l) {
		long cnt = 0;
		for (int i = 0; i < K; i++)
			if ((cnt += (S[i] / l)) >= N)
				return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		S = new int[K];
		for (int i = 0; i < K; i++)
			S[i] = Integer.parseInt(br.readLine());
		System.out.println(binSearch());
	}

}
