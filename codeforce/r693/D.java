package codeforce.r693;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			ArrayList<Integer> S = new ArrayList<Integer>(N);
			for (int i = 0; i < N; i++)
				S.add(Integer.parseInt(st.nextToken()));
			Collections.sort(S, Collections.reverseOrder());
			long A = 0;
			long B = 0;
			for (int i = 0; i < S.size(); i++) {
				// Â¦¼ö¹øÂ° ÀÎµ¦½º´Â Alice
				if (i % 2 == 0) {
					if (S.get(i) % 2 == 0)
						A += S.get(i);
				// È¦¼ö¸é Bob
				} else {
					if (S.get(i) % 2 == 1)
						B += S.get(i);
				}
			}
			if (A > B)
				bw.write("Alice");
			else if (A < B)
				bw.write("Bob");
			else
				bw.write("Tie");
			bw.newLine();
		}
		bw.close();
	}

}