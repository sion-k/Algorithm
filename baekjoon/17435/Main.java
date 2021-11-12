import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 18;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        int[] S = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) S[i] = Integer.parseInt(st.nextToken());
        int[][] f = new int[MAX + 1][M + 1];
        for (int j = 1; j <= M; j++) f[0][j] = S[j - 1];
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= M; j++) {
                f[i][j] = f[i - 1][f[i - 1][j]];
            }
        }
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            for (int j = MAX; j >= 0; j--) {
                if ((n & (1 << j)) > 0) {
                    x = f[j][x];
                }
            }
            bw.append(x).append("\n");
        }
        System.out.print(bw);
    }

}
