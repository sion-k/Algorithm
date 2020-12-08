package baekjoon.p16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<ArrayList<PriorityQueue<Integer>>> P;
	static int[][] food; // 현재 땅에 남아있는 양분
	static int[][] A; // 매 겨울마다 추가하는 양분
	static int[][] dead; // 죽어서 양분이 될 나무

	static final int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static final int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void spring() {
		// 모든 칸의 모든 나무에 대해서 나이가 어린 나무부터 양분을 먹임
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				PriorityQueue<Integer> here = P.get(i).get(j);
				PriorityQueue<Integer> next = new PriorityQueue<>();
				while (!here.isEmpty()) {
					int tree = -here.poll();
					// 땅에 충분한 양이 남아있다면 양분을 먹고 나이가 1 증가한다
					if (food[i][j] >= tree) {
						food[i][j] -= tree;
						next.offer(-(tree + 1));
					} else { // 아니라면 나머지는 모두 죽게된다
						dead[i][j] += tree / 2;
					}
				}
				P.get(i).set(j, next);
			}
		}
	}

	static void summer() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				food[i][j] += dead[i][j];
		dead = new int[N][N];
	}

	// 나이가 5의 배수인 나무에 대해 인접한 8칸에 나이가 1인 나무를 추가한다
	static void autumn() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				PriorityQueue<Integer> here = P.get(y).get(x);
				for (int tree : here) {
					if (-tree % 5 != 0) {continue;}
					for (int d = 0; d < 8; d++) {
						int ny = y + dy[d]; int nx = x + dx[d];
						if (!inRange(ny, nx)) {continue;}
						P.get(ny).get(nx).add(-1);
					}
				}
			}
		}
	}

	// 모든 땅에 A를 추가
	static void winter() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				food[i][j] += A[i][j];
	}

	static void print() {
		for (int i = 0; i < N; i++)
			System.out.println(P.get(i));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		// 2차원 평면에 나무의 나이들을 저장하는 P 초기화
		P = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			P.add(new ArrayList<>());
			for (int j = 0; j < N; j++)
				P.get(i).add(new PriorityQueue<>());
		}
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 현재 땅에 존재하는 양문의 양 F[y][x], 5로 초기화 한다
		food = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(food[i], 5);
		// 겨울마다 추가하는 양분의 양 A[y][x]
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		// 봄에 죽어서 쌓이는 나무의 양
		dead = new int[N][N];
		// 초기 나무의 위치 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken());
			P.get(y).get(x).add(-a);
		}
		// K년을 지나게 한다
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			autumn();
			winter();
		}
		// 살아남은 나무의 수 구하기
		int sum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				sum += P.get(i).get(j).size();
		System.out.println(sum);
	}

}