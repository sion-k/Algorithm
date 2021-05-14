package codeforce.r1497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringBuilder ans = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			int[] cnt = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
				cnt[S[i]]++;
			}
			Arrays.sort(S);
			int p = 0;
			while (p < N && cnt[p] != 0) {
				ans.append(p).append(" ");
				cnt[p]--;
				p++;
			}
			for (int i = 0; i <= 100; i++) 
				for (int j = 0; j < cnt[i]; j++)
					ans.append(i).append(" ");
			System.out.println(ans.toString().trim());
		}
	}

}