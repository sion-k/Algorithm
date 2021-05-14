package baekjoon.p01343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String board = br.readLine();
		br.close();
		StringBuilder ret = new StringBuilder();
		int index = 0;
		while (index < board.length()) {
			if (board.charAt(index) == '.') {
				ret.append('.'); index++; continue;
			}
			int seq = 0; // 연속한 X의 수
			while (index < board.length() && board.charAt(index) == 'X') {
				seq++; index++;
			}
			int q = seq / 4; int r = seq % 4;
			if (r == 0 || r == 2) {
				for (int i = 0; i < q; i++) {ret.append("AAAA");}
				if (r == 2) {ret.append("BB");}
			} else {
				ret = new StringBuilder("-1"); break;
			}
		}
		System.out.println(ret.toString());
	}
}