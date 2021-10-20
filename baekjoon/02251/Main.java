import java.util.*;
import java.io.*;

public class Main {

    static void bfs(int A, int B, int C) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, C });
        boolean[][][] booked = new boolean[C + 1][C + 1][C + 1];
        booked[0][0][C] = true;
        boolean[] check = new boolean[C + 1];
        check[C] = true;
        int[] capacity = new int[] { A, B, C };
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int u = 0; u < 3; u++) {
                for (int v = 0; v < 3; v++) {
                    if (u == v) continue;
                    int[] ns = p.clone();
                    int[] t = pour(p[u], p[v], capacity[v]);
                    ns[u] = t[0]; ns[v] = t[1];
                    if (!booked[ns[0]][ns[1]][ns[2]]) {
                        q.offer(ns);
                        booked[ns[0]][ns[1]][ns[2]] = true;
                        if (ns[0] == 0) check[ns[2]] = true;
                    }
                }
            }
        }
        StringBuilder bw = new StringBuilder();
        for (int i = 0; i <= C; i++) if (check[i]) {
            bw.append(i).append(" ");
        }
        System.out.println(bw);
    }

    // u만큼 물이 차있는 물통을 v만큼 차있는 물통으로 옮겨담고
    // 결과값인 (u, v)반환, 단 c는 두 번째 물통의 용량
    static int[] pour(int u, int v, int c) {
        int amount = Math.min(v + u, c) - v;
        return new int[] { u - amount, v + amount };
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        bfs(A, B, C);
    }

}
