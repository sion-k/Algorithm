import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N; static int[][][] cache;

	// i번째 행과 i + 1번째 행이 각각 b0, b1일 때
	// 블록으로 모두 덮는 경우의 수
	static int dp(int i, int b0, int b1) {
		if (i == N) return b0 == 0 && b1 == 0 ? 1 : 0;
		if (cache[i][b0][b1] != -1) return cache[i][b0][b1];
		return cache[i][b0][b1] = btk(i, 0, new int[] {b0, b1, 0});
	}
	
	// (i, j)부터 블록을 놓을 때, i, i + 1, i + 2번째 행이 int[] b일때
	// 블록으로 완전히 덮을 수있는 경우의 수
	static int btk(int i, int j, int[] b) {
		if (j == 3) return dp(i + 1, b[1], b[2]);
		// 이미 덮여있는 경우 다음으로 넘어간다
		if ((b[0] & (1 << j)) > 0) return btk(i, j + 1, b);
		// 덮여있지 않은 경우 가능한 모든 타일을 시도해본다
		int sum = 0;
		// 블록의 모양
		for (int t = 0; t < dy.length; t++) {
			// 블록을 회전한 모양
			for (int r = 0; r < dy[t].length; r++) {
				boolean check = true;
				// 블럭의 칸 수는 4개, 4개 다 놓을 수 있는지 확인
				for (int p = 0; p < 4; p++) {
					int y = i + dy[t][r][p]; int x = j + dx[t][r][p];
					if (!inRange(y, x) || (b[dy[t][r][p]] & (1 << x)) > 0) {check = false; break;}
				}
				if (check) {
					int[] temp = b.clone();
					for (int p = 0; p < 4; p++) {
						int x = j + dx[t][r][p];
						temp[dy[t][r][p]] |= (1 << x);
					}
					sum = (sum + btk(i, j + 1, temp)) % MOD;
				}
			}
		}
		return sum;
	}
	
	static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < 3;}
	
	static final int MOD = 1000000;
	
	// 모든 모양들에 대해 가장 맨 위 맨 왼쪽을 기준으로 상대 좌표 저장
	// 회전되는 순서는 시계방향으로 90도씩 회전하면서 블록들의 순서는 맨 위 먼저, 같다면 맨 왼쪽 먼저
	static final int[][][] dy = {
			// OO
			// OO 회전 없음
			{{0, 0, 1, 1}},
			//  OO         O
			// OO          OO
			//              O
			{{0, 0, 1, 1}, {0, 1, 1, 2}},
			// OO           O
			//  OO         OO
			//             O
			{{0, 0, 1, 1}, {0, 1, 1, 2}},
			//  O          O             OOO            O
			// OOO         OO             O            OO
			//             O                            O
			{{0, 1, 1, 1}, {0, 1, 1, 2}, {0, 0, 0, 1}, {0, 1, 1, 2}},
			//   O         O             OOO           OO
			// OOO         O             O              O
			//             OO                           O
			{{0, 1, 1, 1}, {0, 1, 2, 2}, {0, 0, 0, 1}, {0, 0, 1, 2}},
			// OOO          O            O             OO
			//   O          O            OOO           O
			//             OO                          O
			{{0, 0, 0, 1}, {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}}
	};
	
	static final int[][][] dx = {
			// OO
			// OO 회전 없음
			{{0, 1, 0, 1}},
			//  OO         O
			// OO          OO
			//              O
			{{0, 1, -1, 0}, {0, 0, 1, 1}},
			// OO           O
			//  OO         OO
			//             O
			{{0, 1, 1, 2}, {0, -1, 0, -1}},
			//  O          O             OOO            O
			// OOO         OO             O            OO
			//             O                            O
			{{0, -1, 0, 1}, {0, 0, 1, 0}, {0, 1, 2, 1}, {0, -1, 0, 0}},
			//   O         O             OOO           OO
			// OOO         O             O              O
			//             OO                           O
			{{0, -2, -1, 0}, {0, 0, 0, 1}, {0, 1, 2, 0}, {0, 1, 1, 1}},
			// OOO          O            O             OO
			//   O          O            OOO           O
			//             OO                          O
			{{0, 1, 2, 2}, {0, 0, 0, -1}, {0, 0, 1, 2}, {0, 1, 0, 0}}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if ((N * 3) % 4 == 0) {
			cache = new int[N][8][8];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < 8; j++)
					Arrays.fill(cache[i][j], -1);
			System.out.println(dp(0, 0, 0));
		} else {
			System.out.println(0);
		}
	}
	
}
