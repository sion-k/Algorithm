import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class Loc implements Comparable<Loc>{
		int x; int y;
		public Loc (int x, int y) {
			this.x = x; this.y = y;
		}
		@Override
		public int compareTo(Loc o) {
			return x == o.x ? y - o.y : x - o.x;
		}
		public String toString() {
			return x + " " + y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Loc> loc = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			loc.add(new Loc(x, y));
		}
		br.close();
		Collections.sort(loc);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			bw.write(loc.get(i).toString());
			bw.newLine();
		}
		bw.close();
	}

}
