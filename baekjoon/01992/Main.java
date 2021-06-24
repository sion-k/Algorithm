import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] PIXEL;
	
	// 모두 한가지로만 되어있는지 반환
	static boolean filled(int y1, int x1, int y2, int x2) {
		int comp = PIXEL[y1][x1];
		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				if (PIXEL[i][j] != comp) {return false;}
			}
		}
		return true;
	}
	
	static String quadTree(int y1, int x1, int y2, int x2) {
		if (filled(y1, x1, y2, x2)) {return PIXEL[y1][x1] + "";}
		int L = (y2 - y1 + 1) / 2; // 4개로 쪼갠 정사각형 한 변의 길이
		String NW = quadTree(y1, x1, y1 + L - 1, x1 + L - 1);
		String NE = quadTree(y1, x1 + L, y1 + L - 1, x2);
		String SW = quadTree(y1 + L, x1, y2, x1 + L - 1);
		String SE = quadTree(y1 + L, x1 + L, y2, x2);
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(NW).append(NE).append(SW).append(SE).append(")");
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PIXEL = new int[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				PIXEL[i][j] = row.charAt(j) - '0';
			}
		}
		br.close();
		System.out.println(quadTree(0, 0, N - 1, N - 1));
	}
}
