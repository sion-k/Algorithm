import java.util.*;
import java.io.*;

public class Main {

    static final int MOD = 1000000007;

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
        int[][] ret = new int[M1.length][M2[0].length];
        for (int i = 0; i < M1.length; i++)
            for (int j = 0; j < M2[0].length; j++)
                for (int k = 0; k < M1[0].length; k++)
                    ret[i][j] = (int)((ret[i][j] + (long)M1[i][k] * M2[k][j]) % MOD);
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] S = new int[M][M];
        S[0][0] = S[0][M - 1] = 1;
        for (int k = 0; k < M - 1; k++) S[1 + k][k] = 1;
        int[][] R = pow(S, N);
        System.out.println(R[0][0]);
    }

}
