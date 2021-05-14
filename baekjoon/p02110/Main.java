package baekjoon.p02110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static ArrayList<Integer> S;
	
	// �־��� �������� �ּ� �Ÿ��� d�̻��� �ǰ� C���� ������ ��ġ�� �������� ��ȯ
	static boolean f(int d) {
		int last = S.get(0); int cnt = 1;
		for (int i = 0; i < N; i++) {
			if (S.get(i) - last >= d) {last = S.get(i); cnt++;}
			if (cnt == C) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		S = new ArrayList<Integer>(N);
		for (int i = 0; i < N; i++) S.add(Integer.parseInt(br.readLine()));
		Collections.sort(S);
		int lo = 1; int hi = S.get(S.size() - 1) - S.get(0) + 1;
		// f(lo) == true && f(hi) == false�� lo�� ã�´�
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (f(mid)) lo = mid;
			else hi = mid;
		}
		System.out.println(lo);
	}
	
}