import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] S = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
			boolean flag = false;
			for (int i = 0; i < N - 1; i++) {
				if (S[i] >= S[i + 1]) flag = true;
			}
			bw.append(N % 2 == 0 || flag ? "YES" : "NO");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}