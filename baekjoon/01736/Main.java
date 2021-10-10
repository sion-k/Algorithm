import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[][] S;
    static int C;

    static void move(int y, int x) {
        int range = x;
        for (int j = x; j < M; j++) {
            if (S[y][j]) {
                range = j;
                S[y][j] = false;
                C--;
            }
        }
        x = range;
        // 맨 마지막 행이 아니라면 아래로 이동
        if (y != N - 1) move(y + 1, x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                S[i][j] = st.nextToken().equals("1");
                if (S[i][j]) C++;
            }
        }
        int cnt = 0;
        while (C > 0) {
            move(0, 0);
            cnt++;
        }
        System.out.println(cnt);
    }

}
