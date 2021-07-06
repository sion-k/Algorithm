import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] p = new int[2];
			p[0] = Integer.parseInt(st.nextToken());
			p[1] = Integer.parseInt(st.nextToken());
			char[] S = br.readLine().toCharArray();
			boolean flag = true;
			int head = 0; int tail = S.length - 1;
			while (head < tail) {
				if (S[head] != '?' && S[head] == S[tail]) p[S[head] - '0'] -= 2;
				else if (S[head] == '?' && S[tail] != '?') {
					S[head] = S[tail];
					p[S[tail] - '0'] -= 2;
				} else if (S[head] != '?' && S[tail] == '?') {
					S[tail] = S[head];
					p[S[head] - '0'] -= 2;
				}
				head++; tail--;
			}
			if (S.length % 2 == 1 && S[S.length / 2] != '?') {
				p[S[S.length / 2] - '0']--;
			}
			head = 0; tail = S.length - 1;
			while (head < tail) {
				if (S[head] != S[tail]) { flag = false; break; }
				else if (S[head] == '?') {
					int max = Math.max(p[0], p[1]);
					if (max < 2) { flag = false; break; }
					if (p[0] > p[1]) {
						p[0] -= 2;
						S[head] = S[tail] = '0';
					} else {
						p[1] -= 2;
						S[head] = S[tail] = '1';
					}
				}
				head++; tail--;
			}
			if (S.length % 2 == 1 && S[S.length / 2] == '?') {
				int max = Math.max(p[0], p[1]);
				if (max < 1) flag = false;
				if (p[0] > p[1]) {
					S[head] = S[tail] = '0';
					p[0]--;
				} else {
					S[head] = S[tail] = '1';
					p[1]--;
				}
			}
			if (p[0] != 0 || p[1] != 0) flag = false;
			if (flag) {
				for (char x : S) ans.append(x);
			} else {
				ans.append(-1);
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}

}