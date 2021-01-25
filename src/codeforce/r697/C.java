package codeforce.r697;

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
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[] u = new int[k];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < k; i++)
				u[i] = Integer.parseInt(st.nextToken());
			int[] v = new int[k];
			for (int i = 0; i < k; i++)
				v[i] = Integer.parseInt(st.nextToken());


		}
		bw.close();
	}

}