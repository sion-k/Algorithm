import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = 29;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine(), " ");
        int[] S = new int[N];
        for (int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
        int[][] f = new int[MAX + 1][K + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= K; i++) f[0][i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= K; j++) {
                f[i][j] = f[i - 1][f[i - 1][j]];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = MAX; j >= 0; j--) {
                if ((M & (1 << j)) > 0) {
                    S[i] = f[j][S[i]];
                }
            }
            bw.append(S[i]).append(" ");
        }
        System.out.println(bw);
    }

}
