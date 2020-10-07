package baekjoon.p11501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] S = new int[N]; 
			for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
			int max = 0; long profit = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (S[i] > max) {max = S[i];} 
				else {profit += (max - S[i]);}
			}
			System.out.println(profit);
		}
	}

}