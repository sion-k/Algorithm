package baekjoon.p01011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// [1, i]까지의 합
	static long sum(int i) {return (long)i * (i + 1) / 2;}

	static int binsearch(int dist) {
		int lo = 0; int hi = 100000;
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (2 * sum(mid) < dist)
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dist = y - x;
			// dist안에 들어갈 수 있는 최대의 2 * (1부터 i까지의 합)
			int i = binsearch(dist);
			int ret = 2 * i;
			int left = (int)(dist - 2 * sum(i));
			if (left > 0) ret++;
			if (left > i + 1) ret++;
			ans.append(ret).append("\n");
		}
		System.out.print(ans);
	}

}