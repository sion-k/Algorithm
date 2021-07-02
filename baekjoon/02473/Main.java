import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(S);
		long sum = 0;
		long best = 3000000000L;
		int p = 0; int q = N - 2; int r = N - 1;
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				sum = S[i] + S[j];
				// S[j + 1, N)에서 S[i] + S[j] + S[k]가 0에 가장 가까운 k를 찾는다
				// S[hi] = 더했을 때 0보다 같거나 큼
				// S[lo] = 더했을 때 0보다 작음 
				int lo = j + 1; int hi = N - 1;
				while (lo + 1 < hi) {
					int mid = (lo + hi) / 2;
					if (sum + S[mid] >= 0) hi = mid;
					else lo = mid;
				}
				if (Math.abs(sum + S[lo]) < Math.abs(sum + S[hi])) {
					if (best > Math.abs(sum + S[lo])) {
						best = Math.abs(sum + S[lo]);
						p = i; q = j; r = lo;
					}
				} else {
					if (best > Math.abs(sum + S[hi])) {
						best = Math.abs(sum + S[hi]);
						p = i; q = j; r = hi;
					}
				}
			}
		}
		System.out.printf("%d %d %d\n", S[p], S[q], S[r]);
	}

}
