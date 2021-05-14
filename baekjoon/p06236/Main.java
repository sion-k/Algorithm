package baekjoon.p06236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	
	// 한 기간에 사용 가능한 금액이 K원일 때, M개의 구간으로 기간을 나눌 수 있는지 여부
	static boolean f(int K, int M) {
		int i = 0;
		while (i < N) {
			if (M == 0 || S[i] > K) return false;
			int sum = 0;
			while (i < N && sum + S[i] <= K) {
				sum += S[i];
				i++;
			}
			M--;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		S = new int[N];
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(br.readLine());
		// f(lo) == false && f(hi) == true인 hi를 반환
		int lo = 0; int hi = 10000 * N;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (f(mid, M)) hi = mid;
			else lo = mid;
		}
		System.out.println(hi);
	}
	
}