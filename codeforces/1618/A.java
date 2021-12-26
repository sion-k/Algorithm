import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int[] S = new int[7];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 7; i++) S[i] = Integer.parseInt(st.nextToken());
			int[] R = new int[3];
			boolean found = false;
			for (int i = 0; i < 7; i++) {
				for (int j = i + 1; j < 7; j++) {
					for (int k = j + 1; k < 7; k++) {
						R = new int[] { S[i], S[j], S[k] };
						int[] Q = new int[7];
						for (int b = 1; b < 8; b++) {
							int sum = 0;
							for (int x = 0; x < 3; x++) {
								if ((b & (1 << x)) > 0) {
									sum += R[x];
								}
							}
							Q[b - 1] = sum;
						}
						Arrays.sort(Q);
						boolean flag = true;
						for (int t = 0; t < 7; t++) {
							if (S[t] != Q[t]) flag = false;
						}
						if (flag) found = true;
						if (found) break;
					}
					if (found) break;
				}
				if (found) break;
			}
			for (int i = 0; i < 3; i++) {
				bw.append(R[i]).append(" ");
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}