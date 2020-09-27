package baekjoon.p2447;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static final char[][] BASE  = { { '*', '*', '*' }, { '*', ' ', '*' }, { '*', '*', '*' } };

	static char[][] star(int N) {
		if (N == 3) {return BASE;}
		char[][] ret = new char[N][N];
		for(char[] line : ret) {Arrays.fill(line, ' ');}
		char[][] part = star(N / 3);

		for (int i = 0; i < N; i += N / 3) {
			for (int j = 0; j < N; j += N / 3) {
				if (i != N / 3 || j != N / 3) {
					stamp(ret, i, j, part);
				}
			}
		}
		return ret;
	}
	
	static void stamp(char[][] flat, int y, int x, char[][] part) {
		for (int dy = 0; dy < part.length; dy++) {
			for (int dx = 0; dx < part[0].length; dx++) {
				flat[y + dy][x + dx] = part[dy][dx];
			}
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[][] star = star(N);
		for (char[] line : star) {
			for(char c : line) {bw.write(c);}
			bw.newLine();
		}
		bw.close();
	}
}