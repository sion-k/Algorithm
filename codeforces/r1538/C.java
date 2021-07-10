import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			ArrayList<Integer> S = new ArrayList<>(n);
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) S.add(Integer.parseInt(st.nextToken()));
			Collections.sort(S);
			long sum = 0;
			for (int i = 0; i < n - 1; i++) {
				// S[i] + S[x]가 l보다 같거나 커지는 최초의 위치 x를 찾는다
				int lo = i + 1; int hi = n - 1;
				if (S.get(i) + S.get(lo) >= l) {
					sum += (n - lo); continue;
				} else if (S.get(i) + S.get(hi) < l) {
					continue;
				}
				while (lo + 1 < hi) {
					int mid = (lo + hi) / 2;
					if (S.get(i) + S.get(mid) >= l) hi = mid;
					else lo = mid;
				}
				sum += (n - hi);
			}
			for (int i = 0; i < n - 1; i++) {
				// S[i] + S[x]가 r + 1보다 같거나 커지는 최초의 위치 x를 찾는다
				int lo = i + 1; int hi = n - 1;
				if (S.get(i) + S.get(lo) >= r + 1) {
					sum -= (n - lo); continue;
				} else if (S.get(i) + S.get(hi) < r + 1) {
					continue;
				}
				while (lo + 1 < hi) {
					int mid = (lo + hi) / 2;
					if (S.get(i) + S.get(mid) >= r + 1) hi = mid;
					else lo = mid;
				}
				sum -= (n - hi);
			}
			bw.append(sum).append("\n");
		}
		System.out.print(bw);
	}

}