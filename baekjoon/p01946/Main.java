package baekjoon.p01946;

import java.util.*;
import java.io.*;

public class Main {

	private static class Applicant implements Comparable<Applicant> {
		public int t1;
		public int t2;

		public Applicant(int t1, int t2) {
			this.t1 = t1;
			this.t2 = t2;
		}

		@Override
		public int compareTo(Applicant o) {
			return t1 - o.t1;
		}
	}

	private static Applicant[] APP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			APP = new Applicant[N];

			for (int a = 0; a < N; a++) {
				st = new StringTokenizer(br.readLine(), " ");
				APP[a] = new Applicant(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(APP);
			int sum = 1;
			int last = APP[0].t2;

			for (int i = 1; i < N; i++) {
				if (APP[i].t2 < last) {
					last = APP[i].t2;
					sum++;
				}
			}
			System.out.println(sum);
		}
		br.close();
	}

}
