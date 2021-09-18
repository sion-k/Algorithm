import java.util.*;
import java.io.*;

public class Main {
    static int N;

    static void flipCol(boolean[][] S, int j) {
        for (int i = 0; i < N; i++) S[i][j] ^= true;
    }

    // 0번째 행부터 시작해서 모두 검은색으로 만드는 것과 흰색으로 만드는 것의 최솟값 반환
    static int check(boolean[][] S) {
        int black = 0; int white = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            // 흰색의 개수를 센다
            for (int j = 0; j < N; j++) if (S[i][j]) cnt++;
            // 모두 흰색으로 바꾸려면
            white += (Math.min(N - cnt, 1 + cnt));
            // 모두 검은색으로 바꾸려면
            black += (Math.min(cnt, 1 + N - cnt));
        }
        return Math.min(black, white);
    }

    // 열을 뒤집는 경우 모두 시도해서 최솟값 반환
    static int btk(boolean[][] S, int j) {
        if (j == N) return check(S);
        int min = btk(S, j + 1);
        flipCol(S, j);
        min = Math.min(min, 1 + btk(S, j + 1));
        flipCol(S, j);
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        boolean[][] S = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) S[i][j] = st.nextToken().equals("1");
        }
        bw.append(btk(S, 0)).append("\n");
        System.out.print(bw);
    }

}
