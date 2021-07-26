package codeforce.r1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> S = new ArrayList<>(N);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S.add(Integer.parseInt(st.nextToken()));
			Collections.sort(S);
			int cnt = 0; 
			int minDiff = 1000000000;
			for (int i = 0; i < N; i++) {
				if (S.get(i) <= 0) {
					cnt++;
					if (i + 1 < N && S.get(i + 1) <= 0)
						minDiff = Math.min(minDiff, Math.abs(S.get(i) - S.get(i + 1)));
				} else if (cnt == 0) {
					cnt++; break;
				} else if (S.get(i) <= minDiff) {
					cnt++; break;
				}
			}
			ans.append(cnt).append("\n");
		}
		System.out.print(ans);
	}
	
}
