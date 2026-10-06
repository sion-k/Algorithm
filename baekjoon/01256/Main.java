import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] C = new int[201][201];

    static {
        for (int i = 0; i < 201; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j < i; j++)
                C[i][j] = Math.min(1000000001, C[i - 1][j - 1] + C[i - 1][j]);
        }
    }

    static char[] S;

    // n개의 'a' m개의 'z'로 이루어진 문자열 중에 k번 스킵한 문자를 출력
    static void kth(int here, int n, int m, int skip) {
        if (n == 0) {
            for (int i = here; i < S.length; i++)
                S[i] = 'z';
            for (int i = 0; i < S.length; i++)
                System.out.print(S[i]);
            System.out.println();
            return;
        }
        // here번째부터 시작하는 문자열은 'a'로 시작하는게 먼저 온다
        // S[here]번째 문자열이 'a'로 시작하는 경우의 수가 skip보다 작다면 스킵하지 않는다
        if (skip < C[n + m - 1][n - 1]) {
            S[here] = 'a';
            kth(here + 1, n - 1, m, skip);
        } else {
            S[here] = 'z';
            kth(here + 1, n, m - 1, skip - C[n + m - 1][n - 1]);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        S = new char[N + M];
        if (K <= C[N + M][N])
            kth(0, N, M, K - 1);
        else
            System.out.println(-1);
    }

}