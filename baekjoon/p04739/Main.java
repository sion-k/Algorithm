package baekjoon.p04739;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static long max(int L, int P, int V) {
		long sum = V / P * L;
		int remain = V % P;
		sum += (remain >= L ? L : remain);
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		int c = 1;
		while (!(L == 0 && P == 0 && V == 0)) {
			System.out.println("Case " + c + ": " + max(L, P, V));
			st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			c++;
		}
		br.close();
	}

}
