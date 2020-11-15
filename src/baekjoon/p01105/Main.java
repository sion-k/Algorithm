package baekjoon.p01105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String L = st.nextToken(); String R = st.nextToken();
		if (L.length() != R.length()) {
			System.out.println(0);
		} else {
			int cnt = 0;
			for (int i = 0; i < L.length(); i++) {
				if (L.charAt(i) == R.charAt(i)) {
					if (L.charAt(i) == '8') {cnt++;}
				} else {break;}
			}
			System.out.println(cnt);
		}
	}

}