package baekjoon.p05430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			String P = br.readLine();
			int N = Integer.parseInt(br.readLine());
			Deque<Integer> dq = new LinkedList<>();
			String S = br.readLine();
			S = S.substring(1, S.length() - 1);
			StringTokenizer st = new StringTokenizer(S, ",");
			for (int i = 0; i < N; i++) dq.offer(Integer.parseInt(st.nextToken()));
			int d = 1; // 왼쪽에서 오른쪽 방향 (-1이면 반대)
			boolean ok = true; // 에러가 발생하지 않았는지 여부
			for (int i = 0; i < P.length(); i++) {
				switch (P.charAt(i)) {
				case 'R':
					d *= -1;
					break;
				case 'D':
					if (!dq.isEmpty()) 
						if (d == 1) dq.pollFirst();
						else dq.pollLast();
					else ok = false;
					break;
				}
				if (!ok) break;
			}
			if (ok) {
				ans.append("[");
				int range = dq.size();
				for (int i = 0; i < range; i++) {
					ans.append(d == 1 ? dq.pollFirst() : dq.pollLast());
					if (i != range - 1) ans.append(",");
				}
				ans.append("]");
			} else {
				ans.append("error");
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}

}