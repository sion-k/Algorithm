package codeforce.r1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] S = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			int head = 0; int tail = N - 1;
			while (head < tail) {
				if (S[head] > 0) {
					S[head]--; S[tail]++;
					K--;
				}
				if (S[head] == 0) head++;
				if (K == 0) break;
			}
			for (int i = 0; i < N; i++) {
				ans.append(S[i]);
				if (i != N - 1) ans.append(" ");
			}
			ans.append("\n");
		}
		System.out.print(ans.toString().trim());
	}
	
}