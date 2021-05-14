package baekjoon.p02675;

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
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			String word = st.nextToken();
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				for (int j = 0; j < R; j++) {bw.write(ch);}
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}