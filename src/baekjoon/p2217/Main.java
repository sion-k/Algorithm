package baekjoon.p2217;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Integer[] R = new Integer[N];
		for (int i = 0; i < N; i++) {
			R[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		Arrays.sort(R, Collections.reverseOrder());
		int max = R[0];
		for (int i = 1; i < N; i++) {
			max = Math.max(max, R[i] * (i + 1));
		}
		System.out.println(max);
	}

}
