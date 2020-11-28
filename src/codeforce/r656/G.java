package codeforce.r656;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class G {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[][] S = new int[2][N];
			Map<Integer, Integer> A = new HashMap<>(N);
			Map<Integer, Integer> B = new HashMap<>(N);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				S[0][i] = Integer.parseInt(st.nextToken());
				A.put(S[0][i], A.getOrDefault(S[0][i], 0) + 1);
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				S[1][i] = Integer.parseInt(st.nextToken());
				B.put(S[1][i], B.getOrDefault(S[1][i], 0) + 1);
			}
			for (int i = 0; i < N; i++) {
				int a = A.get(S[0][i]);
				int b = B.get(S[0][i]);
				if (a > 1 && b == 0) {
					A.put(S[0][i], A.getOrDefault(S[0][i], 0) - 1);
					B.put(S[0][i], 1);
					B.put(S[1][i], B.getOrDefault(S[1][i], 0) - 1);
					A.put(S[1][i], A.getOrDefault(S[1][i], 0) + 1);
				} else if (b > 1 && a == 0) {
					A.put(b, B.getOrDefault(b, 0) + 1);
				}
			}
		}
		bw.close();
	}
}
