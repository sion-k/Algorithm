package codeforce.r677.B;

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
			int N = Integer.parseInt(br.readLine());
			boolean[] bookShelf = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				bookShelf[i] = st.nextToken().equals("1");
			}
			int l = 0;
			int r = N - 1;
			while (l < N && !bookShelf[l]) {l++;}
			while (r >= 0 && !bookShelf[r]) {r--;}
			int ret = 0;
			if (l == r) {ret = 0;}
			else {
				for (int i = l; i <= r; i++) {
					if (!bookShelf[i]) {ret++;}
				}
			}
			bw.write(String.valueOf(ret));
			bw.newLine();
		}
		bw.close();
	}

}