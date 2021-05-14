package baekjoon.p20162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] S;
	static int[] cache;

	// start���� �����ϴ� ���� �κ� ���� �� ���� ���� ū ���� ũ�� ��ȯ
	static int BIS(int start) {
		if (cache[start] != 0) {return cache[start];}
		int ret = S[start];
		for (int next = start + 1; next < N; next++) {
			if (S[next] > S[start]) {
				ret = Math.max(ret,  S[start] + BIS(next));
			}
		}
		return cache[start] = ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N]; cache = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(br.readLine());
		}
		int max = 1;
		for (int i = 0; i < N; i++) {max = Math.max(max, BIS(i));}
		System.out.println(max);
	}

}