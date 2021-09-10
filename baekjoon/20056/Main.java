import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Queue<Tuple>>> S;

	static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	// S[y][x]에 위치한 파이어볼을 모두 이동시킴
	// 이동할 파이어볼들은 큐에 저장해둔다.
	static void move(int y, int x, Queue<Tuple> q) {
		while (!S.get(y).get(x).isEmpty()) {
			Tuple t = S.get(y).get(x).poll();
			int m = t.m; int s = t.s; int d = t.d;
			int ny = (y + (s * dy[d] % N) + N) % N;
			int nx = (x + (s * dx[d] % N) + N) % N;
			q.offer(new Tuple(m, d, s, ny, nx));
		}
	}

	static void moveAll() {
		Queue<Tuple> q = new LinkedList<>();
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				move(y, x, q);
		// 이제 S[y][x]는 모두 비어있다. 파이어볼들을 이동
		while (!q.isEmpty()) {
			Tuple t = q.poll();
			S.get(t.y).get(t.x).offer(t);
		}
		// 이동이 끝난 뒤 합쳐준다
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				merge(y, x);
	}

	// S[y][x]에 존재하는 이동이 끝난 파이어볼을 합친다
	static void merge(int y, int x) {
		int n = S.get(y).get(x).size();
		// 2개 이상의 파이어볼이 있어야 합쳐진다.
		if (n < 2) return;
		int mSum = 0; int sSum = 0;
		boolean odd = false; boolean even = false;
		while (!S.get(y).get(x).isEmpty()) {
			Tuple t = S.get(y).get(x).poll();
			mSum += t.m; sSum += t.s;
			if (t.d % 2 == 1) odd = true;
			else even = true;
		}
		mSum /= 5; sSum /= n;
		// 질량이 0인 파이어볼은 소멸되어 없어진다.
		if (mSum == 0) return;
		// 모두 홀수이거나 모두 짝수인 경우
		if (odd ^ even) {
			for (int d = 0; d <= 6; d += 2)
				S.get(y).get(x).offer(new Tuple(mSum, d, sSum, y, x));
		} else {
			for (int d = 1; d <= 7; d += 2)
				S.get(y).get(x).offer(new Tuple(mSum, d, sSum, y, x));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		S = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			S.add(new ArrayList<>());
			for (int j = 0; j < N; j++) S.get(i).add(new LinkedList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			S.get(r).get(c).offer(new Tuple(m, d, s, r, c));
		}
		for (int i = 0; i < K; i++) moveAll();
		int sum = 0;
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				for (Tuple t : S.get(y).get(x))
					sum += t.m;
		System.out.println(sum);
	}

}
class Tuple {
	int m, d, s;
	int y, x;

	Tuple(int m, int d, int s, int y, int x) {
		this.m = m; this.d = d; this.s = s;
		this.y = y; this.x = x;
	}
}
