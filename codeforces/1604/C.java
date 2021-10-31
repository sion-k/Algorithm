import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());
			boolean flag = true;
			for (int i = 1; i <= Math.min(N, 22); i++) {
				boolean temp = false;
				for (int j = 2; j <= i + 1; j++) {
					if ((S[i] % j) != 0) temp = true;
				}
				if (!temp) flag = false;
			}
			bw.append(flag ? "YES" : "NO");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}