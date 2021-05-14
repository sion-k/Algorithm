package baekjoon.p17509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Pair[] S = new Pair[11];
		for (int i = 0; i < 11; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			S[i] = new Pair(d, v);
		}
		Arrays.sort(S);
		int sum = 0; int t = 0;
		for (int i = 0; i < 11; i++) {
			t += S[i].D;
			sum += (t + 20 * S[i].V);
		}
		System.out.println(sum);
	}

}
class Pair implements Comparable<Pair> {
	int D, V;
	public Pair(int d, int v) {D = d; V = v;}
	@Override
	public int compareTo(Pair o) {return D - o.D;}
}