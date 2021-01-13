package baekjoon.p10816;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		// [-10000000, 10000000] -> [0, 20000000]
		int[] S = new int[20000001];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			S[Integer.parseInt(st.nextToken()) + 10000000]++;
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			bw.write(String.valueOf(S[Integer.parseInt(st.nextToken()) + 10000000]));
			bw.write(" ");
		}
		bw.close();
	}

}
