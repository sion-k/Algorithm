import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static class Pair implements Comparable<Pair> {
		int W; int H;
		public Pair(int w, int h) {W = w; H = h;}
		public int compareTo(Pair o) {
			if(W > o.W && H > o.H) {return 1;}
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pair[] S = new Pair[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			S[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			int rank = 0;
			for (int j = 0; j < N; j++) {
				if(S[j].compareTo(S[i]) == 1) {rank++;}// j가 i보다 덩치가 크다면
			}
			bw.write(String.valueOf(rank + 1));
			if (i != N - 1) {bw.write(" ");}
		}
		bw.newLine();
		bw.close();
	}

}
