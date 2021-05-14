package baekjoon.p17179;

import java.util.*;
import java.io.*;

public class Main {
	static int[] S; static int N;
	static int L;
	
	// �־��� �������� �ּ� �Ÿ��� d�̻��� �ǰ� q���� ������ �� �ִ��� ��ȯ
	static boolean f(int d, int q) {
		int last = 0; int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (S[i] - last >= d) {last = S[i]; cnt++;}
			if (cnt == q) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		S = new int[N];
		for (int i = 0; i < N; i++) S[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			int q = Integer.parseInt(br.readLine());
			int lo = 1; int hi = L;
			// f(lo) == true && f(hi) == false�� lo��ȯ
			while (lo + 1 < hi) {
				int mid = (lo + hi) / 2;
				if (f(mid, q)) lo = mid;
				else hi = mid;
			}
			ans.append(lo).append("\n");
		}
		System.out.print(ans);
	}

}
