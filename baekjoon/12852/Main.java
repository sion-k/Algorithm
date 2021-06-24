import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int[] cache;
	static int[] choice;
	static StringBuilder ANS = new StringBuilder();

	static final int INF = 1000000;

	static int move(int here, int moveWay) {
		switch (moveWay) {
		case 0:
			if (here % 3 == 0) {return here / 3;}
			break;
		case 1:
			if (here % 2 == 0) {return here / 2;}
			break;
		case 2:
			return here - 1;
		}
		return -1;
	}

	static int dp(int X) {
		if (X == 1) {return 0;}
		if (cache[X] != 0) {return cache[X];}
		int min = INF; int best = 0; int cand = 0;
		for (int i = 0; i < 3; i++) {
			int next = move(X, i);
			if (next == -1) {continue;}
			if ((cand = dp(next)) < min) {
				min = cand; best = i;
			}
		}
		choice[X] = best;
		return cache[X] = 1 + min;
	}

	static void reconstruct(int X) {
		ANS.append(X);
		if (X == 1) {return;}
		ANS.append(" ");
		reconstruct(move(X, choice[X]));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int X = Integer.parseInt(br.readLine());
		cache = new int[X + 1];
		choice = new int[X + 1]; Arrays.fill(choice, -1);
		bw.write(String.valueOf(dp(X)));
		bw.newLine();
		reconstruct(X);
		bw.write(ANS.toString());
		bw.newLine();
		bw.close();
	}

}
