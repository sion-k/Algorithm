import java.io.*;
import java.util.*;

public class E {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int k = r - l + 1;
			long min = k * (k + 1) / 2;
			long max = k * (n - k + 1 + n) / 2;
			boolean flag = min <= s && s <= max;
			if (flag) {
				boolean[] S = new boolean[n + 1];
				int[] t = new int[k + 1];
				for (int i = 1; i <= k; i++)
					t[i] = n - k + i;
				int head = 1;
				while (max > s) {
					if (t[head] == head) head++;
					else {
						t[head]--; max--;
					}
				}
				for (int i = 1; i <= k; i++) S[t[i]] = true;
				int[] ret = new int[n + 1];
				for (int i = l; i <= r; i++)
					ret[i] = t[i - l + 1];
				head = 1;
				for (int i = 1; i <= n; i++) {
					if (l <= i && i <= r) continue;
					while (S[head]) head++;
					ret[i] = head;
					S[head] = true;
				}
				for (int i = 1; i <= n; i++)
					ans.append(ret[i]).append(" ");
			} else {
				ans.append("-1");
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}

}