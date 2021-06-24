import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Pair> S = new ArrayList<Pair>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			S.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(S);
		StringBuilder ans = new StringBuilder();
		for (Pair p : S) ans.append(p).append("\n");
		System.out.print(ans);
	}

}

class Pair implements Comparable<Pair> {
	int x; int y;
	public Pair(int x, int y) {this.x = x; this.y = y;}
	@Override
	public String toString() {return new StringBuilder().append(x).append(" ").append(y).toString();}
	@Override
	public int compareTo(Pair o) {return y != o.y ? y - o.y : x - o.x;}
}
