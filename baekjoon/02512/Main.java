import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int max = 0;
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, S[i]);
		}
		int M = Integer.parseInt(br.readLine());
		int lo = -1; int hi = max + 1;
		// lo < hi
		// f(lo) <= M < f(hi)
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			long sum = 0;
			for (int x : S) sum += Math.min(x, mid);
			if (sum > M) hi = mid;
			else lo = mid;
		}
		System.out.println(lo);
	}
	
}
