package baekjoon.p02562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] S = new int[9];
		int max = 0; int maxIndex = 0;
		for (int i = 0; i < 9; i++) {
			S[i] = Integer.parseInt(br.readLine());
			if (S[i] > max) {
				max = S[i];
				maxIndex = i;
			}
		}
		br.close();
		System.out.println(max);
		System.out.println(maxIndex + 1);
	}

}