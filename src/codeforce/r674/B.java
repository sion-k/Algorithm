package codeforce.r674;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			boolean ok = false;
			for (int i = 0; i < N; i++) {
				int[] tile = new int[4];
				st = new StringTokenizer(br.readLine(), " ");
				tile[0] = Integer.parseInt(st.nextToken());
				tile[1] = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(br.readLine(), " ");
				tile[2] = Integer.parseInt(st.nextToken());
				tile[3] = Integer.parseInt(st.nextToken());
				if (tile[1] == tile[2]) {ok = true;}
			}
			if (M % 2 != 0) {ok = false;}
			if (ok) {bw.write("YES");}
			else {bw.write("NO");}
			bw.newLine();
		}
		bw.close();
	}

}