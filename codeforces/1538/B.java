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
			int sum = 0;
			for (int x : S) sum += x;
			boolean flag = sum % N == 0;
			int mean = sum / N;
			int k = 0;
			for (int x : S) if (x > mean) k++;
			bw.append(flag ? k : -1);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}