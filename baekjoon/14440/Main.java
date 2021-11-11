import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 100;

    // 행렬 M의 k제곱 반환
    static int[][] pow(int[][] M, long k) {
        if (k == 1) return M;
        if (k % 2 == 1) return mul(M, pow(M, k - 1));
        int[][] t = pow(M, k / 2);
        return mul(t, t);
    }

    // M1 * M2 행렬을 반환
    // 단 M1의 열 길이와 M2의 행 길이가 같아야한다.
    // 결과는 M1의 행 길이 X M2의 열 길이 행렬
    static int[][] mul(int[][] M1, int[][] M2) {
        int N = M1.length;
        int M = M2[0].length;
        int[][] ret = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                for (int k = 0; k < M; k++)
                    ret[i][j] = (ret[i][j] + M1[i][k] * M2[k][j]) % MOD;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int a0 = Integer.parseInt(st.nextToken());
        int a1 = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int ret = 0;
        if (n == 0) {
            ret = a0;
        } else if (n == 1) {
            ret = a1;
        } else {
            int[][] M = new int[2][2];
            M[0][0] = x; M[0][1] = y;
            M[1][0] = 1;
            M = pow(M, n - 1);
            ret = (M[0][0] * a1 + M[0][1] * a0) % MOD;
        }
        bw.append(String.format("%02d\n", ret));
        System.out.print(bw);
    }

}
