package baekjoon.p01920;

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
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> S = new ArrayList<>(N);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S.add(Integer.parseInt(st.nextToken()));}
		Collections.sort(S);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < M; i++) {
			int index = Collections.binarySearch(S, Integer.parseInt(st.nextToken()));
			if (index >= 0) {bw.write("1");}
			else {bw.write("0");}
			bw.newLine();
		}
		bw.close();
	}

}