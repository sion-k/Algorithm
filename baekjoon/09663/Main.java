import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] board;
	static boolean[] taken;
	static int BTK(int i) {
		if (i == N) {return 1;}
		int sum = 0;
		for (int pick = 0; pick < N; pick++) {
			if (!taken[pick]) {
				boolean ok = true;
				for (int j = 0; j < i; j++) {
					if (i - j == Math.abs(pick - board[j])) {
						ok = false; break;
					}
				}
				if (!ok) {continue;}
				taken[pick] = true;  board[i] = pick;
				sum += BTK(i + 1);
				taken[pick] = false;
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N];
		taken = new boolean[N];
		System.out.println(BTK(0));
	}

}
