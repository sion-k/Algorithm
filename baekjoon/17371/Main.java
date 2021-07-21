import java.io.*;
import java.util.*;

public class Main {
	
	static int dist(ArrayList<Pair> S,int i, int j) {
		Pair u = S.get(i); Pair v = S.get(j);
		return Math.abs(u.x - v.x) * Math.abs(u.x - v.x) + Math.abs(u.y - v.y) * Math.abs(u.y - v.y);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Pair> S = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			S.add(new Pair(x, y));
		}
		int min = Integer.MAX_VALUE;
		int x = 0;
		for (int i = 0; i < N; i++) {
			int max = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				max = Math.max(max, dist(S, i, j));
			}
			if (min > max) {
				min = max;
				x = i;
			}
		}
		System.out.printf("%d %d\n", S.get(x).x, S.get(x).y);
	}

}

class Pair {
	int x, y;
	
	Pair(int x, int y) { this.x = x; this.y = y; }
	
}