package codeforce.r696;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class C {
	static int N;
	static long[] S;

	static boolean btk(long x, boolean[] removed) {
		boolean ok = true;
		for (int i = 0; i < N; i++)
			if (!removed[i]) ok = false;
		if (ok) return true;
		for (int i = 0; i < N; i++) {
			if (removed[i]) continue;
			for (int j = i + 1; j < N; j++) {
				if (removed[j]) continue;
				if (S[i] + S[j] == x) {
					removed[i] = removed[j] = true;
					if (btk(Math.max(S[i], S[j]), removed))
						return true;
					removed[i] = removed[j] = false;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine()) * 2;
			S = new long[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			if (btk(6, new boolean[N]))
				System.out.println("Yes");
		}
		bw.close();
	}

}