package baekjoon.p18119;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] W;
	static int R = (int)(Math.pow(2, 27)) - 1;

	static void toggle(int x) {R ^= (1 << x);}

	static int count() {
		int cnt = 0;
		for (int i = 0; i < W.length; i++)
			if ((R & W[i]) == W[i]) cnt++;
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		W = new int[N];
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (int j = 0; j < word.length(); j++) {
				W[i] |= (1 << (word.charAt(j) - 'a'));
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			toggle(st.nextToken().charAt(0) - 'a');
			bw.write(String.valueOf(count()));
			bw.newLine();
		}
		bw.close();
	}

}