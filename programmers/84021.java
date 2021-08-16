import java.util.*;

class Solution {
    static int N;
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

    static boolean[][] booked;

    static ArrayList<Puzzle> bfsAll(int[][] S) {
        ArrayList<Puzzle> ret = new ArrayList<>();
        booked = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (S[i][j] == 1 && !booked[i][j])
                    ret.add(bfs(S, i, j));
        return ret;
    }

    static Puzzle bfs(int[][] S, int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { sy, sx });
        booked[sy][sx] = true;
        boolean[][] P = new boolean[11][11];
        while (!q.isEmpty()) {
            int[] here = q.poll();
            int y = here[0]; int x = here[1];
            P[5 + (y - sy)][5 + (x - sx)] = true;
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (!inRange(ny, nx) || S[ny][nx] == 0 || booked[ny][nx]) continue;
                q.offer(new int[] { ny, nx });
                booked[ny][nx] = true;
            }
        }
        return new Puzzle(P);
    }

    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                game_board[i][j] ^= 1;
        int answer = 0;
        ArrayList<Puzzle> B = bfsAll(game_board);
        ArrayList<Puzzle> T = bfsAll(table);
        boolean[] used = new boolean[T.size()];
        for (int i = 0; i < B.size(); i++)
            for (int j = 0; j < T.size(); j++) {
                if (!used[j] && B.get(i).equals(T.get(j))) {
                    used[j] = true;
                    answer += B.get(i).size;
                    break;
                }
            }
        return answer;
    }

}
class Puzzle {
    int N, M;
    boolean[][] P; int size;

    // 11 x 11, 중심은 (5, 5)로 들어온 p를 블럭의 크기에 맞게 잘라낸다
    Puzzle(boolean[][] p) {
        // 왼쪽 위 (y1, x1) 오른쪽 아래 (y2, x2)
        int y1 = 11; int x1 = 11; int y2 = 0; int x2 = 0;
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 11; j++)
                if (p[i][j]) {
                    size++;
                    y1 = Math.min(y1, i); x1 = Math.min(x1, j);
                    y2 = Math.max(y2, i); x2 = Math.max(x2, j);
                }
        N = y2 - y1 + 1; M = x2 - x1 + 1;
        P = new boolean[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                P[i][j] = p[y1 + i][x1 + j];
    }

    // 반시계 방향으로 회전
    void rotate() {
        boolean[][] rotated = new boolean[M][N];
        for (int i = 0; i < rotated.length; i++)
            for (int j = 0; j < rotated[i].length; j++)
                rotated[i][j] = P[j][M - 1 - i];
        P = rotated;
        int temp = N; N = M; M = temp;
    }

    public boolean equals(Puzzle o) {
        for (int r = 0; r < 4; r++) {
            if (N == o.N && M == o.M) {
                boolean flag = true;
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < M; j++)
                        if (P[i][j] != o.P[i][j])
                            flag = false;
                if (flag) return true;
            }
            rotate();
        }
        return false;
    }

}
