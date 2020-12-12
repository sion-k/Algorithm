package baekjoon.p05543;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int minA = 2000; int minB = 2000;
		for (int i = 0; i < 3; i++)
			minA = Math.min(minA, Integer.parseInt(br.readLine()));
		for (int i = 0; i < 2; i++)
			minB = Math.min(minB, Integer.parseInt(br.readLine()));
		System.out.println(minA + minB - 50);
	}

}