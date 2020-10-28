package baekjoon.p09009;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	static final int INF = 1_000_000_000;
	static Stack<Integer> ANS;
	static ArrayList<Integer> FIBO = new ArrayList<>();

	static {
		FIBO.add(0); FIBO.add(1);
		while (FIBO.get(FIBO.size() - 1) < INF) {
			int a = FIBO.get(FIBO.size() - 2);
			int b = FIBO.get(FIBO.size() - 1);
			FIBO.add(a + b);
		}
	}

	static void rec(int d) {
		if (d == 0) {return;}
		int maxIndex = 0;
		for (int i = 0; i < FIBO.size(); i++) {
			if (FIBO.get(i) > d) {
				maxIndex = i - 1; break;
			}
		}
		ANS.push(FIBO.get(maxIndex));
		rec(d - FIBO.get(maxIndex));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			ANS = new Stack<>();
			rec(N);
			while (!ANS.isEmpty()) {
				bw.write(String.valueOf(ANS.pop()));
				if (!ANS.isEmpty()) {bw.write(" ");}
			}
			bw.newLine();
		}
		bw.close();
	}

}