import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Pair[] S;
    static int[][] cache;

    // 물건 가치의 합이 k일때 최소 무게
    static int dp(int i, int k) {
        if (i == N) return 0;
        if (cache[i][k] != -1) return cache[i][k];
        int min = dp(i + 1, k);
        if (k + S[i].value <= 100000)
            min = Math.min(min, S[i].index + dp(i + 1, k + S[i].value));
        return cache[i][k] = min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        S = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            S[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        cache = new int[N][100001];
        for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
        int[] R = new int[M];
        int maxNum = M + 1; double max = 0;
        for (int i = 0; i < M; i++) {
            R[i] = Integer.parseInt(br.readLine());
            for (int j = 0; j <= 100000; j++) {
                int temp = dp(0, j);
                if (R[i] >= temp) {
                    double cand = (double)j / R[i];
                    if (max < cand) {
                        max = cand;
                        maxNum = Math.min(maxNum, i + 1);
                    }
                }
            }
        }
        bw.append(maxNum).append("\n");
        System.out.print(bw);
    }

}
class Pair {
    int index, value;

    Pair (int i, int v) { index = i; value = v; }

}
