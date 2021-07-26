import java.io.*;
import java.util.*;

public class D {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			// (a_i - i + N, a_i - i + N의 개수)
			long[] S = new long[2 * N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				S[Integer.parseInt(st.nextToken()) - i + N]++;
			long cnt = 0;
			for (long x : S) cnt += (x - 1) * x / 2;
			ans.append(cnt).append("\n");
		}
		System.out.print(ans);
	}
	
}
