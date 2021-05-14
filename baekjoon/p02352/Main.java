package baekjoon.p02352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;

	static int LIS() {
		ArrayList<Integer> lis = new ArrayList<>();
		lis.add(S[0]);
		for (int i = 1; i < N; i++) {
			if (S[i] > lis.get(lis.size() - 1)) {lis.add(S[i]); continue;}
			int found = Collections.binarySearch(lis, S[i]);
			if (found < 0) {lis.set(found * -1 - 1, S[i]);
			} else {lis.set(found, S[i]);}
		}
		return lis.size();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		System.out.println(LIS());
	}

}