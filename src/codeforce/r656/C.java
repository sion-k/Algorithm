package codeforce.r656;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C {
	static int N;
	static Integer[] S;
	static int[] ldsCache;
	static int[] btnCache;

	static int LDS(int start) {
		if (start == N - 1) {return 1;}
		if (ldsCache[start] != 0) {return ldsCache[start];}
		int ret = 1;
		if (S[start + 1] <= S[start]) {ret = Math.max(ret,  1 + LDS(start + 1));}
		return ldsCache[start] = ret;
	}

	static int BTN(int start) {
		if (start == N - 1) {return 1;}
		if (btnCache[start] != 0) {return btnCache[start];}
		int max = LDS(start);
		if (S[start] <= S[start + 1]) {max = Math.max(max, 1 + BTN(start + 1));}
		return btnCache[start] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			N = Integer.parseInt(br.readLine());
			S = new Integer[N];
			ldsCache = new int[N]; btnCache = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
			List<Integer> list = Arrays.asList(S);
			Collections.reverse(list);
			bw.write(String.valueOf(N - BTN(0)));
			bw.newLine();
		}
		bw.close();
	}

}