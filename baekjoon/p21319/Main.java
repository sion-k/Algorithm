package baekjoon.p21319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	
	// i��° �ִ� ����� è�Ǿ��� �� �� �ִ��� ��ȯ
	// �� i��°�� ���� �������� ���� ����� �߿��� ���� ���ʿ� ����
	static boolean f(int i) {
		int here = i; int size = S[i];
		while (here - 1 >= 0 && size > S[here - 1]) { size++; here--; }
		if (here != 0) return false;
		here = i;
		while (here + 1 < N && size > S[here + 1]) { size++; here++; }
		if (here != N - 1) return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		ArrayList<Integer> P = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			if (i == 0 || S[i] > S[i - 1]) P.add(i);
		}
		int lo = 0; int hi = P.size() - 1;
		if (f(P.get(lo))) {
			for (int x : P) bw.append(x + 1).append(" ");
			System.out.println(bw.toString().trim());
		} else if (!f(P.get(hi))) {
			System.out.println(-1);
		} else {
			while (lo + 1 < hi) {
				int mid = (lo + hi) / 2;
				if (f(P.get(mid))) hi = mid;
				else lo = mid;
			}
			for (int x : P) if (x >= P.get(hi))
				bw.append(x + 1).append(" ");
			System.out.println(bw.toString().trim());
		}
	}
	
}
