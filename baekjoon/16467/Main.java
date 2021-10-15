import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 100000007;
    // 행렬 M의 k제곱 반환
    static int[][] pow(int[][] M, long k) {
        if (k == 0) {
            int[][] base = new int[M.length][M.length];
            for (int i = 0; i < M.length; i++) base[i][i] = 1;
            return base;
        }
        if (k == 1) return M;
        if (k % 2 == 1) return mul(M, pow(M, k - 1));
        int[][] t = pow(M, k / 2);
        return mul(t, t);
    }

    // M1 * M2 행렬을 반환
    // 단 M1의 열 길이와 M2의 행 길이가 같아야한다.
    // 결과는 M1의 행 길이 X M2의 열 길이 행렬
    static int[][] mul(int[][] M1, int[][] M2) {
        int[][] ret = new int[M1.length][M2[0].length];
        for (int i = 0; i < M1.length; i++)
            for (int j = 0; j < M2[0].length; j++)
                for (int k = 0; k < M1[0].length; k++)
                    ret[i][j] = (int)((ret[i][j] + (long)M1[i][k] * M2[k][j]) % MOD);
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[][] M = new int[K + 1][K + 1];
            int t = 0;
            while (0 <= t + 1 && t + 1 < K + 1) M[t + 1][t++] = 1;
            M[0][0]++; M[0][K]++;
            bw.append(pow(M, N)[0][0]).append("\n");
        }
        System.out.print(bw);
    }

}
