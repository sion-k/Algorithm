import java.io.*;
import java.util.*;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] S = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(S);
			int sum = 0;
			for (int i = 0; i < N - 2 * K; i++) sum += S[i];
			for (int i = 0; i < K; i++) sum += S[N - 2 * K + i] / S[N - K + i];
			bw.append(sum);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}