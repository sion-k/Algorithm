import java.util.*;
import java.io.*;

public class Main {

    static int[][] K1 = {
        { -1, 1 },
        { 1, 1 }
    };

    static int[][][] K2 = {
        {
            { -1, 1, 2, 2 },
            { 1, 1, 3, 2 },
            { 4, 3, 3, 5 },
            { 4, 4, 5, 5 }
        },
        {
            { 1, -1, 2, 2 },
            { 1, 1, 3, 2 },
            { 4, 3, 3, 5 },
            { 4, 4, 5, 5 }
        },
        {
            { 1, 1, 2, 2 },
            { 1, -1, 3, 2 },
            { 4, 3, 3, 5 },
            { 4, 4, 5, 5 }
        },
        {
            { 1, 1, 2, 2 },
            { -1, 1, 3, 2 },
            { 4, 3, 3, 5 },
            { 4, 4, 5, 5 }
        }
    };

    static int[][] rotate(int[][] S) {
        int N = S.length;
        int[][] ret = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret[i][j] = S[N - j - 1][i];
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        if (K == 1) {
            y = 1 - y;
            for (int i = 0; i < 4; i++) {
                if (K1[y][x] == -1) break;
                K1 = rotate(K1);
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    bw.append(K1[i][j]).append(" ");
                }
                bw.append("\n");
            }
        } else {
            y = 3 - y;
            int flag = -1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (K2[i][y][x] == -1) {
                        flag = i; break;
                    }
                    K2[i] = rotate(K2[i]);
                }
                if (flag != -1) break;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    bw.append(K2[flag][i][j]).append(" ");
                }
                bw.append("\n");
            }
        }
        System.out.print(bw);
    }

}
