import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] T;
	static int MAX = -1000000000;
	static int MIN = 1000000000;

	static int operate(int a, int b, int operator) {
		switch (operator) {
		case 0: return a + b;
		case 1: return a - b;
		case 2: return a * b;
		default : return a / b;
		}
	}

	static void compare(int[] taken) {
		int ret = T[0];
		for (int i = 0; i < N - 1; i++) {
			ret = operate(ret, T[i + 1], taken[i]);
		}
		MAX = Math.max(MAX, ret);
		MIN = Math.min(MIN, ret);
	}

	// 3 ? 4 ? 5
	static void BFC(int i, int[] picked, int[] toPick) {
		if (i == N - 1) {compare(picked); return;}
		for (int pick = 0; pick < 4; pick++) {
			if (toPick[pick] != 0) {
				toPick[pick]--; picked[i] = pick;
				BFC(i + 1, picked, toPick);
				toPick[pick]++; picked[i] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			T[i] = Integer.parseInt(st.nextToken());
		}
		int[] op = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		int[] picked = new int[N - 1];
		BFC(0, picked, op);
		System.out.println(MAX);
		System.out.println(MIN);
	}

}
