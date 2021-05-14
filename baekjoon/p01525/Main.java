package baekjoon.p01525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// [0, 8]�ε����� ���� �ҷ���
	static int get(int mask, int index) {
		if (((mask >> 27) & 15) == index) {return 0;}
		return ((mask >> (index * 3)) & 7) + 1;
	}

	// � �ε����� 0���� �ٲٴ� �͵� ����
	static int set(int mask, int index, int value) {
		if (value == 0) {
			mask = mask & ~(7 << (index * 3)) | (value << (index * 3));
			return mask & ~(15 << 27) | (index << 27);
		} else {
			value--;
			return mask & ~(7 << (index * 3)) | (value << (index * 3));
		}
	}

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < 3 && 0 <= x && x < 3;
	}

	// ��ĭ �������� ������ �����¿� [0, 4)�� ĭ�� ���� ��ȯ
	static int move(int mask, int d) {
		int blank = ((mask >> 27) & 15);
		int y = blank / 3; int x = blank % 3;
		int ny = y + dy[d]; int nx = x + dx[d];
		if (inRange(ny, nx)) {
			int therePos = ny * 3 + nx;
			int thereVal = get(mask, therePos);
			mask = set(mask, blank, thereVal);
			return set(mask, therePos, 0);
		} else {
			return mask;
		}
	}
	static final int GOAL = 0b1000_000_111_110_101_100_011_010_001_000;

	static int BFS(int start) {
		if (start == GOAL) {return 0;}
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		Map<Integer, Integer> dist = new HashMap<>();
		dist.put(start, 0);
		while (!q.isEmpty()) {
			int here = q.poll();
			for (int d = 0; d < 4; d++) {
				int there = move(here, d);
				if (!dist.containsKey(there)) {
					dist.put(there, dist.get(here) + 1);
					q.offer(there);
					if (there == GOAL) {return dist.get(there);}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int start = 0;
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				start = set(start, 3 * i + j, Integer.parseInt(st.nextToken()));
			}
		}
		System.out.println(BFS(start));
	}

}