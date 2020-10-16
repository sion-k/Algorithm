package baekjoon.p14501;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] C; // 시간과 금액
	static int[] cache;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		C = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			C[i][0] = Integer.parseInt(st.nextToken());
			C[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.close();
	}


}
