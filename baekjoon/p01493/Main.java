package baekjoon.p01493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] S;
	static int ret = 0;
	static boolean check = true;
	
	// length �� width �� height ũ���� �ڽ��� ť�긦 ä���ִ´�
	static void solve(int l, int w, int h) {
		if (!check) return;
		if (l == 0 || w == 0 || h == 0) return;
		// ���� ������ �ִ� ť���߿��� ���� ū ť�긦 ���´�
		for (int[] p : S) {
			int r = p[0];
			if (p[1] > 0 && l >= r && w >= r && h >= r) {
				// ť�� �������� �ѹ��� ������ü ������� ���� �� �ִ� �ִ������� ���´�
				int min = (int)(Math.min(l, (int)(Math.min(w, h))));
				int x = 1;
				while (r * x <= min && (int)(Math.pow(x, 3)) <= p[1]) x++;
				x--;
				r = r * x;
				p[1] -= (int)(Math.pow(x, 3));
				ret += (int)(Math.pow(x, 3));
				// ť�긦 ���� �Ʒ� ������ ���� ������ 3�κ����� ������ ��������
				solve(r, r, h - r);
				solve(l, w - r, h);
				solve(l - r, r, h);
				return; // �Ѱ��� �������� ��
			}
		}
		check = false; // �κ� ������ �� ������ �̰��� �����Ѵٸ� ��ü ������ �ذ� �Ұ����ϴ�		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int l = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		S = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			S[i] = new int[] {(int)(Math.pow(2, Integer.parseInt(st.nextToken()))), Integer.parseInt(st.nextToken())};
		}
		Arrays.sort(S, (u, v) -> v[0] - u[0]);
		solve(l, w, h);
		System.out.println(check ? ret : -1);
	}
	
}