package baekjoon.p01436;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean is666(int S) {
		boolean ret = false;
		while (S >= 666) {
			if (S % 1000 == 666) {ret = true; break;}
			S /= 10;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int i = 666;
		int cnt = 0;
		while (cnt < N) {
			if (is666(i))
				cnt++;
			i++;
		}
		i--;
		System.out.println(i);
	}

}