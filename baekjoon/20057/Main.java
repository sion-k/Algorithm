import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] S;
    static int[][] D;

    static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dr = { 2, 3, 1, 0 }; // 왼쪽 회전하면 방향

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

    // d방향을 보고있을 때 상대위치 (y, x, k)값들 (a제외)
    static final int[][][] spread = {
        {
            { -2, 0, 5 }, 
            { -1, -1, 10 }, { -1, 1, 10 }, 
            { 0, -2, 2 }, { 0, -1, 7 }, { 0, 1, 7 }, { 0, 2, 2 },
            { 1, -1, 1 }, { 1, 1, 1 }
        },
        {
            { -1, -1, 1 }, { -1, 1, 1 },
            { 0, -2, 2 }, { 0, -1, 7 }, { 0, 1, 7 }, { 0, 2, 2 },
            { 1, -1, 10 }, { 1, 1, 10 },
            { 2, 0, 5 }
        },
        { 
            { -2, 0, 2 },
            { -1, -1, 10 }, { -1, 0, 7 }, { -1, 1, 1 },
            { 0, -2, 5 },
            { 1, -1, 10 }, { 1, 0, 7 }, { 1, 1, 1 },
            { 2, 0, 2 }
        },
        { 
            { -2, 0, 2 },
            { -1, -1, 1 }, { -1, 0, 7 }, { -1, 1, 10 },
            { 0, 2, 5 },
            { 1, -1, 1 }, { 1, 0, 7 }, { 1, 1, 10 },
            { 2, 0, 2 }
        }
    };

    // d방향을 보고 있을 때 a의 상대 위치
    static final int[][] da = {
        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };

    // (y, x)에 있는 토네이도를 다음 칸으로 이동시키고
    // 밖으로 나간 모래의 양을 반환
    static int move(int y, int x) {
        if (y == 0 && x == 0) return 0;
        int d = D[y][x];
        y += dy[d]; x += dx[d];
        int sum = 0; int left = S[y][x];
        for (int[] p : spread[d]) {
            int ty = p[0]; int tx = p[1]; int tk = p[2];
            int ny = y + ty;
            int nx = x + tx;
            int amount = (S[y][x] * tk / 100);
            left -= amount;
            if (inRange(ny, nx)) S[ny][nx] += amount;
            else sum += amount;
        }
        // 남은 모래의 양을 a로 이동
        int ny = y + da[d][0]; int nx = x + da[d][1];
        if (inRange(ny, nx)) S[ny][nx] += left;
        else sum += left;
        return sum + move(y, x);
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
        }
        D = new int[N][N];
        int d = 2; int r = 1;
        int y = N / 2; int x = N / 2;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < r; j++) {
                D[y][x] = d;
                y += dy[d]; x += dx[d];
            }
            d = dr[d];
            for (int j = 0; j < r; j++) {
                D[y][x] = d;
                y += dy[d]; x += dx[d];
            }
            d= dr[d];
            r++;
        }
        for (int j = N - 1; j > 0; j--) D[0][j] = 2;
        System.out.println(move(N / 2, N / 2));
	}

}
