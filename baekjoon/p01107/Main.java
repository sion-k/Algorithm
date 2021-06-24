package baekjoon.p01107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String N;
	static boolean[] broken;

	// 지금까지 고른 수가 here이고, i번째 자리를 고를 때
	// 해당 채널로 이동해서 N채널까지 이동하는데 최소 비용
	static int dfs(int i, int here) {
		int cost = String.valueOf(here).length() + Math.abs(Integer.valueOf(N) - here);
		if (here == -1) cost = Math.abs(Integer.valueOf(N) - 100);
		if (i == 6) return cost;
		int min = cost;
		for (int d = 0; d <= 9; d++)
			if (!broken[d]) {
				if (here == -1) here = 0;
				here += (d * (int)(Math.pow(10, i)));
				min = Math.min(min, dfs(i + 1, here));
				here -= (d * (int)(Math.pow(10, i)));
			}
		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = br.readLine();
		int M = Integer.parseInt(br.readLine());
		broken = new boolean[10]; // [0, 9]까지 부서진 여부
		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++)
				broken[Integer.parseInt(st.nextToken())] = true;
		}
		// +, -로만 이동하는 경우
		int min = Math.abs(Integer.valueOf(N) - 100);
		// 숫자 버튼도 이용하는 경우
		min = Math.min(min, dfs(0, -1));
		System.out.println(min);
	}

}