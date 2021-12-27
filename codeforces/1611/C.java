import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			if (S[0] != N && S[N - 1] != N) {
				bw.append(-1);
			} else {
				bw.append(N).append(" ");
				int t = S[0] == N ? 1 : 0;
				for (int i = N - 2; i >= 0; i--) {
					bw.append(S[i + t]).append(" ");
				}
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}