import java.io.*;
import java.util.*;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> S = new ArrayList<>(N + 2);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long sum = 0;
			for (int i = 0; i < N + 2; i++) {
				S.add(Integer.parseInt(st.nextToken()));
				sum += S.get(i);
			}
			Collections.sort(S);
			int head = 0; int tail = N + 1;
			int p = 0; int q = 0;
			while (head < tail) {
				long temp = 2 * S.get(head);
				temp += S.get(tail);
				if (temp == sum) {
					p = head; q = tail;
					break;
				}
				if (temp < sum) head++;
				else tail--;
			}
			head = 0; tail = N + 1;
			while (head < tail) {
				long temp = S.get(head);
				temp += 2 * S.get(tail);
				if (temp == sum) {
					p = head; q = tail;
					break;
				}
				if (temp < sum) head++;
				else tail--;
			}
			if (p == 0 && q == 0) {
				ans.append(-1);
			} else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < N + 2; i++) 
					if (i != p && i != q) sb.append(S.get(i)).append(" ");
				ans.append(sb.toString().trim());
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}

}