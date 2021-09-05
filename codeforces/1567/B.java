import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int[] S = new int[300001];
		for (int i = 1; i <= 300000; i++) S[i] = S[i - 1] ^ i;
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int sum = S[a - 1];
			int ret = a;
			if (sum != b) {
				ret++;
				if ((sum ^ a) == b) ret++;
			}
			bw.append(ret);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}