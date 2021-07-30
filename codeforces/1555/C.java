import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int M = Integer.parseInt(br.readLine());
			long[][] S = new long[2][M + 1];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= M; j++) S[i][j] = Long.parseLong(st.nextToken());
			}
			long[][] P = new long[2][M + 1];
			P[0] = S[0].clone();
			P[1] = S[1].clone();
			for (int i = 0; i < 2; i++)
				for (int j = 1; j <= M; j++)
					P[i][j] += P[i][j - 1];
			long min = P[0][M] + P[1][M];
			int mid = 0;
			for (int j = 1; j <= M; j++)
				if (min > Math.max(P[0][M] - P[0][j], P[1][j - 1])) {
					min = Math.max(P[0][M] - P[0][j], P[1][j - 1]);
					mid = j;
				}
			for (int j = 1; j <= mid; j++) S[0][j] = 0;
			for (int j = mid; j <= M; j++) S[1][j] = 0;
			for (int i = 0; i < 2; i++)
				for (int j = 1; j <= M; j++)
					S[i][j] += S[i][j - 1];
			long max = 0;
			for (int j = 1; j <= M; j++)
				max = Math.max(max, S[0][j] + S[1][M] - S[1][j - 1]);
			bw.append(max);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}