package competition.hoddingTest.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// �־��� �迭�� �ð���� 90�� ȸ���� �迭�� ��ȯ
	static boolean[][] rotate(boolean[][] arr) {
		boolean[][] ret = new boolean[arr[0].length][arr.length];
		for (int i = 0; i < ret.length; i++)
			for (int j = 0; j < ret[0].length; j++)
				ret[i][j] = arr[arr.length - 1 - j][i];
		return ret;
	}
	
	// �־��� �迭�� ���δ� ���� ���� ������ ���� ��ȯ
	static int area(boolean[][] arr) {
		int minY = arr.length - 1; int maxY = 0;
		int minX = arr[0].length - 1; int maxX = 0;
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				if (arr[i][j]) {
					minY = Math.min(minY, i);
					maxY = Math.max(maxY, i);
					minX = Math.min(minX, j);
					maxX = Math.max(maxX, j);
				}
		return (maxY - minY + 1) * (maxX - minX + 1);
	}
	
	static StringBuilder print(boolean[][] arr) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++)
				ret.append(arr[i][j] ? "1" : "0");
			ret.append("\n");
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N1 = Integer.parseInt(st.nextToken()); int M1 = Integer.parseInt(st.nextToken());
		boolean[][] P = new boolean[N1][M1];
		for (int i = 0; i < N1; i++) {
			String row = br.readLine();
			for (int j = 0; j < M1; j++) P[i][j] = row.charAt(j) == '1';
		}
		st = new StringTokenizer(br.readLine(), " ");
		int N2 = Integer.parseInt(st.nextToken()); int M2 = Integer.parseInt(st.nextToken());
		boolean[][] Q = new boolean[N2][M2];
		for (int i = 0; i < N2; i++) {
			String row = br.readLine();
			for (int j = 0; j < M2; j++) Q[i][j] = row.charAt(j) == '1';
		}
		int min = 987654321;
		// P�迭�� �ð�������� 90 * r �� ȸ����Ų �迭�� ���ؼ� ������ ���纻��
		for (int r = 0; r < 4; r++) {
			boolean[][] combine = new boolean[150][150];
			// combine�� ����� Q�� ���´� (���� ���� (50, 50)�� �ǵ���)
			for (int i = 0; i < N2; i++)
				for (int j = 0; j < M2; j++)
					combine[50 + i][50 + j] = Q[i][j];
			// Q�� ���� temp�� P�� ���´�.
			// P�� ���� ���� (0, 0)���� (100, 100)�����ǵ��� ���´�
			boolean ok = true;
			for (int y = 0; y <= 100; y++) {
				for (int x = 0; x <= 100; x++) {
					boolean[][] temp = new boolean[150][150];
					for (int i = 0; i < combine.length; i++) temp[i] = combine[i].clone();
					ok = true;
					for (int dy = 0; dy < P.length; dy++) {
						for (int dx = 0; dx < P[0].length; dx++) {
							if (P[dy][dx] && temp[y + dy][x + dx]) ok = false;
							if (ok) temp[y + dy][x + dx] = P[dy][dx];
							else break;
						}
						if (!ok) break;
					}
					if (ok) {
						//System.out.print(print(temp));
						//System.out.println(area(temp));
						min = Math.min(min, area(temp));
					}
				}
			}
			P = rotate(P);
		}
		System.out.println(min);
	}
	
}