package baekjoon.p1449;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(sc.nextLine(), " ");
		sc.close();
		int[] leak = new int[N];
		for (int i = 0; i < N; i++) {
			leak[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(leak);

		int covered = leak[0] + L - 1;
		int used = 1;
		for (int i = 1; i < N; i++) {
			if (leak[i] <= covered) {
				continue;
			}
			covered = leak[i] + L - 1;
			used++;
		}

		System.out.println(used);
	}
}
