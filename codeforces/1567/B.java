import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int[] P = new int[300001];
		for (int i = 1; i <= 300000; i++) P[i] = i ^ P[i - 1];
		int TC = Integer.parseInt(br.readLine());
		while (TC -- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int ret = a;
			if (P[a - 1] != b) {
				ret++;
				if ((P[a - 1] ^ a) == b) ret++;
			}
			bw.append(ret).append("\n");
		}
		System.out.print(bw);
	}

}