import java.io.*;
import java.util.*;

public class F {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		ArrayList<Long> S1 = new ArrayList<>();
		for (int a = 1; a <= 9; a++) {
			for (int t = 1; t <= 10; t++) {
				for (int d = 1; d < (1 << t); d++) {
				StringBuilder temp = new StringBuilder();
				for (int bit = 0; bit < t; bit++)
					if ((d & (1 << bit)) > 0) temp.append(a);
				S1.add(Long.parseLong(temp.toString()));
				}
			}
		}
		Collections.sort(S1);
		ArrayList<Long> S2 = new ArrayList<>();
		for (int a = 0; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				for (int t = 1; t <= 10; t++) {
					for (int d = 0; d < (1 << t); d++) {
						StringBuilder temp = new StringBuilder();
						for (int bit = 0; bit < t; bit++)
							if ((d & (1 << bit)) > 0) temp.append(a);
							else temp.append(b);
						S2.add(Long.parseLong(temp.toString()));
					}
				}
			}
		}
		Collections.sort(S2);
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			if (K == 1) {
				int lo = 0; int hi = S1.size() - 1;
				while (lo + 1 < hi) {
					int mid = (lo + hi) / 2;
					if (Long.compare(S1.get(mid), N) >= 0) hi = mid;
					else lo = mid;
				}
				bw.append(S1.get(hi));
			} else {
				int lo = 0; int hi = S2.size() - 1;
				while (lo + 1 < hi) {
					int mid = (lo + hi) / 2;
					if (Long.compare(S2.get(mid), N) >= 0) hi = mid;
					else lo = mid;
				}
				bw.append(S2.get(hi));
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}