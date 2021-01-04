package codeforce.r690;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class C {
	static int x;
	static boolean found = false;
	static StringBuilder A = new StringBuilder();
	static boolean[] picked;

	static void bfc(int toPick, int lastPick) {
		if (found) return;
		if (toPick == 0) {
			int sum = 0;
			for (int i = 1; i <= 9; i++)
				if (picked[i])
					sum += i;
			if (sum == x) {
				for (int i = 1; i <= 9; i++)
					if (picked[i])
						A.append(i);
				found = true;
			}
		}
		for (int pick = lastPick + 1; pick <= 9; pick++) {
			picked[pick] = true;
			bfc(toPick - 1, pick);
			picked[pick] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			x = Integer.parseInt(br.readLine());
			A = new StringBuilder();
			picked = new boolean[10];
			found = false;
			for (int d = 1; d <= 9; d++) {
				bfc(d, 0);
				if (found)
					break;
			}
			if (found)
				bw.write(A.toString());
			else
				bw.write("-1");
			bw.newLine();
		}
		bw.close();
	}

}