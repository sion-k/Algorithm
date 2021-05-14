package baekjoon.p12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static void print(Board b) {
		for (int i = 0; i < b.N; i++)
			System.out.println(Arrays.toString(b.map[i]));
		System.out.println();
	}

	static int dfs(Board b, int depth) {
		if (depth == 10) return b.max();
		int max = 2;
		for (int d = 0; d < 4; d++) {
			Board nextBoard = b.shift(d);
			if (!b.equals(nextBoard))
				max = Math.max(max, dfs(b.shift(d), depth + 1));
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dfs(new Board(arr), 0));
	}

}

class Board {
	int N; int[][] map;

	public Board(int[][] map) {
		this.map = map;
		N = map.length;
	}

	public boolean equals(Board o) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] != o.map[i][j])
					return false;
		return true;
	}

	public int max() {
		int max = 1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				max = Math.max(max, map[i][j]);
		return max;
	}

	// d : �����¿� [0, 4)�� �̵���Ų Board�� ��ȯ
	public Board shift(int d) {
		int[][] nextMap = new int[N][N];
		switch (d) {
			case 0 :
				for (int j = 0; j < N; j++) {
					// ���� �����ϴ� ���� ������� ��´�
					List<Integer> col = new ArrayList<Integer>();
					for (int i = 0; i < N; i++)
						if (map[i][j] != 0) col.add(map[i][j]);
					// ������ ���ΰ�� ���������� ���ʷ� ������ ���Ҹ� ã�´�
					List<Integer> nextCol = new ArrayList<Integer>();
					for (int i = 0; i < col.size(); i++) {
						if (i + 1 < col.size() && col.get(i).equals(col.get(i + 1))) {
							nextCol.add(2 * col.get(i));
							i++;
						} else {
							nextCol.add(col.get(i));
						}
					}
					for (int i = 0; i < nextCol.size(); i++)
						nextMap[i][j] = nextCol.get(i);
				}
				break;
			case 1 :
				for (int j = 0; j < N; j++) {
					// ���� �����ϴ� ���� �Ųٷ� ��´�
					List<Integer> col = new ArrayList<Integer>();
					for (int i = N - 1; i >= 0; i--)
						if (map[i][j] != 0) col.add(map[i][j]);
					// ������ �Ʒ��ΰ�� �Ʒ��������� ���ʷ� ������ ���Ҹ� ã�´�
					// �Ųٷ� ������Ƿ� ���� ����
					List<Integer> nextCol = new ArrayList<Integer>();
					for (int i = 0; i < col.size(); i++) {
						if (i + 1 < col.size() && col.get(i).equals(col.get(i + 1))) {
							nextCol.add(2 * col.get(i));
							i++;
						} else {
							nextCol.add(col.get(i));
						}
					}
					for (int i = 0; i < nextCol.size(); i++)
						nextMap[N - i - 1][j] = nextCol.get(i);
				}
				break;
			case 2 :
				for (int i = 0; i < N; i++) {
					// �࿡ �����ϴ� ���� ������� ��´�
					List<Integer> row = new ArrayList<Integer>();
					for (int j = 0; j < N; j++)
						if (map[i][j] != 0) row.add(map[i][j]);
					// ������ �����ΰ�� ���ʿ������� ���ʷ� ������ ��ȣ�� ã�´�
					List<Integer> nextRow = new ArrayList<Integer>();
					for (int j = 0; j < row.size(); j++) {
						if (j + 1 < row.size() && row.get(j).equals(row.get(j + 1))) {
							nextRow.add(2 * row.get(j));
							j++;
						} else {
							nextRow.add(row.get(j));
						}
					}
					for (int j = 0; j < nextRow.size(); j++)
						nextMap[i][j] = nextRow.get(j);
				}
				break;
			case 3 :
				for (int i = 0; i < N; i++) {
					// �࿡ �����ϴ� ���� �Ųٷ� ��´�
					List<Integer> row = new ArrayList<Integer>();
					for (int j = N - 1; j >= 0; j--)
						if (map[i][j] != 0) row.add(map[i][j]);
					// ������ �������ΰ�� �����ʿ������� ���ʷ� ������ ��ȣ�� ã�´�
					// �Ųٷ� ������Ƿ� ���� ����
					List<Integer> nextRow = new ArrayList<Integer>();
					for (int j = 0; j < row.size(); j++) {
						if (j + 1 < row.size() && row.get(j).equals(row.get(j + 1))) {
							nextRow.add(2 * row.get(j));
							j++;
						} else {
							nextRow.add(row.get(j));
						}
					}
					for (int j = 0; j < nextRow.size(); j++)
						nextMap[i][N - j - 1] = nextRow.get(j);
				}
				break;
		}
		return new Board(nextMap);
	}
}