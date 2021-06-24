import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] prime = new int[10000];
	static {
		Arrays.fill(prime, -1);
		prime[1] = 0;
		for (int i = 2; i <= 9999; i++) {
			if (prime[i] == -1) {
				prime[i] = 1;
				for (int k = i + i; k <= 9999; k += i) {
					prime[k] = 0;
				}
			}
		}
	}

	// pos(뒤에서부터 [0, 3])위치의 수를 d로 바꾼 수 반환
	static int move(int here, int pos, int d) {
		int[] digit = new int[4];
		for (int p = 0; here > 0; p++) {digit[p] = here % 10; here /= 10;}
		digit[pos] = d;
		int sum = 0;
		int[] pow = {1, 10, 100, 1000};
		for (int i = 0; i < 4; i++) {sum += digit[i] * pow[i];}
		return sum;
	}

	static int BFS(int start, int end) {
		if (start == end) {return 0;}
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[] BOOKED = new boolean[10000];
		BOOKED[start] = true;
		int[] DIST = new int[10000];
		DIST[start] = 0;

		while (!q.isEmpty()) {
			int here = q.poll();
			for (int p = 0; p < 4; p++) {
				for (int d = 0; d <= 9; d++) {
					int there = move(here, p, d);
					if (there >= 1000 && prime[there] == 1 && !BOOKED[there]) {
						q.offer(there);
						BOOKED[there] = true;
						DIST[there] = DIST[here] + 1;
						if (there == end) {return DIST[there];}
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = BFS(start, end);
			if (dist != -1) {bw.write(String.valueOf(dist));}
			else {bw.write("Impossible");}
			bw.newLine();
		}
		bw.close();
	}

}
