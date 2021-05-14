package baekjoon.p01365;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;

	static int lis() {
		ArrayList<Integer> L = new ArrayList<>();
		L.add(S[0]);
		for (int i = 1; i < N; i++) {
			if (S[i] > L.get(L.size() - 1)) {
				L.add(S[i]);
			} else {
				int index = Collections.binarySearch(L, S[i]);
				if (index < 0) {index = -(index + 1);}
				L.set(index, S[i]);
			}
		}
		return L.size();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());
		System.out.println(N - lis());
	}

}