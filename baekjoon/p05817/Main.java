package baekjoon.p09345;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[N]; // i번째 서있는 난쟁이의 키
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken()) - 1;
		int[] P = new int[N]; // x의 키를 가진 난쟁이의 위치
		for (int i = 0; i < N; i++) P[S[i]] = i;
		RangeMaxQuery maxSeg = new RangeMaxQuery(); // 키가 i인 난쟁이의 위치를 저장
		RangeMinQuery minSeg = new RangeMinQuery(); // 키가 i인 난쟁이의 위치를 저장
		maxSeg.n = N; minSeg.n = N;
		for (int i = 0; i < N; i++) maxSeg.t[N + i] = minSeg.t[N + i] = P[i];
		maxSeg.build(); minSeg.build();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int Q = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			if (Q == 1) {
				maxSeg.update(S[A], B); maxSeg.update(S[B], A);
				minSeg.update(S[A], B); minSeg.update(S[B], A);
				int temp = S[A]; S[A] = S[B]; S[B] = temp;
			} else {
				int max = maxSeg.query(A, B + 1); int min = minSeg.query(A, B + 1);
				ans.append(max - min == B - A ? "YES" : "NO").append("\n");
			}
		}
		System.out.print(ans);
	}
	
}

class RangeMaxQuery {
	int n;
	int N = 262144; int[] t = new int[2 * N];
	
	void build() {
		for (int i = n - 1; i > 0; i--)
			t[i] = Math.max(t[i << 1], t[i << 1 | 1]);
	}
	
	void update(int i, int newValue) {
		for (t[i += n] = newValue; i > 1; i >>= 1)
			t[i >> 1] = Math.max(t[i], t[i ^ 1]);
	}
	
	int query(int l, int r) {
		int max = 0;
		for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) max = Math.max(max, t[l++]);
			if ((r & 1) == 1) max = Math.max(max, t[--r]);
		}
		return max;
	}
	
}

class RangeMinQuery {
	int n;
	int N = 262144; int[] t = new int[2 * N];
	
	void build() { 
		for (int i = n - 1; i > 0; i--)
			t[i] = Math.min(t[i << 1], t[i << 1 | 1]);
	}
	
	void update(int i, int newValue) {
		for (t[i += n] = newValue; i > 1; i >>= 1)
			t[i >> 1] = Math.min(t[i], t[i ^ 1]);
	}
	
	int query(int l, int r) {
		int min = 200000;
		for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) min = Math.min(min, t[l++]);
			if ((r & 1) == 1) min = Math.min(min, t[--r]);
		}
		return min;
	}
	
}
