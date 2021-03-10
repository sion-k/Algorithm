package baekjoon.p01493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int l = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		long sum = (long)l * w * h;
		int N = Integer.parseInt(br.readLine());
		Pair[] S = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			S[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(S, (u, v) -> v.A - u.A);
		int ret = 0;
		for (int i = 0; i < N; i++) {
			long v = (long)Math.pow(Math.pow(2, S[i].A), 3);
			long n = (long)Math.min(sum / v, S[i].B);
			sum -= v * n;
			ret += n;
		}
		System.out.println(sum == 0 ? ret : -1);
	}
	
}
class Pair {
	int A, B;
	public Pair(int a, int b) {A = a; B = b;}
	public String toString() {return A + " " + B;}
}