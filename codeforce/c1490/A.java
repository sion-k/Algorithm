package codeforce.c1490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A {
	static boolean dense(int i, int j) {return (double)Math.max(i, j) / Math.min(i, j) <= 2;}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> S = new ArrayList<>(N);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S.add(Integer.parseInt(st.nextToken()));
			int cnt = 0;
			for (int i = 0; i < S.size() - 1; i++) {
				if (!dense(S.get(i), S.get(i + 1))) {
					if (S.get(i) < S.get(i + 1)) {
						S.add(i + 1, 2 * S.get(i));
					} else {
						S.add(i + 1, (int)Math.ceil((double)S.get(i) / 2));
					}
					cnt++;
				}
			}
			ans.append(cnt).append("\n");
		}
		System.out.print(ans.toString().trim());
	}

}