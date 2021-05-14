package baekjoon.p01158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {q.offer(i);}
		bw.write("<");
		for (int i = 0; i < K - 1; i++) {q.offer(q.poll());}
		while (!q.isEmpty()) {
			bw.write(String.valueOf(q.poll()));
			for (int i = 0; i < K - 1; i++) {
				if (!q.isEmpty()) {q.offer(q.poll());}
			}
			if (!q.isEmpty()) {bw.write(", ");}
		}
		bw.write(">");
		bw.newLine();
		bw.close();
	}

}