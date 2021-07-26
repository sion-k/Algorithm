package codeforce.r667;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int q = n - 1;
			int diff = y - x;
			while (q > 0 && diff % q != 0) {q--;}
			int d = diff / q;
			StringBuilder ans = new StringBuilder();
			int fac = 0; int term = 0;
			while (n > 0 && (term = y - d * fac) >= 1) {
				ans.append(term).append(" ");
				n--; fac++;
			}
			fac = 1;
			while (n > 0) {
				ans.append(y + d * fac).append(" ");
				n--; fac++;
			}
			bw.write(ans.toString().trim());
			bw.newLine();
		}
		bw.close();
	}

}