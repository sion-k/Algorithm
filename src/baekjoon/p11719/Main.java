package baekjoon.p11719;

import java.util.Scanner;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();

	private static int moved = 0;

	private static void hanoii(int n, int from, int to, int other) {
		if (n == 0)
			return;
		hanoii(n - 1, from, other, to);
		sb.append(from).append(" ").append(to).append("\n");
		moved++;
		hanoii(n - 1, other, to, from);
	}

	public static void main(String[] args) {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Scanner sc = new Scanner(System.in);
		int input = Integer.parseInt(sc.nextLine());
		hanoii(input, 1, 3, 2);
		try {
			bw.write(moved + "\n");
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
