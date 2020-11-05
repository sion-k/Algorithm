package baekjoon.p16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int BTK(int A, int B) {
		if (A == B) {return 0;}
		int min = 32;
		int mult = A * 2;
		if (mult <= B) {min = Math.min(min, BTK(mult, B));}
		long extend = A * 10 + 1;
		if (extend <= B) {min = Math.min(min, BTK((int)extend, B));}
		return 1 + min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int ret = BTK(A, B) + 1;
		if (ret >= 32) {ret = -1;}
		System.out.println(ret);
	}

}