package codeforce.r1519;

import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int[] u = new int[n]; int[] s = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) u[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) s[i] = Integer.parseInt(st.nextToken());
			Map<Integer, ArrayList<Long>> map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				ArrayList<Long> x = map.get(u[i]);
				if (x == null) map.put(u[i], new ArrayList<>());
				x = map.get(u[i]);
				x.add((long)s[i]);
			}
			for (ArrayList<Long> x : map.values()) {
				Collections.sort(x, Collections.reverseOrder());
				for (int i = 1; i < x.size(); i++) x.set(i, x.get(i) + x.get(i - 1));
			}
			StringBuilder ans = new StringBuilder();
			for (long k = 1; k <= n; k++) {
				long sum = 0;
				for (ArrayList<Long> x : map.values())
					if ((x.size() / k) != 0)
						sum += x.get((int)((x.size() / k) * k - 1));
				ans.append(sum).append(" ");
			}
			System.out.println(ans.toString().trim());
		}
	}

}