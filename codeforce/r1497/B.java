package codeforce.r1497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int[] R = new int[M];
			for (int i = 0; i < N; i++) R[Integer.parseInt(st.nextToken()) % M]++;
			int cnt = 0;
			if (R[0] != 0) cnt++;
			for (int i = 1; i < M; i++) {
				if (R[i] == 0) continue;
				int d = Math.abs(R[i] - R[M - i]);
				if (d < 2) {
					cnt++;
				} else {
					cnt += d;
				}
				R[i] = R[M - i] = 0;
			}
			ans.append(cnt).append("\n");
		}
		System.out.print(ans.toString().trim());
	}

}