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
            int max = 1;
            for (int i = 0; i < N; i++) max = Math.max(max, S[i]);
            boolean flag = false;
            for (int i = 0; i < N; i++) if (S[i] == max) {
                if ((i - 1 >= 0 && S[i - 1] < S[i]) || (i + 1 < N && S[i] > S[i + 1])) {
                    bw.append(i + 1);
                    flag = true;
                    break;
                }     
            }
            if (!flag) bw.append(-1);
			bw.append("\n");
		}
		System.out.print(bw);
	}

}