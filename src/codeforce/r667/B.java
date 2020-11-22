package codeforce.r667;

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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int ta = Math.max(a - n, x);
			int tan = n - (a - ta);
			int tb = Math.max(b - tan, y);
			long product = (long)ta * tb;
			tb = Math.max(b - n, y);
			int tbn = n - (b - tb);
			ta = Math.max(a - tbn, x);
			product = Math.min(product, (long)ta * tb);
			bw.write(String.valueOf(product));
			bw.newLine();
		}
		bw.close();
	}

}