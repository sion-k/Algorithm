package baekjoon.p16505;

import java.io.*;
import java.util.*;

public class Main {
	static String[] fractal(int N) {
		int length = (int) Math.pow(2, N);
		String[] ret = new String[length];
		if (N == 0) {
			ret[0] = "*";
			return ret;
		}
		String[] frac = fractal(N - 1);

		StringBuilder sb = null;

		for (int i = 0; i < length / 2; i++) {
			sb = new StringBuilder();
			sb.append(frac[i]);
			for (int j = 0; j < i; j++) {
				sb.append(" ");
			}
			sb.append(frac[i]);
			ret[i] = sb.toString();
		}
		for (int i = length / 2; i < length; i++) {
			ret[i] = frac[i - length / 2];
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		String[] fr = fractal(N);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < fr.length; i++) {
			bw.write(fr[i]);
			bw.newLine();
		}
		bw.close();
	}

}
