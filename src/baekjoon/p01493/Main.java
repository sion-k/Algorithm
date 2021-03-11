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
	
	// length × width × height 크기의 박스에 큐브를 채워넣는다
	static void solve(int l, int w, int h) {
		if (!check) return;
		if (l == 0 || w == 0 || h == 0) return;
		// 현재 가지고 있는 큐브중에서 가장 큰 큐브를 놓는다
		for (int[] p : S) {
			int r = p[0];
			if (p[1] > 0 && l >= r && w >= r && h >= r) {
				// 큐브 여러개를 한번에 정육면체 모양으로 놓을 수 있는 최대한으로 놓는다
				int min = (int)(Math.min(l, (int)(Math.min(w, h))));
				int x = 1;
				while (r * x <= min && (int)(Math.pow(x, 3)) <= p[1]) x++;
				x--;
				r = r * x;
				p[1] -= (int)(Math.pow(x, 3));
				ret += (int)(Math.pow(x, 3));
				// 큐브를 왼쪽 아래 구석에 놓고 나머지 3부분으로 나눠서 분할정복
				solve(r, r, h - r);
				solve(l, w - r, h);
				solve(l - r, r, h);
				return; // 한개를 놓았으면 끝
			}
		}
		check = false; // 부분 문제중 한 문제라도 이곳에 도달한다면 전체 문제는 해결 불가능하다		
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