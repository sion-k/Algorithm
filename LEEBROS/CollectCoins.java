package LEEBROS;

import java.util.*;

public class CollectCoins {
    static int N;
    static char[][] S;
    // [1, 9]번째 동전의 위치, 0과 10은 각각 S와 E를 표현
    static int[][] P;
    static int[][] adj;
    
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1 ,1};
    
    static boolean inRange(int y, int x) {return 0 <= y && y < N && 0 <= x && x < N;}
    
    static int toNum(char ch) {
        if ('1' <= ch && ch <= '9') return ch - '0';
        else if (ch == 'S') return 0;
        return 10;
    }
    
    static void bfs(int start) {
        int[] p = P[start];
        int sy = p[0]; int sx = p[1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sy, sx});
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
        dist[sy][sx] = 0;
        while (!q.isEmpty()) {
            p = q.poll();
            int y = p[0]; int x = p[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (inRange(ny, nx) && S[ny][nx] != '#' && dist[ny][nx] == -1) {
                    q.offer(new int[]{ny, nx});
                    dist[ny][nx] = dist[y][x] + 1;
                    if (S[ny][nx] != '.') adj[start][toNum(S[ny][nx])] = adj[toNum(S[ny][nx])][start] = dist[ny][nx];
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); sc.nextLine();
        S = new char[N][N];
        for (int i = 0; i < N; i++) {
            String row = sc.nextLine();
            for (int j = 0; j < N; j++)
                S[i][j] = row.charAt(j);
        }
        P = new int[11][2];
        for (int i = 0; i < 11; i++) Arrays.fill(P[i], -1);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (S[i][j] != '.' && S[i][j] != '#')
                    P[toNum(S[i][j])] = new int[] {i, j};
        adj = new int[11][11];
        for (int i = 0; i < 11; i++) Arrays.fill(adj[i], -1);
        for (int here = 0; here <= 10; here++)
            if (P[here][0] != -1 && P[here][1] != -1) bfs(here);
        int min = 4000;
        for (int i = 1; i <= 9; i++) {
            if (adj[0][i] == -1) continue;
            for (int j = i + 1; j <= 9; j++) {
                if (adj[i][j] == -1) continue;
                for (int k = j + 1; k <= 9; k++) {
                    if (adj[j][k] == -1 || adj[k][10] == -1) continue;
                    min = Math.min(min, adj[0][i] + adj[i][j] + adj[j][k] + adj[k][10]);
                }
            }
        }
        System.out.println(min == 4000 ? -1 : min);
    }
    
}