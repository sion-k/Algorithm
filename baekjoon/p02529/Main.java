package baekjoon.p02529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int[] EQ; //왼쪽이 더 크면 -1 아니면 1

	static long MAX = 0; static long MIN = 9876543210L;

	static ArrayList<Integer> ANS = new ArrayList<>();

	static void BFC(int i, int prev, boolean[] taken) {
		if (i == K + 1) {comp(); return;}
		int start = 0; int d = 0;
		if (EQ[i - 1] == -1) {start = prev - 1; d = -1;}
		else {start = prev + 1; d = 1;}
		for (int pick = start; 0 <= pick && pick <= 9; pick += d) {
			if (!taken[pick]) {
				taken[pick] = true; ANS.add(pick);
				BFC(i + 1, pick, taken);
				taken[pick] = false; ANS.remove(ANS.size() - 1);
			}
		}
	}

	static final long[] pow = {1, 10, 100, 1_000, 10_000,
	100_000, 1_000_000, 10_000_000, 100_000_000,1_000_000_000};

	static void comp() {
		long sum = 0;
		for (int i = ANS.size() - 1, k = 0; i >= 0; i--, k++) {
			sum += (ANS.get(i) * pow[k]);
		}
		MAX = Math.max(MAX, sum); MIN = Math.min(MIN, sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		EQ = new int[K];
		for (int i = 0; i < K; i++) {
			EQ[i] = st.nextToken().equals(">") ? -1 : 1;
		}
		boolean[] taken = new boolean[10];
		for (int first = 0; first <= 9; first++) {
			taken[first] = true; ANS.add(first);
			BFC(1, first, taken);
			taken[first] = false; ANS.remove(ANS.size() - 1);
		}
		System.out.printf("%0" + (K + 1) + "d", MAX);
		System.out.println();
		System.out.printf("%0" + (K + 1) + "d", MIN);
	}

}