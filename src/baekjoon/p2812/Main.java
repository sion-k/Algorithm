package baekjoon.p2812;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//int N = Integer.parseInt(st.nextToken());
		int N = new Random().nextInt(500000) + 1;
		System.out.println(N);
		//int K = Integer.parseInt(st.nextToken());
		int K = new Random().nextInt(N - 1) + 1;
		System.out.println(K);
		//char[] temp = br.readLine().toCharArray();
		br.close();
		int[] S = new int[N];
		S[0] = new Random().nextInt(9) + 1;
		for(int i = 1; i < N; i++) {
			S[i] = new Random().nextInt(10);
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i : S) {bw.write(String.valueOf(i));}
		bw.newLine();
		bw.close();
	}

}
