import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        char[][] S = new char[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(S[i], ' ');
        for (int i = 0; i < N; i++) S[i][0] = S[i][N - 1] = '*';
        for (int j = 0; j < N; j++) S[0][j] = S[N - 1][j] = '*';
        if (N % 2 == 1) {
            int y = 1; int x = 1;
            while (y < N && x < N) {
                S[y][x] = '*';
                y++; x++;
            }
            y = N - 2; x = 1;
            while (y >= 0 && x < N) {
                S[y][x] = '*';
                y--; x++;
            }
        } else {
            int y = 1; int x = 1;
            while (y < N && x < N) {
                S[y][x] = '*';
                y++; x++;
            }
            y = N - 2; x = 1;
            while (y >= 0 && x < N) {
                S[y][x] = '*';
                y--; x++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) bw.append(S[i][j]);
            bw.append("\n");
        }
        System.out.print(bw);
    }

}
