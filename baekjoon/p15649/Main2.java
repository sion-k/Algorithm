package baekjoon.p15649;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
	static BufferedWriter bw =
	new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static boolean[] taken;
	static ArrayList<Integer> picked = new ArrayList<>();

	static void print() throws IOException {
		for (int i = 0; i < picked.size(); i++) {
			bw.write(String.valueOf(picked.get(i)));
			if (i != N - 1) {bw.write(" ");}
		}
		bw.newLine();
	}

	static void BFC(int toPick) throws IOException {
		if (toPick == 0) {print(); return;}
		for (int i = 1; i <= N; i++) {
			if (!taken[i]) {
				picked.add(i); taken[i] = true;
				BFC(toPick - 1);
				picked.remove(picked.size() - 1); taken[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		taken = new boolean[N + 1];
		int M = Integer.parseInt(st.nextToken());
		BFC(M);
		bw.close();
	}

}