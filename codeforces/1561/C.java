import java.io.*;
import java.util.*;

public class C {
	
	static int[] getMin(int N, int[] S) {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			S[i] -= i;
			ret = Math.max(ret, S[i] + 1);
		}
		return new int[] { ret, N } ;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Pair> S = new ArrayList<>(N);
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int M = Integer.parseInt(st.nextToken());
				int[] temp = new int[M];
				for (int j = 0; j < M; j++) temp[j] = Integer.parseInt(st.nextToken());
				int[] p = getMin(M, temp);
				S.add(new Pair(p[0], p[1]));
			}
			Collections.sort(S);
			int ret = 0; int add = 0;
			for (Pair p : S) {
				ret = Math.max(ret, p.power - add);
				add += p.num;
			}
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
class Pair implements Comparable<Pair> {
	int power, num;
	
	Pair (int p, int n) { power = p; num = n; }
	
	@Override
	public int compareTo(Pair o) { return power - o.power; }
	
}