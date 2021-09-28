import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> S = new ArrayList<>(N);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S.add(Integer.parseInt(st.nextToken()));
			StringBuilder temp = new StringBuilder();
			int t = 0;
			for (int i = 0; i < N; i++) {
				int max = Integer.MIN_VALUE; int maxIndex = 0;
				for (int j = 0; j < S.size(); j++) {
					if (max < S.get(j)) {
						max = S.get(j);
						maxIndex = j;
					}
				}
				if (maxIndex + 1 != S.size()) {
					temp.append(String.format("%d %d 1\n", maxIndex + 1, S.size()));
					t++;
				}
				S.remove(maxIndex);
			}
			bw.append(t).append("\n");
			bw.append(temp);
		}
		System.out.print(bw);
	}

}