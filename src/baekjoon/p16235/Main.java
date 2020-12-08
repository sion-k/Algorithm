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
	static int[][] food; // ���� ���� �����ִ� ���
	static int[][] A; // �� �ܿ︶�� �߰��ϴ� ���
	static int[][] dead; // �׾ ����� �� ����

	static final int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static final int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void spring() {
		// ��� ĭ�� ��� ������ ���ؼ� ���̰� � �������� ����� ����
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				PriorityQueue<Integer> here = P.get(i).get(j);
				PriorityQueue<Integer> next = new PriorityQueue<>();
				while (!here.isEmpty()) {
					int tree = -here.poll();
					// ���� ����� ���� �����ִٸ� ����� �԰� ���̰� 1 �����Ѵ�
					if (food[i][j] >= tree) {
						food[i][j] -= tree;
						next.offer(-(tree + 1));
					} else { // �ƴ϶�� �������� ��� �װԵȴ�
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

	// ���̰� 5�� ����� ������ ���� ������ 8ĭ�� ���̰� 1�� ������ �߰��Ѵ�
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

	// ��� ���� A�� �߰�
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
		// 2���� ��鿡 ������ ���̵��� �����ϴ� P �ʱ�ȭ
		P = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			P.add(new ArrayList<>());
			for (int j = 0; j < N; j++)
				P.get(i).add(new PriorityQueue<>());
		}
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// ���� ���� �����ϴ� �繮�� �� F[y][x], 5�� �ʱ�ȭ �Ѵ�
		food = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(food[i], 5);
		// �ܿ︶�� �߰��ϴ� ����� �� A[y][x]
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		// ���� �׾ ���̴� ������ ��
		dead = new int[N][N];
		// �ʱ� ������ ��ġ �ʱ�ȭ
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken());
			P.get(y).get(x).add(-a);
		}
		// K���� ������ �Ѵ�
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			autumn();
			winter();
		}
		// ��Ƴ��� ������ �� ���ϱ�
		int sum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				sum += P.get(i).get(j).size();
		System.out.println(sum);
	}

}