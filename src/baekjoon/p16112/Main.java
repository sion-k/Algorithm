package baekjoon.p16112;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer> S = new ArrayList<>(N);
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			S.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(S);
		long sum = 0;
		for (int i = 0, j = 0; i < N; i++) {
			sum += ((long)j * S.get(i));
			if (j < K) {j++;}
		}
		bw.write(String.valueOf(sum));
		bw.newLine();
		bw.close();
	}

}