import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Pair[] S;

	static int LIS() {
		ArrayList<Integer> lis = new ArrayList<>();
		lis.add(S[0].b);
		for (int i = 1; i < N; i++) {
			if (S[i].b > lis.get(lis.size() - 1)) {lis.add(S[i].b); continue;}
			int found = Collections.binarySearch(lis, S[i].b);
			if (found < 0) {lis.set(found * -1 - 1, S[i].b);
			} else {lis.set(found, S[i].b);}
		}
		return lis.size();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new Pair[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			S[i] = new Pair(a, b);
		}
		Arrays.sort(S);
		System.out.println(N - LIS());
	}

}

class Pair implements Comparable<Pair>{
	int a; int b;
	public Pair(int a, int b) {this.a = a; this.b = b;}
	@Override
	public int compareTo(Pair o) {return a - o.a;}
}
