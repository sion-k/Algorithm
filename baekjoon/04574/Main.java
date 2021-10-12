import java.util.*;
import java.io.*;

public class Main {
    // 현재 칸 기준으로 오른쪽으로 놓는 경우와 아래로 놓는 경우
    static final int[][] dy = { 
        { 0, 0 },
        { 0, 1 }
    };

    static final int[][] dx = {
        { 0, 1 },
        { 0, 0 }
    };

    static boolean inRange(int y, int x) { return 0 <= y && y < 9 && 0 <= x && x < 9; }

    static int[] toPos(String s) {
        return new int[] { s.charAt(0) - 'A', s.charAt(1) - '1' };
    }

    static StringBuilder bw;
    static boolean found;

    static void print(int[][] S) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) bw.append(S[i][j]);
            bw.append("\n");
        }
        found = true;
    }

    // 사용할 수 있는지 확인
    static boolean canUse(boolean[][] used, int i, int j) {
        if (i > j) { int temp = i; i = j; j = temp; }
        return !used[i][j];
    }

    static boolean canPlace(int[][] S, int y1, int x1, int u, int y2, int x2, int v, boolean[][] row, boolean[][] col, boolean[][] cell) {
        // 범위 밖을 벗어나는 경우
        if (!inRange(y1, x1) || !inRange(y2, x2)) return false;
        // 이미 놓여져 있는 경우
        if (S[y1][x1] != 0 || S[y2][x2] != 0) return false;
        // 첫 번째 블록 확인
        if (row[y1][u] || col[x1][u] || cell[y1 / 3 * 3 + x1 / 3][u]) return false;
        // 두 번째 블록 확인
        if (row[y2][v] || col[x2][v] || cell[y2 / 3 * 3 + x2 / 3][v]) return false;
        return true;
    }

    static void btk(int[][] S, int y, int x, boolean[][] row, boolean[][] col, boolean[][] cell, boolean[][] used) {
        if (found) return;
        if (x == 9) { y++; x = 0; }
        if (y == 9) { print(S); return; }
        // 이미 블럭이 놓여있는 경우
        if (S[y][x] != 0) { btk(S, y, x + 1, row, col, cell, used); return; }
        // 아직 사용하지 않은 (i, j)를 사용
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (!canUse(used, i, j)) continue;
                // 오른쪽으로 놓는 경우와 아래로 놓는 경우
                for (int k = 0; k < 2; k++) {
                    int ny1 = y + dy[k][0]; int nx1 = x + dx[k][0];
                    int ny2 = y + dy[k][1]; int nx2 = x + dx[k][1];
                    if (!canPlace(S, ny1, nx1, i, ny2, nx2, j, row, col, cell)) continue;
                    used[i][j] = used[j][i] = true;
                    row[ny1][i] = col[nx1][i] = row[ny2][j] = col[nx2][j] = true;
                    cell[ny1 / 3 * 3 + nx1 / 3][i] = cell[ny2 / 3 * 3 + nx2 / 3][j] = true;
                    S[ny1][nx1] = i; S[ny2][nx2] = j;
                    btk(S, y, x + 2 - k, row, col, cell, used);
                    S[ny1][nx1] = 0; S[ny2][nx2] = 0;
                    row[ny1][i] = col[nx1][i] = row[ny2][j] = col[nx2][j] = false;
                    cell[ny1 / 3 * 3 + nx1 / 3][i] = cell[ny2 / 3 * 3 + nx2 / 3][j] = false;
                    used[i][j] = used[j][i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new StringBuilder();
        String line = "";
        int TC = 1;
        while (!(line = br.readLine()).equals("0")) {
            bw.append(String.format("Puzzle %d\n", TC++));
            found = false;
            int N = Integer.parseInt(line);
            int[][] S = new int[9][9];
            // i번째 행에서 k가 사용되었는지 여부
            boolean[][] row = new boolean[9][10];
            // j번째 열에서 k가 사용되었는지 여부
            boolean[][] col = new boolean[9][10];
            // n번째 3 * 3 정사각형에 k가 사용되었는지 여부
            boolean[][] cell = new boolean[9][10];
            // i < j인 타일이 사용되었는지 여부
            boolean[][] used = new boolean[10][10];
            // (k, k)인 경우 예외 처리
            for (int k = 1; k <= 9; k++) used[k][k] = true;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int[] pu = toPos(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int[] pv = toPos(st.nextToken());
                int y1 = pu[0]; int x1 = pu[1];
                int y2 = pv[0]; int x2 = pv[1];
                S[y1][x1] = u; 
                S[y2][x2] = v;
                row[y1][u] = col[x1][u] = true;
                row[y2][v] = col[x2][v] = true;
                cell[y1 / 3 * 3 + x1 / 3][u] = true;
                cell[y2 / 3 * 3 + x2 / 3][v] = true;
                // i < j가 되도록 조정
                if (u > v) { int temp = u; u = v; v = temp; }
                used[u][v] = true; 
            }
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= 9; i++) {
                int[] p = toPos(st.nextToken());
                int y = p[0]; int x = p[1];
                S[y][x] = i;
                row[y][i] = col[x][i] = true;
                cell[y / 3 * 3 + x / 3][i] = true;
            }
            btk(S, 0, 0, row, col, cell, used);
        }
        System.out.print(bw);
    }

}
