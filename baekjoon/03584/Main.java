import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                parent[v] = u;
            }
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            boolean[] visit = new boolean[N + 1];
            while (true) {
                visit[u] = true;
                if (parent[u] == 0) break;
                u = parent[u];
            }
            while (true) {
                if (visit[v]) break;
                visit[v] = true;
                v = parent[v];
            }
            bw.append(v).append("\n");
        }
        System.out.print(bw);
    }

}
