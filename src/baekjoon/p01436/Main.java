package baekjoon.p01436;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int i = 666;
		int cnt = 0;
		while (cnt < N) {
			if ((i + "").matches(".*666.*"))
				cnt++;
			i++;
		}
		System.out.println(i - 1);
	}

}
