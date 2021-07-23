import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[][] S;
	
	static final int INF = 1000;
	
	static void set(Pair p) {
		int now = p.start;
		boolean isEmpty = false;
		for (int i = 1; i <= N; i++)
			if (S[i][now]) isEmpty = false;
		if (isEmpty) {
			for (int i = now; i < p.end; i++)
				S[1][i] = true;
			return;
		}
		int max = 0; int cand = 0;
		for (int i = 1; i <= N; i++) {
			if (S[i][now]) continue;
			// i번에 앉는다면 가장 가까운 사람과의 거리를 구한다
			int ld = INF;
			for (int l = i - 1; l >= 1; l--)
				if (S[l][now]) {
					ld = i - l;
					break;
				}
			int rd = INF;
			for (int r = i + 1; r <= N; r++)
				if (S[r][now]) {
					rd = r - i;
					break;
				}
			int dist = Math.min(ld, rd);
			if (max < dist) {
				max = dist;
				cand = i;
			}
 		}
		for (int i = now; i < p.end; i++)
			S[cand][i] = true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		S = new boolean[N + 1][21 * 60 + 1];
		Pair[] A = new Pair[T];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			A[i] = new Pair(st.nextToken(), st.nextToken());
		}
		Arrays.sort(A);
		for (Pair a : A) set(a);
		int cnt = 0;
		for (int i = 9 * 60; i < 21 * 60; i++)
			if (!S[P][i]) {
				cnt++;
			}
		System.out.println(cnt);
	}

}
class Pair implements Comparable<Pair> {
	int start, end, length;
	
	Pair (String u, String v) {
		start = Integer.parseInt(u.substring(0, 2)) * 60 + Integer.parseInt(u.substring(2));
		end = Integer.parseInt(v.substring(0, 2)) * 60 + Integer.parseInt(v.substring(2));
		length = end - start;
	}
	
	@Override
	public int compareTo(Pair o) {
		return start == o.start ? length - o.length : start - o.start;
	}
	
}
