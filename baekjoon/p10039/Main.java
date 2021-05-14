package baekjoon.p10039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp < 40) {temp = 40;}
			sum += temp;
		}
		System.out.println(sum / 5);
	}

}