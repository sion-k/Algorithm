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

	// d : 상하좌우 [0, 4)로 이동시킨 Board를 반환
	public Board shift(int d) {
		int[][] nextMap = new int[N][N];
		switch (d) {
			case 0 :
				for (int j = 0; j < N; j++) {
					// 열에 존재하는 수를 순서대로 담는다
					List<Integer> col = new ArrayList<Integer>();
					for (int i = 0; i < N; i++)
						if (map[i][j] != 0) col.add(map[i][j]);
					// 방향이 위인경우 위에서부터 차례로 연속한 원소를 찾는다
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
					// 열에 존재하는 수를 거꾸로 담는다
					List<Integer> col = new ArrayList<Integer>();
					for (int i = N - 1; i >= 0; i--)
						if (map[i][j] != 0) col.add(map[i][j]);
					// 방향이 아래인경우 아래에서부터 차례로 연속한 원소를 찾는다
					// 거꾸로 담았으므로 위와 동일
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
					// 행에 존재하는 수를 순서대로 담는다
					List<Integer> row = new ArrayList<Integer>();
					for (int j = 0; j < N; j++)
						if (map[i][j] != 0) row.add(map[i][j]);
					// 방향이 왼쪽인경우 왼쪽에서부터 차례로 연속한 번호를 찾는다
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
					// 행에 존재하는 수를 거꾸로 담는다
					List<Integer> row = new ArrayList<Integer>();
					for (int j = N - 1; j >= 0; j--)
						if (map[i][j] != 0) row.add(map[i][j]);
					// 방향이 오른쪽인경우 오른쪽에서부터 차례로 연속한 번호를 찾는다
					// 거꾸로 담았으므로 위와 동일
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